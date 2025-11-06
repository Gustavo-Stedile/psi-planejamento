package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.CanteiroDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroComAtividadesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;

public class CanteiroDAOTest {
    public static void main(String[] args) {
        CanteiroDAO canteiroDAO = new CanteiroDAO();
        List<CanteiroVO> canteiros = canteiroDAO.listarTodos();
        canteiros.forEach(canteiro -> System.out.println(canteiro.getNome()));

        CanteiroVO novo = new CanteiroVO(3, "Canteiro Daora", 1000, "canteiro muito daora", 3000);
        // canteiroDAO.inserir(novo);

        CanteiroComAtividadesVO ca = canteiroDAO.buscarCanteiroComAtividades(1);
        ca.getAtividades().forEach(a -> System.out.println(a.getDataAtividade()));
    }
}
