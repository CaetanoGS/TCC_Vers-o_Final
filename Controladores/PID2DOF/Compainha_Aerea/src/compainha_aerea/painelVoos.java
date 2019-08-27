package compainha_aerea;

public class painelVoos implements painelInterface{
    
    private String origem;
    private String chegada;
    private String horario_chegada;
    private String horario_saida;
    private String compainha;
    private String aeroporto_chegada;
    private String aeroporto_saida;
    private boolean confirmado;
    

    public painelVoos(String origem, String chegada, String horario_chegada, String horario_saida, String compainha, String aeroporto_chegada, String aeroporto_saida, boolean confirmado) {
        this.origem = origem;
        this.chegada = chegada;
        this.horario_chegada = horario_chegada;
        this.horario_saida = horario_saida;
        this.compainha = compainha;
        this.aeroporto_chegada = aeroporto_chegada;
        this.aeroporto_saida = aeroporto_saida;
        this.confirmado = confirmado;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
    }

    public String getHorario_chegada() {
        return horario_chegada;
    }

    public void setHorario_chegada(String horario_chegada) {
        this.horario_chegada = horario_chegada;
    }

    public String getHorario_saida() {
        return horario_saida;
    }

    public void setHorario_saida(String horario_saida) {
        this.horario_saida = horario_saida;
    }

    public String getCompainha() {
        return compainha;
    }

    public void setCompainha(String compainha) {
        this.compainha = compainha;
    }

    public String getAeroporto_chegada() {
        return aeroporto_chegada;
    }

    public void setAeroporto_chegada(String aeroporto_chegada) {
        this.aeroporto_chegada = aeroporto_chegada;
    }

    public String getAeroporto_saida() {
        return aeroporto_saida;
    }

    public void setAeroporto_saida(String aeroporto_saida) {
        this.aeroporto_saida = aeroporto_saida;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
    
    @Override
    public void encontraVoo(String o, String c, painelVoos v[]){
        
        int count = 0;
        
        for(int i = 0; i < v.length; i++){
            
            if(v[i].getOrigem() == o && v[i].getChegada() == c){
                count++;
                System.out.println("Compainha: "+v[i].getCompainha());
                System.out.println("Aeroporto de saída: "+v[i].getAeroporto_saida());
                System.out.println("Aeroporto de chegada: "+ v[i].getAeroporto_chegada());
                System.out.println("Status: "+v[i].isConfirmado());
                System.out.println("Quantidade de voos para o destino: "+count);
            }      
    
        }
        
        if(count == 0)
            System.out.println("Nenhum voo localizado para o destino desejado"); 
    }
    
}
