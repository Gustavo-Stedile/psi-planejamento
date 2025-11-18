package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.List;

public class CanteiroComAtividadesVO {
    private CanteiroVO canteiro;
    private List<AtividadeNoCanteiroVO> atividades;

    public CanteiroComAtividadesVO(CanteiroVO canteiro, List<AtividadeNoCanteiroVO> atividades) {
        this.canteiro = canteiro;
        this.atividades = atividades;
    }

    public CanteiroVO getCanteiro() {
        return canteiro;
    }

    public void setCanteiro(CanteiroVO canteiro) {
        this.canteiro = canteiro;
    }

    public List<AtividadeNoCanteiroVO> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeNoCanteiroVO> atividades) {
        this.atividades = atividades;
    }

}

