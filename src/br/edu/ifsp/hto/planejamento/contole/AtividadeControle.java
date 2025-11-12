package br.edu.ifsp.hto.planejamento.controle;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;

/**
 * Classe responsável pelo controle das operações relacionadas às atividades,
 * incluindo inserção, listagem, atualização, exclusão e busca de atividades
 * no banco de dados.
 *
 * @author Nicolas Jesus Silva
 */
public class AtividadeControle {

    /**
     * Insere uma nova atividade no banco de dados.
     *
     * @param atividade objeto AtividadeVO contendo os dados da nova atividade
     */
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
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todas as atividades cadastradas no banco de dados.
     *
     * @return lista de objetos AtividadeVO
     */
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
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Atualiza os dados de uma atividade existente.
     *
     * @param atividade objeto AtividadeVO contendo os dados atualizados
     */
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
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exclui uma atividade do banco de dados com base no seu ID.
     *
     * @param id identificador da atividade a ser excluída
     */
    public void deletar(int id) {
        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "DELETE FROM atividade WHERE id = ?";
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
     * Busca uma atividade específica pelo seu ID.
     *
     * @param id identificador da atividade
     * @return objeto AtividadeVO correspondente ao ID informado, ou null se não encontrado
     */
    public AtividadeVO buscarPorId(int id) {
        AtividadeVO atividade = null;

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM atividade WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                atividade = resultSetToAtividade(rs);
            }

            rs.close();
            stmt.close();
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return atividade;
    }

    /**
     * Busca todas as atividades associadas a um determinado canteiro.
     *
     * @param canteiroId identificador do canteiro
     * @return lista de objetos AtividadeNoCanteiroVO vinculados ao canteiro
     */
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

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atividadesNoCanteiro;
    }

    /**
     * Converte uma linha de resultado do banco de dados em um objeto AtividadeVO.
     *
     * @param rs ResultSet contendo os dados da atividade
     * @return objeto AtividadeVO populado com os dados do ResultSet
     * @throws SQLException caso ocorra erro ao acessar os dados
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
