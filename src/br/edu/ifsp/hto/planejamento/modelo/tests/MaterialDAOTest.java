package br.edu.ifsp.hto.planejamento.modelo.tests;

import br.edu.ifsp.hto.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialVO;

public class MaterialDAOTest {
    
    public static void main(String[] args) {
        PlanejamentoControle materialC = new PlanejamentoControle();

        materialC.inserir(new MaterialVO( 1, "TESTE", 10, "m²"));

        materialC.listarMateriais().forEach(material -> System.out.println(material.getId()));

        materialC.atualizar(new MaterialVO(6, 1, "TESTE TESTE", 20, "km²"));

        System.out.println(materialC.buscarMaterialPorId(1).getNome());

        materialC.deletarMaterial(1);

        materialC.buscarMateriaisDaAtividade(4).forEach(m -> System.out.println(m.getMaterial().getNome()));
    }
}
