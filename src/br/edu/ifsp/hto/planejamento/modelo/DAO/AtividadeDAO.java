package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;

public class AtividadeDAO {

    // Inserir novo atividade
    public void inserir(AtividadeVO atividade) {

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO atividade (nome_atividade, descricao, observacoes, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
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

    // Listar todos os materiais
    public List<AtividadeVO> listarTodos() {
        List<AtividadeVO> lista = new ArrayList<>();

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM atividade";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(resultSetToAtividade(rs));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Atualizar atividade existente
    public void atualizar(AtividadeVO atividade) {
        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "UPDATE atividade SET nome_atividade = ?, descricao = ?, observacoes = ?, status = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, atividade.getNomeAtividade());
            stmt.setString(2, atividade.getDescricao());
            stmt.setString(3, atividade.getObervacoes());
            stmt.setString(4, atividade.getStatus());
            stmt.setInt(5, atividade.getId());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deletar atividade
    public void deletar(int id) {
        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "DELETE FROM atividade WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Buscar atividade por ID
    public AtividadeVO buscarPorId(int id) {
        AtividadeVO atividade = null;

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM atividade WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // se encontrou algum registro
                atividade = resultSetToAtividade(rs);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return atividade; // retorna o objeto ou null se não encontrou
    }

    public ArrayList<AtividadeNoCanteiroVO> buscarAtividadesDoCanteiro(int canteiroId) {
         ArrayList<AtividadeNoCanteiroVO> atividadesNoCanteiro = new ArrayList<>();

        try {
            Connection conn = ConexaoDoProjeto.connect();
            String sql = "SELECT * FROM atividade_canteiro WHERE canteiro_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, canteiroId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AtividadeVO atividade = buscarPorId(rs.getInt("atividade_id"));
                float tempoGastoHoras = rs.getFloat("tempo_gasto_horas");
                Date dataAtividade = rs.getDate("data_atividade");

                atividadesNoCanteiro.add(new AtividadeNoCanteiroVO(
                    atividade,
                    tempoGastoHoras,
                    dataAtividade
                ));
            }

            stmt.close();
            conn.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atividadesNoCanteiro;
    }

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