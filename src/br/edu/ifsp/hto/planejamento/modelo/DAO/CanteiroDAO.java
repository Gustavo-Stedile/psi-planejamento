package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroComAtividadesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;

public class CanteiroDAO {

    /**
     * Adiciona um novo canteiro no banco de dados
     * 
     * @param canteiro objeto do tipo {@code CanteiroVO}
     */
    public void inserir(CanteiroVO canteiro) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO canteiro (plano_id, nome, area_canteiro_m2, observacoes, kg_gerados) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, canteiro.getPlanoId());
            stmt.setString(2, canteiro.getNome());
            stmt.setFloat(3, canteiro.getAreaCanteiroM2());
            stmt.setString(4, canteiro.getObservacoes());
            stmt.setFloat(5, canteiro.getKgGerados());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona uma atividade no canteiro
     * 
     * @param canteiroId identificador do canteiro
     * @param atividadeId identificador da atividade
     * @param tempoGastoHoras tempo a ser gasto na atividade
     * @param dataAtividade data que será realizada a atividade
     */
    public void adicionarAtividade(int canteiroId, int atividadeId, float tempoGastoHoras, Date dataAtividade) {
        try{
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO atividade_canteiro (canteiro_id, atividade_id, tempo_gasto_horas, data_atividade) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, canteiroId);
            stmt.setInt(2, atividadeId);
            stmt.setFloat(3, tempoGastoHoras);
            stmt.setDate(4, dataAtividade);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Lista todos os canteiros presentes no banco de dados
     * 
     * @return um {@code List} contendo {@code CanteiroVO} como elementos
     */
    public List<CanteiroVO> listarTodos() {
        List<CanteiroVO> canteiros = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM canteiro WHERE ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                canteiros.add(resultSetToCanteiro(rs));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canteiros;
    }

    /**
     * Busca um canteiro no banco de dados pelo id
     * 
     * @param id identificador do cabteiro
     * 
     * @return um objeto do tipo {@code CanteiroVO}
     */
    public CanteiroVO buscarPorId(int id) {
        CanteiroVO canteiro = null;

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM canteiro WHERE id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                canteiro = resultSetToCanteiro(rs);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canteiro;
    }

    /**
     * Busca todos os canteiros pertencentes a um plano
     * 
     * @param id identificador do plano
     * 
     * @return um {@code List} contendo {@code CanteiroVO} como elementos
     */
    public List<CanteiroVO> buscarCanteirosDoPlano(int id) {
        List<CanteiroVO> canteiros = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();
            String sql = "SELECT * FROM canteiro WHERE plano_id = ? AND ativo = true";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                canteiros.add(resultSetToCanteiro(rs));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canteiros;
    }

    /**
     * Busca um canteiro especifico que possue atividades
     * 
     * @param id identificador do canteiro
     * 
     * @return um objeto do tipo {@code CanteiroComAtividadesVO}
     */
    public CanteiroComAtividadesVO buscarCanteiroComAtividades(int id) {
        CanteiroVO canteiro = buscarPorId(id);

        AtividadeDAO atividadeDAO = new AtividadeDAO();
        List<AtividadeNoCanteiroVO> atividades = atividadeDAO.buscarAtividadesDoCanteiro(id);

        return new CanteiroComAtividadesVO(canteiro, atividades);
    }

    /**
     * Atualiza um canteiro presente no banco de dados
     * 
     * @param canteiro objeto {@code CanteiroVO} contendo os novos dados
     */
    public void atualizar(CanteiroVO canteiro) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE canteiro SET plano_id = ?, nome = ?, area_canteiro_m2 = ?, observacoes = ?, kg_gerados = ? WHERE id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, canteiro.getPlanoId());
            stmt.setString(2, canteiro.getNome());
            stmt.setFloat(3, canteiro.getAreaCanteiroM2());
            stmt.setString(4, canteiro.getObservacoes());
            stmt.setFloat(5, canteiro.getKgGerados());
            stmt.setInt(6, canteiro.getId());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta um canteiro presente no banco de dados
     * 
     * @param id identificador do canteiro a ser excluido
     */
    public void deletar(int id) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE canteiro SET ativo = false WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            sql = "UPDATE atividade_canteiro SET ativo = false WHERE canteiro_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove uma atividade de um canteiro específico
     * 
     * @param canteiroId identificador do canteiro
     * @param atividadeId identificador da atividade
     */
    public void removerAtividade(int canteiroId, int atividadeId) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE atividade_canteiro SET ativo = false WHERE canteiro_id = ? AND atividade_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, canteiroId);
            stmt.setInt(2, atividadeId);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retorna o canteiro presente no banco de dados contendo todas
     * as suas informações
     * 
     * @param rs {@code ResultSet} contendo os atributos de {@code CanteiroVO}
     * 
     * @return um objeto do tipo {@code CanteiroVO}
     * 
     * @throws SQLException caso ocorra algum erro no acesso ao banco
     */
    private CanteiroVO resultSetToCanteiro(ResultSet rs) throws SQLException {
        CanteiroVO canteiro = new CanteiroVO();

        canteiro.setId(rs.getInt("id"));
        canteiro.setPlanoId(rs.getInt("plano_id"));
        canteiro.setNome(rs.getString("nome"));
        canteiro.setAreaCanteiroM2(rs.getFloat("area_canteiro_m2"));
        canteiro.setObservacoes(rs.getString("observacoes"));
        canteiro.setKgGerados(rs.getFloat("kg_gerados"));

        return canteiro;
    }
}
