package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeComMateriaisVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;

public class AtividadeDAOTest {
    
    public static void main(String[] args) {
        PlanejamentoControle atividadeC = new PlanejamentoControle();

        atividadeC.inserir(new AtividadeVO("Teste", "teste", "TESTE", "NÂO"));
    
        atividadeC.listarAtividades().forEach(atividade -> System.out.println(atividade.getNomeAtividade()));

        atividadeC.buscarAtividadesDoCanteiro(1).forEach(a -> System.out.println(a.getAtividadeVO().getNomeAtividade()));

        System.out.println(atividadeC.buscarAtividadePorId(1).getNomeAtividade());

        atividadeC.atualizar(new AtividadeVO(5, "Colheita", "Colheita manual dos produtos", "Realizar pela manhã", "Concluída"));

        atividadeC.deletarAtividade(6);

        AtividadeComMateriaisVO atividade = atividadeC.buscarAtividadeComMateriais(4);

        atividade.getMateriais().forEach(material -> System.out.println(material.getMaterial().getNome()));
    }
}
