import java.io.*;
import java.util.*;

public class IndiceService {

    public static void salvarIndicePrimario(Map<String, Integer> indice, String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Map.Entry<String, Integer> entry : indice.entrySet()) {
                writer.write(entry.getKey() + "|" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar índice primário: " + e.getMessage());
        }
    }

    public static void salvarIndiceSecundario(Map<String, List<String>> indice, String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Map.Entry<String, List<String>> entry : indice.entrySet()) {
                for (String isbn : entry.getValue()) {
                    writer.write(entry.getKey() + "|" + isbn);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar índice secundário: " + e.getMessage());
        }
    }
}
