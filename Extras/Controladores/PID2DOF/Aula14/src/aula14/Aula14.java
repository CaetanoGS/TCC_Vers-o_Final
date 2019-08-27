package aula14;

public class Aula14 {

    public static void main(String[] args) {
        
        Video v[] = new Video[2];
        Aluno a[] = new Aluno[2];
        Visualizacao vz[] = new Visualizacao[2];
        
        v[0] = new Video("Aula01", 85, 150000, 2500);
        v[1] = new Video("Aula02", 90, 200000, 5000);
        
        a[0] = new Aluno("Gustavo", 22, "Masculino", "Iniciante");
        a[1] = new Aluno("Mayara", 22, "Feminino", "Intermediário");
        
        vz[0] = new Visualizacao(a[0],v[1]);
        vz[1] = new Visualizacao(a[1],v[0]);
        
        a[0].setLogin("CaetanooG");
        a[0].setTotalAssistido(15);
        a[1].setLogin("MayJG");
        a[1].setTotalAssistido(5);
        
        for(int i = 0; i < 2; i++){
            a[i].statusA();
        }
        
        v[0].setReproduzindo(true);
        v[1].setReproduzindo(false);
        
        for(int i = 0; i < 2; i++){
            vz[i].statusVZ();
        }
        
        
        v[0].like();
        
        for(int i = 0; i < 2; i++){
            v[i].statusV();
        }
    }
    
}
