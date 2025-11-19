package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.List;

public class TalhaoComPlanosVO {
    private TalhaoVO talhao;
    private List<PlanoVO> planos;

    public TalhaoComPlanosVO(TalhaoVO talhao, List<PlanoVO> planos) {
        this.talhao = talhao;
        this.planos = planos;
    }

    public TalhaoVO getTalhao() {
        return talhao;
    }

    public void setTalhao(TalhaoVO talhao) {
        this.talhao = talhao;
    }

    public List<PlanoVO> getPlanos() {
        return planos;
    }

    public void setPlanos(List<PlanoVO> planos) {
        this.planos = planos;
    }
}
