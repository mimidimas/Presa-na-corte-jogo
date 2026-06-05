package entity;

public class GarotaHumana {
    private String nome;
    private Boolean prisioneira;
    private Inventario inventario; // implementação do inventario de acordo com o diagrama

    public GarotaHumana(String nome) {
        this.nome = nome;
        this.prisioneira = true;
        this.inventario = new Inventario();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getPrisioneira() {
        return prisioneira;
    }

    public void setPrisioneira(Boolean prisioneira) {
        this.prisioneira = prisioneira;
    }

    public Inventario getInventario() {
        return inventario;
    }

}
