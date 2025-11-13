package br.edu.ifsp.hto.planejamento.modelo.tests;

import br.edu.ifsp.hto.planejamento.controle.MaterialControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialVO;

public class MaterialDAOTest {
    
    public static void main(String[] args) {
        MaterialControle materialC = new MaterialControle();

        // materialC.inserir(new MaterialVO(6, 1, "TESTE", 10, "m²"));

        materialC.listarTodos().forEach(material -> System.out.println(material.getNome()));

        // materialC.atualizar(new MaterialVO(6, 1, "TESTE TESTE", 20, "km²"));

        System.out.println(materialC.buscarPorId(1).getNome());

        materialC.buscarMateriaisDaAtividade(4).forEach(m -> System.out.println(m.getMaterial().getNome()));

    }
}
