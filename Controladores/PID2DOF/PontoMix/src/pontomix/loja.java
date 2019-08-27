package pontomix;

import pontomix.Funcionario;
import pontomix.cliente;
import pontomix.fornecedor;

public class loja {
    
    private String nome;
    private String hour;
    private double vendas;
    private double gastos;
    private double lucro;

    public loja() {
    }

    public double getVendas() {
        return vendas;
    }

    public void setVendas(double vendas) {
        this.vendas = vendas;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
    
    public void informations(){
        
        System.out.println("Localização: "+this.getNome());
        System.out.println("Horario de atendimento: "+this.getHour());   
    }
    
   public void compraIgredientes(fornecedor f, double v, loja l, Funcionario fu){
       
       if("Gerente".equals(fu.getCargo())){
           f.setValorC(v);
           l.setGastos(v);
       }else
           System.out.println("Somente para gerentes");
       
       
   }
    public void finantialInfo(Funcionario f[], loja l){
        
        double salario = 0;
        
        for(int i = 0; i<f.length;i++){
            if(l.getNome() == f[i].getLoja().getNome()){
                System.out.println("Funcionários: "+f[i].getNome());
                salario = salario + f[i].getSalario();
            }
        }
        l.setLucro(l.getVendas()-l.getGastos());
        System.out.println("Lucros da Loja: "+l.getLucro());
    }
    
    
    
    
    
}
