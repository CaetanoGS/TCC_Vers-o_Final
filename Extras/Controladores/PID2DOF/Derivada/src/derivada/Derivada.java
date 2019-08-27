package derivada;

public class Derivada {
    
    public static void main(String[] args) {
        
       double output[];
       output = new double[5];
       double ref = 35;
       
       controle C = new controle();
       
       for (int i = 0; i < output.length;i++)
           output[i] = 40-i;
       
       C.controle(output, ref, 17.37, 20.00, 1);
     
    }
    
}
