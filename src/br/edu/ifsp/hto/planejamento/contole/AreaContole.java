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

    /**
     * Cadastra um novo plano no banco de dados.
     */
    public void cadastrarPlano(PlanoVO plano) {
        if (plano.getNomePlano() == null || plano.getNomePlano().isEmpty()) {
            System.out.println("O nome do plano não pode ser vazio!");
            return;
        }

        planoDAO.inserir(plano);
        System.out.println("Plano cadastrado com sucesso!");
    }

    /**
     * Retorna a lista de todos os planos cadastrados.
     */
    public List<PlanoVO> listarPlanos() {
        return planoDAO.listarTodos();
    }

    /**
     * Busca um plano pelo ID.
     */
    public PlanoVO buscarPlanoPorId(int id) {
        PlanoVO plano = planoDAO.buscarPorId(id);

        if (plano == null) {
            System.out.println("Plano não encontrado!");
        }

        return plano;
    }

    /**
     * Atualiza os dados de um plano existente.
     */
    public void atualizarPlano(PlanoVO plano) {
        if (plano.getId() <= 0) {
            System.out.println("ID inválido para atualização!");
            return;
        }

        planoDAO.atualizar(plano);
        System.out.println("Plano atualizado com sucesso!");
    }

    /**
     * Remove um plano pelo ID.
     */
    public void deletarPlano(int id) {
        planoDAO.deletar(id);
        System.out.println("Plano removido com sucesso!");
    }

    /**
     * Retorna a lista de planos de um talhão específico.
     */
    public ArrayList<PlanoVO> buscarPlanosDoTalhao(int talhaoId) {
        return planoDAO.buscarPlanosDoTalhao(talhaoId);
    }

    /**
     * Retorna um plano com todos os seus canteiros associados.
     */
    public PlanoComCanteirosVO buscarPlanoComCanteiros(int id) {
        return planoDAO.buscarPlanoComCanteiros(id);
    }
}
