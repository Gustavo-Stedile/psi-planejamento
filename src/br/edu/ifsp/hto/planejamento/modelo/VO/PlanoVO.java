package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.sql.Date;

public class PlanoVO {
    private int id;
    private int especieId;
    private int talhaoId;
    private String nomePlano;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private String observacoes;
    private float areaCultivo;

    public PlanoVO() {
        
    }

    public PlanoVO(int especieId, int talhaoId, String nomePlano, String descricao, Date dataInicio, Date dataFim,
            String observacoes, float areaCultivo) {
        this.especieId = especieId;
        this.talhaoId = talhaoId;
        this.nomePlano = nomePlano;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacoes = observacoes;
        this.areaCultivo = areaCultivo;
    }

    public PlanoVO(int id, int especieId, int talhaoId, String nomePlano, String descricao, Date dataInicio,
            Date dataFim, String observacoes, float areaCultivo) {
        this.id = id;
        this.especieId = especieId;
        this.talhaoId = talhaoId;
        this.nomePlano = nomePlano;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacoes = observacoes;
        this.areaCultivo = areaCultivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEspecieId() {
        return especieId;
    }

    public void setEspecieId(int especieId) {
        this.especieId = especieId;
    }

    public int getTalhaoId() {
        return talhaoId;
    }

    public void setTalhaoId(int talhaoId) {
        this.talhaoId = talhaoId;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public float getAreaCultivo() {
        return areaCultivo;
    }

    public void setAreaCultivo(float areaCultivo) {
        this.areaCultivo = areaCultivo;
    }
}
