package br.edu.ifsp.hto.planejamento.controle;

import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.hto.planejamento.modelo.DAO.PlanoDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;

public class PlanoControle {
    private PlanoDAO planoDAO;

    public PlanoControle() {
        this.planoDAO = new PlanoDAO();
    }

    // Inserir novo plano
    public void inserir(PlanoVO plano) {
        if (plano != null) {
            planoDAO.inserir(plano);
            System.out.println("Plano inserido com sucesso!");
        } else {
            System.out.println("Erro: plano inválido para inserção.");
        }
    }

    // Listar todos os planos
    public List<PlanoVO> listarTodos() {
        List<PlanoVO> planos = planoDAO.listarTodos();
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano encontrado.");
        }
        return planos;
    }

    // Buscar plano por ID
    public PlanoVO buscarPorId(int id) {
        PlanoVO plano = planoDAO.buscarPorId(id);
        if (plano == null) {
            System.out.println("Plano com ID " + id + " não encontrado.");
        }
        return plano;
    }

    // Buscar planos de um talhão
    public ArrayList<PlanoVO> buscarPlanosDoTalhao(int talhaoId) {
        ArrayList<PlanoVO> planos = planoDAO.buscarPlanosDoTalhao(talhaoId);
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano encontrado para o talhão ID: " + talhaoId);
        }
        return planos;
    }

    // Atualizar plano existente
    public void atualizar(PlanoVO plano) {
        if (plano != null && plano.getId() > 0) {
            planoDAO.atualizar(plano);
            System.out.println("Plano atualizado com sucesso!");
        } else {
            System.out.println("Erro: plano inválido para atualização.");
        }
    }

    // Deletar plano
    public void deletar(int id) {
        PlanoVO plano = planoDAO.buscarPorId(id);
        if (plano != null) {
            planoDAO.deletar(id);
            System.out.println("Plano removido com sucesso!");
        } else {
            System.out.println("Erro: plano com ID " + id + " não encontrado.");
        }
    }

    // Buscar plano com canteiros
    public PlanoComCanteirosVO buscarPlanoComCanteiros(int id) {
        PlanoComCanteirosVO planoCanteiro = planoDAO.buscarPlanoComCanteiros(id);
        if (planoCanteiro == null || planoCanteiro.getPlano() == null) {
            System.out.println("Plano não encontrado ou sem canteiros vinculados.");
        }
        return planoCanteiro;
    }
}
