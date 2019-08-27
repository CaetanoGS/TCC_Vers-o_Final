package aula14;

public class Visualizacao {
    
    private Aluno espectador;
    private Video filme;

    public Visualizacao(Aluno espectador, Video filme) {
        this.espectador = espectador;
        this.filme = filme;
    }

    public void statusVZ(){
        
        System.out.println("Espectador: "+this.getEspectador().getLogin());
        System.out.println("Avaliação em %: "+this.getFilme().getAvaliacao());
        
    }
    
    public Aluno getEspectador() {
        return espectador;
    }

    public void setEspectador(Aluno espectador) {
        this.espectador = espectador;
    }

    public Video getFilme() {
        return filme;
    }

    public void setFilme(Video filme) {
        this.filme = filme;
    }
    
    public void avaliar(){
        System.out.println("Bom");
    }
    public void avaliar(int nota){
        System.out.println("Nota: "+nota);
    }
    public void avaliar(double porcentagem){
        System.out.println("Nota em %: " + porcentagem);
    }
    
}
