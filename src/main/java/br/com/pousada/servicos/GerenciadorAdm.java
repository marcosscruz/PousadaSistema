package br.com.pousada.servicos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.pousada.pessoas.*;

/**
 * Classe intermediária para funcionalidades direcionadas aos Administradores,
 * além das funcionalidades que os Funcionários já exercem
 *
 * @author Marcos Vinícius Santos Cruz
 * @author Filipe Fernades Costa
 */
// Q.1 - Implementar todas as classes com base no diagrma de classes criado
public class GerenciadorAdm extends GerenciadorFunci {

    /**
     * Q.4 - Utilizar a palavra chave super para implementar os construtores das
     * subsclasses
     */
    public GerenciadorAdm() {
        super();
    }

    // =======================================================================================================
    // FUNCIONALIDADES DO ADMINISTRADOR

    private List<Despesa> despesas;

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public void gerarDespesasDoMes() {
        Scanner input = new Scanner(System.in);

        boolean parada = false;
        do {
            System.out.println("Selecione uma opção: \n\t1. Gerenciar despesas \n\t2. Finalizar");
            int escolha = input.nextInt();

            switch (escolha) {
                case 1: {
                    System.out.println("Nome da despesa: ");
                    String nome = input.nextLine();

                    System.out.println("Descrição: ");
                    String desc = input.nextLine();

                    System.out.println("Valor a ser pago: ");
                    double valor = input.nextDouble();

                    System.out.println("Data da despesa (mm/yyyy): ");
                    String dataString = input.nextLine();

                    LocalDate data = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("MM/yyyy"));

                    Despesa novaDespesa = new Despesa(nome, desc, valor, data);
                    despesas.add(novaDespesa);
                }
                case 2: {
                    System.out.println("Finalizando...");
                    parada = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida! Tente novamente.");
                }
            }

        } while (parada == false);

        System.out.println("Despesas salva com sucesso!");
    }

    public double gerarBalancoMensal(int mes, int ano) {
        double totalReceitas = 0.0, totalDespesas = 0.0;

        for (Reserva reserva : GerenciadorAdm.getListaReservas()) {
            LocalDate dataReservaInicio = reserva.getDataInicio();

            // verifica se a reserva ocorreu no mês e ano especificados
            if (dataReservaInicio.getMonthValue() == mes && dataReservaInicio.getYear() == ano) {
                totalReceitas += reserva.getPrecoReservaTotal();
            }
        }

        for (Despesa despesa : despesas) {
            LocalDate dataDespesa = despesa.getData();

            if (dataDespesa.getMonthValue() == mes && dataDespesa.getYear() == ano) {
                totalDespesas += despesa.getValorDespesa();
            }
        }

        double balanco = totalReceitas - totalDespesas;
        return balanco;
    }

    // =======================================================================================================
    // MANIPULAÇÃO DE FUNCIONÁRIOS

    private static List<Funcionario> funcionarios = new ArrayList<>();

    public static List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionario(List<Funcionario> funcionarios) {
        GerenciadorAdm.funcionarios = funcionarios;
    }

    /**
     * Função de cadastro de novos colaboradores no sistema
     * Q.6 - Deve ser possível cadastrar os colaboradores no sistema, alterar ou
     * editar seus atributos;
     */
    public void cadastroFunci() {
        String nomeColab, sobrenomeColab, CPF, loginColab, senhaColab;
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Nome: ");
        nomeColab = scanner.nextLine();

        System.out.printf("Sobrenome: ");
        sobrenomeColab = scanner.nextLine();

        do {
            System.out.printf("CPF: ");
            CPF = scanner.nextLine();
            if (validaCPF(CPF) == false || consultaColab(CPF) != null) {
                System.out.println("CPF inválido ou já cadastrado. Tente novamente!");
            }
        } while (validaCPF(CPF) == false || consultaColab(CPF) != null);

        System.out.println("Login: ");
        loginColab = scanner.nextLine();

        System.out.println("Senha: ");
        senhaColab = scanner.nextLine();

        Funcionario funcionario = new Funcionario(nomeColab, sobrenomeColab, CPF, loginColab, senhaColab);
        addFunci(funcionario);
        System.out.println("Cadastro de Funcinário realizado com sucesso!");
    }

    /**
     * Função padrão de acesso às opções de modificações do Colaborador
     * Q.6 - Deve ser possível cadastrar os colaboradores no sistema, alterar ou
     * editar seus atributos;
     * 
     * @param cpf chave de busca do objeto Funcinario na base de
     *            colaboradores do sistema
     */
    public void editarFunci(String cpf) {
        Funcionario funci = consultaFunci(cpf);
        boolean menuAnaterior = false;
        do {
            System.out.println(cpf + "\n--------------------------------\n");
            Scanner entrarDados = new Scanner(System.in);
            System.out.println(
                    "Escolha uma opção: \n\t1. Alterar login \n\t2. Alterar senha \n\t3. Alterar CPF \n\t4. Alterar nome \n\t5. Fechar menu");
            int opcao = entrarDados.nextInt();
            switch (opcao) {
                case 1: {
                    entrarDados = new Scanner(System.in);
                    System.out.println("Novo login: ");
                    String novoLogin = entrarDados.nextLine();

                    System.out.println("Cofirmar login: ");
                    String confirmarLogin = entrarDados.nextLine();

                    if (novoLogin.equals(confirmarLogin)) {
                        funci.setLoginUsuario(confirmarLogin);
                        break;
                    } else {
                        System.out.println("Dados inseridos não conferem. Tente novamente.");
                        break;
                    }
                }
                case 2: {
                    entrarDados = new Scanner(System.in);
                    System.out.printf("Senha anterior: ");
                    String senhaAnterior = entrarDados.nextLine();

                    System.out.printf("Nova senha: ");
                    String novaSenha = entrarDados.nextLine();

                    System.out.printf("Confrimar senha: ");
                    String confirmarSenha = entrarDados.nextLine();

                    if (funci.getSenhaUsuario().equals(senhaAnterior)) {
                        if (novaSenha.equals(confirmarSenha)) {
                            funci.setSenhaUsuario(novaSenha);
                            System.out.println("Senha alterada com sucesso!");
                            break;
                        } else {
                            System.out.println("Senhas diferentes! Tente novamente.");
                            break;
                        }
                    } else {
                        System.out.println("Senha antiga não confere! Tente Novamante.");
                        break;
                    }

                }
                case 3: {
                    String novoCPF;
                    entrarDados = new Scanner(System.in);
                    do {
                        System.out.println("Novo CPF: ");
                        novoCPF = entrarDados.nextLine();
                        if (GerenciadorAdm.validaCPF(novoCPF) == false || consultaColab(novoCPF) != null) {
                            System.out.println("CFP inválido ou já cadastrado! Tente novamente.");
                            break;
                        }
                    } while (GerenciadorAdm.validaCPF(novoCPF) == false || consultaColab(novoCPF) != null);
                    funci.setCPF(novoCPF);
                    System.out.println("Alteração feita com sucesso!");
                    break;
                }
                case 4: {
                    entrarDados = new Scanner(System.in);
                    System.out.printf("Novo nome: ");
                    String novoNome = entrarDados.nextLine();
                    funci.setNomePessoa(novoNome);

                    System.out.printf("Novo sobrenome: ");
                    String novoSobrenome = entrarDados.nextLine();
                    funci.setSobrenomePessoa(novoSobrenome);

                    System.out.println("Alteração feita com sucesso!");
                    break;
                }
                case 5: {
                    menuAnaterior = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida! Tente novamente.");
                    break;
                }
            }
        } while (menuAnaterior == false);
    }

    /**
     * Função padrão para exclusão de um Funcionario
     * Q.6 - Deve ser possível cadastrar os colaboradores no sistema, alterar ou
     * editar seus atributos;
     * 
     * @param cpf chave de busca do objeto Colaborador na base de colaboradores do
     *            Sistema
     */
    public void excluirFunci(String cpf) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCPF().equals(cpf)) {
                removeFunic(funcionario);
                System.out.println("Alteração feita com sucesso!");
                break;
            }
        }
        System.out.println("CPF não encontrado!");
    }

    // =======================================================================================================================================================
    // MANIPULAÇÃO DE ADMINISTRADORES

    private List<Administrador> administradores = new ArrayList<>();

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }

    public void addAdm(Administrador administrador) {
        administradores.add(administrador);
    }

    public void removeAdm(Administrador administrador) {
        administradores.remove(administrador);
    }

    /**
     * Função de cadastro de novos colaboradores no sistema
     * Q.6 - Deve ser possível cadastrar os colaboradores no sistema, alterar ou
     * editar seus atributos;
     */
    public void cadastradoAdm() {
        String nomeColab, sobrenomeColab, CPF, loginColab, senhaColab;
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Nome: ");
        nomeColab = scanner.nextLine();

        System.out.printf("Sobrenome: ");
        sobrenomeColab = scanner.nextLine();

        do {
            System.out.printf("CPF: ");
            CPF = scanner.nextLine();
            if (validaCPF(CPF) == false || consultaColab(CPF) != null) {
                System.out.println("CPF inválido ou já cadastrado! Tente novamente.");
            }
        } while (validaCPF(CPF) == false || consultaColab(CPF) != null);

        System.out.println("Login: ");
        loginColab = scanner.nextLine();

        System.out.println("Senha: ");
        senhaColab = scanner.nextLine();

        Administrador administrador = new Administrador(nomeColab, sobrenomeColab, CPF, loginColab, senhaColab);
        addAdm(administrador);
        System.out.println("Cadastro de Administrsdor feito com sucesso!");
    }

    /**
     * função para exibição dos dados associados ao ADM
     * 
     * @param administrador ADM que deve esta logado no momento da chamada
     */
    public void consultaAdm(Administrador administrador) {
        System.out.println(administrador);
    }

    /**
     * Função padrão de acesso às opções de modificações do Colaborador
     * Q.6 - Deve ser possível cadastrar os colaboradores no sistema, alterar ou
     * editar seus atributos;
     * 
     * @param administrador chave que indica o parâmetro administrador é do tipo
     *                      Administrador.
     */
    public void editarAdm(Administrador administrador) {
        boolean menuAnaterior = false;
        do {
            System.out.println(administrador + "\n--------------------------------\n");
            Scanner entrarDados = new Scanner(System.in);
            System.out.println(
                    "Escolha uma opção: \n\t1. Alterar login \n\t2. Alterar senha \n\t3. Alterar CPF \n\t4. Alterar nome \n\t5. Fechar menu");
            int opcao = entrarDados.nextInt();
            switch (opcao) {
                case 1: {
                    entrarDados = new Scanner(System.in);
                    System.out.println("Novo login: ");
                    String novoLogin = entrarDados.nextLine();

                    System.out.println("Cofirmar login: ");
                    String confirmarLogin = entrarDados.nextLine();

                    if (novoLogin.equals(confirmarLogin)) {
                        administrador.setLoginUsuario(confirmarLogin);
                        break;
                    } else {
                        System.out.println("Dados inseridos não conferem. Tente novamente.");
                        break;
                    }
                }
                case 2: {
                    entrarDados = new Scanner(System.in);
                    System.out.printf("Senha anterior: ");
                    String senhaAnterior = entrarDados.nextLine();

                    System.out.printf("Nova senha: ");
                    String novaSenha = entrarDados.nextLine();

                    System.out.printf("Confrimar senha: ");
                    String confirmarSenha = entrarDados.nextLine();

                    if (administrador.getSenhaUsuario().equals(senhaAnterior)) {
                        if (novaSenha.equals(confirmarSenha)) {
                            administrador.setSenhaUsuario(novaSenha);
                            System.out.println("Senha alterada com sucesso!");
                            break;
                        } else {
                            System.out.println("Senhas diferentes! Tente novamente.");
                            break;
                        }
                    } else {
                        System.out.println("Senha antiga não confere! Tente Novamante.");
                        break;
                    }

                }
                case 3: {
                    String novoCPF;
                    entrarDados = new Scanner(System.in);
                    do {
                        System.out.println("Novo CPF: ");
                        novoCPF = entrarDados.nextLine();
                        if (GerenciadorAdm.validaCPF(novoCPF) == false || consultaColab(novoCPF) != null) {
                            System.out.println("CFP inválido ou já cadastrado! Tente novamente.");
                            break;
                        }
                    } while (GerenciadorAdm.validaCPF(novoCPF) == false || consultaColab(novoCPF) != null);
                    administrador.setCPF(novoCPF);
                    System.out.println("Alteração feita com sucesso!");
                    break;
                }
                case 4: {
                    entrarDados = new Scanner(System.in);
                    System.out.printf("Novo nome: ");
                    String novoNome = entrarDados.nextLine();
                    administrador.setNomePessoa(novoNome);

                    System.out.printf("Novo sobrenome: ");
                    String novoSobrenome = entrarDados.nextLine();
                    administrador.setSobrenomePessoa(novoSobrenome);

                    System.out.println("Alteração feita com sucesso!");
                    break;
                }
                case 5: {
                    menuAnaterior = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida! Tente novamente.");
                    break;
                }
            }
        } while (menuAnaterior == false);
    }

    /**
     * Função padrão para exclusão de um Administrador
     * Q.6 - Deve ser possível cadastrar os colaboradores no sistema, alterar ou
     * editar seus atributos;
     * 
     * @param cpf chave de busca do objeto Colaborador na base de colaboradores do
     *            Sistema
     */
    public void excluirAdm(String cpf) {
        for (Administrador administrador : administradores) {
            if (administrador.getCPF().equals(cpf)) {
                removeAdm(administrador);
                System.out.println("Alteração feita com sucesso!");
                break;
            }
        }
        System.out.println("CPF não encontrado!");
    }

    // =======================================================================================================================================================
    // VALIDAÇÃO DE CPF
    /**
     * Função padrão para a validação do número de CPF repassado como parâmetro
     * em cadastros de Clientes e Colaboradores
     * 
     * @param cpf dado fornecido pelo usuário
     * @return validade do CPF
     */
    public static boolean validaCPF(String cpf) {
        // verificando se o CPF é formado apenas por números iguais
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
                || cpf.equals("99999999999") || (cpf.length() != 11)) {
            System.out.println("CPF inválido. Digite novamente!");
            return false;
        }

        // variáveis do 10º e 11º digito
        char digito10, digito11;
        int soma, r, numero, peso;

        try {
            // calculando o primeiro digito 'verificador'
            soma = 0;
            peso = 0;

            for (int i = 0; i < 9; i++) {
                // o '48' representa o 0 (zero) na tabela ASCII
                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (numero * peso);
                peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11)) {
                digito10 = '0';
            } else {
                digito10 = (char) (r + 48);
            }

            // calculando o segundo 'verificador'
            soma = 0;
            peso = 11; // peso é 11 já que o primeiro verificador já foi calculado
            for (int i = 0; i < 10; i++) {
                // x5 é a variável a qual vai receber os valores das somas
                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (numero * peso);
                // o xpeso sempre diminui de uma soma para a outra
                peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11)) {
                digito11 = '0';
            } else {
                digito11 = (char) (r + 48);
            }

            // valida se os números informados batem o valor com os números
            if (digito10 == cpf.charAt(9) && (digito11 == cpf.charAt(10))) {
                return (true);
            } else {
                System.out.println("CPF inválido. Digite novamente!");
                return (false);
            }
        } catch (Exception exception) {
            System.out.println("Não foi possível validar o CPF!");
            return (false);
        }
    }

    // Q.3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "Gerendiador Administrador";
    }

}
