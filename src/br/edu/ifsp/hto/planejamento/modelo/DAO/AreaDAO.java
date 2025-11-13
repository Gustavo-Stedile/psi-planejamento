package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaComTalhoesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoVO;

public class AreaDAO {

    /**
     * Adiciona uma nova área no banco de dados
     * 
     * @param area objeto do tipo {@code AreaVO}
     */
    public void inserir(AreaVO area) {
        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO area (associado_id, nome, area_total, area_utilizada, ph) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, area.getAssociadoId());
            stmt.setString(2, area.getNome());
            stmt.setFloat(3, area.getAreaTotal());
            stmt.setFloat(4, area.getAreaUtilizada());
            stmt.setFloat(5, area.getPh());
            stmt.executeUpdate();

            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todas as áreas presentes no banco de dados
     * 
     * @return um {@code List} contendo {@code AreaVO} como elementos
     */
    public List<AreaVO> listarTodas() {
        List<AreaVO> lista = new ArrayList<>();

        try {
            Connection conexao = ConexaoDoProjeto.connect();
            String sql = "SELECT * FROM area";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(resultSetToArea(rs));
            }

            rs.close();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Atualiza uma área presente no banco de dados
     * 
     * @param area objeto {@code AreaVO} contendo os novos dados
     */
    public void atualizar(AreaVO area) {

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "UPDATE area SET associado_id = ?, nome = ?, area_total = ?, area_utilizada = ?, ph = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, area.getAssociadoId());
            stmt.setString(2, area.getNome());
            stmt.setFloat(3, area.getAreaTotal());
            stmt.setFloat(4, area.getAreaUtilizada());
            stmt.setFloat(5, area.getPh());
            stmt.setInt(6, area.getId());
            stmt.executeUpdate();

            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta uma área presente no banco de dados
     * 
     * @param id identificador da área a ser excluida
     */
    public void deletar(int id) {

        try {
            Connection conexao = ConexaoDoProjeto.connect();
            String sql = "DELETE FROM area WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca uma área no banco de dados pelo id
     * 
     * @param id identificador da área
     * 
     * @return um objeto do tipo {@code AreaVO}
     */
    public AreaVO buscarPorId(int id) {
        AreaVO area = null;

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM area WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                area = resultSetToArea(rs);
            }

            rs.close();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return area;
    }

    /**
     * Busca uma área especifica que possui talhões
     * 
     * @param id identificador da área
     * 
     * @return um objeto do tipo {@code AreaTalhaoVO}
     */
    public AreaComTalhoesVO buscarAreaComTalhoes(int id) {
        AreaVO area = buscarPorId(id);
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        List<TalhaoVO> talhoes = talhaoDAO.buscarTalhoesDaArea(area.getId());

        return new AreaComTalhoesVO(area, talhoes);
    }

    /**
     * Retorna a área presente no banco de dados contendo todas
     * as suas informações
     * 
     * @param rs {@code ResultSet} contendo os atributos de {@code AreaVO}
     * 
     * @return um objeto do tipo {@code AreaVO}
     * 
     * @throws SQLException caso ocorra algum erro no acesso ao banco
     */
    private AreaVO resultSetToArea(ResultSet rs) throws SQLException {
        AreaVO area = new AreaVO();

        area = new AreaVO();
        area.setId(rs.getInt("id"));
        area.setAssociadoId(rs.getInt("associado_id"));
        area.setNome(rs.getString("nome"));
        area.setAreaTotal(rs.getFloat("area_total"));
        area.setAreaUtilizada(rs.getFloat("area_utilizada"));
        area.setPh(rs.getFloat("ph"));

        return area;
    }
}
