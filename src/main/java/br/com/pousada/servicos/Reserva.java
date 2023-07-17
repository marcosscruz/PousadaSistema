package br.com.pousada.servicos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Classe representativa da entidade Reserva no Sistema
 *
 * @author Marcos Vinícius Santos Cruz
 * @author Filipe Fernades Costa
 */
// Q.1 - Implementar todas as classes com base no diagrama de classes criado
public class Reserva {

    private static int numReserva = 0;
    private int idReserva;
    private int statuReserva;

    private String numeroCartao;
    private double precoReserva; // referente a UMA diária
    private double precoReservaTotal; // referente aos DIAS reservados
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Quarto quarto; // quarto compõem reserva

    private ArrayList<Integer> listaQuartos = new ArrayList<>();

    /**
     * Construtor parametrizado
     *
     * @param numeroCartao define os dados do cartão de crédito do cliente
     * @param precoReserva define o valor da reserva de acordo com o cadastro
     *                     do tipo de quarto
     * @param dataInicio   define a data de reserva do quarto
     * @param dataFim      define a data final da reserva
     * @param quarto       define uma quantidade quartos por reserva
     */
    public Reserva(String numeroCartao, double precoReserva, double precoReservaTotal, LocalDate dataInicio,
            LocalDate dataFim, Quarto quarto) {
        this.numeroCartao = numeroCartao;
        this.precoReserva = precoReserva;
        this.precoReservaTotal = precoReservaTotal;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quarto = quarto;
    }

    // Construtor padrão
    public Reserva() {
    }

    // getters e setters
    public int getStatuReserva() {
        return statuReserva;
    }

    public void setStatuReserva(int statuReserva) {
        this.statuReserva = statuReserva;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public double getPrecoReserva() {
        return precoReserva;
    }

    public void setPrecoReserva(double precoReserva) {
        this.precoReserva = precoReserva;

    }

    public double getPrecoReservaTotal() {
        return precoReservaTotal;
    }

    public void setPrecoReservaTotal(double precoReservaTotal) {
        this.precoReservaTotal = precoReservaTotal;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    // =======================================

    public static int getNumReserva() {
        return numReserva;
    }

    public static void setNumReserva(int numReserva) {
        Reserva.numReserva = numReserva;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva() {
        this.idReserva = Reserva.getNumReserva();
    }

    public ArrayList<Integer> getListaQuartos() {
        return listaQuartos;
    }

    public void setListaQuartos(Integer idQuarto) {
        this.listaQuartos.add(idQuarto);
    }

    // Q.3 - Implementar o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String statu = null;
        switch (statuReserva) {
            case 1: {
                statu = "PRELIMINAR";
                break;
            }
            case 2: {
                statu = "DEFINITIVA";
                break;
            }
            case 3: {
                statu = "CANCELADA";
                break;
            }
        }
        return "[" + idReserva + "] RESERVADO EM: " + dataFim.format(dataFormatter) + " PREÇO TOTAL: "
                + precoReservaTotal + " STATU: " + statu.toUpperCase();
    }
}
