package aula10;

public class Professor extends Pessoa{
    
    private String especialidade;
    private double salario; 
    
    public Professor(String nome, int idade, String sexo) {
        super(nome, idade, sexo);
    }
    
    public void status(){
        
        System.out.println("Nome: "+this.getNome());
        System.out.println("Idade: "+this.getIdade());
        System.out.println("Sexo: "+this.getSexo());
        System.out.println("Especialidade: "+this.getEspecialidade());
        System.out.println("Salario: "+this.getSalario());
        
    }
    
    public void receberAumento(double aumento){
        
        this.setSalario(this.getSalario()+aumento);        
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    
    
}
