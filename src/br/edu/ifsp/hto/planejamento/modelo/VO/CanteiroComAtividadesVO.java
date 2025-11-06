package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.ArrayList;

public class CanteiroComAtividadesVO {
    private CanteiroVO canteiro;
    private ArrayList<AtividadeVO> atividades;

    public CanteiroComAtividadesVO(CanteiroVO canteiro, ArrayList<AtividadeVO> atividades) {
        this.canteiro = canteiro;
        this.atividades = atividades;
    }

    public CanteiroVO getCanteiro() {
        return canteiro;
    }

    public void setCanteiro(CanteiroVO canteiro) {
        this.canteiro = canteiro;
    }

    public ArrayList<AtividadeVO> getAtividades() {
        return atividades;
    }

    public void setAtividades(ArrayList<AtividadeVO> atividades) {
        this.atividades = atividades;
    }
}
