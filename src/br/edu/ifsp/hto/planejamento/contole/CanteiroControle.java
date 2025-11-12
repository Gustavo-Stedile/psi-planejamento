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

/**
 * Classe responsável pelo controle das operações CRUD relacionadas à entidade {@code Canteiro}.
 * <p>
 * Atua como intermediária entre a camada de modelo (VO) e o banco de dados,
 * realizando operações de inserção, listagem, busca, atualização e exclusão.
 * Também permite a busca de canteiros associados a planos e de atividades associadas a cada canteiro.
 * </p>
 *
 * @author Nicolas Jesus Silva
 */
public class CanteiroControle {

    /**
     * Insere um novo canteiro no banco de dados.
     *
     * @param c objeto {@link CanteiroVO} contendo os dados do canteiro a ser inserido
     */
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

    /**
     * Retorna todos os canteiros cadastrados no banco de dados.
     *
     * @return lista de objetos {@link CanteiroVO}
     */
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

    /**
     * Busca um canteiro pelo seu ID.
     *
     * @param id identificador do canteiro
     * @return objeto {@link CanteiroVO} correspondente ao ID informado ou {@code null} se não encontrado
     */
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

    /**
     * Busca todos os canteiros relacionados a um determinado plano.
     *
     * @param id identificador do plano
     * @return lista de objetos {@link CanteiroVO} pertencentes ao plano informado
     */
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

    /**
     * Atualiza as informações de um canteiro existente.
     *
     * @param c objeto {@link CanteiroVO} contendo os dados atualizados
     */
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

    /**
     * Exclui um canteiro com base em seu ID.
     *
     * @param id identificador do canteiro a ser deletado
     */
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

    /**
     * Busca um canteiro e suas atividades associadas.
     *
     * @param canteiroId identificador do canteiro
     * @return objeto {@link CanteiroComAtividadesVO} contendo o canteiro e suas atividades
     */
    public CanteiroComAtividadesVO buscarCanteiroComAtividades(int canteiroId) {
        CanteiroVO canteiro = buscarPorId(canteiroId);

        AtividadeControle atividadeControle = new AtividadeControle();
        ArrayList<AtividadeNoCanteiroVO> atividades = atividadeControle.buscarAtividadesDoCanteiro(canteiroId);

        return new CanteiroComAtividadesVO(canteiro, atividades);
    }

    /**
     * Converte uma linha de resultado do banco de dados em um objeto {@link CanteiroVO}.
     *
     * @param rs ResultSet contendo os dados do canteiro
     * @return objeto {@link CanteiroVO} com os dados mapeados
     * @throws SQLException caso ocorra erro ao acessar os dados do ResultSet
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
