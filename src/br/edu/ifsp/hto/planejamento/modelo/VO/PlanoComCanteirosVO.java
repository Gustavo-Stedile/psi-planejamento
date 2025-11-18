package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.List;

public class PlanoComCanteirosVO {
    private PlanoVO plano;
    private List<CanteiroVO> canteiros;

    public PlanoComCanteirosVO(PlanoVO plano, List<CanteiroVO> canteiros) {
        this.plano = plano;
        this.canteiros = canteiros;
    }

    public PlanoVO getPlano() {
        return plano;
    }

    public void setPlano(PlanoVO plano) {
        this.plano = plano;
    }

    public List<CanteiroVO> getCanteiros() {
        return canteiros;
    }

    public void setCanteiros(List<CanteiroVO> canteiros) {
        this.canteiros = canteiros;
    }
}
