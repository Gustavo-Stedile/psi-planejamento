package br.edu.ifsp.hto.planejamento.controle;

import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.hto.planejamento.modelo.DAO.PlanoDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;

/**
 * Classe de controle responsável por gerenciar as operações relacionadas aos planos.
 * Faz a intermediação entre a camada de visão e a camada de acesso a dados (DAO).
 * 
 * @author Nicolas Jesus Silva
 */
public class PlanoControle {

    private PlanoDAO planoDAO;

    /**
     * Construtor padrão que inicializa o objeto PlanoDAO.
     */
    public PlanoControle() {
        this.planoDAO = new PlanoDAO();
    }

    /**
     * Cadastra um novo plano no banco de dados.
     *
     * @param plano Objeto PlanoVO contendo os dados do plano a ser inserido.
     */
    public void cadastrarPlano(PlanoVO plano) {
        if (plano == null || plano.getNomePlano() == null || plano.getNomePlano().isEmpty()) {
            System.out.println("❌ O nome do plano não pode ser vazio ou nulo!");
            return;
        }

        planoDAO.inserir(plano);
        System.out.println("✅ Plano cadastrado com sucesso!");
    }

    /**
     * Retorna a lista de todos os planos cadastrados.
     *
     * @return Lista de objetos PlanoVO.
     */
    public List<PlanoVO> listarPlanos() {
        List<PlanoVO> planos = planoDAO.listarTodos();

        if (planos.isEmpty()) {
            System.out.println("⚠️ Nenhum plano encontrado no sistema.");
        }

        return planos;
    }

    /**
     * Busca um plano pelo seu ID.
     *
     * @param id Identificador único do plano.
     * @return Objeto PlanoVO correspondente ao ID, ou null se não encontrado.
     */
    public PlanoVO buscarPlanoPorId(int id) {
        PlanoVO plano = planoDAO.buscarPorId(id);

        if (plano == null) {
            System.out.println("⚠️ Plano com ID " + id + " não encontrado!");
        }

        return plano;
    }

    /**
     * Atualiza os dados de um plano existente.
     *
     * @param plano Objeto PlanoVO com os novos dados a serem atualizados.
     */
    public void atualizarPlano(PlanoVO plano) {
        if (plano == null || plano.getId() <= 0) {
            System.out.println("❌ ID inválido para atualização!");
            return;
        }

        planoDAO.atualizar(plano);
        System.out.println("✅ Plano atualizado com sucesso!");
    }

    /**
     * Remove um plano do banco de dados pelo ID.
     *
     * @param id Identificador do plano a ser removido.
     */
    public void deletarPlano(int id) {
        PlanoVO plano = planoDAO.buscarPorId(id);

        if (plano == null) {
            System.out.println("⚠️ Não foi possível excluir: plano com ID " + id + " não existe.");
            return;
        }

        planoDAO.deletar(id);
        System.out.println("🗑️ Plano removido com sucesso!");
    }

    /**
     * Retorna todos os planos vinculados a um talhão específico.
     *
     * @param talhaoId Identificador do talhão.
     * @return Lista de planos associados ao talhão.
     */
    public ArrayList<PlanoVO> buscarPlanosDoTalhao(int talhaoId) {
        ArrayList<PlanoVO> planos = planoDAO.buscarPlanosDoTalhao(talhaoId);

        if (planos.isEmpty()) {
            System.out.println("⚠️ Nenhum plano encontrado para o talhão ID: " + talhaoId);
        }

        return planos;
    }

    /**
     * Retorna um plano com todos os seus canteiros associados.
     *
     * @param id Identificador do plano.
     * @return Objeto PlanoComCanteirosVO contendo o plano e seus canteiros.
     */
    public PlanoComCanteirosVO buscarPlanoComCanteiros(int id) {
        PlanoComCanteirosVO planoCanteiro = planoDAO.buscarPlanoComCanteiros(id);

        if (planoCanteiro == null || planoCanteiro.getPlano() == null) {
            System.out.println("⚠️ Plano não encontrado ou sem canteiros vinculados.");
        }

        return planoCanteiro;
    }
}
