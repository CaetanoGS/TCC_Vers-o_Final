package aula10;

public class Aluno extends Pessoa{
    
    private boolean matricula;
    private String curso;
    double mensalidade;
    private int count;
    
    public Aluno(String nome, int idade, String sexo) {
        super(nome, idade, sexo);
    }
    
    public void status(){
        
        System.out.println("Nome: "+this.getNome());
        System.out.println("Idade: "+this.getIdade());
        System.out.println("Sexo: "+this.getSexo());
        System.out.println("Matricula: "+this.getMatricula());
        System.out.println("Curso: "+this.getCurso());
        System.out.println("Mensalidade: "+this.mensalidade);
        System.out.println("Qtd de mensalidades pagas: " + this.getCount());
        
    }
    
    public void pagarMensalidade(){
        
        if(this.getCount() < 12 && this.getMatricula())
            this.setCount(this.getCount()+1);
        else
            System.out.println("Impossivel pagar matricula");
    }
    
    public void cancelarMatricula(){
        
        if (this.getMatricula())
            this.setMatricula(false);
        else
            System.out.println("Você não esta matriculado");
        
    }

    public boolean getMatricula() {
        return matricula;
    }

    public void setMatricula(boolean matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
