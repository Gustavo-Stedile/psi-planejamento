package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.util.ArrayList;

public class AreaComTalhoesVO {
    private AreaVO area;
    private ArrayList<TalhaoVO> talhoes;

    public AreaComTalhoesVO(AreaVO area, ArrayList<TalhaoVO> talhoes) {
        this.area = area;
        this.talhoes = talhoes;
    }

    public AreaVO getArea() {
        return area;
    }

    public void setArea(AreaVO area) {
        this.area = area;
    }

    public ArrayList<TalhaoVO> getTalhoes() {
        return talhoes;
    }

    public void setTalhoes(ArrayList<TalhaoVO> talhoes) {
        this.talhoes = talhoes;
    }
}
