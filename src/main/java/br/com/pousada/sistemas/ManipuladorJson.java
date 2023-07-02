package br.com.pousada.sistemas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import br.com.pousada.pessoas.Colaborador;
import com.google.gson.Gson;

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

    public void descarregarColab(Colaborador colab) throws IOException {
        Gson jsonObt = new Gson();
        File colaboradorArquivo = new File("");

        FileWriter colaboradorWriter = null;
        String dadosColaboradores = jsonObt.toJson(colab);
    }

    // Q.3 - sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "Manipulador JSON";
    }
}
