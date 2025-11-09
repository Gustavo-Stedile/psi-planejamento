package br.edu.ifsp.hto.planejamento.modelo.tests;

import br.edu.ifsp.hto.planejamento.modelo.DAO.MaterialDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialVO;

public class MaterialDAOTest {
    
    public static void main(String[] args) {
        MaterialDAO materialDAO = new MaterialDAO();

        // materialDAO.inserir(new MaterialVO(6, 1, "TESTE", 10, "m²"));

        materialDAO.listarTodos().forEach(material -> System.out.println(material.getNome()));

        // materialDAO.atualizar(new MaterialVO(6, 1, "TESTE TESTE", 20, "km²"));

        System.out.println(materialDAO.buscarPorId(1).getNome());

        materialDAO.buscarMateriaisDaAtividade(4).forEach(m -> System.out.println(m.getMaterial().getNome()));

    }
}
