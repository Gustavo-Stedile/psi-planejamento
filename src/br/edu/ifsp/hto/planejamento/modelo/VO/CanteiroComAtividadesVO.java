package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.ArrayList;

public class CanteiroComAtividadesVO {
    private CanteiroVO canteiro;
    private ArrayList<AtividadeNoCanteiroVO> atividades;

    public CanteiroComAtividadesVO(CanteiroVO canteiro, ArrayList<AtividadeNoCanteiroVO> atividades) {
        this.canteiro = canteiro;
        this.atividades = atividades;
    }

    public CanteiroVO getCanteiro() {
        return canteiro;
    }

    public void setCanteiro(CanteiroVO canteiro) {
        this.canteiro = canteiro;
    }

    public ArrayList<AtividadeNoCanteiroVO> getAtividades() {
        return atividades;
    }

    public void setAtividades(ArrayList<AtividadeNoCanteiroVO> atividades) {
        this.atividades = atividades;
    }

}

