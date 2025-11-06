package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "INSERT INTO area (associado_id, nome, area_total, area_utilizada, ph, area_m2) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, area.getAssociadoId());
            stmt.setString(2, area.getNome());
            stmt.setFloat(3, area.getAreaTotal());
            stmt.setFloat(4, area.getAreaUtilizada());
            stmt.setFloat(5, area.getPh());
            stmt.setFloat(6, area.getAreaM2());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todas as áreas presentes no banco de dados
     * 
     * @return um {@code ArrayList} contendo {@code AreaVO} como elementos
     */
    public List<AreaVO> listarTodas() {
        List<AreaVO> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "SELECT * FROM area";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(resultSetToArea(rs));
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
     * Atualiza uma área presente no banco de dados
     * 
     * @param area objeto {@code AreaVO} contendo os novos dados
     */
    public void atualizar(AreaVO area) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "UPDATE area SET associado_id = ?, nome = ?, area_total = ?, area_utilizada = ?, ph = ?, area_m2 = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, area.getAssociadoId());
            stmt.setString(2, area.getNome());
            stmt.setFloat(3, area.getAreaTotal());
            stmt.setFloat(4, area.getAreaUtilizada());
            stmt.setFloat(5, area.getPh());
            stmt.setFloat(6, area.getAreaM2());
            stmt.setInt(7, area.getId());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "DELETE FROM area WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "SELECT * FROM area WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // faz a troca do banco do id pelo id do parametro
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            // cria um objeto area nulo

            // verifica se tem resultado
            if (rs.next()) {
                // se tiver, instancia o objeto area e preenche com os dados do resultado
                area = resultSetToArea(rs);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return area;
    }

    /**
     * Lista todas as áreas que possuem talhões
     * 
     * @param id identificador da área
     * 
     * @return Um objeto do tipo {@code AreaTalhaoVO}
     */
    public AreaComTalhoesVO listarAreaComTalhoes(int id) {
        AreaVO area = buscarPorId(id);
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        ArrayList<TalhaoVO> talhoes = talhaoDAO.buscarTalhoesPorArea(area.getId());

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
        area.setAreaM2(rs.getFloat("area_m2"));

        return area;
    }
}
