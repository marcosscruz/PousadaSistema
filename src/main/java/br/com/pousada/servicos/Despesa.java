package br.com.pousada.servicos;

import java.time.LocalDate;

public class Despesa {
    private String nomeDespesa;
    private String descricaoDespesa;
    private double valorDespesa;
    private LocalDate data;

    // Construtor
    public Despesa(String nomeDespesa, String descricaoDespesa, double valorDespesa, LocalDate data) {
        this.nomeDespesa = nomeDespesa;
        this.descricaoDespesa = descricaoDespesa;
        this.valorDespesa = valorDespesa;
        this.data = data;
    }

    // métodos get e set
    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public void setNomeDespesa(String nomeDespesa) {
        this.nomeDespesa = nomeDespesa;
    }

    public String getDescricaoDespesa() {
        return descricaoDespesa;
    }

    public void setDescricaoDespesa(String descricaoDespesa) {
        this.descricaoDespesa = descricaoDespesa;
    }

    public double getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(double valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    // Q.3 - sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return getNomeDespesa() + " " + getDescricaoDespesa() + " " + getValorDespesa() + " " + getData();
    }
}
