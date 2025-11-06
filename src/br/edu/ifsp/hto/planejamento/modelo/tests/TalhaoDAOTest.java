package br.edu.ifsp.hto.planejamento.modelo.tests;

import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.TalhaoDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoComPlanosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoVO;

public class TalhaoDAOTest {
    public static void main(String[] args) {
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        List<TalhaoVO> talhoes = talhaoDAO.listarTodos();
        talhoes.forEach(talhao -> System.out.println(talhao.getNome()));

        TalhaoVO novo = new TalhaoVO(3, 3, "Talhão Daora", 400, "muito daora", "sem nada");
        // talhaoDAO.inserir(novo);

        TalhaoComPlanosVO tp = talhaoDAO.listarTalhaoComPlanos(1);
        tp.getPlanos().forEach(plano -> System.out.println("PLANO: " + plano.getNomePlano()));

        talhaoDAO.deletar(2);
    }

}
