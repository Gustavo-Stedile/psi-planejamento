package br.edu.ifsp.hto.planejamento.controle;

import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.hto.planejamento.modelo.DAO.PlanoDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;

/**
 * Classe de controle responsável por gerenciar as operações relacionadas a {@link PlanoVO}.
 * Atua como intermediária entre a camada de visualização e o {@link PlanoDAO}.
 * 
 * @author Nicolas de Jesus Silva
 */
public class PlanoControle {

    private PlanoDAO planoDAO;

    /**
     * Construtor padrão que inicializa o DAO de plano.
     */
    public PlanoControle() {
        this.planoDAO = new PlanoDAO();
    }

    /**
     * Insere um novo plano no banco de dados.
     * 
     * @param plano Objeto {@link PlanoVO} contendo os dados do plano a ser inserido.
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
     * Lista todos os planos cadastrados no banco de dados.
     * 
     * @return Lista de objetos {@link PlanoVO}.
     */
    public List<PlanoVO> listarTodos() {
        List<PlanoVO> planos = planoDAO.listarTodos();
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano encontrado.");
        }
        return planos;
    }

    /**
     * Busca um plano pelo seu identificador (ID).
     * 
     * @param id Identificador do plano a ser buscado.
     * @return Objeto {@link PlanoVO} correspondente ao ID informado, ou {@code null} se não encontrado.
     */
    public PlanoVO buscarPorId(int id) {
        PlanoVO plano = planoDAO.buscarPorId(id);
        if (plano == null) {
            System.out.println("Plano com ID " + id + " não encontrado.");
        }
        return plano;
    }

    /**
     * Busca todos os planos associados a um determinado talhão.
     * 
     * @param talhaoId Identificador do talhão.
     * @return Lista de {@link PlanoVO} relacionados ao talhão informado.
     */
    public ArrayList<PlanoVO> buscarPlanosDoTalhao(int talhaoId) {
        ArrayList<PlanoVO> planos = planoDAO.buscarPlanosDoTalhao(talhaoId);
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano encontrado para o talhão ID: " + talhaoId);
        }
        return planos;
    }

    /**
     * Atualiza os dados de um plano existente.
     * 
     * @param plano Objeto {@link PlanoVO} contendo os novos dados do plano.
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
     * Deleta um plano do banco de dados com base em seu ID.
     * 
     * @param id Identificador do plano a ser deletado.
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
     * Busca um plano juntamente com seus canteiros vinculados.
     * 
     * @param id Identificador do plano.
     * @return Objeto {@link PlanoComCanteirosVO} contendo o plano e seus canteiros, ou {@code null} se não encontrado.
     */
    public PlanoComCanteirosVO buscarPlanoComCanteiros(int id) {
        PlanoComCanteirosVO planoCanteiro = planoDAO.buscarPlanoComCanteiros(id);
        if (planoCanteiro == null || planoCanteiro.getPlano() == null) {
            System.out.println("Plano não encontrado ou sem canteiros vinculados.");
        }
        return planoCanteiro;
    }
}
