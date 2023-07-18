package br.com.pousada.sistemas;

import java.io.IOException;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

import br.com.pousada.pessoas.*;
import br.com.pousada.servicos.*;

/**
 * Classe principal para o Sistema da Pousada contendo o método main()
 *
 * @author Marcos Vinícius Santos Cruz
 * @author Filipe Fernades Costa
 * @version 1.02.2
 */
// Q.1 - Implementar todas as classes com base no diagrama de classes criado
public class Sistema {

    /**
     * Q.12 - Criar um método de classe para classe Sistema que deverá retornar
     * quantas instâncias foram criadas dos tipos Cliente e Reserva;
     */
    private static int contadorHospedes = 0;
    private static int contadorReservas = 0;

    /**
     * @return número de instâncias de Hospede/Cliente
     */
    public static int getContadorHospedes() {
        return contadorHospedes;
    }

    /**
     * @return número de instâncias de Reserva
     */
    public static int getContadorReservas() {
        return contadorReservas;
    }

    /**
     * Função responsável por contar o número de instâncias de Hospede/Cliente
     */
    public static void setContadorHospedes() {
        for (Hospede hospede : GerenciadorAdm.getListaHospedes()) {
            if (hospede != null) {
                Sistema.contadorHospedes++;
            }
        }
    }

    /**
     * Função responsável por contar o número de instâncias de Reserva
     */
    public static void setContadorReservas() {
        for (Reserva reserva : GerenciadorFunci.getListaReservas()) {
            if (reserva != null) {
                Sistema.contadorReservas++;
            }
        }
    }

    // ==============================================================================================

    /**
     *
     * @param args
     * @throws IOException a declaração throws IOException é usada para indicar
     *                     que o método pode lançar uma exceção relacionada à
     *                     operação de entrada e saída (IO), como erros ao ler ou
     *                     escrever arquivos.
     */
    public static void main(String[] args) throws IOException {
        // definindo a configuração regional padrão para o programa
        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);

        /*
         * TESTES DE FUNÇÕES
         * As chamadas a seguir representam testes de funções conforme as questões que
         * poderiam ser identificadas pelo uso de funções.
         * 
         * Ainda, tenha em mente que essas funções foram pensadas para serem executadas
         * enquanto um sistema completo e cujos diferentes componentes interagem entre
         * si. Assim, as chamadas individuais das funções podem não apresentar
         * o mesmo comportamento que aconteceria se estivessem sido chamadas pelo
         * sistema, função iniciarSistema que será a última a ser chamada nessa lista.
         * 
         * PARA O ACESSO:
         * 
         * ADMINISTRADOR:
         * login: admin
         * senha: admin
         * 
         * FUNCINÁRIO:
         * login: funci
         * senha: funci
         */

        ManipuladorJson manipuladorJson = new ManipuladorJson();
        manipuladorJson.assimilarGeral();

        GerenciadorAdm menuAdm = new GerenciadorAdm();
        GerenciadorFunci menuFunci = new GerenciadorFunci();

        Usuario userAtual = new Usuario();
        userAtual = Sistema.loginSistema(manipuladorJson);

        if (userAtual instanceof Administrador) {
            System.out.println("Nível de Acesso: Administrador " + userAtual);

            // cadastro
            System.out.println("\nCadastro Hóspede \n------------------------------");
            menuAdm.cadHospede();

            // edição de dados
            System.out.println("\nAlterar Dados de Hóspedes \n------------------------------");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entre com um CPF válido: ");
            String cpfHospede = scanner.nextLine();
            menuAdm.alterarHospede(cpfHospede);

            // lista de reservas
            System.out.println("\nLista de Reservas \n------------------------------");
            System.out.println("CPF do Hóspede: ");
            String cpf = scanner.nextLine();
            if (GerenciadorAdm.consultaHospede(cpf) != null) {
                if (GerenciadorAdm.consultaHospede(cpf).getReservasHospede().size() >= 1) {
                    System.out.println("Lista de Reservas do Hóspede: ");
                    Collections.sort(GerenciadorAdm.consultaHospede(cpf).getReservasHospede(), new ReservaComparator());
                    for (int i = 0; i < GerenciadorAdm.consultaHospede(cpf).getReservasHospede().size(); i++) {
                        System.out.println(GerenciadorAdm.consultaHospede(cpf).getReservasHospede().get(i));
                    }
                } else {
                    System.out.println("Não há Reservas para este Hóspede!");
                }
            } else {
                System.out.println("CPF Inválido ou não Cadastrado!");
            }

            // lançamneto da receita do mês
            // System.out.println("\nBalanço Geral do
            // Meês\n------------------------------");
            // menuAdm.gerarDespesasDoMes();

            // comparator hóspede
            System.out.println("\nOrdenação dos Hóspedes por CPF \n------------------------------");
            HospedeComparator comparator = new HospedeComparator();
            Collections.sort(GerenciadorAdm.getListaHospedes(), comparator);
            for (Hospede hospede : GerenciadorAdm.getListaHospedes()) {
                if (hospede != null) {
                    System.out.println(cpf);
                }
            }

            // contador hóspedes private
            GerenciadorAdm.setHospedeCountPrivate();
            System.out.println("\nQuantidade de Hóspedes cadastrados: " + GerenciadorAdm.getHospedeCountPrivate());

            // contador hóspedes protected
            GerenciadorAdm.setHospedeCountProtected();
            System.out.println("\nQuantidade de Hóspedes cadastrados: " + GerenciadorAdm.getHospedeCountProtected());
        } else if (userAtual instanceof Funcionario) {
            System.out.println("Nível de Acesso: Funcionário " + userAtual);

            // extrato
            System.out.println("\nCadastro Reservas \n------------------------------");
            menuFunci.cadastroReserva();

            // listar todos os extratos
            System.out.println("\nExtratos Reservas \n------------------------------");
            manipuladorJson.descarregarExtratoReservas(menuFunci.extratosReservas());
            GerenciadorFunci.setExtratosReservas(manipuladorJson.assimilarExtratoReservas());
            for (String extratoString : GerenciadorFunci.getExtratosReservas()) {
                System.out.println(extratoString);
            }

            // comparator reservas
            System.out.println("\nOrdenação de Reservas \n------------------------------");
            System.out.println("CPF Hóspede: ");
            Scanner input = new Scanner(System.in);
            String cpf = input.nextLine();
            System.out.println("Lista de Reservas do Hóspde: ");
            Collections.sort(GerenciadorAdm.consultaHospede(cpf).getReservasHospede(), new ReservaComparator());
            for (int i = 0; i < GerenciadorAdm.consultaHospede(cpf).getReservasHospede().size(); i++) {
                System.out.println(GerenciadorAdm.consultaHospede(cpf).getReservasHospede().get(i));
            }

            System.out.println("\nListar Resrevas \n------------------------------");
            menuFunci.listarReservas();
        } else {
            System.out.println("Login ou Senha Inválidos!");
        }

        manipuladorJson.descarregarGeral(menuFunci);
        if (userAtual instanceof Administrador) {
            manipuladorJson.descarregarAdm((Administrador) userAtual);
        }

        Sistema.inciarSistema();
    }

    /**
     * função para login no sistema
     * 
     * @param manipuladorJson objeto de ManipularJson para assimilar os dados
     * @return usuário logado no sistema desde que as satisfaças as credenciais
     * @throws IOException exceção associada a manipulação de arquivos JSON
     */
    public static Usuario loginSistema(ManipuladorJson manipuladorJson) throws IOException {
        Administrador adm = manipuladorJson.assimilarAdministrador();
        Scanner scanner = new Scanner(System.in);
        System.out.printf("E-mail: ");
        String loginUser = scanner.nextLine();
        System.out.printf("Senha: ");
        {
            String userSenha = scanner.nextLine();
            Usuario userAtual = null;
            if (loginUser.equals(adm.getLoginUsuario()) && userSenha.equals(adm.getSenhaUsuario())) {
                userAtual = adm;
            } else {
                for (Funcionario funcionario : GerenciadorAdm.getFuncionarios()) {
                    if (funcionario != null) {
                        if (loginUser.equals(funcionario.getLoginUsuario())
                                && userSenha.equals(funcionario.getSenhaUsuario())) {
                            userAtual = funcionario;
                            break;
                        }
                    }
                }
            }
            if (userAtual == null) {
                System.out.println("Login Inválido");
            }
            return userAtual;
        }
    }

    /**
     * função de inicialização do sistema
     * 
     * @throws IOException exceção associada a manipulação de dados JSON
     */
    public static void inciarSistema() throws IOException {
        ManipuladorJson manipuladorJson = new ManipuladorJson();
        manipuladorJson.assimilarGeral();
        Usuario userAtual = null;

        // iniciando sistema
        GerenciadorAdm admMenu = new GerenciadorAdm();
        GerenciadorFunci funciMenu = new GerenciadorFunci();
        GerenciadorFunci.setExtratosReservas(manipuladorJson.assimilarExtratoReservas());
        MenuSistema menuSistema = new MenuSistema();

        // login
        Scanner inputSistema = new Scanner(System.in);
        int opcao;
        boolean sair = false;
        System.out.printf(
                "Escolha uma Opção: \n------------------------------ \n\t1. Login \n\t2. Sair \n------------------------------");
        inputSistema = new Scanner(System.in);
        try {
            opcao = inputSistema.nextInt();
        } catch (Exception exception) {
            opcao = 5;
        }

        switch (opcao) {
            case 1: {
                while (userAtual == null) {
                    userAtual = Sistema.loginSistema(manipuladorJson);
                }
                if (userAtual instanceof Administrador) {
                    menuSistema.menuAdministrador(admMenu, funciMenu, userAtual);
                    manipuladorJson.descarregarAdm((Administrador) userAtual);
                } else if (userAtual instanceof Funcionario) {
                    menuSistema.menuFuncionario(admMenu, funciMenu, userAtual);
                }
                break;
            }
            case 2: {
                manipuladorJson.descarregarGeral(funciMenu);
                sair = true;
                break;
            }
            default: {
                System.out.println("Entrada Inválida! Encerrando...");
            }
        }

    }

    // Q.3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "Sistema Pousada";
    }
}
