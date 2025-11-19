package br.edu.ifsp.hto.planejamento.controle;

import java.sql.Date;
import java.util.List;

import br.edu.ifsp.hto.planejamento.modelo.DAO.*;
import br.edu.ifsp.hto.planejamento.modelo.VO.*;

public class PlanejamentoControle {

    private AreaDAO areaDAO;
    private AtividadeDAO atividadeDAO;
    private MaterialDAO materialDAO;
    private PlanoDAO planoDAO;
    private TalhaoDAO talhaoDAO;

    public PlanejamentoControle() {
        this.areaDAO = new AreaDAO();
        this.atividadeDAO = new AtividadeDAO();
        this.materialDAO = new MaterialDAO();
        this.planoDAO = new PlanoDAO();
        this.talhaoDAO = new TalhaoDAO();
    }

    // ---------------| AreaControle |---------------//

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
     * @return um {@code List} contendo {@code AreaVO} como elementos
     * 
     * @see AreaDAO#listarTodas()
     */
    public List<AreaVO> listarAreas() {
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
    public AreaVO buscarAreaPorId(int id) {
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
    public void deletarArea(int id) {
        areaDAO.deletar(id);
    }

    // ---------------| AtividadeControle |---------------//

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
     * Adiciona um material na atividade
     * 
     * @param materialId identificador do material
     * @param atividadeId identificador da atividade
     * @param quantidadeUtilizada quantidade de material a ser utilizada
     * 
     * @see AtividadeDAO#adicionarMaterial(int, int, float)
     */
    public void adicionarMaterial(int materialId, int atividadeId, float quantidadeUtilizada) {
        atividadeDAO.adicionarMaterial(materialId, atividadeId, quantidadeUtilizada);
    }

    /**
     * Lista todas as atividades presentes no banco de dados
     * 
     * @return um {@code List} contendo {@code AtividadeVO} como elementos
     * 
     * @see AtividadeDAO#listarTodas()
     */
    public List<AtividadeVO> listarAtividades() {
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
    public AtividadeVO buscarAtividadePorId(int id) {
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
     * @return um {@code List} contendo {@code AtividadeNoCanteiroVO} como elementos
     * 
     * @see AtividadeDAO#buscarAtividadesDoCanteiro(int)
     */
    public List<AtividadeNoCanteiroVO> buscarAtividadesDoCanteiro(int id) {
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
    public void deletarAtividade(int id) {
        atividadeDAO.deletar(id);
    }

    /**
     * Remove um material de uma atividade específica
     * 
     * @param materialId identificador do material
     * @param atividadeId identificador da atividade
     * 
     * @see AtividadeDAO#removerMaterial(int, int)
     */
    public void removerMaterial(int materialId, int atividadeId) {
        atividadeDAO.removerMaterial(materialId, atividadeId);
    }

    // ---------------| MaterialControle |---------------//

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
     * @return um {@code List} contendo {@code MaterialVO} como elementos
     * 
     * @see MaterialDAO#listarTodos()
     */
    public List<MaterialVO> listarMateriais() {
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
    public MaterialVO buscarMaterialPorId(int id) {
        return materialDAO.buscarPorId(id);
    }

    /**
     * Busca todos os materiais pertencentes a uma atividade
     * 
     * @param id identificador do material
     * 
     * @return um {@code List} contendo {@code MaterialNaAtividadeVO} como elementos
     * 
     * @see MaterialDAO#buscarMateriaisDaAtividade(int)
     */
    public List<MaterialNaAtividadeVO> buscarMateriaisDaAtividade(int id) {
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
    public void deletarMaterial(int id) {
        materialDAO.deletar(id);
    }

    // ---------------| PlanoControle |---------------//

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
     * @return um {@code List} contendo {@code PlanoVO} como elementos
     * 
     * @see PlanoDAO#listarTodos()
     */
    public List<PlanoVO> listarPlanos() {
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
    public PlanoVO buscarPlanoPorId(int id) {
        return planoDAO.buscarPorId(id);
    }

    /**
     * Busca todos os planos pertencentes a um talhão
     * 
     * @param id identificador do talhão
     * 
     * @return um {@code List} contendo {@code PlanoVO} como elementos
     * 
     * @see PlanoDAO#buscarPlanosDoTalhao(int)
     */
    public List<PlanoVO> buscarPlanosDoTalhao(int id) {
        return planoDAO.buscarPlanosDoTalhao(id);
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
    public void deletarPlano(int id) {
        planoDAO.deletar(id);
    }

    // ---------------| TalhaoControle |---------------//

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
     * @return um {@code List} contendo {@code TalhaoVO} como elementos
     * 
     * @see TalhaoDAO#listarTodos()
     */
    public List<TalhaoVO> listarTalhoes() {
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
    public TalhaoVO buscarTalhaoPorId(int id) {
        return talhaoDAO.buscarPorId(id);
    }

    /**
     * Busca todos os talhões pertencentes a uma área
     * 
     * @param id identificador da área
     * 
     * @return um {@code List} contendo {@code TalhaoVO} como elementos
     * 
     * @see TalhaoDAO#buscarTalhoesDaArea(int)
     */
    public List<TalhaoVO> buscarTalhoesDaArea(int id) {
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
    public void deletarTalhao(int id) {
        talhaoDAO.deletar(id);
    }
}
