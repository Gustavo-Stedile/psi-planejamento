package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.ArrayList;

public class TalhaoComPlanosVO {
    private TalhaoVO talhao;
    private ArrayList<PlanoVO> planos;

    public TalhaoComPlanosVO(TalhaoVO talhao, ArrayList<PlanoVO> planos) {
        this.talhao = talhao;
        this.planos = planos;
    }

    public TalhaoVO getTalhao() {
        return talhao;
    }

    public void setTalhao(TalhaoVO talhao) {
        this.talhao = talhao;
    }

    public ArrayList<PlanoVO> getPlanos() {
        return planos;
    }

    public void setPlanos(ArrayList<PlanoVO> planos) {
        this.planos = planos;
    }
}