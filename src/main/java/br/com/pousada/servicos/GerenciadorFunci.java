package br.com.pousada.servicos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import br.com.pousada.pessoas.*;

/**
 * Classe intermediária para funcionalidades direcionadas aos Funcionários
 *
 * @author Marcos Vinícius Santos Cruz
 * @author Filipe Fernades Costa
 */
// Q.1 - Implementar todas as classes com base no diagrma de classes criado
public class GerenciadorFunci {

    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static ArrayList<Colaborador> colaboradores = new ArrayList<>();

    // Construtor padrão
    public GerenciadorFunci() {
    }

    // get e set
    public static List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public static void setColaboradores(ArrayList<Colaborador> colaboradores) {
        GerenciadorFunci.colaboradores = colaboradores;
    }

    public void addColab(Colaborador colaborador) {
        colaboradores.add(colaborador);
    }

    public void removeColab(Colaborador colaborador) {
        colaboradores.remove(colaborador);
    }

    public static List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public static void setFuncionario(Funcionario funcionario) {
        GerenciadorFunci.funcionarios = (List<Funcionario>) funcionario;
    }

    public void addFunci(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void removeFunic(Funcionario funcionario) {
        funcionarios.remove(funcionario);
    }

    // =======================================================================================
    // CRIAÇÃO DOS QUARTOS DE FORMA ESTÁTICA
    // Q.5 - O sistema deverá armazenar de forma estática os 10 quartos da pousada.
    private static Quarto quartos[] = new Quarto[10];

    /**
     * @return Lista de quartos cadastrados no Sistema
     */

    public static Quarto[] getQuartos() {
        return quartos;
    }

    /**
     * 
     * @param quartos lista de objetos Quarto representando a lista de quartos
     */
    public static void setQuartos(Quarto[] quartos) {
        GerenciadorFunci.quartos = quartos;
    }

    // =====================================================================================
    // MANIPULAÇÃO DA LISTA DE QUARTOS
    // Q.5 - O sistema deverá armazenar de forma estática os 10 quartos da pousada.

    /**
     * Função para adição de novos quartos à lista mantida pelo Sistema
     * 
     * @param quarto objeto quarto a ser adicionado à lista de quartos
     */
    public void adicionarQuarto(Quarto quarto) {
        for (int i = 0; i < 10; i++) {
            if (GerenciadorFunci.quartos[i] == null) {
                GerenciadorFunci.quartos[i] = quarto;
                break;
            }
        }
    }

    /**
     * 
     * @return
     */
    public Quarto cadastroQuarto() {
        String tipoQrt, situacao = null; // quarto até presente momento sem informação da situação

        Scanner scanner = new Scanner(System.in);

        System.out.println("Número do quarto: ");
        int numeroQrt = scanner.nextInt();

        boolean condicao = false;
        do {
            System.out.printf("Tipo do quarto (1. Luxo ou 2. Comum): ");
            int tipo = scanner.nextInt();

            if (tipo == 1) {
                tipoQrt = "Luxo";
                situacao = "Reservado";
                Quarto quarto = new Quarto(numeroQrt, situacao, tipoQrt);
                adicionarQuarto(quarto);
                condicao = true;
                return quarto;
            } else if (tipo == 2) {
                tipoQrt = "Comum";
                situacao = "Reservado";
                Quarto quarto = new Quarto(numeroQrt, situacao, tipoQrt);
                adicionarQuarto(quarto);
                condicao = true;
                return quarto;
            } else {
                System.out.print("Tipo de quarto inválido. Tente novamente!");
            }
        } while (condicao == false);

        return null;
    }

    // ============================================================================================================================================

    /**
     * Q.9 - As reservas e os clientes devem ser salvas de forma dinâmica no
     * sistema.
     */
    private static ArrayList<Hospede> listaHospedes = new ArrayList<>();

    /**
     * @return lista de hóspedes cadastrados no Sistema
     */
    public static ArrayList<Hospede> getListaHospedes() {
        return listaHospedes;
    }

    /**
     * Q.11 - Criar duas variáveis de classe (static) que irão armazenar quantas
     * instâncias foram criadas dos tipos Cliente dentro da classe Sistema
     * usando duas soluções diferentes:
     *
     * a. Uma delas utilizando o enfoque de encapsulamento de acordo com a
     * engenharia de software (atributo private static e métodos get e set);
     *
     * b. Na segunda estratégia, implementar usando o controle de acesso do tipo
     * protect;
     */
    private static int hospedeCountPrivate;
    protected static int hospedeCountProtected;

    /**
     * Enfoque no encapsulamento
     * 
     * @return quantidade de hóspedes/clientes cadastrados no Sistema
     */
    public static int getHospedeCountPrivate() {
        return hospedeCountPrivate;
    }

    /**
     * @param hospedes define a lista de hóspedes/clientes para a base do Sistema
     */
    public static void setHospedes(ArrayList<Hospede> hospedes) {
        GerenciadorFunci.listaHospedes = hospedes;
    }

    /**
     * Implementando usando o controle de acesso do tipo protected
     * 
     * @return quantidade de hóspedes/clientes cadastrados no Sistema
     */
    public static int getHospedeCountProtected() {
        return hospedeCountProtected;
    }

    /**
     * Incremento da quantidade de hóspedes/clientes cadastrados no Sistema
     */
    public static void setHospedeCountPrivate() {
        int qnt = 0;
        for (Hospede h1 : GerenciadorFunci.getListaHospedes()) {
            qnt++;
        }
        GerenciadorFunci.hospedeCountPrivate = qnt;
    }

    public static void setHospedeCountProtected() {
        int qnt = 0;
        for (Hospede h1 : GerenciadorFunci.getListaHospedes()) {
            qnt++;
        }
        hospedeCountProtected = qnt;
    }

    // ===================================================================================================================================
    // MANIPULAÇÃO DE HÓSPEDES/CLIENTES
    /**
     * Q.7 - Cadastrar, alterar ou excluir clientes;
     */
    public void cadHospede() {
        String nomeHospede, sobrenomeHospede, CPF, enderecoHospede, telefoneHospede, emailHospde;

        Scanner scanner = new Scanner(System.in);

        // entrada de dados
        System.out.printf("Nome: ");
        nomeHospede = scanner.nextLine();
        System.out.printf("Sobrenome: ");
        sobrenomeHospede = scanner.nextLine();

        do {
            System.out.printf("CPF: ");
            CPF = scanner.nextLine();
            if (GerenciadorAdm.validaCPF(CPF) == false || consultaColab(CPF) != null) {
                System.out.println("Número de CPF inválido ou já cadastrado. Tente novamente!");
            }
        } while (GerenciadorAdm.validaCPF(CPF) == false || consultaColab(CPF) != null);

        System.out.println("Endereço: ");
        enderecoHospede = scanner.nextLine();
        System.out.println("Telefone: ");
        telefoneHospede = scanner.nextLine();
        System.out.println("E-mail: ");
        emailHospde = scanner.nextLine();

        Hospede h1 = new Hospede(nomeHospede, sobrenomeHospede, CPF, enderecoHospede, telefoneHospede, emailHospde);
        GerenciadorFunci.listaHospedes.add(h1);
        System.out.println("Cadastro realizado com sucesso!");

        setHospedeCountPrivate(); // manipulação com membro devidamente encapsulado
        hospedeCountProtected++; // permite acesso direto à variável
    }

    /**
     * Função para a exclusão de hóspedes cadastrados no Sistema
     * Q.7 - Cadastrar, alterar ou excluir clientes;
     * 
     * @param cpf chave de busca na base de dados de hóspedes cadastrados no Sistema
     */
    public void excluirHospede(String cpf) {
        String cpfHospede = cpf;

        for (Hospede hospede : GerenciadorFunci.getListaHospedes()) {
            if (cpfHospede.equals(hospede.getCPF())) {
                GerenciadorFunci.getListaHospedes().remove(hospede);
                break;
            }
        }
        System.out.println("Alteração feita com sucesso!");
    }

    /**
     * Q.7 - Cadastrar, alterar ou excluir clientes;
     * 
     * @param cpf chave de busca na base de dados de hóspedes cadastrados no Sistema
     */
    public void alterarHospede(String cpf) {
        Scanner inputSwitch = new Scanner(System.in);
        boolean menuAnaterior = false;
        String cpfHospede = cpf;

        do {
            if (consultaHospede(cpfHospede) != null) {
                Hospede altHospede = consultaHospede(cpfHospede);
                System.out.println("Dados Hóspede");
                System.out.println(altHospede + "\n-------------------------------------------\n");
                System.out.println(
                        "Escolha uma opção: \n\t1. Alterar nome \n\t2. Alterar CPF \n\t3. Alterar Endereço \n\t4. Alterar telefone \n\t5. Alterar e-mail \n\t6. Fechar");
                int opcao = inputSwitch.nextInt();
                Scanner inputDados = new Scanner(System.in);

                switch (opcao) {
                    case 1: {
                        inputDados = new Scanner(System.in);
                        System.out.printf("Novo nome: ");
                        String dado = inputDados.nextLine();
                        altHospede.setNomePessoa(dado);

                        System.out.printf("Novo sobrenome: ");
                        dado = inputDados.nextLine();
                        altHospede.setSobrenomePessoa(dado);
                        System.out.println("Alteração feita com sucesso!");
                        break;
                    }
                    case 2: {
                        String dado;
                        do {
                            System.out.printf("Novo CPF: ");
                            dado = inputDados.nextLine();
                            if (GerenciadorAdm.validaCPF(dado) == false || consultaHospede(cpf) != null) {
                                System.out.println("CPF inválido ou já cadastrado. Tente outra vez!");
                            }
                        } while (GerenciadorAdm.validaCPF(dado) == false || consultaHospede(cpf) != null);
                        altHospede.setCPF(dado);
                        System.out.println("CPF alterado com sucesso!");
                        cpfHospede = dado;
                        break;
                    }
                    case 3: {
                        System.out.printf("Novo endereço: ");
                        String dado = inputDados.nextLine();
                        altHospede.setEnderecoHospede(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }
                    case 4: {
                        System.out.printf("Novo telefone: ");
                        String dado = inputDados.nextLine();
                        altHospede.setTelefoneHospede(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }
                    case 5: {
                        System.out.printf("Novo e-mail: ");
                        String dado = inputDados.nextLine();
                        altHospede.setEmailHospede(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }
                    case 6: {
                        menuAnaterior = true;
                        break;
                    }
                    default: {
                        System.out.println("Opção Inválida!");
                    }
                }
            } else {
                System.out.println("CPF Inválido!");
            }
        } while (menuAnaterior == false);
    }

    public void imprimirHospedes() {
        // Q.13 - Implementar a interface Comparator para as classes Cliente e Reserva e
        // fazer comparações por diferentes atributos.
        HospedeComparator comparator = new HospedeComparator();
        Collections.sort(GerenciadorFunci.getListaHospedes(), comparator);
        for (Hospede hospede : GerenciadorFunci.getListaHospedes()) {
            if (hospede != null) {
                System.out.println(hospede);
            }
        }
    }

    /**
     * Função para exibição de dados de um Hóspede/Cliente específico
     * 
     * @param cpf chave de busac do hóspede/cliente cadastrado no sistema
     */
    public void imprimirHospede(String cpf) {
        if (consultaHospede(cpf) != null) {
            System.out.println(consultaHospede(cpf));
        }
    }

    // ======================================================================================================================
    // MANIPULAÇÃO DA LISTA DE RESERVAS

    /**
     * Q.9 - As reservas e os clientes devem ser salvas de forma dinâmica no
     * sistema.
     */
    private static ArrayList<Reserva> listaReservas = new ArrayList<>();

    /**
     * @return lista de reservas cadastradas no Sistema
     */
    public static ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    /**
     * @param listaReservas define a lista de reservas a ser casdastrada no Sistema
     */
    private static void setListaReservas(ArrayList<Reserva> listaReservas) {
        GerenciadorFunci.listaReservas = listaReservas;
    }

    // RESERVAS
    private static ArrayList<String> extratoReservas = new ArrayList<>();

    /**
     * Função para cadastro de novas reservas a um hóspede
     */
    public void cadastroReserva() {
        System.out.printf("CPF do hóspede: ");
        Scanner input = new Scanner(System.in);
        String cpfHospede = input.nextLine();
        Hospede h = GerenciadorFunci.consultaHospede(cpfHospede);

        int numReservas = 0;

        if (h != null) {
            Reserva novaReserva = new Reserva();

            for (Hospede hospede : GerenciadorAdm.getListaHospedes()) {
                for (Reserva re : hospede.getReservasHospede()) {
                    numReservas += 1;
                }
            }
            Reserva.setNumReserva(numReservas);
            novaReserva.setIdReserva();

            boolean quartoCadastrado = false;

            do {
                int switchCad;
                System.out.println(
                        "Escolha uma opção: \n\t1. Adcionar reserva \n\t2. Lista de reservas \n\t3. Finalizar");
                switchCad = input.nextInt();

                switch (switchCad) {
                    case 1: {
                        System.out.printf("ID da reserva: ");
                        Integer idQuarto = input.nextInt();

                        for (Quarto quarto : GerenciadorFunci.getQuartos()) {
                            if (quarto != null) {
                                if (quarto.getIdQuarto() == idQuarto) {
                                    novaReserva.setListaQuartos(idQuarto);
                                    quartoCadastrado = true;
                                    break;
                                }
                            }
                        }

                        boolean parar = false;
                        do {
                            cadHospede();
                            Quarto novoQuarto = cadastroQuarto();

                            System.out.printf("Data Início (dd/MM/yyyy): ");
                            String inicioString = input.nextLine();

                            System.out.printf("Data Fim (dd/MM/yyyy): ");
                            String fimString = input.nextLine();

                            /**
                             * Fornece métodos para formatar uma data/hora em uma determinada
                             * representação de string e também para analisar uma string em um objeto de
                             * data/hora.
                             */
                            LocalDate dataInicio = LocalDate.parse(inicioString,
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            LocalDate dataFim = LocalDate.parse(fimString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                            // calcula o número de dias da reserva
                            long numeroDias = ChronoUnit.DAYS.between(dataInicio, dataFim);

                            System.out.printf("Número do cartão de crédito: ");
                            String creditCard = input.nextLine();
                            novaReserva.setNumeroCartao(creditCard);

                            double precoDiaria = 0.0, precoTotal = 0.0;

                            final double precoLuxo = 300.00;
                            final double precoComum = 150.00;

                            if (novoQuarto.getTipoQuarto().equals("Luxo")) {
                                precoDiaria = precoLuxo;
                                precoTotal = numeroDias * precoLuxo;
                            } else if (novoQuarto.getTipoQuarto().equals("Comum")) {
                                precoDiaria = precoComum;
                                precoTotal = numeroDias * precoComum;
                            }

                            novaReserva.setPrecoReserva(precoDiaria);
                            novaReserva.setPrecoReservaTotal(precoTotal);

                            parar = true;
                        } while (parar == false);
                        break;
                    }
                    case 2: {
                        for (Quarto reserva : GerenciadorFunci.getQuartos()) {
                            if (reserva != null) {
                                System.out.println("[" + reserva.getIdQuarto() + "]" + "  "
                                        + reserva.getTipoQuarto().toUpperCase());
                            }
                        }
                        System.out.println("\n");
                        quartoCadastrado = false;
                        break;
                    }
                    case 3: {
                        System.out.printf("Processo finalizado com sucesso!");
                        quartoCadastrado = true;
                        break;
                    }
                    default: {
                        System.out.println("Opção inválida! Tente novamente.");

                    }
                }
            } while (quartoCadastrado == false);

            h.setReservasHospede(novaReserva);
            novaReserva.setStatuReserva(2);

            System.out.printf(
                    "EXTRATO: \n-------------------------\n" + h.getCPF() + " " + h.getNomePessoa().toUpperCase() + " "
                            + h.getSobrenomePessoa().toUpperCase() + "\n" + novaReserva);

        } else {
            System.out.println("CPF inválido! Tente novamente.");
        }
    }

    /**
     * função de acesso as opções de alteração de reservas
     * 
     * @param idReserva chave de busca de reservas na lista de reservas associado a
     *                  um hóspede
     * @param hospede   hóspede o qual possui uma lista de reservas associada e ele
     */
    public void modificarReserva(int idReserva, Hospede hospede) {
        Scanner input = new Scanner(System.in);
        boolean sairMneu = false;
        do {
            if (consultaReserva(idReserva, hospede) != null) {
                Reserva modficaReserva = consultaReserva(idReserva, hospede);
                System.out.println("Dados da Reserva: \n--------------------------\n");
                System.out.println("ID: " + modficaReserva.getIdReserva() + " Data: " + modficaReserva.getDataInicio()
                        + " Valor Diária: " + modficaReserva.getPrecoReserva() + "\n--------------------------");
                System.out.println(
                        "\nEscolaha uma opção: \n\t1. Alterar data de início \n\t2. Alterar data final \n\t3. Alterar statu reserva \n\t4. Fechar");
                int opcao = input.nextInt();
                switch (opcao) {
                    case 1: {
                        System.out.println("Entre com o a nova data de início (dd/MM/yyyy): ");
                        String novaDataInicioStr = input.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate novaDataInicio = LocalDate.parse(novaDataInicioStr, formatter);
                        modficaReserva.setDataInicio(novaDataInicio);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }
                    case 2: {
                        System.out.println("Entre com o a nova data final (dd/MM/yyyy): ");
                        String novaDataFimStr = input.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate novaDataFim = LocalDate.parse(novaDataFimStr, formatter);
                        modficaReserva.setDataInicio(novaDataFim);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }
                    case 3: {
                        System.out.println(
                                "Insira o código do sattu: \n\t1. Preliminar \n\t2.Definitiva \n\t3.Cancelada");
                        int novoStatu = input.nextInt();
                        modficaReserva.setStatuReserva(novoStatu);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }
                    case 4: {
                        System.out.println("Finalizando...");
                        sairMneu = true;
                        break;
                    }
                    default: {
                        System.out.println("Opção Inválida!");
                    }
                }
            }
        } while (sairMneu == false);
    }

    /**
     * Função para exclusão de reservas do sistema
     */
    public void excluirReserva() {
        System.out.println("CPF: ");
        Scanner input = new Scanner(System.in);
        String cpf = input.nextLine();
        Hospede hospede = GerenciadorAdm.consultaHospede(cpf);
        if (hospede != null) {
            System.out.println("RESERVAS CADASTRADAS: \n----------------------------");
            for (int i = 0; i < hospede.getReservasHospede().size(); i++) {
                LocalDate dataReserva = hospede.getReservasHospede().get(i).getDataInicio();
                System.out.println("ID: " + hospede.getReservasHospede().get(i).getIdReserva() + " DATA: "
                        + hospede.getReservasHospede().get(i).getDataInicio());
            }

            System.out.println("ID da Reserva: ");
            int idReservaDel = input.nextInt();

            for (int r = 0; r < hospede.getReservasHospede().size(); r++) {
                if (hospede.getReservasHospede().get(r).getIdReserva() == idReservaDel) {
                    hospede.getReservasHospede().remove(r);
                    System.out.println("Reserva removida com sucesso!");
                }
            }
        }
    }

    /**
     * função para acesso as opcções de exibição de reservas realizadas no sistema
     * fornece as opções de listagem por intervalo de datas
     */
    public void listarReservas() {
        boolean encerrar = false;
        do {
            System.out.println("Escolha uma Opção: \n\t1. Pesquisar por uma data \n\t2. Voltar");
            Scanner input = new Scanner(System.in);
            int opcao = input.nextInt();
            switch (opcao) {
                case 1: {
                    String diaMin = null, diaMax = null, mesMin = null, mesMax = null, anoMin = null, anoMax = null;
                    boolean validaMin = false, validaMax = false;
                    while (validaMax == false || validaMin == false) {
                        System.out.println("DE\n------------------------------");

                        input = new Scanner(System.in);
                        System.out.println("Dia (ex.: 02): ");
                        diaMin = input.nextLine();
                        if (diaMin.length() == 1) {
                            diaMin = "0" + diaMin;
                        }

                        System.out.println("Mês (ex.: 04): ");
                        mesMin = input.nextLine();
                        if (mesMin.length() == 1) {
                            mesMin = "0" + mesMin;
                        }

                        System.out.println("Ano (ex.: 2002): ");
                        anoMin = input.nextLine();

                        System.out.println("ATÉ\n------------------------------ ");

                        System.out.println("Dia (ex.: 02): ");
                        diaMax = input.nextLine();
                        if (diaMax.length() == 1) {
                            diaMax = "0" + diaMax;
                        }

                        System.out.println("Mês (ex.: 01): ");
                        mesMax = input.nextLine();
                        if (mesMax.length() == 1) {
                            mesMax = "0" + mesMin;
                        }

                        System.out.println("Ano (ex.: 2023): ");
                        anoMax = input.nextLine();

                        String dataMin = anoMin + mesMin + diaMin;
                        String dataMax = anoMax + mesMax + diaMax;

                        validaMin = isDateValid(dataMin);
                        validaMax = isDateValid(dataMax);
                    }
                    LocalDate dateMin = LocalDate.parse(anoMin + "-" + mesMin + "-" + diaMin);
                    LocalDate dateMax = LocalDate.parse(anoMax + "-" + mesMax + "-" + diaMax);
                    System.out.println("Reservas Cadastradas\n------------------------------");
                    for (int i = 0; i < GerenciadorAdm.getListaHospedes().size(); i++) {
                        for (int j = 0; j < GerenciadorAdm.getListaHospedes().size(); j++) {
                            LocalDate dataReserva = GerenciadorAdm.getListaHospedes().get(i).getReservasHospede().get(j)
                                    .getDataFim();
                            LocalDate dataPrint = GerenciadorAdm.getListaHospedes().get(i).getReservasHospede().get(j)
                                    .getDataFim();
                            if ((dataReserva.isEqual(dateMin) || (dataReserva.isAfter(dateMin)))
                                    && ((dataReserva.isEqual(dateMax)) || (dataReserva.isBefore(dateMax)))) {
                                System.out.println("Hóspede: "
                                        + GerenciadorAdm.getListaHospedes().get(i).getNomePessoa().toUpperCase() + " "
                                        + GerenciadorAdm.getListaHospedes().get(i).getSobrenomePessoa().toUpperCase()
                                        + "\nENDEREÇO: " + GerenciadorAdm.getListaHospedes().get(i).getEnderecoHospede()
                                        + "\nTELEONE: " + GerenciadorAdm.getListaHospedes().get(i).getTelefoneHospede()
                                        + "\nID RESERVA: "
                                        + GerenciadorAdm.getListaHospedes().get(i).getReservasHospede().get(j)
                                                .getIdReserva()
                                        + "\n------------------------------");
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    encerrar = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        } while (encerrar == false);
    }

    /**
     * Q.10 - Cada reserva efetuada vai gerar um extrato que deverá ser impresso e
     * salvo
     * junto com a informação do cliente que fez a reserva.
     * 
     * @return lista de extratos de todas as reservas cadastradas
     */
    public static ArrayList<String> getExtratosReservas() {
        return extratoReservas;
    }

    /**
     * Q.10 - Cada reserva efetuada vai gerar um extrato que deverá ser impresso e
     * salvo junto com a informação do cliente que fez a reserva.
     * 
     * @param extratosReservas lista de extratos
     */
    public static void setExtratosReservas(ArrayList<String> extratosReservas) {
        GerenciadorFunci.extratoReservas = extratosReservas;
    }

    public ArrayList<String> extratosReservas() {
        ArrayList<String> extratos = new ArrayList<>();
        for (Hospede hospede : GerenciadorAdm.getListaHospedes()) {
            for (Reserva reserva : hospede.getReservasHospede()) {
                extratos.add(hospede.getCPF() + " " + hospede.getNomePessoa().toUpperCase() + " ["
                        + reserva.getIdReserva() + "]  Valor diária: R$" + reserva.getPrecoReserva()
                        + " Data de início: " + reserva.getDataInicio());
            }
        }
        GerenciadorAdm.setExtratosReservas(extratos);
        return GerenciadorAdm.getExtratosReservas();
    }

    /**
     * Q.8 - Verificar e imprimir dados das reservas e dos clientes;
     */
    public void imprimirDadosReservaHospedes() {
        boolean finalizar = false;

        do {
            System.out.println("Escolha uma opção: \n\t1. Exibir reservas \n\t2. Finalizar");
            Scanner input = new Scanner(System.in);
            int caso = input.nextInt();

            switch (caso) {
                case 1: {
                    System.out.println("===== Dados =====");

                    for (Hospede hospede : GerenciadorAdm.getListaHospedes()) {
                        System.out.println(
                                "Nome: " + hospede.getNomePessoa().toUpperCase() + " CPF: " + hospede.getCPF());

                        System.out.println("Reservas:");
                        for (Reserva reserva : hospede.getReservasHospede()) {
                            System.out.println("Número reserva: " + reserva.getIdReserva() + " Data de Entrada: "
                                    + reserva.getDataInicio() + " Data de Saída: " + reserva.getDataFim()
                                    + " Preço total: " + reserva.getPrecoReservaTotal());
                        }
                        System.out.println("=====================");
                    }
                    break;
                }
                case 2: {
                    finalizar = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida! Tente novamente.");
                }
            }
        } while (finalizar == false);
    }

    /**
     * Função padrão de consulta a uma Reserva associado a um Hóspede
     * 
     * @param idReserva chave de busca de reserva
     * @param hospede   Hóspede associado a reserva
     * @return objeto do tipo Reserva se a chave satisfazer as condições
     */
    public Reserva consultaReserva(int idReserva, Hospede hospede) {
        Reserva reservaConsultar = new Reserva();
        reservaConsultar = null;

        for (int i = 0; i < hospede.getReservasHospede().size(); i++) {
            if (hospede.getReservasHospede().get(i).getIdReserva() == idReserva) {
                reservaConsultar = hospede.getReservasHospede().get(i);
            }
        }
        return reservaConsultar;
    }

    // ======================================================================================================================
    // ÁREA DE CONSULTA

    /**
     * Função padrão para consulta a um objeto do tipo Colaborador
     * 
     * @param cpf chave de comparação entre objetos do tipo Colaborador
     * @return objeto do tipo colaborador caso a chave esteja cadastrada no sistema
     */
    public Colaborador consultaColab(String cpf) {
        String cpfColab = cpf;
        Colaborador attColab = null;

        for (Colaborador colab : this.getColaboradores()) {
            if (colab != null) {
                if (cpfColab.equals(colab.getCPF())) {
                    attColab = colab;
                    break;
                }
            }
        }
        return attColab;
    }

    /**
     * Função para consulta a um objeto do tipo Hospede na base de hospedes
     * cadastrados no Sistema
     * 
     * @param cpf chave de busca na base de dados do Sistema
     * @return objeto hospede caso a chave estaja devidamente cadastrada
     */
    public static Hospede consultaHospede(String cpf) {
        String cpfHospede = cpf;
        Hospede attHospede = new Hospede();
        attHospede = null;

        for (Hospede hospede : GerenciadorFunci.getListaHospedes()) {
            if (cpfHospede.equals(hospede.getCPF())) {
                attHospede = hospede;
                break;
            }
        }
        return attHospede;
    }

    /**
     * Função padrão para consulta a um objeto do tipo Funcionario
     * 
     * @param cpf chave de comparação entre objetos do tipo Funcinario
     * @return objeto do tipo funcionário caso a chave esteja cadastrada no sistema
     */
    public Funcionario consultaFunci(String cpf) {
        String cpfColab = cpf;
        Funcionario attFunci = null;

        for (Funcionario funcionario : this.getFuncionarios()) {
            if (funcionario != null) {
                if (cpfColab.equals(funcionario.getCPF())) {
                    attFunci = funcionario;
                    break;
                }
            }
        }
        return attFunci;
    }

    /**
     * função para identificação de datas válidas
     * 
     * @param strDate recebe uma data em formato String para validação
     * @return estado de validação: true ou false
     */
    public static boolean isDateValid(String strDate) {
        // Formatter de data
        String dateFormat = "uuuuMMdd";
        // Resolver Strict garante que seja feito dentro dos valores possíveis
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true; // Se for válida
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Q.3 - sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "Gerenciador Funcionário.";
    }
}
