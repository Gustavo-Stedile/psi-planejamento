package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.AreaDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaComTalhoesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaVO;

public class AreaDAOTest {
    public static void main(String[] args) throws Exception {
        AreaDAO areaDAO = new AreaDAO();

        // areaDAO.inserir(new AreaVO(1, "Area Daora", 1000, 0, 7));

        List<AreaVO> areas = areaDAO.listarTodas();
        areas.forEach(area -> System.out.println(area.getNome()));

        AreaComTalhoesVO at = areaDAO.listarAreaComTalhoes(1);
        System.out.println(at.getTalhoes().get(0).getNome());

        AreaVO mudar = areas.get(0);
        mudar.setNome("FAZENDA SUL DAORA");
        areaDAO.atualizar(mudar);

        areaDAO.deletar(2);
    }
}