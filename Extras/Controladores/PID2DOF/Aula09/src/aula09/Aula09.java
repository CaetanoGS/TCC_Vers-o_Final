package aula09;

public class Aula09 {

    public static void main(String[] args) {
        
        Pessoa p[] = new Pessoa[2];
        
        p[0] = new Pessoa ("Gustavo", 22, "Masculino");
        p[1] = new Pessoa ("Gabriel", 16, "Masculino");
        
        Livro L[] = new Livro[2];
        
        L[0] = new Livro ("Dom Casmurro", "Machado de Assis", 300, p[0]);
        L[1] = new Livro ("Steve Jobs", "Fulano X", 500, p[1]);
        
        for(int i = 0; i < 2; i++)
            L[i].Identificacao();
        
        L[0].abrir();
        L[0].folhear(299);
        
     
        
    }
    
}
