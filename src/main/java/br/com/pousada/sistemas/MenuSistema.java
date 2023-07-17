package br.com.pousada.sistemas;

import java.io.IOException;
import java.util.Scanner;

import br.com.pousada.pessoas.Usuario;
import br.com.pousada.servicos.GerenciadorAdm;
import br.com.pousada.servicos.GerenciadorFunci;

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

    public void menuAdministrador(GerenciadorAdm menuAdm, GerenciadorFunci menuColab, Usuario userAtual)
            throws IOException {
        Scanner input = new Scanner(System.in);
        boolean sairSistema = false;
        do {
            System.out.println(
                    "Escolha uma opção do menu: \n--------------------\n\t1. Funcionários \n\t2. Hóspedes \n\t3. Quartos \n\t4. Perfil \n\t5. Encerrar \n--------------------");
            int escolha = input.nextInt();

            switch (escolha) {
                case 1: {

                }
                case 2: {

                }
                case 3: {

                }
                case 4: {

                }
                case 5: {
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

    public void menuColabAdm() {

    }

    public void menuHospede() {

    }

    public void menuQuarto() {

    }

    public void menuOpcaoAdm() {

    }
}
