package pontomix;

import java.util.Scanner;

public class menus {
    
    private String produto;
    private double valor;
    
      
    public menus() {
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void cadastraProduto(String p, double v){
        
        this.setProduto(p);
        this.setValor(v);
        
    }

}
