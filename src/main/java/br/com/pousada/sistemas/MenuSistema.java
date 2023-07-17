package br.com.pousada.sistemas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Scanner;

import br.com.pousada.pessoas.Administrador;
import br.com.pousada.pessoas.Colaborador;
import br.com.pousada.pessoas.Usuario;
import br.com.pousada.servicos.GerenciadorAdm;
import br.com.pousada.servicos.GerenciadorFunci;
import br.com.pousada.servicos.Reserva;
import br.com.pousada.servicos.ReservaComparator;

/**
 * Classe representativa do Menu de Opções do Sistema
 *
 * @author Marcos Vinícius Santos Cruz
 * @author Filipe Fernades Costa
 */
// Q.1 - Implementar todas as classes com base no diagrama de classes criado
public class MenuSistema {

    /**
     * Construtor padrão
     */
    public MenuSistema() {
    }

    /**
     * Tela de opções acessível ao ADM
     * 
     * @param menuAdm   objeto da classe intermediária de funcionalidades do ADM
     * @param menuColab objeto da classe intermediária de funcionalidades do FUNCI
     * @param userAtual objeto referente ao user logado no sistema
     * @throws IOException exceção associada a manipulação de dados em arquivo JSON
     */
    public void menuAdministrador(GerenciadorAdm menuAdm, GerenciadorFunci menuColab, Usuario userAtual)
            throws IOException {
        Scanner input = new Scanner(System.in);
        boolean sairSistema = false;
        do {
            System.out.println(
                    "Escolha uma opção do menu: \n--------------------\n\t1. Colaboradores \n\t2. Reservas \n\t3. Hóspedes \n\t4. Quartos \n\t5. Perfil \n\t6. Contas do mês \n\t7. Encerrar \n--------------------");
            int escolha = input.nextInt();

            switch (escolha) {
                case 1: {// entra em colaboradores do sistema
                    menuOpcaoColabAdm(menuAdm, menuColab, userAtual);
                    break;
                }
                case 2: {// entra em reservas
                    menuOpcaReservas(menuAdm, menuColab, userAtual);
                    break;
                }
                case 3: {// entar em hospdes
                    menuOpcaoHospede(menuAdm, menuColab, userAtual);
                    break;
                }
                case 4: {// entar em quartos
                    menuOpcaoQuartos(menuAdm, menuColab, userAtual);
                    break;
                }
                case 5: {// entra no perfil do usuario
                    break;
                }
                case 6: {// entra em contas do mes
                    menuOpcaoContasMes(menuAdm, menuColab, userAtual);
                    break;
                }
                case 7: {
                    System.out.println("Finalizando...");
                    ManipuladorJson mapJson = new ManipuladorJson();
                    mapJson.descarregarGeral(menuColab);
                    sairSistema = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        } while (sairSistema == false);
    }

    /**
     * tela de acesso as opções destinasdas aos FUNCI
     * 
     * @param admMenu   objeto da classe intermediária de funcionalidades do ADM
     * @param menuFunci objeto da classe intermediária de funcionalidades do FUNCI
     * @param userAtual objeto referente ao user logado no sistema
     * @throws IOException exceção associada a manipulação de dados em arquivo JSON
     */
    public void menuFuncionario(GerenciadorAdm admMenu, GerenciadorFunci menuFunci, Usuario userAtual)
            throws IOException {
        Scanner inputS = new Scanner(System.in);
        boolean sairSistema = false;
        do {
            System.out.println(
                    "Selecione a opção desejada: \n-------------------------\n\t1. Reservas \n\t2. Quartos \n\t3. Hóspedes \n\t4. Perfil \n\t5. Encerrar \n-------------------------");
            int opcao = inputS.nextInt();
            switch (opcao) {
                case 1: {// entra em reservas
                    menuOpcaReservas(admMenu, menuFunci, userAtual);
                    break;
                }
                case 2: {// entra em quartos
                    menuOpcaoQuartos(admMenu, menuFunci, userAtual);
                    break;
                }
                case 3: {// entra em hospedes
                    menuOpcaoHospede(admMenu, menuFunci, userAtual);
                    break;
                }
                case 4: {// entrar no perfil do usuario
                    break;
                }
                case 5: {// sair
                    System.out.println("Finalizando...");
                    ManipuladorJson mapJson = new ManipuladorJson();
                    mapJson.descarregarGeral(menuFunci);
                    sairSistema = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        } while (sairSistema == false);
    }

    // ======================================================================================================================
    // OPÇÕES DOS MENUS DE ADM E FUNCI
    /**
     * Função para acesso as opções de manipulação de Colaboradores/Funcionários do
     * sistema
     * 
     * @param admMenu   recebe objeto da classe intermediária de funcionalidades
     *                  disponíveis ao ADM
     * @param funciMenu recebe objeto da classe intermediária de funcionalidades
     *                  disponíveis ao COLAB
     * @param userAtual recebe objeto referente ao user logado no sistema
     * @throws IOException exeção associada a manipulação de dados em arquivo JSON
     */
    public void menuOpcaoColabAdm(GerenciadorAdm admMenu, GerenciadorFunci funciMenu, Usuario userAtual)
            throws IOException {
        Scanner inputS = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        boolean menuAnterior = false;
        do {
            System.out.println(
                    "Escolha uma Opção: \n--------------------\n\t1. Cadastrar \n\t2.Editar \n\t3. Consultar \n\t4. Remover \n\t5. Voltar \n--------------------");
            int caso = inputS.nextInt();
            String cpf;
            switch (caso) {
                case 1: { // cadastro funcionario
                    admMenu.cadastroFunci();
                    break;
                }
                case 2: { // editar funcionario
                    System.out.println("Insira o CPF: ");
                    cpf = input.nextLine();
                    admMenu.editarFunci(cpf);
                    break;
                }
                case 3: { // consultar funcionario
                    System.out.println("Insira o CPF: ");
                    cpf = input.nextLine();
                    System.out.println(admMenu.consultaFunci(cpf));
                    break;
                }
                case 4: { // remover funcionario
                    System.out.println("Insira o CPF: ");
                    cpf = input.nextLine();
                    admMenu.excluirFunci(cpf);
                    break;
                }
                case 5: {
                    menuAnterior = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        } while (menuAnterior == false);
    }

    /**
     * tela de acesso as opções de manipulação de Hóspedes
     * 
     * @param admMenu   objeto da classe intermediária de funcionalidades do ADM
     * @param funciMenu objeto da classe intermediária de funcionalidades do FUNCI
     * @param userAtual objeto referente ao user logado no sistema
     * @throws IOException exceção associada a manipulação de dados em arquivo JSON
     */
    public void menuOpcaoHospede(GerenciadorAdm admMenu, GerenciadorFunci funciMenu, Usuario userAtual)
            throws IOException {
        Scanner inputS = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        boolean menuAnaterior = false;
        do {
            System.out.println(
                    "Escolha a opção desejada: \n--------------------\n\t1. Cadastrar \n\t2.Editar \n\t3. Remover \n\t4. Consultar \n\t5. Lista de Reservas \n\t6. Voltar \n--------------------");
            int escolha = inputS.nextInt();
            String cpf;
            switch (escolha) {
                case 1: {
                    admMenu.cadHospede();
                    break;
                }
                case 2: {
                    System.out.println("CPF do Hóspede: ");
                    cpf = input.nextLine();
                    admMenu.alterarHospede(cpf);
                    break;
                }
                case 3: {
                    do {
                        System.out.printf("CPF Hóspede: ");
                        cpf = input.nextLine();
                    } while (GerenciadorAdm.validaCPF(cpf) == false);
                    admMenu.excluirHospede(cpf);
                    break;
                }
                case 4: {
                    System.out.println("CPF Hóspede: ");
                    cpf = input.nextLine();
                    if (GerenciadorAdm.consultaHospede(cpf) == null) {
                        System.out.println("CPF não cadastrado. Tente novamente!");
                    } else {
                        System.out.println(GerenciadorAdm.consultaHospede(cpf));
                    }
                    break;
                }
                case 5: {
                    // Q.8 - Verificar e imprimir dados das reservas e dos clientes;
                    System.out.println("CPF do hóspede: ");
                    cpf = input.nextLine();
                    if (GerenciadorAdm.consultaHospede(cpf) != null) {
                        if (GerenciadorAdm.consultaHospede(cpf).getReservasHospede().size() >= 1) {
                            System.out.println("Lista de Reservas do Cliente: ");
                            Collections.sort(GerenciadorAdm.consultaHospede(cpf).getReservasHospede(),
                                    new ReservaComparator());
                            for (int i = 0; i < GerenciadorAdm.consultaHospede(cpf).getReservasHospede().size(); i++) {
                                System.out.println(GerenciadorAdm.consultaHospede(cpf).getReservasHospede().get(i));
                            }
                        } else {
                            System.out.println("Não há reservas cadastradas para esse hóspede!");
                        }
                    } else {
                        System.out.println("CPF Inválido ou não Cadastrado!");
                    }
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
        } while (menuAnaterior == false);
    }

    /**
     * função para acesso as opções de manipulação de quartos
     * 
     * @param menuAdm   objeto da classe intermediária de funcionalidades do ADM
     * @param funciMenu objeto da classe intermediária de funcionalidades do FUNCI
     * @param userAtual objeto referente ao user logado no sistema
     * @throws IOException exceção associada a manipulação de dados em arquivo JSON
     */
    public void menuOpcaoQuartos(GerenciadorAdm menuAdm, GerenciadorFunci funciMenu, Usuario userAtual)
            throws IOException {
        Scanner inputS = new Scanner(System.in);
        Scanner intput = new Scanner(System.in);
        boolean menuAntes = false;
        do {
            System.out.println("Selecione a opção:  \n------------------------- \n\t1. Cadastrar \n\t2. Consulta ");
        } while (menuAntes == false);
    }

    /**
     * função de acesso as opções de manipulação de reservas
     * 
     * @param menuAdm   objeto da classe intermediária de funcionalidades do ADM
     * @param funciMenu objeto da classe intermediária de funcionalidades do FUNCI
     * @param userAtual objeto referente ao user logado no sistema
     * @throws IOException exceção associada a manipulação de dados em arquivo JSON
     */
    public void menuOpcaReservas(GerenciadorAdm menuAdm, GerenciadorFunci funciMenu, Usuario userAtual)
            throws IOException {
        Scanner inputS = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        boolean menuAnterior = false;
        do {
            System.out.println(
                    "Selecione a opção desejada: \n-------------------------\n\t1. Cadastrar \n\t2. Editar \n\t3. Excluir \n\t4. Extratos \n\t5. Consultar reservas \n\t6. Voltar \n-------------------------");
            int opcao = inputS.nextInt(), idReservaMod;
            String cpf = null;
            switch (opcao) {
                case 1: {// cadastro de reservas
                    funciMenu.cadastroReserva();
                    break;
                }
                case 2: {// modificação de reservas
                    System.out.println("CPF: ");
                    cpf = input.nextLine();
                    if (GerenciadorAdm.consultaHospede(cpf) != null) {
                        System.out.println("Lista de reservas do Hóspede: ");
                        for (int i = 0; i < GerenciadorAdm.consultaHospede(cpf).getReservasHospede().size(); i++) {
                            System.out.println(GerenciadorAdm.consultaHospede(cpf).getReservasHospede().get(i));
                        }
                        System.out.println("ID da Reserva: ");
                        idReservaMod = input.nextInt();
                        funciMenu.modificarReserva(idReservaMod, GerenciadorFunci.consultaHospede(cpf));
                        break;
                    }
                }
                case 3: { // exclusão de reservas
                    funciMenu.excluirReserva();
                    break;
                }
                case 4: {// extratos de reservas
                    ManipuladorJson mJson = new ManipuladorJson();
                    mJson.descarregarExtratoReservas(funciMenu.extratosReservas());
                    GerenciadorFunci.setExtratosReservas(mJson.assimilarExtratoReservas());
                    for (String extratoString : GerenciadorFunci.getExtratosReservas()) {
                        System.out.println(extratoString);
                    }
                    break;
                }
                case 5: {// consultar reservar
                    System.out.println("CPF do hóspede: ");
                    cpf = input.nextLine();
                    if (GerenciadorAdm.consultaHospede(cpf) != null) {
                        if (GerenciadorAdm.consultaHospede(cpf).getReservasHospede().size() >= 1) {
                            System.out.println("Lista de reservas do hóspede: ");
                            for (Reserva reserva : GerenciadorAdm.consultaHospede(cpf).getReservasHospede()) {
                                LocalDate dataInicioRe = reserva.getDataInicio();
                                LocalDate dataFimRe = reserva.getDataFim();
                                System.out.println("[" + reserva.getIdReserva() + "] RESERVADO EM: " + dataInicioRe
                                        + " ATÉ: " + dataFimRe + " VALOR DIÁRIA: " + reserva.getPrecoReserva());
                            }

                            System.out.println("ID da Reserva: ");
                            idReservaMod = input.nextInt();
                            Reserva consultaReserva = funciMenu.consultaReserva(idReservaMod,
                                    GerenciadorFunci.consultaHospede(cpf));
                            if (consultaReserva != null) {
                                System.out.println(consultaReserva);
                            } else {
                                System.out.println("Reserva não encontrada!");
                            }
                        } else {
                            System.out.println("Não há reservas associadas a esse hóspede!");
                        }
                    } else {
                        System.out.println("CPF Inválido!");
                    }
                    break;
                }
                case 6: {
                    menuAnterior = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        } while (menuAnterior == false);

    }

    /**
     * tela de acesso as opções de manipulação das informações do ADM
     * 
     * @param menuAdm   objeto da classe intermediária de funcionalidades do ADM
     * @param funciMenu objeto da classe intermediária de funcionalidades do FUNCI
     * @param userAtual objeto referente ao user logado no sistema
     * @throws IOException exceção associada a manipulação de arquivo JSON
     */
    public void menuOpcaoAdm(GerenciadorAdm menuAdm, GerenciadorFunci funciMenu, Usuario userAtual) throws IOException {
        Scanner inputS = new Scanner(System.in);
        boolean menuAnterior = false;
        do {
            System.out.println(
                    "Selecione a opção desejada: \n-------------------------\n\t1. Exibir dados \n\t2. Alterar dados \n\t3. Voltar \n-------------------------");
            int escolha = inputS.nextInt();
            switch (escolha) {
                case 1: { // mostar dados do ADM
                    menuAdm.consultaAdm((Administrador) userAtual);
                    break;
                }
                case 2: { // edita dados do ADM
                    menuAdm.editarAdm((Administrador) userAtual);
                    break;
                }
                case 3: {
                    menuAnterior = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        } while (menuAnterior == false);
    }

    /**
     * função destinada apenas ao ADM do sistema, referente ao balanço mensal
     * 
     * @param menuAdm   objeto da classe intermediária de funcionalidades do ADM
     * @param funciMenu objeto da classe intermediária de funcionalidades do FUNCI
     * @param userAtual objeto referente ao user logado no sistema
     * @throws IOException exceção associada a manipulação de dados em arquivo JSON
     */
    public void menuOpcaoContasMes(GerenciadorAdm menuAdm, GerenciadorFunci funciMenu, Usuario userAtual)
            throws IOException {
        Scanner inputS = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        boolean menuAnterior = false;
        do {
            System.out.println(
                    "Seleciona uma opção: \n------------------------- \n\t1. Gerenciar contas do mes \n\t2. Gerar banlanço mensal \n\t3. Voltar");
            int opcao = inputS.nextInt();
            int verificaMes, verificaAno;
            switch (opcao) {
                case 1: { // cataloga as despesas da pousada em determinado mes
                    menuAdm.gerarDespesasDoMes();
                    break;
                }
                case 2: { // gera a receita da pousada no intervalo especificado
                    System.out.println("Gerar balanço mensal referente a qual mês: ");
                    verificaMes = input.nextInt();
                    System.out.println("E referente a qual ano: ");
                    verificaAno = input.nextInt();
                    menuAdm.gerarBalancoMensal(verificaMes, verificaAno);
                    break;
                }
                case 3: {
                    menuAnterior = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        } while (menuAnterior == false);
    }

    public void menuOpcaoDetalhes(GerenciadorAdm admMenu, GerenciadorFunci menuFunci, Usuario userAtual)
            throws IOException {

    }

    public void menuOpcaoColabFunci(GerenciadorAdm menuADM, GerenciadorFunci funciMenu, Usuario userAtual)
            throws IOException {
        Scanner inputs = new Scanner(System.in);
        boolean menuANtes = false;
        do {
            System.out.println("-------------------------\n" + userAtual.getNomePessoa() + " " + userAtual.getSobrenomePessoa() + "\n-------------------------\n");
            System.out.println("Selecione uma opção: \n------------------------- \n\t1. Alterar senha \n\t2. Voltar \n-------------------------");
            int escolha = inputs.nextInt();
            switch(escolha){
                case 1: {
                    menuADM.editarAdm((Administrador) userAtual);
                    break;
                }
                case 2: {
                    menuANtes = true;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        } while (menuANtes == false);
    }

    // Q.3 - Sobrescrever o método toString() de todas as classes implementadas;
    @Override
    public String toString() {
        return "Menu Sistema";
    }
}
