package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;

public class PlanoDAO {

    /**
     * Adiciona um novo plano no banco de dados
     * 
     * @param plano objeto do tipo {@code PlanoVO}
     */
    public void inserir(PlanoVO plano) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO plano (especie_id, talhao_id, nome_plano, descricao, data_inicio, data_fim, observacoes, area_cultivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, plano.getEspecieId());
            stmt.setInt(2, plano.getTalhaoId());
            stmt.setString(3, plano.getNomePlano());
            stmt.setString(4, plano.getDescricao());
            stmt.setDate(5, plano.getDataInicio());
            stmt.setDate(6, plano.getDataFim());
            stmt.setString(7, plano.getObservacoes());
            stmt.setFloat(8, plano.getAreaCultivo());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todos os planos presentes no banco de dados
     * 
     * @return um {@code List} contendo {@code PlanoVO} como elementos
     */
    public List<PlanoVO> listarTodos() {
        List<PlanoVO> lista = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM plano WHERE ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(resultSetToPlano(rs));
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
     * Busca um plano no banco de dados pelo id
     * 
     * @param id identificador do plano
     * 
     * @return um objeto do tipo {@code PlanoVO}
     */
    public PlanoVO buscarPorId(int id) {
        PlanoVO plano = null;

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM plano WHERE id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                plano = resultSetToPlano(rs);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return plano;
    }

    /**
     * Busca um plano especifico que possue canteiros
     * 
     * @param id identificador do plano
     * 
     * @return um objeto do tipo {@code PlanoComCanteirosVO}
     */
    public PlanoComCanteirosVO buscarPlanoComCanteiros(int id) {
        PlanoVO plano = buscarPorId(id);

        CanteiroDAO canteiroDAO = new CanteiroDAO();
        List<CanteiroVO> canteiros = canteiroDAO.buscarCanteirosDoPlano(id);

        return new PlanoComCanteirosVO(plano, canteiros);
    }
    
    /**
     * Busca todos os planos pertencentes a um talhão
     * 
     * @param id identificador do talhão
     * 
     * @return um {@code List} contendo {@code PlanoVO} como elementos
     */
    public List<PlanoVO> buscarPlanosDoTalhao(int id) {
        List<PlanoVO> planos = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM plano WHERE talhao_id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                planos.add(resultSetToPlano(rs));
            }

            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planos;
    }

    /**
     * Atualiza um plano presente no banco de dados
     * 
     * @param talhao objeto {@code PlanoVO} contendo os novos dados
     */
    public void atualizar(PlanoVO plano) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE plano SET especie_id = ?, talhao_id = ?, nome_plano = ?, descricao = ?, data_inicio = ?, data_fim = ?, observacoes = ?, area_cultivo = ? WHERE id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, plano.getEspecieId());
            stmt.setInt(2, plano.getTalhaoId());
            stmt.setString(3, plano.getNomePlano());
            stmt.setString(4, plano.getDescricao());
            stmt.setDate(5, plano.getDataInicio());
            stmt.setDate(6, plano.getDataFim());
            stmt.setString(7, plano.getObservacoes());
            stmt.setFloat(8, plano.getAreaCultivo());
            stmt.setInt(9, plano.getId());
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta um plano presente no banco de dados
     * 
     * @param id identificador do plano a ser excluido
     */
    public void deletar(int id) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE plano SET ativo = false WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            CanteiroDAO canteiroDAO = new CanteiroDAO();
            canteiroDAO.buscarCanteirosDoPlano(id).forEach(c -> canteiroDAO.deletar(c.getId()));

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna o plano presente no banco de dados contendo todas
     * as suas informações
     * 
     * @param rs {@code ResultSet} contendo os atributos de {@code PlanoVO}
     * 
     * @return um objeto do tipo {@code PlanoVO}
     * 
     * @throws SQLException caso ocorra algum erro no acesso ao banco
     */
    private PlanoVO resultSetToPlano(ResultSet rs) throws SQLException {
        PlanoVO plano = new PlanoVO();

        plano.setId(rs.getInt("id"));
        plano.setEspecieId(rs.getInt("especie_id"));
        plano.setTalhaoId(rs.getInt("talhao_id"));
        plano.setNomePlano(rs.getString("nome_plano"));
        plano.setDescricao(rs.getString("descricao"));
        plano.setDataInicio(rs.getDate("data_inicio"));
        plano.setDataFim(rs.getDate("data_fim"));
        plano.setObservacoes(rs.getString("observacoes"));
        plano.setAreaCultivo(rs.getFloat("area_cultivo"));

        return plano;
    }
}
