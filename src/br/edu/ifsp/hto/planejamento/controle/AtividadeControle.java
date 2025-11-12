package br.edu.ifsp.hto.planejamento.controle;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.AtividadeDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeComMateriaisVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeNoCanteiroVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeVO;

public class AtividadeControle {

    private AtividadeDAO atividadeDAO;

    public AtividadeControle() {
        this.atividadeDAO = new AtividadeDAO();
    }

    /**
     * Adiciona uma nova atividade no banco de dados
     * 
     * @param atividade objeto do tipo {@code AtividadeVO}
     * 
     * @see AtividadeDAO#inserir(AtividadeVO)
     */
    public void inserir(AtividadeVO atividade) {
        atividadeDAO.inserir(atividade);
    }

    /**
     * Lista todas as atividades presentes no banco de dados
     * 
     * @return um {@code ArrayList} contendo {@code AtividadeVO} como elementos
     * 
     * @see AtividadeDAO#listarTodas()
     */
    public List<AtividadeVO> listarTodas() {
        return atividadeDAO.listarTodas();
    }

    /**
     * Busca uma atividade no banco de dados pelo id
     * 
     * @param id identificador da atividade
     * 
     * @return um objeto do tipo {@code AtividadeVO}
     * 
     * @see AtividadeDAO#buscarPorId(int)
     */
    public AtividadeVO buscarPorId(int id) {
        return atividadeDAO.buscarPorId(id);
    }

    /**
     * Busca uma atividade especifica que possue materiais
     * 
     * @param id identificador da atividade
     * 
     * @return um objeto do tipo {@code AtividadeComMateriaisVO}
     * 
     * @see AtividadeDAO#buscarAtividadeComMateriais(int)
     */
    public AtividadeComMateriaisVO buscarAtividadeComMateriais(int id) {
        return atividadeDAO.buscarAtividadeComMateriais(id);
    }

    /**
     * Lista todas as atividades que pertencentes a um canteiro especifico
     * 
     * @param id identificador do canteiro
     * 
     * @return um {@code ArrayList} contendo {@code AtividadeNoCanteiroVO} como elementos
     * 
     * @see AtividadeDAO#buscarAtividadesDoCanteiro(int)
     */
    public ArrayList<AtividadeNoCanteiroVO> buscarAtividadesDoCanteiro(int id) {
        return atividadeDAO.buscarAtividadesDoCanteiro(id);
    }

    /**
     * Atualiza uma área presente no banco de dados
     * 
     * @param atividade objeto {@code AtividadeVO} contendo os novos dados
     * 
     * @see AtividadeDAO#atualizar(AtividadeVO)
     */
    public void atualizar(AtividadeVO atividade) {
        atividadeDAO.atualizar(atividade);
    }

    /**
     * Deleta uma atividade presente no banco de dados
     * 
     * @param id identificador da atividade a ser excluida
     * 
     * @see AtividadeDAO#deletar(int)
     */
    public void deletar(int id) {
        atividadeDAO.deletar(id);
    }
}