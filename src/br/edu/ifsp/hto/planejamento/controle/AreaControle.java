package br.edu.ifsp.hto.planejamento.controle;

import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.AreaDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaComTalhoesVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaVO;

public class AreaControle {

    private AreaDAO areaDAO;

    public AreaControle() {
        this.areaDAO = new AreaDAO();
    }

    /**
     * Adiciona uma nova área no banco de dados
     * 
     * @param area objeto do tipo {@code AreaVO}
     * 
     * @see AreaDAO#inserir(AreaVO)
     */
    public void inserir(AreaVO area) {
        areaDAO.inserir(area);
    }

    /**
     * Lista todas as áreas presentes no banco de dados
     * 
     * @return um {@code ArrayList} contendo {@code AreaVO} como elementos
     * 
     * @see AreaDAO#listarTodas()
     */
    public List<AreaVO> listarTodas() {
        return areaDAO.listarTodas();
    }

    /**
     * Busca uma área no banco de dados pelo id
     * 
     * @param id identificador da área
     * 
     * @return um objeto do tipo {@code AreaVO}
     * 
     * @see AreaDAO#buscarPorId(int)
     */
    public AreaVO buscarPorId(int id) {
        return areaDAO.buscarPorId(id);
    }

    /**
     * Busca uma área especifica que possui talhões
     * 
     * @param id identificador da área
     * 
     * @return um objeto do tipo {@code AreaTalhaoVO}
     * 
     * @see AreaDAO#buscarAreaComTalhoes(int)
     */
    public AreaComTalhoesVO buscarAreaComTalhoes(int id) {
        return areaDAO.buscarAreaComTalhoes(id);
    }

    /**
     * Atualiza uma área presente no banco de dados
     * 
     * @param area objeto {@code AreaVO} contendo os novos dados
     * 
     * @see AreaDAO#atualizar(AreaVO)
     */
    public void atualizar(AreaVO area) {
        areaDAO.atualizar(area);
    }

    /**
     * Deleta uma área presente no banco de dados
     * 
     * @param id identificador da área a ser excluida
     * 
     * @see AreaDAO#deletar(int)
     */
    public void deletar(int id) {
        areaDAO.deletar(id);
    }
}
