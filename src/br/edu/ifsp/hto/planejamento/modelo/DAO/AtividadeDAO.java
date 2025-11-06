package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;

public class AtividadeDAO {

    // Inserir novo atividade
    public void inserir(AtividadeVO atividade){

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "INSERT INTO atividade (nome_atividade, descricao, observacoes, status_2) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, atividade.getNomeAtividade());
            stmt.setString(2, atividade.getDescricao());
            stmt.setString(3, atividade.getObervacoes());
            stmt.setString(4, atividade.getStatus2());

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Listar todos os materiais
    public List<AtividadeVO> listarTodos(){
        List<AtividadeVO> lista = new ArrayList<>();

        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM atividade";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AtividadeVO a = new AtividadeVO();
                a.setId(rs.getInt("id"));
                a.setNomeAtividade(rs.getString("nome_atividade"));
                a.setDescricao(rs.getString("descricao"));
                a.setObervacoes(rs.getString("observacoes"));
                a.setStatus2(rs.getString("status_2"));
                lista.add(a);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
    }

    // Atualizar atividade existente
    public void atualizar(AtividadeVO atividade){
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "UPDATE atividade SET nome_atividade = ?, descricao = ?, observacoes = ?, status_2 = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, atividade.getNomeAtividade());
            stmt.setString(2, atividade.getDescricao());
            stmt.setString(3, atividade.getObervacoes());
            stmt.setString(4, atividade.getStatus2());
            stmt.setInt(5, atividade.getId());

            stmt.executeUpdate();
            stmt.close();
        } catch(Exception e) {
            e.printStackTrace();
        }    
    }

    // Deletar atividade
    public void deletar(int id){
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
        
            String sql = "DELETE FROM atividade WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Buscar atividade por ID
    public AtividadeVO buscarPorId(int id){
        AtividadeVO atividade = null;

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
        
            String sql = "SELECT * FROM atividade WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) { // se encontrou algum registro
                atividade = new AtividadeVO();
                atividade.setId(rs.getInt("id"));
                atividade.setNomeAtividade(rs.getString("nome_atividade"));
                atividade.setDescricao(rs.getString("descricao"));
                atividade.setObervacoes(rs.getString("observacoes"));
                atividade.setStatus2(rs.getString("status_2"));
            }

            rs.close();
            stmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return atividade; // retorna o objeto ou null se não encontrou
    }
}