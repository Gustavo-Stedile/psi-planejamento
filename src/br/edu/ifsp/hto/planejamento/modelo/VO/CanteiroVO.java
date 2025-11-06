package br.edu.ifsp.hto.planejamento.modelo.VO;

public class CanteiroVO {
    private int id;
    private int planoId;
    private String nome;
    private float areaCanteiroM2;
    private String observacoes;
    private float kgGerados;

    public CanteiroVO() {
        
    }

    public CanteiroVO(int planoId, String nome, float areaCanteiroM2, String observacoes, float kgGerados) {
        this.planoId = planoId;
        this.nome = nome;
        this.areaCanteiroM2 = areaCanteiroM2;
        this.observacoes = observacoes;
        this.kgGerados = kgGerados;
    }

    public CanteiroVO(int id, int planoId, String nome, float areaCanteiroM2, String observacoes, float kgGerados) {
        this.id = id;
        this.planoId = planoId;
        this.nome = nome;
        this.areaCanteiroM2 = areaCanteiroM2;
        this.observacoes = observacoes;
        this.kgGerados = kgGerados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlanoId() {
        return planoId;
    }

    public void setPlanoId(int planoId) {
        this.planoId = planoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAreaCanteiroM2() {
        return areaCanteiroM2;
    }

    public void setAreaCanteiroM2(float areaCanteiroM2) {
        this.areaCanteiroM2 = areaCanteiroM2;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public float getKgGerados() {
        return kgGerados;
    }

    public void setKgGerados(float kgGerados) {
        this.kgGerados = kgGerados;
    }
}
