/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pontomix;

import pontomix.cliente;
import pontomix.loja;

import java.util.Scanner;
        
public class Funcionario extends Pessoa{
    
    private double salario;
    private String cargo;
    private String turno;
    private loja Loja;

    public Funcionario() {
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public loja getLoja() {
        return Loja;
    }

    public void setLoja(loja Loja) {
        this.Loja = Loja;
    }
    
    public void cadastraFunc(String nome, String cpf, String data, double salario, String cargo, String turno, loja Loja) {
        
        this.setNome(nome);
        this.setCpf(cpf);
        this.setDataNasc(data);
        this.setSalario(salario);
        this.setCargo(cargo);
        this.setTurno(turno);
        this.setLoja(Loja);
    }
   
    public void localizaFunc(String cpf, Funcionario f[]){
        
        boolean conf = false;
        
        for(int i = 0; i < f.length; i++){
            if(f[i].cpf.equals(cpf)){
                conf = true;
                System.out.println("Funcionário encontrado");
            }
        }
        
        if(!conf)
            System.out.println("Funcionário não cadastrado");
        
    }
    
    public void localizaC(String cpf, cliente c[]){
        
        boolean conf = false;
        
        for(int i = 0; i < c.length; i++){
            if(c[i].cpf.equals(cpf)){
                conf = true;
                System.out.println("Cliente encontrado: "+c[i].getNome());
            }
        }
        
        if(!conf)
            System.out.println("Cliente não cadastrado");
        
    }
    
    void Registracompra(cliente c, menus p[], loja l){    
        
        
        Scanner teclado = new Scanner(System.in);
        System.out.print("O que deseja comprar: ");
        String s = teclado.nextLine();
        System.out.print("Qtde: ");
        int N = teclado.nextInt();
        
        for (int i = 0; i < p.length; i++) {
            
            if(p[i].getProduto().equals(s))
            {
                c.setValorGasto(N*p[i].getValor());
                System.out.println("Valor total: "+ c.getValorGasto());
            }
        }
        
        l.setVendas(c.getValorGasto()+ l.getVendas());

    }
    
    
}
