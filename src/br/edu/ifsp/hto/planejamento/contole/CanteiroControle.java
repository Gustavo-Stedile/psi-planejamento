package br.edu.ifsp.hto.planejamento.controle;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroComAtividadesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoVO;

public class CanteiroControle {

    //  Inserir um novo Canteiro
    public void inserir(CanteiroVO c) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO canteiro (plano_id, nome, area_canteiro_m2, observacoes, kg_gerados) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.getPlanoId());
            stmt.setString(2, c.getNome());
            stmt.setFloat(3, c.getAreaCanteiroM2());
            stmt.setString(4, c.getObservacoes());
            stmt.setFloat(5, c.getKgGerados());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Listar todos os Canteiros
    public List<CanteiroVO> listarTodos() {
        List<CanteiroVO> canteiros = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();

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

    //  Buscar Canteiro por ID
    public CanteiroVO buscarPorId(int id) {
        CanteiroVO canteiro = null;

        try {
            Connection conn = ConexaoDoProjeto.connect();

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

    //  Buscar canteiros de um plano específico
    public ArrayList<CanteiroVO> buscarCanteirosDoPlano(int id) {
        ArrayList<CanteiroVO> canteiros = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();
            String sql = "SELECT * FROM canteiro WHERE plano_id = ?";

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

    //  Atualizar Canteiro
    public void atualizar(CanteiroVO c) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

            String sql = "UPDATE canteiro SET plano_id = ?, nome = ?, area_canteiro_m2 = ?, observacoes = ?, kg_gerados = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.getPlanoId());
            stmt.setString(2, c.getNome());
            stmt.setFloat(3, c.getAreaCanteiroM2());
            stmt.setString(4, c.getObservacoes());
            stmt.setFloat(5, c.getKgGerados());
            stmt.setInt(6, c.getId());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Deletar Canteiro
    public void deletar(int id) {
        try {
            Connection conn = ConexaoDoProjeto.connect();

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

    //  Buscar Canteiro com suas atividades
    public CanteiroComAtividadesVO buscarCanteiroComAtividades(int canteiroId) {
        CanteiroVO canteiro = buscarPorId(canteiroId);

        AtividadeControle atividadeControle = new AtividadeControle();
        ArrayList<AtividadeNoCanteiroVO> atividades = atividadeControle.buscarAtividadesDoCanteiro(canteiroId);
        
        return new CanteiroComAtividadesVO(canteiro, atividades);
    }

    //  Converter ResultSet para VO
    private CanteiroVO resultSetToCanteiro(ResultSet rs) throws SQLException {
        CanteiroVO canteiro = new CanteiroVO();

        canteiro.setId(rs.getInt("id"));
        canteiro.
