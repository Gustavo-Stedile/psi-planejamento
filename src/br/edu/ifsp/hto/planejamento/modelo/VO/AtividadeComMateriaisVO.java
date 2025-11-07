package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.ArrayList;

public class AtividadeComMateriaisVO {
    private AtividadeVO atividade;
    private ArrayList<MaterialNaAtividadeVO> materiais;

    public AtividadeComMateriaisVO(AtividadeVO atividade, ArrayList<MaterialNaAtividadeVO> materiais) {
        this.atividade = atividade;
        this.materiais = materiais;
    }

    public AtividadeVO getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeVO atividade) {
        this.atividade = atividade;
    }

    public ArrayList<MaterialNaAtividadeVO> getMateriais() {
        return materiais;
    }

    public void setMateriais(ArrayList<MaterialNaAtividadeVO> materiais) {
        this.materiais = materiais;
    }
}
