package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.controle.AtividadeControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeComMateriaisVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;

public class AtividadeDAOTest {
    
    public static void main(String[] args) {
        AtividadeControle atividadeC = new AtividadeControle();

        // atividadeC.inserir(new AtividadeVO(1, "Teste", "teste", "TESTE", "NÂO"));
    
        atividadeC.listarTodas().forEach(atividade -> System.out.println(atividade.getNomeAtividade()));

        atividadeC.buscarAtividadesDoCanteiro(1).forEach(atividadesDoCanteiro -> System.out.println(atividadesDoCanteiro.getDataAtividade()));

        System.out.println(atividadeC.buscarPorId(1).getNomeAtividade());

        // atividadeC.atualizar(new AtividadeVO(1, "Teste", "teste", "TESTE TESTE", "NÃO"));

        // atividadeC.deletar(1);

        AtividadeComMateriaisVO atividade = atividadeC.buscarAtividadeComMateriais(4);

        atividade.getMateriais().forEach(material -> System.out.println(material.getMaterial().getNome()));

        List<AtividadeNoCanteiroVO> atividadeNoCanteiro = atividadeC.buscarAtividadesDoCanteiro(1);

        atividadeNoCanteiro.forEach(a -> System.out.println(a.getAtividadeVO().getNomeAtividade()));
    }
}
