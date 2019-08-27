package pontomix;

public class fornecedor {
    
    private String cnpj;
    private double valorC;

    public fornecedor() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public double getValorC() {
        return valorC;
    }

    public void setValorC(double valorC) {
        this.valorC = valorC;
    }
    
        public void cadastraForn(String cnpj) {
        
        this.setCnpj(cnpj);

    }   
}
