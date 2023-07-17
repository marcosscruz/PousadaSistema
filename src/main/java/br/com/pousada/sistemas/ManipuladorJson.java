package br.com.pousada.sistemas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.pousada.pessoas.*;
import br.com.pousada.servicos.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * Classe que contém métodos de manipulação de arquivos JSON
 *
 * @author Marcos Vinícius Santos Cruz
 * @author Filipe Fernades Costa
 * 
 * @see <a href="https://mvnrepository.com/artifact/com.google.code.gson/gson">
 *      Gson MvnRepository </a>
 * 
 * @see <a href=
 *      "https://www.devmedia.com.br/como-converter-objetos-java-para-ou-de-json-com-a-biblioteca-gson/28091">
 *      DevMedia - Como converter objetos java para Json com a biblioteca Gson
 *      </a>
 * 
 * @see <a href=
 *      "https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt">
 *      StackOverflow - Convert from json to a typed ArrayList </a>
 *
 *      Q.14 - Salve e recupere todas as informações dos Clientes, Quartos,
 *      Colaboradores e Reservas em um arquivo de texto. Utilizem classes já
 *      prontas
 *      na internet que trabalhem com o formato json. Ao manipular um arquivo
 *      utilize
 *      os conceitos aprendidos em aula para alocar e desalocar recursos com
 *      segurança.
 */
public class ManipuladorJson {

    // Construtor padrão
    public ManipuladorJson() {
    }

    /**
     * Funcção que descarrega dados referentes aos colaboradores cadastrados
     * 
     * @param list Lista de colaboradores do sistema
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public void descarregarColab(List<Colaborador> list) throws IOException {
        // Criação de uma instância da classe Gson para realizar a
        // serialização/deserialização JSON.
        Gson jsonObt = new Gson();
        File colaboradorArquivo = new File("src\\main\\java\\br\\com\\pousada\\database\\Colaboradores.json");

        FileWriter colaboradorWriter = null;
        String dadosColaboradores = jsonObt.toJson(list);

        try {
            colaboradorWriter = new FileWriter("src\\main\\java\\br\\com\\pousada\\database\\Colaboradores.json");
            colaboradorWriter.write(dadosColaboradores);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            colaboradorWriter.close();
        }
    }

    /**
     * Função de quisição dos dados referentes aos colaboradores existentes no
     * arquivo texto
     * 
     * @return lista de colaboradores do sistema
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public Colaborador assimilarColaborador() throws IOException {
        Gson jsonObj = new Gson();
        File colabFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Colaboradores.json");

        try {
            /**
             * @Line73 Leitura do conteúdo do arquivo JSON e armazenamento em uma string.
             * @Line74 Desserialização do JSON para um objeto Colaborador usando o Gson.
             */
            String dadsoColab = new String(Files.readAllBytes(Paths.get(colabFile.toURI())));
            Colaborador colab = jsonObj.fromJson(dadsoColab, new TypeToken<Colaborador>() {
            }.getType());
            return colab;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Função para descarregar dados referentes aos hóspedes cadastrados
     * 
     * @param hospede lista de hóspedes do sistema
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public void descarregarHospede(ArrayList<Hospede> hospede) throws IOException {
        Gson jsonObt = new Gson();
        File hospedeFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Hospedes.json");
        FileWriter hospedeWriter = null;
        String dadosHospede = jsonObt.toJson(hospede);

        try {
            hospedeWriter = new FileWriter("src\\main\\java\\br\\com\\pousada\\database\\Hospedes.json");
            hospedeWriter.write(dadosHospede);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            hospedeWriter.close();
        }
    }

    /**
     * Função para aquisição dos dados referentes aos hóspedes no arquivo
     * 
     * @return lista de Hóspedes
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public ArrayList<Hospede> assimilarHospedes() throws IOException {
        Gson jsonObj = new Gson();
        File hospedeFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Hospedes.json");

        try {
            String dadosHospede = new String(Files.readAllBytes(Paths.get(hospedeFile.toURI())));
            ArrayList<Hospede> HospedeH = jsonObj.fromJson(dadosHospede, new TypeToken<ArrayList<Hospede>>() {
            }.getType());
            return HospedeH;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Função para descarregar dados referentes aos extratos de reservas cadastradas
     * 
     * @param extratoReservas lista de extratos de Reserva
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    // Q.10 - Cada reserva efetuada vai gerar um extrato que deverá ser impresso e
    // salvo junto com a informação do cliente que fez a reserva.
    public void descarregarExtratoReservas(ArrayList<String> extratoReservas) throws IOException {
        Gson jsonObjt = new Gson();
        File reservaFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Reservas.json");
        FileWriter reservaWriter = null;
        String dadosReservas = jsonObjt.toJson(extratoReservas);

        try {
            reservaWriter = new FileWriter("src\\main\\java\\br\\com\\pousada\\database\\Reservas.json");
            reservaWriter.write(dadosReservas);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            reservaWriter.close();
        }
    }

    /**
     * Função de assimilação de dados relacinados aos Extratos de Reserva
     * 
     * @return lista de extratos de reservas
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    // Q.10 - Cada reserva efetuada vai gerar um extrato que deverá ser impresso e
    // salvo junto com a informação do cliente que fez a reserva.
    public ArrayList<String> assimilarExtratoReservas() throws IOException {
        Gson jsonObjt = new Gson();
        File hospedeFile = new File("src\\mian\\java\\br\\com\\pousada\\database\\Reservas.json");

        try {
            String dadosHospede = new String(Files.readAllBytes(Paths.get(hospedeFile.toURI())));
            ArrayList<String> extratoReservas = jsonObjt.fromJson(dadosHospede, new TypeToken<ArrayList<String>>() {
            }.getType());
            return extratoReservas;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Função para descarregar dados referentes aos quartos
     * 
     * @param quarto lista de quartos cadastrados
     * @throws IOException exceçõa associada à manipulação de dados JSON
     */
    public void descarregarQuartos(Quarto[] quarto) throws IOException {
        Gson jsonObjt = new Gson();
        File quartoFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Quartos.json");

        FileWriter quartoWriter = null;
        String dadosQuartos = jsonObjt.toJson(quarto);

        try {
            quartoWriter = new FileWriter("src\\main\\java\\br\\com\\pousada\\database\\Quartos.json");
            quartoWriter.write(dadosQuartos);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            quartoWriter.close();
        }
    }

    /**
     * Função para assimilar dados referentes aos quartos
     * 
     * @return lsita de quartos
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public Quarto[] assimilarQuartos() throws IOException {
        Gson jsonObjt = new Gson();
        File quartoFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Qaurtos.json");

        try {
            String dadosQuartos = new String(Files.readAllBytes(Paths.get(quartoFile.toURI())));
            Quarto[] QuartoQ = jsonObjt.fromJson(dadosQuartos, new TypeToken<Quarto[]>() {
            }.getType());
            return QuartoQ;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Função para descarregar dados referentes aos Administradores do Sistema
     * 
     * @param adm Objeto de Administrador
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public void descarregarAdm(Administrador adm) throws IOException {
        Gson jsonObjt = new Gson();
        File admFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Administradores.json");
        FileWriter admWriter = null;
        String dadosAdm = jsonObjt.toJson(adm);

        try {
            admWriter = new FileWriter("src\\main\\java\\br\\com\\pousada\\database\\Administradores.json");
            admWriter.write(dadosAdm);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            admWriter.close();
        }
    }

    /**
     * Função para assimilar dados do Administrador salvos no sistema
     * 
     * @return obejto do tipo Administrador
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public Administrador assimilarAdministrador() throws IOException {
        Gson jsonObjt = new Gson();
        File admFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Administradores.json");

        try {
            String dadosAdm = new String(Files.readAllBytes(Paths.get(admFile.toURI())));
            Administrador admA = jsonObjt.fromJson(dadosAdm, new TypeToken<Administrador>() {
            }.getType());
            return admA;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Função para descarregar dados referentes aos Funcionários do Sistema
     * 
     * @param funci objeto de Funcionario
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public void descarregarFunci(Funcionario funci) throws IOException {
        Gson jsonObjt = new Gson();
        File funciFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Funcionarios.json");
        FileWriter funciWriter = null;
        String dadosFunci = jsonObjt.toJson(funci);

        try {
            funciWriter = new FileWriter("src\\main\\java\\br\\com\\pousada\\database\\Funcionarios.json");
            funciWriter.write(dadosFunci);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            funciWriter.close();
        }
    }

    /**
     * Função para assimilar dados do Funcionários salvos no sistema
     * 
     * @return obejto do tipo Funcionario
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public Funcionario assimilarFuncinario() throws IOException {
        Gson jsonObjt = new Gson();
        File funciFile = new File("src\\main\\java\\br\\com\\pousada\\database\\Funcionarios.json");

        try {
            String dadosFunci = new String(Files.readAllBytes(Paths.get(funciFile.toURI())));
            Funcionario funciF = jsonObjt.fromJson(dadosFunci, new TypeToken<Funcionario>() {
            }.getType());
            return funciF;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Função destinada à chamada das funções de assimilação de forma geral
     * 
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public void assimilarGeral() throws IOException {
        GerenciadorFunci.setFuncionario(assimilarFuncinario());
        GerenciadorAdm.setHospedes(assimilarHospedes());
        GerenciadorAdm.setQuartos(assimilarQuartos());
    }

    /**
     * Função destinada à chamada das funções de descarregamento de dados de forma
     * geral
     * 
     * @param menuColab
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public void descarregarGeral(GerenciadorFunci menuColab) throws IOException {
        descarregarColab(GerenciadorAdm.getColaboradores());
        descarregarHospede(GerenciadorAdm.getListaHospedes());
        descarregarQuartos(GerenciadorAdm.getQuartos());
        descarregarExtratoReservas(menuColab.extratosReservas());
    }

    // Q.3 - sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "Manipulação de Arquivos JSON";
    }
}
