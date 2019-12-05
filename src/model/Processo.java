/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rafael
 */
public class Processo {
    private int idProcesso;
    private String processo;
    private String situacao;
    private String observacao;
    private String dataVerificacao;

    public Processo() {
    }

    public Processo( String processo, String stuacao, String observacao, String dataVerificacao) {
        
        this.processo = processo;
        this.situacao = stuacao;
        this.observacao = observacao;
        this.dataVerificacao = dataVerificacao;
    }

    public int getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(int idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String stuacao) {
        this.situacao = stuacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDataVerificacao() {
        return dataVerificacao;
    }

    public void setDataVerificacao(String dataVerificacao) {
        this.dataVerificacao = dataVerificacao;
    }
    
}
