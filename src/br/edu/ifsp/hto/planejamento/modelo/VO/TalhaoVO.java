package br.edu.ifsp.hto.planejamento.modelo.VO;

public class TalhaoVO {
    private int id;
    private int areaId;
    private String nome;
    private float areaTalhao;
    private String observacoes;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAreaTalhao() {
        return areaTalhao;
    }

    public void setAreaTalhao(float areaTalhao) {
        this.areaTalhao = areaTalhao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
