package compainha_aerea;

public class Compainha_Aerea {

    public static void main(String[] args) {
        
        painelVoos v[] = new painelVoos[5];
        
        v[0] = new painelVoos("São Paulo", "Cuiaba", "19:00", "17:00", "Azul", "Aeroporto International de Cuiabá CGB", "Guarulhos",true);
        v[1] = new painelVoos("Colonia", "São Paulo", "7:00", "17:00", "Condor", "Aeroporto International de Colonia", "Viracopos",false);
        v[2] = new painelVoos("Cuiaba", "Maceio", "20:00", "23:30", "Azul", "Aeroporto de Maceio", "Aeroporto International de Cuiabá CGB",true);
        v[3] = new painelVoos("São Paulo", "Fortaleza", "19:00", "22:00", "Gol", "Aeroporto de Fortaleza", "Guarulhos",true);
        v[4] = new painelVoos("São Paulo", "Frankfurt", "7:00", "17:00", "TAP", "Aeroporto International de Frankfurt", "Guarulhos",true);
        
        v[1].encontraVoo("Colonia", "São Paulo", v);
        
       
        
        

    }
    
}
