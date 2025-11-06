package br.edu.ifsp.hto.planejamento.modelo.VO;

public class MaterialVO {
    private int id;
    private int associadoId;
    private String nome;
    private float quantidade;
    private String unidadeMedida;

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

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}
