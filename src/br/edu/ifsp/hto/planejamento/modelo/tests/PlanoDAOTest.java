package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.controle.PlanoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;

public class PlanoDAOTest {
    public static void main(String[] args) {
        PlanoControle planoC = new PlanoControle();
        List<PlanoVO> planos = planoC.listarTodos();
        planos.forEach(plano -> System.out.println(plano.getNomePlano()));

        PlanoVO novo = new PlanoVO(
            1, 1,
            "Plantio Daora",
            "plantar bastante coisa",
            null, null,
            "nao plantou nada ainda",
            1000
        );
        // planoC.inserir(novo);

        PlanoComCanteirosVO pc = planoC.buscarPlanoComCanteiros(1);
        pc.getCanteiros().forEach(canteiro -> System.out.println(canteiro.getNome()));

        // planoC.deletar(1);
    }
}
