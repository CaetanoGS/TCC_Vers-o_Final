package aula10;

public class Visitante extends Pessoa{
    
    public Visitante(String nome, int idade, String sexo) {
        super(nome, idade, sexo);
    }
    
    public void status(){
        
        System.out.println("Nome: "+this.getNome());
        System.out.println("Idade: "+this.getIdade());
        System.out.println("Sexo: "+this.getSexo());        
    }
}
