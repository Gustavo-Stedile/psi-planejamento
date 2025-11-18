package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.List;

public class AtividadeComMateriaisVO {
    private AtividadeVO atividade;
    private List<MaterialNaAtividadeVO> materiais;

    public AtividadeComMateriaisVO(AtividadeVO atividade, List<MaterialNaAtividadeVO> materiais) {
        this.atividade = atividade;
        this.materiais = materiais;
    }

    public AtividadeVO getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeVO atividade) {
        this.atividade = atividade;
    }

    public List<MaterialNaAtividadeVO> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MaterialNaAtividadeVO> materiais) {
        this.materiais = materiais;
    }
}
