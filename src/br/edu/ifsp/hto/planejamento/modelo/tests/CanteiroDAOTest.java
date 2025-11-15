package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroComAtividadesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;

public class CanteiroDAOTest {
    public static void main(String[] args) {
        PlanejamentoControle canteiroC = new PlanejamentoControle();
        List<CanteiroVO> canteiros = canteiroC.listarCanteiros();
        canteiros.forEach(canteiro -> System.out.println(canteiro.getNome()));

        CanteiroVO novo = new CanteiroVO(4, 4, "Canteiro Beterraba 2", 100, "canteiro sombreado", 200);
        canteiroC.inserir(novo);

        CanteiroComAtividadesVO ca = canteiroC.buscarCanteiroComAtividades(1);
        ca.getAtividades().forEach(a -> System.out.println(a.getDataAtividade()));

        canteiroC.buscarAtividadesDoCanteiro(1);
        
        canteiroC.deletarCanteiro(8);
    }
}
