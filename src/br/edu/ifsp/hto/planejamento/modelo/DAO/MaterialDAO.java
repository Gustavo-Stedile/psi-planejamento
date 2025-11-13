package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.ConexaoDoProjeto;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialNaAtividadeVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialVO;

public class MaterialDAO {

    /**
     * Adiciona um novo material no banco de dados
     * 
     * @param material objeto do tipo {@code MaterialVO}
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
     * Lista todos os materiais presentes no banco de dados
     * 
     * @return um {@code List} contendo {@code MaterialVO} como elementos
     */
    public List<MaterialVO> listarTodos() {
        List<MaterialVO> lista = new ArrayList<>();

        try {
            Connection conexao = ConexaoDoProjeto.connect();

            String sql = "SELECT * FROM Material";
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
     * Atualiza um material presente no banco de dados
     * 
     * @param material objeto {@code MaterialVO} contendo os novos dados
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
     * Deleta um material presente no banco de dados
     * 
     * @param id identificador do material a ser excluido
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
     * Busca todos os materiais pertencentes a uma atividade
     * 
     * @param id identificador do material
     * 
     * @return um {@code List} contendo {@code MaterialNaAtividadeVO} como elementos
     */
    public List<MaterialNaAtividadeVO> buscarMateriaisDaAtividade(int atividadeId){
        List<MaterialNaAtividadeVO> materiais = new ArrayList<>();
        
        try {
            Connection conexao = ConexaoDoProjeto.connect();
            String sql = "SELECT * FROM material_atividade WHERE atividade_id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, atividadeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MaterialVO material = buscarPorId(rs.getInt("material_id"));
                float quantidadeUtilizada = rs.getFloat("quantidade_utilizada");
                materiais.add(new MaterialNaAtividadeVO(material, quantidadeUtilizada));
            }

            stmt.close();
            rs.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiais;
    }

    /**
     * Busca um material no banco de dados pelo id
     * 
     * @param id identificador do material
     * 
     * @return um objeto do tipo {@code MaterialVO}
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
     * Retorna o material presente no banco de dados contendo todas
     * as suas informações
     * 
     * @param rs {@code ResultSet} contendo os atributos de {@code MaterialVO}
     * 
     * @return um objeto do tipo {@code MaterialVO}
     * 
     * @throws SQLException caso ocorra algum erro no acesso ao banco
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
