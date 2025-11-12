package br.edu.ifsp.hto.planejamento.controle;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.MaterialDAO;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialNaAtividadeVO;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialVO;

public class MaterialControle {

    private MaterialDAO materialDAO;

    public MaterialControle() {
        this.materialDAO = new MaterialDAO();
    }

    /**
     * Adiciona um novo material no banco de dados
     * 
     * @param material objeto do tipo {@code MaterialVO}
     * 
     * @see MaterialDAO#inserir(MaterialVO)
     */
    public void inserir(MaterialVO material) {
        materialDAO.inserir(material);
    }

    /**
     * Lista todos os materiais presentes no banco de dados
     * 
     * @return um {@code ArrayList} contendo {@code MaterialVO} como elementos
     * 
     * @see MaterialDAO#listarTodos()
     */
    public List<MaterialVO> listarTodos() {
        return materialDAO.listarTodos();
    }

    /**
     * Busca um material no banco de dados pelo id
     * 
     * @param id identificador do material
     * 
     * @return um objeto do tipo {@code MaterialVO}
     * 
     * @see MaterialDAO#buscarPorId(int)
     */
    public MaterialVO buscarPorId(int id) {
        return materialDAO.buscarPorId(id);
    }

    /**
     * Busca todos os materiais pertencentes a uma atividade
     * 
     * @param id identificador do material
     * 
     * @return um {@code ArrayList} contendo {@code MaterialNaAtividadeVO} como elementos
     * 
     * @see MaterialDAO#buscarMateriaisDaAtividade(int)
     */
    public ArrayList<MaterialNaAtividadeVO> buscarMateriaisDaAtividade(int id) {
        return materialDAO.buscarMateriaisDaAtividade(id);
    }

    /**
     * Atualiza um material presente no banco de dados
     * 
     * @param material objeto {@code MaterialVO} contendo os novos dados
     * 
     * @see MaterialDAO#atualizar(MaterialVO)
     */
    public void atualizar(MaterialVO material) {
        materialDAO.atualizar(material);
    }

    /**
     * Deleta um material presente no banco de dados
     * 
     * @param id identificador do material a ser excluido
     * 
     * @see MaterialDAO#deletar(int)
     */
    public void deletar(int id) {
        materialDAO.deletar(id);
    }
}
