package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoComPlanosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoVO;

public class TalhaoDAO {

    /**
     * Adiciona um novo talhão no banco de dados
     * 
     * @param talhao objeto do tipo {@code TalhaoVO}
     */
    public void inserir(TalhaoVO talhao) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO talhao (area_id, nome, area_talhao, observacoes, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, talhao.getAreaId());
            stmt.setString(2, talhao.getNome());
            stmt.setFloat(3, talhao.getAreaTalhao());
            stmt.setString(4, talhao.getObservacoes());
            stmt.setString(5, talhao.getStatus());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todos os talhões presentes no banco de dados
     * 
     * @return um {@code List} contendo {@code TalhaoVO} como elementos
     */
    public List<TalhaoVO> listarTodos() {
        List<TalhaoVO> talhoes = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM talhao WHERE ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                talhoes.add(resultSetToTalhao(rs));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return talhoes;
    }

    /**
     * Busca um talhão no banco de dados pelo id
     * 
     * @param id identificador do talhão
     * 
     * @return um objeto do tipo {@code TalhaoVO}
     */
    public TalhaoVO buscarPorId(int id) {
        TalhaoVO talhao = null;

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM talhao WHERE id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                talhao = resultSetToTalhao(rs);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return talhao;
    }

    /**
     * Lista todos os talhões que possuem planos
     * 
     * @param id identificador do talhão
     * 
     * @return Um objeto do tipo {@code TalhaoPlanoVO}
     */
    public TalhaoComPlanosVO buscarTalhaoComPlanos(int id) {
        TalhaoVO talhao = buscarPorId(id);
        
        PlanoDAO planoDAO = new PlanoDAO();
        List<PlanoVO> planos = planoDAO.buscarPlanosDoTalhao(id);

        return new TalhaoComPlanosVO(talhao, planos);
    }

    /**
     * Busca todos os talhões pertencentes a uma área
     * 
     * @param id identificador da área
     * 
     * @return um {@code List} contendo {@code TalhaoVO} como elementos
     */
    public List<TalhaoVO> buscarTalhoesDaArea(int id) {
        List<TalhaoVO> talhoes = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM Talhao WHERE area_id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                talhoes.add(resultSetToTalhao(rs));
            }

            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return talhoes;
    }

    /**
     * Atualiza um talhão presente no banco de dados
     * 
     * @param talhao objeto {@code TalhaoVO} contendo os novos dados
     */
    public void atualizar(TalhaoVO talhao) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE talhao SET area_id = ?, nome = ?, area_talhao = ?, observacoes = ?, status = ? WHERE id = ? AND ativo = true";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, talhao.getAreaId());
            stmt.setString(2, talhao.getNome());
            stmt.setFloat(3, talhao.getAreaTalhao());
            stmt.setString(4, talhao.getObservacoes());
            stmt.setString(5, talhao.getStatus());
            stmt.setInt(6, talhao.getId());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deleta um talhão presente no banco de dados
     * 
     * @param id identificador do talhão a ser excluido
     */
    public void deletar(int id) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE talhao SET ativo = false WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            PlanoDAO planoDAO = new PlanoDAO();
            planoDAO.buscarPlanosDoTalhao(id).forEach(p -> planoDAO.deletar(p.getId()));

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna o talhão presente no banco de dados contendo todas
     * as suas informações
     * 
     * @param rs {@code ResultSet} contendo os atributos de {@code TalhaoVO}
     * 
     * @return um objeto do tipo {@code TalhaoVO}
     * 
     * @throws SQLException caso ocorra algum erro no acesso ao banco
     */
    private TalhaoVO resultSetToTalhao(ResultSet rs) throws SQLException {
        TalhaoVO talhao = new TalhaoVO();

        talhao.setId(rs.getInt("id"));
        talhao.setAreaId(rs.getInt("area_id"));
        talhao.setNome(rs.getString("nome"));
        talhao.setAreaTalhao(rs.getFloat("area_talhao"));
        talhao.setObservacoes(rs.getString("observacoes"));
        talhao.setStatus(rs.getString("status"));

        return talhao;
    }
}