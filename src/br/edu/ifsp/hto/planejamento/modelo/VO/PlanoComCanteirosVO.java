package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.ArrayList;

public class PlanoComCanteirosVO {
    private PlanoVO plano;
    private ArrayList<CanteiroVO> canteiros;

    public PlanoComCanteirosVO(PlanoVO plano, ArrayList<CanteiroVO> canteiros) {
        this.plano = plano;
        this.canteiros = canteiros;
    }

    public PlanoVO getPlano() {
        return plano;
    }

    public void setPlano(PlanoVO plano) {
        this.plano = plano;
    }

    public ArrayList<CanteiroVO> getCanteiros() {
        return canteiros;
    }

    public void setCanteiros(ArrayList<CanteiroVO> canteiros) {
        this.canteiros = canteiros;
    }
}
