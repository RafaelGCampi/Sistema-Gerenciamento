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
public class Sistema {
    private boolean cadastroCliente;
    private boolean alterarCliente;
    private boolean excluirCliente;
    private boolean cadastroFuncionario;
    private boolean alterarFuncionario;
    private boolean excluirFuncionario;
    private boolean cliente;
    private boolean funcionario;

    public Sistema(String tipoAcesso) {
        if (tipoAcesso.equals("Administrador")) {
            this.alterarCliente = true;
            this.cadastroCliente = true;
            this.excluirCliente = true;
            this.alterarFuncionario = true;
            this.cadastroFuncionario = true;
            this.excluirFuncionario = true;
            this.cliente =true;
            this.funcionario = true;        
        }
        else if (tipoAcesso.equals("Atendente")) {
            this.alterarCliente = true;
            this.cadastroCliente = true;
            this.excluirCliente = true;
            this.alterarFuncionario = false;
            this.cadastroFuncionario = false;
            this.excluirFuncionario = false;
            this.cliente = true;
            this.funcionario = false;
                    
        }
        else if (tipoAcesso.equals("Estagiario")) {
            this.alterarCliente = false;
            this.cadastroCliente = true;
            this.excluirCliente = false;
            this.alterarFuncionario = false;
            this.cadastroFuncionario = false;
            this.excluirFuncionario = false;
            this.cliente = true;
            this.funcionario = false;
        }
    }

    public boolean getCadastroCliente() {
        return cadastroCliente;
    }

    public void setCadastroCliente(boolean cadastroCliente) {
        this.cadastroCliente = cadastroCliente;
    }

    public boolean getAlterarCliente() {
        return alterarCliente;
    }

    public void setAlterarCliente(boolean alterarCliente) {
        this.alterarCliente = alterarCliente;
    }

    public boolean getExcluirCliente() {
        return excluirCliente;
    }

    public void setExcluirCliente(boolean excluirCliente) {
        this.excluirCliente = excluirCliente;
    }

    public boolean getCadastroFuncionario() {
        return cadastroFuncionario;
    }

    public void setCadastroFuncionario(boolean cadastroFuncionario) {
        this.cadastroFuncionario = cadastroFuncionario;
    }

    public boolean getAlterarFuncionario() {
        return alterarFuncionario;
    }

    public void setAlterarFuncionario(boolean alterarFuncionario) {
        this.alterarFuncionario = alterarFuncionario;
    }

    public boolean getExcluirFuncionario() {
        return excluirFuncionario;
    }

    public void setExcluirFuncionario(boolean excluirFuncionario) {
        this.excluirFuncionario = excluirFuncionario;
    }

    public boolean getCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public boolean getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

 
    
}
