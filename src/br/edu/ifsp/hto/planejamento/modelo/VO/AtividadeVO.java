package br.edu.ifsp.hto.planejamento.modelo.VO;

public class AtividadeVO {
    private int id;
    private String nomeAtividade;
    private String descricao;
    private String obervacoes;
    private String status;

    public AtividadeVO() {
        
    }

    public AtividadeVO(String nomeAtividade, String descricao, String obervacoes, String status) {
        this.nomeAtividade = nomeAtividade;
        this.descricao = descricao;
        this.obervacoes = obervacoes;
        this.status = status;
    }

    public AtividadeVO(int id, String nomeAtividade, String descricao, String obervacoes, String status) {
        this.id = id;
        this.nomeAtividade = nomeAtividade;
        this.descricao = descricao;
        this.obervacoes = obervacoes;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObervacoes() {
        return obervacoes;
    }

    public void setObervacoes(String obervacoes) {
        this.obervacoes = obervacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
