package br.edu.ifsp.hto.planejamento.controle;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.PlanoDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoComCanteirosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.PlanoVO;

public class PlanoControle {

    private PlanoDAO planoDAO;

    public PlanoControle() {
        this.planoDAO = new PlanoDAO();
    }

    /**
     * Adiciona um novo plano no banco de dados
     * 
     * @param plano objeto do tipo {@code PlanoVO}
     * 
     * @see PlanoDAO#inserir(PlanoVO)
     */
    public void inserir(PlanoVO plano) {
        planoDAO.inserir(plano);
    }

    /**
     * Lista todos os planos presentes no banco de dados
     * 
     * @return um {@code ArrayList} contendo {@code PlanoVO} como elementos
     * 
     * @see PlanoDAO#listarTodos()
     */
    public List<PlanoVO> listarTodos() {
        return planoDAO.listarTodos();
    }

    /**
     * Busca um plano no banco de dados pelo id
     * 
     * @param id identificador do plano
     * 
     * @return um objeto do tipo {@code PlanoVO}
     * 
     * @see PlanoDAO#buscarPorId(int)
     */
    public PlanoVO buscarPorId(int id) {
        return planoDAO.buscarPorId(id);
    }

    /**
     * Busca todos os planos pertencentes a um talhão
     * 
     * @param id identificador do talhão
     * 
     * @return um {@code ArrayList} contendo {@code PlanoVO} como elementos
     * 
     * @see PlanoDAO#buscarPlanosDoTalhao(int)
     */
    public ArrayList<PlanoVO> buscarPlanosDoTalhao(int id) {
        return planoDAO.buscarPlanosDoTalhao(id);
    }

    /**
     * Busca um plano especifico que possue canteiros
     * 
     * @param id identificador do plano
     * 
     * @return um objeto do tipo {@code PlanoComCanteirosVO}
     * 
     * @see PlanoDAO#buscarPlanoComCanteiros(int)
     */
    public PlanoComCanteirosVO buscarPlanoComCanteiros(int id) {
        return planoDAO.buscarPlanoComCanteiros(id);
    }

    /**
     * Atualiza um plano presente no banco de dados
     * 
     * @param talhao objeto {@code PlanoVO} contendo os novos dados
     * 
     * @see PlanoDAO#atualizar(PlanoVO)
     */
    public void atualizar(PlanoVO plano) {
        planoDAO.atualizar(plano);
    }

    /**
     * Deleta um plano presente no banco de dados
     * 
     * @param id identificador do plano a ser excluido
     * 
     * @see PlanoDAO#deletar(int)
     */
    public void deletar(int id) {
        planoDAO.deletar(id);
    }
}
