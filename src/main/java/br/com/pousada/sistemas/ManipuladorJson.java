package br.com.pousada.sistemas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import br.com.pousada.pessoas.Colaborador;
import br.com.pousada.pessoas.Hospede;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Classe que contém métodos de manipulação de arquivos JSON
 *
 * @author Marcos Vinícius Santos Cruz
 * @author Filipe Fernades Costa
 */
/**
 * Q.14 - Salve e recupere todas as informações dos Clientes, Quartos,
 * Colaboradores e Reservas em um arquivo de texto. Utilizem classes já prontas
 * na internet que trabalhem com o formato json. Ao manipular um arquivo utilize
 * os conceitos aprendidos em aula para alocar e desalocar recursos com
 * segurança.
 */
public class ManipuladorJson {

    // Construtor padrão
    public ManipuladorJson() {
    }

    /**
     * Funcção que descarrega dados referentes aos colaboradores cadastrados
     * 
     * @param colab Lista de colaboradores do sistema
     * @throws IOException exceção associada à manipulação de dados JSON
     */
    public void descarregarColab(Colaborador colab) throws IOException {
        Gson jsonObt = new Gson(); // Criação de uma instância da classe Gson para realizar a
                                   // serialização/deserialização JSON.
        File colaboradorArquivo = new File("src\\main\\java\\br\\com\\pousada\\database\\Colaboradores.json");

        FileWriter colaboradorWriter = null;
        String dadosColaboradores = jsonObt.toJson(colab);

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
            String dadosHospede = new String(File.readAllBytes(Paths.get(hospedeFile.toURI())));
            ArrayList<Hospede> HospedeH = jsonObj.fromJson(dadosHospede, new TypeToken<ArrayList<Hospede>>() {
            }.getType());
            return HospedeH;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // ve o que ta dando de errado
    public ArrayList<String> gerarExtratoReserva() throws IOException {
        Gson jsonObt = new Gson();
        File hospedeArquivo = new File("src\\main\\java\\br\\com\\pousada\\database\\Reservas.json");

        try {
            String dadosHospede = new String(Files.readAllBytes(Paths.get(hospedeArquivo.toURI())));
            ArrayList<String> extratosReservas = jsonObt.fromJson(dadosHospede, new TypeToken<ArrayList<String>>() {
            }.getType());
            return extratosReservas;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Q.3 - sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "Manipulador JSON";
    }
}
