package pontomix;

import java.util.Scanner;

public class PontoMix {

    public static void main(String[] args) {
        
        cliente c[] = new cliente[5];
        Funcionario f[] = new Funcionario[5];
        menus p[] = new menus[2];
        loja l[] = new loja[2];
        
        
        for(int i = 0; i < c.length; i++)
            c[i] = new cliente();
        for(int i = 0; i < f.length; i++)
            f[i] = new Funcionario();
        
        for(int i = 0; i < c.length;i++){
            switch(i){
                case 0: c[i].cadastra("Gustavo", "390.748.138-01", "12/10/1996"); break;
                case 1: c[i].cadastra("Joao", "380.748.138-01", "13/10/1996"); break;
                case 2: c[i].cadastra("Caio", "370.748.138-01", "14/10/1996"); break;
                case 3: c[i].cadastra("Victor", "360.748.138-01", "15/10/1996"); break;
                case 4: c[i].cadastra("Cadela", "350.748.138-01", "16/10/1996"); break;
                
                default: System.out.println("Cliente Inválido");
            }
                
        }
        
        l[0] = new loja();
        l[0].setHour("12:00 - 22:00");
        l[0].setNome("Centro");        
        l[1] = new loja();
        l[0].setHour("10:00 - 22:00");
        l[0].setNome("Shopping");
        
        for(int i = 0; i < f.length;i++){
            switch(i){
                case 0: f[i].cadastraFunc("Gilberto", "390.748.138-01", "14/10/1996", 3000,"Gerente","Integral",l[1]); break;
                case 1: f[i].cadastraFunc("Rosilene", "380.748.138-01", "13/10/1996", 2000,"Gerente","Matutino",l[0]); break;
                case 2: f[i].cadastraFunc("Gabriel", "370.748.138-01", "12/10/1996", 1000,"Atendente","Noturno", l[1]); break;
                case 3: f[i].cadastraFunc("Mayara", "360.748.138-01", "11/10/1996", 2000,"Atendente","Noturno", l[1]); break;
                case 4: f[i].cadastraFunc("Lavinia", "350.748.138-01", "10/10/1996", 1000,"Atendente","Matutino", l[0]); break;
                
                default: System.out.println("Funcionário Inválido");
            }
                
        }
        
       /*
        for(int i = 0;i<p.length;i++){
            
            p[i] = new menus();
            Scanner teclado = new Scanner(System.in);
            System.out.println("Digite o produto: ");
            String s = teclado.nextLine();  
            System.out.println("Digite o valor do Produto R$: ");
            double d = teclado.nextDouble();
           
        }
        */
        p[0] = new menus();
        p[0].cadastraProduto("c1", 2);
        p[1] = new menus();
        p[1].cadastraProduto("c2", 3);
                
        fornecedor fr = new fornecedor();
        fr.setCnpj("159753852");
                
        
        f[1].Registracompra(c[1], p, l[0]);
        l[0].compraIgredientes(fr, 500, l[0], f[1]);
        f[2].Registracompra(c[0], p, l[0]);
        f[1].localizaC("390.748.138-01", c);
        l[0].finantialInfo(f, l[0]);
        //System.out.println("Valor das vendas: " + l[0].getVendas());
    }
    
}
