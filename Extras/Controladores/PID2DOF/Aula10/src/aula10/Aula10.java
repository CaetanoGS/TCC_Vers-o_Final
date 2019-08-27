package aula10;

public class Aula10 {

    public static void main(String[] args) {
        
        Aluno a = new Aluno("Gustavo", 22, "Masculino");    
        Professor p = new Professor("Bruno", 30, "Masculino");
        Visitante v = new Visitante("Gilberto", 50, "Masculino");
        Bolsista b = new Bolsista("Gabriel", 16,"Masculino");
        
        v.status();
        a.setMatricula(true);
        a.setMensalidade(1500.00);
        a.setCurso("Engenharia de Controle e Automação");
        a.pagarMensalidade();
        a.status();
        b.setCurso("Medicina");
        b.setMatricula(true);
        b.setMensalidade(5000.00);
        b.setBolsa(500.00);        
        b.pagarMensalidade();
        b.status();
        

        
    }
    
}
