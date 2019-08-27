package aula07;

public class Lutador implements Perfil{
    
    private String nome;
    private String nacionalidade;
    private double altura;
    private double peso;
    private String categoria;
    private int vitorias;
    private int derrotas;
    private int empates;

    public Lutador(String nome, String nacionalidade, double altura, double peso,int vitorias, int derrotas, int empates) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.altura = altura;
        this.setPeso(peso);
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
        if (this.peso < 50)
            this.setCategoria("Invalido");
        else if (this.peso < 70)
            this.setCategoria("Leve");
        else if (this.peso < 80)
            this.setCategoria("Medio");
        else if (this.peso < 120)
            this.setCategoria("Pesado");
        else
            this.setCategoria("Invalido");
        
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    @Override
    public void apresentar() {
        
        System.out.println("------ APRESENTAÇÃO LUTADOR ------");
        System.out.println("Nome: " + this.getNome());
        System.out.println("Nacionalidade: " + this.getNacionalidade());
        System.out.println("Altura: " + this.getAltura());
        System.out.println("Peso: " + this.getPeso());
        System.out.print("V - "+this.getVitorias());
        System.out.print(" E - "+this.getEmpates());
        System.out.println(" D - "+this.getDerrotas());
        
        
    }

    @Override
    public void status() {
        System.out.println("----------------------------------");
        System.out.println("Peso " + this.getCategoria());
        //System.out.print("V - "+this.getVitorias());
        //System.out.print(" E - "+this.getEmpates());
        //System.out.println(" D - "+this.getDerrotas());
        
        
        
    }

    @Override
    public void ganharLuta() {
        
        this.setVitorias(this.getVitorias()+1);
        
    }

    @Override
    public void perderLuta() {
        
        this.setVitorias(this.getDerrotas()+1);
        
    }

    @Override
    public void empatarLuta() {
        
        this.setVitorias(this.getEmpates()+1);        
        
    }
        
}
