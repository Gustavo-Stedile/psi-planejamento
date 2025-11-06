package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.PlanoDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;

public class PlanoDAOTest {
    public static void main(String[] args) {
        PlanoDAO planoDAO = new PlanoDAO();
        List<PlanoVO> planos = planoDAO.listarTodos();
        planos.forEach(plano -> System.out.println(plano.getNomePlano()));

        PlanoVO novo = new PlanoVO(
            1, 1,
            "Plantio Daora",
            "plantar bastante coisa",
            null, null,
            "nao plantou nada ainda",
            1000
        );
        // planoDAO.inserir(novo);

        PlanoComCanteirosVO pc = planoDAO.buscarPlanoComCanteiros(1);
        pc.getCanteiros().forEach(canteiro -> System.out.println(canteiro.getNome()));

        // planoDAO.deletar(1);
    }
}
