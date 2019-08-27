package aula10;

public class Bolsista extends Aluno{
    
    private double bolsa;
    private boolean rBolsa;
    
        
    public void renovarBolsa(){
        
        this.setrBolsa(true);            
            
    }
    
    public Bolsista(String nome, int idade, String sexo) {
        super(nome, idade, sexo);
    }

    public double getBolsa() {
        return bolsa;
    }

    public void setBolsa(double bolsa) {
        this.bolsa = bolsa;
        this.setMensalidade(this.getMensalidade()-this.getBolsa());
    }

    public boolean getrBolsa() {
        return rBolsa;
    }

    public void setrBolsa(boolean rBolsa) {
        this.rBolsa = rBolsa;
    }

    @Override
    public void pagarMensalidade(){
        
        if(this.getCount()<12 && this.getMatricula())
            this.setCount(this.getCount()+1); 
        else
            System.out.println("Impossivel pagar matricula");
        
    }


    @Override
    public void status(){
        
        System.out.println("Nome: "+this.getNome());
        System.out.println("Idade: "+this.getIdade());
        System.out.println("Sexo: "+this.getSexo());
        System.out.println("Matricula: "+this.getMatricula());
        System.out.println("Curso: "+this.getCurso());
        System.out.println("Mensalidade: " + this.getMensalidade());
        System.out.println("Qtd de mensalidades pagas: " + this.getCount());
        
    }
    
}

