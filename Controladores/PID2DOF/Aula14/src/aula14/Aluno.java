package aula14;

public class Aluno extends Pessoa{
    
    private String login;
    private int totalAssistido;
    
    public Aluno(String nome, int idade, String sexo, String experiencia) {
        super(nome, idade, sexo, experiencia);
    }
    
    public void statusA(){
        System.out.println("Nome: "+this.getNome());
        System.out.println("Idade: "+this.getIdade());
        System.out.println("Sexo: "+this.getSexo());
        System.out.println("Experiência: "+this.getExperiencia());
        System.out.println("Login: "+this.getLogin());
        System.out.println("Total Assistido: "+this.getTotalAssistido());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getTotalAssistido() {
        return totalAssistido;
    }

    public void setTotalAssistido(int totalAssistido) {
        this.totalAssistido = totalAssistido;
    }
    
    public void viumaisum(){
        this.setTotalAssistido(this.getTotalAssistido()+1);
    }
    
}
