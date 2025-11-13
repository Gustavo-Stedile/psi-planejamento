package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.controle.CanteiroControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroComAtividadesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;

public class CanteiroDAOTest {
    public static void main(String[] args) {
        CanteiroControle canteiroC = new CanteiroControle();
        List<CanteiroVO> canteiros = canteiroC.listarTodos();
        canteiros.forEach(canteiro -> System.out.println(canteiro.getNome()));

        CanteiroVO novo = new CanteiroVO(3, "Canteiro Daora", 1000, "canteiro muito daora", 3000);
        // canteiroC.inserir(novo);

        CanteiroComAtividadesVO ca = canteiroC.buscarCanteiroComAtividades(1);
        ca.getAtividades().forEach(a -> System.out.println(a.getDataAtividade()));

        // canteiroC.deletar(2);
    }
}
