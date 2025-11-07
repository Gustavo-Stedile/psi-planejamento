package br.edu.ifsp.hto.planejamento.modelo.tests;

import br.edu.ifsp.hto.planejamento.modelo.DAO.AtividadeDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;

public class AtividadeDAOTest {
    
    public static void main(String[] args) {
        AtividadeDAO atividadeDAO = new AtividadeDAO();

        //atividadeDAO.inserir(new AtividadeVO(1, "Teste", "teste", "TESTE", "NÂO"));
        
        atividadeDAO.listarTodos().forEach(atividade -> System.out.println(atividade.getNomeAtividade()));

        atividadeDAO.buscarAtividadesDoCanteiro(1).forEach(atividadesDoCanteiro -> System.out.println(atividadesDoCanteiro.getDataAtividade()));
    
        System.out.println(atividadeDAO.buscarPorId(1).getNomeAtividade());

        atividadeDAO.atualizar(new AtividadeVO(1, "Teste", "teste", "TESTE TESTE", "NÂO"));

        atividadeDAO.deletar(1);
    }
}
