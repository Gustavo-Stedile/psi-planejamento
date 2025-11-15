package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaComTalhoesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaVO;

public class AreaDAOTest {
    public static void main(String[] args) throws Exception {
        PlanejamentoControle areaC = new PlanejamentoControle();

        areaC.inserir(new AreaVO(1, "Area Daora", 1000, 0, 7));

        List<AreaVO> areas = areaC.listarAreas();
        areas.forEach(area -> System.out.println(area.getNome()));

        AreaComTalhoesVO at = areaC.buscarAreaComTalhoes(1);
        at.getTalhoes().forEach(t -> System.out.println(t.getNome()));

        AreaVO mudar = areas.get(0);
        mudar.setNome("FAZENDA SUL DAORA");
        areaC.atualizar(mudar);

        areaC.deletarArea(2);
    }
}