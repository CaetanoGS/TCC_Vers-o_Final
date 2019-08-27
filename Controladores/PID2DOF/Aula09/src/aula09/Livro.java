package aula09;

public class Livro implements Interface_Livro {
    
    private String titulo;
    private String autor;
    private int totalPag;
    private int pagAtual;
    private boolean aberto;
    private Pessoa leitor;
    
    public void Identificacao(){
        
        System.out.println("------------------------------");
        System.out.println("Leitor: " + this.leitor.getNome());
        System.out.println("Idade: " + this.leitor.getIdade());
        System.out.println("Sexo: " + this.leitor.getSexo());
        System.out.println("Título: " + this.titulo);
        System.out.println("Autor: " + this.autor);
        System.out.println("Total de Páginas: " + this.totalPag);
        System.out.println("Página Atual: " + this.getPagAtual());
    }

    @Override
    public void abrir() {
        
        if (this.aberto == false)
            this.aberto = true;
        
    }

    @Override
    public void fechar() {
        
        if(this.aberto == true)
            this.aberto = false;
        
    }

    @Override
    public void folhear(int pag) {
        
        if ((this.getPagAtual() + pag) <= this.getTotalPag() && pag > 0)
            
            for(int i = 0; i < (this.getPagAtual() + pag); i++)
                this.setPagAtual(this.getPagAtual()+i);
        else
            System.out.println("Impossível Folhear");
                
    }

    @Override
    public void avancarP() {
        
        if(this.getPagAtual() >= 1)
            this.setPagAtual(this.getPagAtual()+1);
        
    }

    @Override
    public void voltarP() {
       
        if(this.getPagAtual() > 1)
            this.setPagAtual(this.getPagAtual()-1);
        
    }

    public Livro(String titulo, String autor, int totalPag, Pessoa leitor) {
        this.titulo = titulo;
        this.autor = autor;
        this.totalPag = totalPag;
        this.pagAtual = 1;
        this.aberto = false;
        this.leitor = leitor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getTotalPag() {
        return totalPag;
    }

    public void setTotalPag(int totalPag) {
        this.totalPag = totalPag;
    }

    public int getPagAtual() {
        return pagAtual;
    }

    public void setPagAtual(int pagAtual) {
        this.pagAtual = pagAtual;
    }

    public boolean getAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public Pessoa getLeitor() {
        return leitor;
    }

    public void setLeitor(Pessoa leitor) {
        this.leitor = leitor;
    }
    
    
}
