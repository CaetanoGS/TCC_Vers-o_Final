package aula07;

import java.util.Random;

public class Luta{
    
    private Lutador desafiado;
    private Lutador desafiante;
    private int rounds;
    private boolean aprovada;
    
    Random random = new Random();

    public void Lutar() {
        
        if (this.aprovada){
            
            this.desafiado.apresentar();
            this.desafiante.apresentar();
            
            int ganhador = random.nextInt(2);
            
            switch (ganhador){
                
                case 0: 
                    this.desafiado.setEmpates(this.desafiado.getEmpates()+1);
                    this.desafiante.setEmpates(this.desafiante.getEmpates()+1);
                    System.out.println("Empate");
                    break;
                    
                case 1:
                    this.desafiado.setVitorias(this.desafiado.getVitorias()+1);
                    this.desafiante.setDerrotas(this.desafiante.getDerrotas()+1);
                    System.out.println("Vencedor: " + this.desafiado.getNome());
                    break;
                    
                case 2:
                    this.desafiado.setDerrotas(this.desafiado.getDerrotas()+1);
                    this.desafiante.setVitorias(this.desafiante.getVitorias()+1);
                    System.out.println("Vencedor: " + this.desafiante.getNome());
                    break;
            }

        }else{
            System.out.println("Luta Inválida");
        }
        
        
    }

    public void marcarLuta(Lutador l1, Lutador l2) {
        
        if (l1.getCategoria().equals(l2.getCategoria()) && l1 != l2){
            
            this.desafiado = l1;
            this.desafiante = l2;
            this.aprovada = true;
            this.rounds = 5;
            
        }else{
            this.aprovada = false;
            this.desafiado = null;
            this.desafiante = null;
        } 
    }

    public Lutador getDesafiado() {
        return desafiado;
    }

    public void setDesafiado(Lutador desafiado) {
        this.desafiado = desafiado;
    }

    public Lutador getDesafiante() {
        return desafiante;
    }

    public void setDesafiante(Lutador desafiante) {
        this.desafiante = desafiante;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public boolean getAprovada() {
        return aprovada;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }

}
