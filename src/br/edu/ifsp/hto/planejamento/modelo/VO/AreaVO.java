package br.edu.ifsp.hto.planejamento.modelo.VO;

public class AreaVO {
    private int id;
    private int associadoId;
    public String nome;
    public float areaTotal;
    public float areaUtilizada;
    public float ph;
    public float areaM2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(int associadoId) {
        this.associadoId = associadoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(float areaTotal) {
        this.areaTotal = areaTotal;
    }

    public float getAreaUtilizada() {
        return areaUtilizada;
    }

    public void setAreaUtilizada(float areaUtilizada) {
        this.areaUtilizada = areaUtilizada;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public float getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(float areaM2) {
        this.areaM2 = areaM2;
    }
}
