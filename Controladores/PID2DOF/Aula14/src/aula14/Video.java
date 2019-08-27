package aula14;

public class Video implements I_Video{
    
    private String titulo;
    private double avaliacao;
    private int views;
    private int curtidas;
    private boolean reproduzindo;

    public Video(String titulo, double avaliacao, int views, int curtidas) {
        this.titulo = titulo;
        this.avaliacao = avaliacao;
        this.views = views;
        this.curtidas = curtidas;
        
    }

    public void statusV(){
        
        System.out.println("Título: " + this.getTitulo());
        System.out.println("Avaliação: "+this.getAvaliacao());
        System.out.println("View: "+this.getViews());
        System.out.println("Curtidas: "+this.getCurtidas());
                
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public boolean isReproduzindo() {
        return reproduzindo;
    }

    public void setReproduzindo(boolean reproduzindo) {
        this.reproduzindo = reproduzindo;
    }
    
    
    @Override
    public void play() {
        
        if(!this.isReproduzindo()) {
            this.setReproduzindo(true);
            System.out.println("Video em Reprodução");
        } else
            System.out.println("Video já esta em reprodução"); 
    }

    @Override
    public void pause() {
        
        if(!this.isReproduzindo())
            System.out.println("Video já está pausado");
        else
            this.setReproduzindo(false);
        
    }

    @Override
    public void like() {
        
        this.setCurtidas(this.getCurtidas()+1);
                
    }
    
    
    
}
