package br.edu.ifsp.hto.planejamento.controle;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialVO;

public class MaterialControle {

    //  Inserir novo material
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

    //  Listar todos os materiais
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

    //  Atualizar material existente
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

    //  Deletar material
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

    //  Buscar Material por ID
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

    //  Converter ResultSet para MaterialVO
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
