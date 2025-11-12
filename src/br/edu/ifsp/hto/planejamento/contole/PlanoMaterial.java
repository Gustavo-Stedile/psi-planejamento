package br.edu.ifsp.hto.planejamento.controle;

import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.hto.planejamento.modelo.DAO.PlanoDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;

/**
 * Classe responsável por controlar as operações relacionadas aos planos de plantio.
 * Atua como intermediária entre a camada de visão e a camada de persistência (DAO).
 * 
 * Fornece métodos para inserção, listagem, atualização, exclusão e busca detalhada de planos.
 * 
 * @author Nicolas
 */
public class PlanoControle {
    private PlanoDAO planoDAO;

    /**
     * Construtor da classe PlanoControle.
     * Inicializa o objeto de acesso aos dados (PlanoDAO).
     */
    public PlanoControle() {
        this.planoDAO = new PlanoDAO();
    }

    /**
     * Insere um novo plano no banco de dados.
     * 
     * @param plano Objeto PlanoVO contendo as informações do plano a ser inserido.
     */
    public void inserir(PlanoVO plano) {
        if (plano != null) {
            planoDAO.inserir(plano);
            System.out.println("Plano inserido com sucesso!");
        } else {
            System.out.println("Erro: plano inválido para inserção.");
        }
    }

    /**
     * Lista todos os planos cadastrados.
     * 
     * @return Lista de objetos PlanoVO representando todos os planos cadastrados.
     */
    public List<PlanoVO> listarTodos() {
        List<PlanoVO> planos = planoDAO.listarTodos();
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano encontrado.");
        }
        return planos;
    }

    /**
     * Busca um plano pelo seu ID.
     * 
     * @param id Identificador único do plano.
     * @return Objeto PlanoVO correspondente ao plano encontrado, ou null se não encontrado.
     */
    public PlanoVO buscarPorId(int id) {
        PlanoVO plano = planoDAO.buscarPorId(id);
        if (plano == null) {
            System.out.println("Plano com ID " + id + " não encontrado.");
        }
        return plano;
    }

    /**
     * Busca todos os planos associados a um talhão específico.
     * 
     * @param talhaoId ID do talhão.
     * @return Lista de objetos PlanoVO vinculados ao talhão informado.
     */
    public ArrayList<PlanoVO> buscarPlanosDoTalhao(int talhaoId) {
        ArrayList<PlanoVO> planos = planoDAO.buscarPlanosDoTalhao(talhaoId);
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano encontrado para o talhão ID: " + talhaoId);
        }
        return planos;
    }

    /**
     * Atualiza os dados de um plano existente no banco de dados.
     * 
     * @param plano Objeto PlanoVO com os dados atualizados do plano.
     */
    public void atualizar(PlanoVO plano) {
        if (plano != null && plano.getId() > 0) {
            planoDAO.atualizar(plano);
            System.out.println("Plano atualizado com sucesso!");
        } else {
            System.out.println("Erro: plano inválido para atualização.");
        }
    }

    /**
     * Remove um plano existente a partir de seu ID.
     * 
     * @param id Identificador único do plano a ser removido.
     */
    public void deletar(int id) {
        PlanoVO plano = planoDAO.buscarPorId(id);
        if (plano != null) {
            planoDAO.deletar(id);
            System.out.println("Plano removido com sucesso!");
        } else {
            System.out.println("Erro: plano com ID " + id + " não encontrado.");
        }
    }

    /**
     * Busca um plano junto com seus canteiros associados.
     * 
     * @param id ID do plano a ser consultado.
     * @return Objeto PlanoComCanteirosVO contendo os dados do plano e dos canteiros vinculados,
     *         ou null se não houver registros.
     */
    public PlanoComCanteirosVO buscarPlanoComCanteiros(int id) {
        PlanoComCanteirosVO planoCanteiro = planoDAO.buscarPlanoComCanteiros(id);
        if (planoCanteiro == null || planoCanteiro.getPlano() == null) {
            System.out.println("Plano não encontrado ou sem canteiros vinculados.");
        }
        return planoCanteiro;
    }
}
