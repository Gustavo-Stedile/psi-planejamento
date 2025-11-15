package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeComMateriaisVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialNaAtividadeVO;

public class AtividadeDAO {

    /**
     * Adiciona uma nova atividade no banco de dados
     * 
     * @param atividade objeto do tipo {@code AtividadeVO}
     */
    public void inserir(AtividadeVO atividade) {

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO atividade (nome_atividade, descricao, observacoes, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, atividade.getNomeAtividade());
            stmt.setString(2, atividade.getDescricao());
            stmt.setString(3, atividade.getObervacoes());
            stmt.setString(4, atividade.getStatus());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona um material na atividade
     * 
     * @param materialId identificador do material
     * @param atividadeId identificador da atividade
     * @param quantidadeUtilizada quantidade de material a ser utilizada
     */
    public void adicionarMaterial(int materialId, int atividadeId, float quantidadeUtilizada) {
        try {
            Connection conn = ConexaoDoProjeto.connect();
            
            String sql = "INSERT INTO material_atividade (material_id, atividade_id, quantidade_utilizada) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, materialId);
            stmt.setInt(2, atividadeId);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todas as atividades presentes no banco de dados
     * 
     * @return um {@code List} contendo {@code AtividadeVO} como elementos
     */
    public List<AtividadeVO> listarTodas() {
        List<AtividadeVO> lista = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM atividade WHERE ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(resultSetToAtividade(rs));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Busca uma atividade no banco de dados pelo id
     * 
     * @param id identificador da atividade
     * 
     * @return um objeto do tipo {@code AtividadeVO}
     */
    public AtividadeVO buscarPorId(int id) {
        AtividadeVO atividade = null;

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM atividade WHERE id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                atividade = resultSetToAtividade(rs);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return atividade;
    }

    /**
     * Busca uma atividade especifica que possue materiais
     * 
     * @param id identificador da atividade
     * 
     * @return um objeto do tipo {@code AtividadeComMateriaisVO}
     */
    public AtividadeComMateriaisVO buscarAtividadeComMateriais(int id) {
        AtividadeVO atividade = buscarPorId(id);

        MaterialDAO materialDAO = new MaterialDAO();
        List<MaterialNaAtividadeVO> materiais = materialDAO.buscarMateriaisDaAtividade(id);

        return new AtividadeComMateriaisVO(atividade, materiais);
    }

    /**
     * Lista todas as atividades que pertencentes a um canteiro especifico
     * 
     * @param id identificador do canteiro
     * 
     * @return um {@code List} contendo {@code AtividadeNoCanteiroVO} como elementos
     */
    public List<AtividadeNoCanteiroVO> buscarAtividadesDoCanteiro(int id) {
        List<AtividadeNoCanteiroVO> atividadesNoCanteiro = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();
            String sql = "SELECT * FROM atividade_canteiro WHERE canteiro_id = ? AND ativo = true";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AtividadeVO atividade = buscarPorId(rs.getInt("atividade_id"));
                float tempoGastoHoras = rs.getFloat("tempo_gasto_horas");
                Date dataAtividade = rs.getDate("data_atividade");

                atividadesNoCanteiro.add(new AtividadeNoCanteiroVO(atividade, tempoGastoHoras, dataAtividade));
            }

            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atividadesNoCanteiro;
    }

    /**
     * Atualiza uma área presente no banco de dados
     * 
     * @param atividade objeto {@code AtividadeVO} contendo os novos dados
     */
    public void atualizar(AtividadeVO atividade) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE atividade SET nome_atividade = ?, descricao = ?, observacoes = ?, status = ? WHERE id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, atividade.getNomeAtividade());
            stmt.setString(2, atividade.getDescricao());
            stmt.setString(3, atividade.getObervacoes());
            stmt.setString(4, atividade.getStatus());
            stmt.setInt(5, atividade.getId());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta uma atividade presente no banco de dados
     * 
     * @param id identificador da atividade a ser excluida
     */
    public void deletar(int id) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE atividade SET ativo = false WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            sql = "UPDATE material_atividade SET ativo = false WHERE atividade_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            sql = "UPDATE atividade_canteiro SET ativo = false WHERE atividade_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove um material de uma atividade específica
     * 
     * @param materialId identificador do material
     * @param atividadeId identificador da atividade
     */
    public void removerMaterial(int materialId, int atividadeId) {
        try {
            Connection conn = ConexaoDoProjeto.connect();
            
            String sql = "UPDATE material_atividade SET ativo = false WHERE material_id = ? AND atividade_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, materialId);
            stmt.setInt(2, atividadeId);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna a atividade presente no banco de dados contendo todas
     * as suas informações
     * 
     * @param rs {@code ResultSet} contendo os atributos de {@code AtividadeVO}
     * 
     * @return um objeto do tipo {@code AtividadeVO}
     * 
     * @throws SQLException caso ocorra algum erro no acesso ao banco
     */
    private AtividadeVO resultSetToAtividade(ResultSet rs) throws SQLException {
        AtividadeVO atividade = new AtividadeVO();

        atividade.setId(rs.getInt("id"));
        atividade.setNomeAtividade(rs.getString("nome_atividade"));
        atividade.setDescricao(rs.getString("descricao"));
        atividade.setObervacoes(rs.getString("observacoes"));
        atividade.setStatus(rs.getString("status"));

        return atividade;
    }
}