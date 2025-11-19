package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.Calendar;
import java.util.List;

import br.edu.ifsp.hto.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;

public class PlanoDAOTest {
    public static void main(String[] args) {
        PlanejamentoControle planoC = new PlanejamentoControle();
        List<PlanoVO> planos = planoC.listarPlanos();
        planos.forEach(plano -> System.out.println(plano.getNomePlano()));

        java.sql.Date data = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

        PlanoVO novo = new PlanoVO(
            1, 4,
            "Plantio Daora",
            "plantar bastante coisa",
            data, data,
            "nao plantou nada ainda",
            1000
        );

        planoC.inserir(novo);

        planoC.deletarPlano(6);
    }
}
