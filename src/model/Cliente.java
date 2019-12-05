/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class Cliente  {
    private int idCliente;
    private String nome;
    private String cpf;
    private String estadoCivil;
    private String profissao;
    private String escolaridade;
    private String dataNasc;
    private String genero;
    private Processo processo;
    private String processoTb;
    private String obsTb;
    private String dataVerificacaoTb;
    private String situacaoTb;
    private Endereco endereco;

    public Cliente() {
    }
    

    public Cliente( String nome, String cpf, String estadoCivil, String profissao, String escolaridade, String dataNasc, String genero) {
        this.nome = nome;
        this.cpf = cpf;
        this.estadoCivil = estadoCivil;
        this.profissao = profissao;
        this.escolaridade = escolaridade;
        this.dataNasc = dataNasc;
        this.genero = genero;
    }
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getProcessoTb() {
        return processoTb;
    }

    public void setProcessoTb(String processoTb) {
        this.processoTb = processoTb;
    }

    public String getObsTb() {
        return obsTb;
    }

    public void setObsTb(String obsTb) {
        this.obsTb = obsTb;
    }

    public String getDataVerificacaoTb() {
        return dataVerificacaoTb;
    }

    public void setDataVerificacaoTb(String dataVerificacaoTb) {
        this.dataVerificacaoTb = dataVerificacaoTb;
    }

    public String getSituacaoTb() {
        return situacaoTb;
    }

    public void setSituacaoTb(String situacaoTb) {
        this.situacaoTb = situacaoTb;
    }

    

    @Override
    public String toString(){
        return "Nome: "+this.nome+" Cpf:"+this.cpf+" Estado civil:"+this.estadoCivil+" Profissao:"+this.profissao+" Escolaridadade:"+this.escolaridade+" Codigo:"+this.idCliente+" Data Nascimento:"+this.dataNasc;
    }
}
