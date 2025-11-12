package br.edu.ifsp.hto.planejamento.controle;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.TalhaoDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoComPlanosVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.TalhaoVO;

public class TalhaoControle {

    private TalhaoDAO talhaoDAO;

    public TalhaoControle() {
        this.talhaoDAO = new TalhaoDAO();
    }

    /**
     * Adiciona um novo talhão no banco de dados
     * 
     * @param talhao objeto do tipo {@code TalhaoVO}
     * 
     * @see TalhaoDAO#inserir(TalhaoVO)
     */
    public void inserir(TalhaoVO talhao) {
        talhaoDAO.inserir(talhao);
    }

    /**
     * Lista todos os talhões presentes no banco de dados
     * 
     * @return um {@code ArrayList} contendo {@code TalhaoVO} como elementos
     * 
     * @see TalhaoDAO#listarTodos()
     */
    public List<TalhaoVO> listarTodos() {
        return talhaoDAO.listarTodos();
    }

    /**
     * Busca um talhão no banco de dados pelo id
     * 
     * @param id identificador do talhão
     * 
     * @return um objeto do tipo {@code TalhaoVO}
     * 
     * @see TalhaoDAO#buscarPorId(int)
     */
    public TalhaoVO buscarPorId(int id) {
        return talhaoDAO.buscarPorId(id);
    }

    /**
     * Busca todos os talhões pertencentes a uma área
     * 
     * @param id identificador da área
     * 
     * @return um {@code ArrayList} contendo {@code TalhaoVO} como elementos
     * 
     * @see TalhaoDAO#buscarTalhoesDaArea(int)
     */
    public ArrayList<TalhaoVO> buscarTalhoesDaArea(int id) {
        return talhaoDAO.buscarTalhoesDaArea(id);
    }

    /**
     * Lista todos os talhões que possuem planos
     * 
     * @param id identificador do talhão
     * 
     * @return Um objeto do tipo {@code TalhaoPlanoVO}
     * 
     * @see TalhaoDAO#buscarTalhaoComPlanos(int)
     */
    public TalhaoComPlanosVO buscarTalhaoComPlanos(int id) {
        return talhaoDAO.buscarTalhaoComPlanos(id);
    }

    /**
     * Atualiza um talhão presente no banco de dados
     * 
     * @param talhao objeto {@code TalhaoVO} contendo os novos dados
     * 
     * @see TalhaoDAO#atualizar(TalhaoVO)
     */
    public void atualizar(TalhaoVO talhao) {
        talhaoDAO.atualizar(talhao);
    }

    /**
     * Deleta um talhão presente no banco de dados
     * 
     * @param id identificador do talhão a ser excluido
     * 
     * @see TalhaoDAO#deletar(int)
     */
    public void deletar(int id) {
        talhaoDAO.deletar(id);
    }
}
