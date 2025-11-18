package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.List;

public class AreaComTalhoesVO {
    private AreaVO area;
    private List<TalhaoVO> talhoes;

    public AreaComTalhoesVO(AreaVO area, List<TalhaoVO> talhoes) {
        this.area = area;
        this.talhoes = talhoes;
    }

    public AreaVO getArea() {
        return area;
    }

    public void setArea(AreaVO area) {
        this.area = area;
    }

    public List<TalhaoVO> getTalhoes() {
        return talhoes;
    }

    public void setTalhoes(List<TalhaoVO> talhoes) {
        this.talhoes = talhoes;
    }
}
