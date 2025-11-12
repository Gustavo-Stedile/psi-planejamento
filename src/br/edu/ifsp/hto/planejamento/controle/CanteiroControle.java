package br.edu.ifsp.hto.planejamento.controle;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.CanteiroDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroComAtividadesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroVO;

public class CanteiroControle {

    private CanteiroDAO canteiroDAO;

    public CanteiroControle() {
        this.canteiroDAO = new CanteiroDAO();
    }

    /**
     * Adiciona um novo canteiro no banco de dados
     * 
     * @param canteiro objeto do tipo {@code CanteiroVO}
     * 
     * @see CanteiroDAO#inserir(CanteiroVO)
     */
    public void inserir(CanteiroVO canteiro) {
        canteiroDAO.inserir(canteiro);
    }

    /**
     * Lista todos os canteiros presentes no banco de dados
     * 
     * @return um {@code ArrayList} contendo {@code CanteiroVO} como elementos
     * 
     * @see CanteiroDAO#listarTodos()
     */
    public List<CanteiroVO> listarTodos() {
        return canteiroDAO.listarTodos();
    }

    /**
     * Busca um canteiro no banco de dados pelo id
     * 
     * @param id identificador do cabteiro
     * 
     * @return um objeto do tipo {@code CanteiroVO}
     * 
     * @see CanteiroDAO#buscarPorId(int)
     */
    public CanteiroVO buscarPorId(int id) {
        return canteiroDAO.buscarPorId(id);
    }

    /**
     * Busca todos os canteiros pertencentes a um plano
     * 
     * @param id identificador do canteiro
     * 
     * @return um {@code ArrayList} contendo {@code CanteiroVO} como elementos
     * 
     * @see CanteiroDAO#buscarCanteirosDoPlano(int)
     */
    public ArrayList<CanteiroVO> buscarCanteiroDoPlano(int id) {
        return canteiroDAO.buscarCanteirosDoPlano(id);
    }

    /**
     * Busca um canteiro especifico que possue atividades
     * 
     * @param id identificador do cabteiro
     * 
     * @return um objeto do tipo {@code CanteiroComAtividadesVO}
     * 
     * @see CanteiroDAO#buscarCanteiroComAtividades(int)
     */
    public CanteiroComAtividadesVO buscarCanteiroComAtividades(int id) {
        return canteiroDAO.buscarCanteiroComAtividades(id);
    }

    /**
     * Atualiza um canteiro presente no banco de dados
     * 
     * @param canteiro objeto {@code CanteiroVO} contendo os novos dados
     * 
     * @see CanteiroDAO#atualizar(CanteiroVO)
     */
    public void atualizar(CanteiroVO canteiro) {
        canteiroDAO.atualizar(canteiro);
    }

    /**
     * Deleta um canteiro presente no banco de dados
     * 
     * @param id identificador do canteiro a ser excluido
     * 
     * @see CanteiroDAO#deletar(int)
     */
    public void deletar(int id) {
        canteiroDAO.deletar(id);
    }
}
