package br.edu.ifsp.hto.planejamento.modelo.VO;

public class AreaVO {
    private int id;
    private int associadoId;
    private String nome;
    private float areaTotal;
    private float areaUtilizada;
    private float ph;

    public AreaVO() {

    }

    public AreaVO(int associadoId, String nome, float areaTotal, float areaUtilizada, float ph) {
        this.associadoId = associadoId;
        this.nome = nome;
        this.areaTotal = areaTotal;
        this.areaUtilizada = areaUtilizada;
        this.ph = ph;
    }

    public AreaVO(int id, int associadoId, String nome, float areaTotal, float areaUtilizada, float ph) {
        this.id = id;
        this.associadoId = associadoId;
        this.nome = nome;
        this.areaTotal = areaTotal;
        this.areaUtilizada = areaUtilizada;
        this.ph = ph;
    }

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
}
