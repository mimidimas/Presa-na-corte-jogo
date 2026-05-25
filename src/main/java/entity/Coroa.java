package entity;

public class Coroa {
    private String donoNome;
    private Boolean perdida;
    private Boolean real;

    public Coroa(String donoNome, Boolean perdida, Boolean real) {
        this.donoNome = donoNome;
        this.perdida = perdida;
        this.real = real;
    }

    public String getDonoNome() {
        return donoNome;
    }

    public void setDonoNome(String donoNome) {
        this.donoNome = donoNome;
    }

    public Boolean getPerdida() {
        return perdida;
    }

    public void setPerdida(Boolean perdida) {
        this.perdida = perdida;
    }

    public Boolean getReal() {
        return real;
    }

    // não tem setReal pq a coroa não pode mudar de falsa pra real ou real pra falsa
}
