package aula07;

public class Aula07 {

    public static void main(String[] args) {
        
        Lutador l[] = new Lutador[6];
        
        l[0] = new Lutador ("Gustavo", "Brasileiro", 1.74, 71.5, 10, 0, 1);
        l[1] = new Lutador ("Gabriel", "Brasileiro", 1.77, 65.8, 7, 3, 0);
        l[2] = new Lutador ("Amon", "Alemão", 1.87, 90.5, 5, 4, 1);
        l[3] = new Lutador ("Christoph", "Alemão", 1.80, 84.5, 10, 0, 5);
        l[4] = new Lutador ("Arthur", "Brasileiro", 1.74, 71.5, 3, 0, 1);
        l[5] = new Lutador ("Gilberto", "Brasileiro", 1.78, 82.5, 10, 5, 1);
        

        for (int i = 0; i < 6; i++){
            l[i].apresentar();
            l[i].status();
        }
        
        Luta UFC01 = new Luta();
        
        UFC01.marcarLuta(l[0], l[4]);
        UFC01.Lutar();
        
        UFC01.marcarLuta(l[0], l[2]);
        UFC01.Lutar();
            
    }
    
}
