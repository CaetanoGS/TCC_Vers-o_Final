package aula14;

public class Pessoa {
    
    protected String nome;
    protected int idade;
    protected String sexo;
    protected String experiencia;
    
    protected void ganharExp(String experiencia){
        
        this.setExperiencia(experiencia);        
    }

    public Pessoa(String nome, int idade, String sexo, String experiencia) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.experiencia = experiencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
    
    
    
}
