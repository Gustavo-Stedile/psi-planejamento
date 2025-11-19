package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.sql.Date;

public class AtividadeNoCanteiroVO {
    private AtividadeVO atividadeVO;
    private float tempoGastoHoras;
    private Date dataAtividade;

    public AtividadeNoCanteiroVO(AtividadeVO atividadeVO, float tempoGastoHoras, Date dataAtividade) {
        this.atividadeVO = atividadeVO;
        this.tempoGastoHoras = tempoGastoHoras;
        this.dataAtividade = dataAtividade;
    }

    public AtividadeVO getAtividadeVO() {
        return atividadeVO;
    }

    public void setAtividadeVO(AtividadeVO atividadeVO) {
        this.atividadeVO = atividadeVO;
    }

    public float getTempoGastoHoras() {
        return tempoGastoHoras;
    }

    public void setTempoGastoHoras(float tempoGastoHoras) {
        this.tempoGastoHoras = tempoGastoHoras;
    }

    public Date getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(Date dataAtividade) {
        this.dataAtividade = dataAtividade;
    }
}

