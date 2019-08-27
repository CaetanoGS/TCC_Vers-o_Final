package derivada;

public class controle {
    
    private double u;
    private double I;
    private double D;
    private double error;
    private double estado;

    public controle() {
    }

    public double getU() {
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getI() {
        return I;
    }

    public void setI(double I) {
        this.I = I;
    }

    public double getD() {
        return D;
    }

    public void setD(double D) {
        this.D = D;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getEstado() {
        return estado;
    }

    public void setEstado(double estado) {
        this.estado = estado;
    }
    
    
    
    public void controle(double output[], double ref, double Ki, double K, double sampleTime){
        
        for(int i = 0; i < output.length; i++){
            
            this.I += (-(ref - output[i]))*sampleTime; // Integral
            
            if (i<=1)
                this.estado = output[i];
            
            else{
                
                this.D = (output[i]-output[i-1])/sampleTime;
                this.setEstado(this.getD());
            }       

            this.setU(Ki*this.getI() + K*this.getEstado());
            
            if(this.getU()>255)
                this.setU(255);
            else if(this.getU()<0)
                this.setU(0);
            else
                this.setU(this.getU());

            System.out.println("Controle: "+this.getU());
    
        }
    }
}

