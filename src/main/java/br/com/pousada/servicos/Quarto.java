package br.com.pousada.servicos;

import java.time.LocalDate;

/**
 *
 * Classe representativa da entidade Quarto no Sistema
 *
 * @author Marcos Vinícius Santos Cruz
 * @author Filipe Fernades Costa
 */
// Q.1 - Implementar todas as classes com base no diagrama de classes criado
// Q.5 - O sistema deverá armazenar de forma estática os 10 quartos da pousada.
public class Quarto {

    private static int idQuarto = 0;

    private int numeroQuarto;
    private String statusQuarto; // IMPORTANTE: mudar a tipagem do variável
    private String tipoQuarto; // luxo || comum
    
    /**
     * Construtor parametrizado
     *
     * @param numeroQuarto define o número referente ao quarto
     * @param statusQuarto define a situação em que o quarto se encontra
     * @param tipoQuarto   define qual o tipo do quarto (luxo ou comum)
     */
    public Quarto(int numeroQuarto, String statusQuarto, String tipoQuarto) {
        this.numeroQuarto = numeroQuarto;
        this.statusQuarto = statusQuarto;
        this.tipoQuarto = tipoQuarto;
    }

    // Construtor padrão
    public Quarto() {
    }

    // getters e setters
    public static int getIdQuarto() {
        return idQuarto;
    }

    public static void setIdQuarto(int idQuarto) {
        Quarto.idQuarto = idQuarto;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto() {
        this.numeroQuarto = Quarto.getIdQuarto();
    }

    public String getStatusQuarto() {
        return statusQuarto;
    }

    public void setStatusQuarto(String statusQuarto) {
        this.statusQuarto = statusQuarto;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    // Q.3 - sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return getNumeroQuarto() + " " + getTipoQuarto();
    }
}
