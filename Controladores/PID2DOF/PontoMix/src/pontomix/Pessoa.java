/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pontomix;

/**
 *
 * @author Caeta
 */
public class Pessoa {
    
    private String nome;
    String cpf;
    private String dataNasc;

    public Pessoa() {
    }

    public void cadastra(String name, String cpf, String dataNasc){
        
        this.setNome(name);
        this.setCpf(cpf);
        this.setDataNasc(dataNasc);
        
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

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    
}
