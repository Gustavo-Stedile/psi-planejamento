package br.edu.ifsp.hto.planejamento.controle;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialVO;

/**
 * Classe responsável por controlar as operações de CRUD na tabela 'material'.
 * Realiza a comunicação entre a camada de modelo (MaterialVO) e o banco de dados.
 * 
 * @author Nicolas Jesus Silva
 */
public class MaterialControle {

    /**
     * Insere um novo material no banco de dados.
     * 
     * @param material Objeto MaterialVO contendo os dados do material a ser inserido.
     */
    public void inserir(MaterialVO material) {
        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "INSERT INTO material (associado_id, nome, quantidade, unidade_medida) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, material.getAssociadoId());
            stmt.setString(2, material.getNome());
            stmt.setFloat(3, material.getQuantidade());
            stmt.setString(4, material.getUnidadeMedida());

            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todos os materiais cadastrados no banco de dados.
     * 
     * @return Lista de objetos MaterialVO.
     */
    public List<MaterialVO> listarTodos() {
        List<MaterialVO> lista = new ArrayList<>();

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM material";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(resultSetToMaterial(rs));
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
     * Atualiza os dados de um material existente.
     * 
     * @param material Objeto MaterialVO com os dados atualizados do material.
     */
    public void atualizar(MaterialVO material) {
        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "UPDATE material SET associado_id = ?, nome = ?, quantidade = ?, unidade_medida = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, material.getAssociadoId());
            stmt.setString(2, material.getNome());
            stmt.setFloat(3, material.getQuantidade());
            stmt.setString(4, material.getUnidadeMedida());
            stmt.setInt(5, material.getId());

            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta um material do banco de dados.
     * 
     * @param id ID do material que será removido.
     */
    public void deletar(int id) {
        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "DELETE FROM material WHERE id = ?";
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
     * Busca um material pelo seu ID.
     * 
     * @param id ID do material que será buscado.
     * @return Objeto MaterialVO correspondente ao material encontrado, ou null se não encontrado.
     */
    public MaterialVO buscarPorId(int id) {
        MaterialVO material = null;

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM material WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                material = resultSetToMaterial(rs);
            }

            rs.close();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return material;
    }

    /**
     * Converte um ResultSet em um objeto MaterialVO.
     * 
     * @param rs ResultSet retornado de uma consulta SQL.
     * @return Objeto MaterialVO preenchido com os dados do ResultSet.
     * @throws SQLException Caso ocorra erro ao acessar os dados do ResultSet.
     */
    private MaterialVO resultSetToMaterial(ResultSet rs) throws SQLException {
        MaterialVO material = new MaterialVO();
        material.setId(rs.getInt("id"));
        material.setAssociadoId(rs.getInt("associado_id"));
        material.setNome(rs.getString("nome"));
        material.setQuantidade(rs.getFloat("quantidade"));
        material.setUnidadeMedida(rs.getString("unidade_medida"));
        return material;
    }
}
