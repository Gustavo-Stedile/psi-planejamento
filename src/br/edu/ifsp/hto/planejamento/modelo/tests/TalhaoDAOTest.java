package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.controle.TalhaoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoComPlanosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoVO;

public class TalhaoDAOTest {
    public static void main(String[] args) {
        TalhaoControle talhaoC = new TalhaoControle();
        List<TalhaoVO> talhoes = talhaoC.listarTodos();
        talhoes.forEach(talhao -> System.out.println(talhao.getNome()));

        TalhaoVO novo = new TalhaoVO(3, 3, "Talhão Daora", 400, "muito daora", "sem nada");
        // talhaoC.inserir(novo);

        TalhaoComPlanosVO tp = talhaoC.buscarTalhaoComPlanos(1);
        tp.getPlanos().forEach(plano -> System.out.println("PLANO: " + plano.getNomePlano()));

        // talhaoC.deletar(2);
    }

}
