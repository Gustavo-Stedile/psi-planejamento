package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoVO;

public class CanteiroDAO {

    // 🔹 Inserir um novo Canteiro
    public void inserir(CanteiroVO c) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "INSERT INTO canteiro (plano_especie_id, plano_id, nome, area_canteriro_m2, observacoes, kg_gerados) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.getPlanoEspecieId());
            stmt.setInt(2, c.getPlanoId());
            stmt.setString(3, c.getNome());
            stmt.setFloat(4, c.getAreaCanteiroM2());
            stmt.setString(5, c.getObservacoes());
            stmt.setFloat(6, c.getKgGerados());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Listar todos os Canteiros
    public List<CanteiroVO> listarTodos() throws SQLException {
        List<CanteiroVO> canteiros = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "SELECT * FROM canteiro";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                canteiros.add(resultSetToCanteiro(rs));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return canteiros;
    }

    // 🔹 Buscar Canteiro por ID
    public CanteiroVO buscarPorId(int id) throws SQLException {
        CanteiroVO canteiro = null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "SELECT * FROM canteiro WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                canteiro = resultSetToCanteiro(rs);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return canteiro;
    }

    public ArrayList<CanteiroVO> buscarCanteirosPorPlano(int id) {
        ArrayList<CanteiroVO> canteiros = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");
            String sql = "SELECT * FROM canteiro WHERE plano_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                canteiros.add(resultSetToCanteiro(rs));
            }

            stmt.close();
            conn.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canteiros;

    }

    // 🔹 Atualizar Canteiro
    public void atualizar(CanteiroVO c) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "UPDATE canteiro SET plano_especie_id = ?, plano_id = ?, nome = ?, area_canteriro_m2 = ?, observacoes = ?, kg_gerados = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.getPlanoEspecieId());
            stmt.setInt(2, c.getPlanoId());
            stmt.setString(3, c.getNome());
            stmt.setFloat(4, c.getAreaCanteiroM2());
            stmt.setString(5, c.getObservacoes());
            stmt.setFloat(6, c.getKgGerados());
            stmt.setInt(7, c.getId());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Deletar Canteiro
    public void deletar(int id) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao",
                    "postegres", " ");

            String sql = "DELETE FROM canteiro WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CanteiroVO resultSetToCanteiro(ResultSet rs) throws SQLException {
        CanteiroVO canteiro = new CanteiroVO();

        canteiro.setId(rs.getInt("id"));
        canteiro.setPlanoEspecieId(rs.getInt("plano_especie_id"));
        canteiro.setPlanoId(rs.getInt("plano_id"));
        canteiro.setNome(rs.getString("nome"));
        canteiro.setAreaCanteiroM2(rs.getFloat("area_canteriro_m2"));
        canteiro.setObservacoes(rs.getString("observacoes"));
        canteiro.setKgGerados(rs.getFloat("kg_gerados"));

        return canteiro;
    }
}
