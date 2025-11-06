package br.edu.ifsp.hto.planejamento.modelo.VO;

public class CanteiroVO {
    private int id;
    private int planoEspecieId;
    private int planoId;
    private String nome;
    private float areaCanteiroM2;
    private String observacoes;
    private float kgGerados;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlanoEspecieId() {
        return planoEspecieId;
    }

    public void setPlanoEspecieId(int planoEspecieId) {
        this.planoEspecieId = planoEspecieId;
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
