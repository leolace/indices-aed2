import java.io.*;
import java.util.*;

public class MangasRepository {
    private List<Mangas> registros = new ArrayList<>();
    private Map<String, Integer> indicePrimario = new HashMap<>();
    private Map<String, List<Integer>> indiceSecundario = new HashMap<>();

    private final String arquivoDados = "mangas.txt";
    private final String arquivoIndicePrimario = "indice_primario.txt";
    private final String arquivoIndiceSecundario = "indice_secundario.txt";

    public MangasRepository() {
        carregarDados();
    }

    public void adicionarManga(Mangas manga) {
        registros.add(manga);
        int posicao = registros.size() - 1;
        indicePrimario.put(manga.getISBN(), posicao);
        indiceSecundario.computeIfAbsent(manga.getTitulo(), k -> new ArrayList<>()).add(posicao);
        salvarTudo();
    }

    public Mangas buscarPorISBN(String isbn) {
        Integer pos = indicePrimario.get(isbn);
        return (pos != null) ? registros.get(pos) : null;
    }

    public List<Mangas> buscarPorTitulo(String titulo) {
        List<Integer> posicoes = indiceSecundario.get(titulo);
        List<Mangas> encontrados = new ArrayList<>();
        if (posicoes != null) {
            for (Integer pos : posicoes) {
                encontrados.add(registros.get(pos));
            }
        }
        return encontrados;
    }

    public boolean removerManga(String isbn, Scanner scan) {
        Integer pos = indicePrimario.get(isbn);
        if (pos == null) return false;

        System.out.print("Tem certeza que deseja remover o mangá com ISBN " + isbn + "? (s/n): ");
        String confirm = scan.nextLine().trim().toLowerCase();
        if (!confirm.equals("s")) return false;

        registros.remove((int) pos);
        reindexar();
        salvarTudo();
        return true;
    }

    public void atualizarManga(Mangas manga) {
        Integer pos = indicePrimario.get(manga.getISBN());
        if (pos == null) {
            System.out.println("Mangá não encontrado para atualização.");
            return;
        }
        
        registros.set(pos, manga);
        reindexar();
        salvarTudo();
    }

    public List<Mangas> listarTodos() {
        return registros;
    }

    private void reindexar() {
        indicePrimario.clear();
        indiceSecundario.clear();
        for (int i = 0; i < registros.size(); i++) {
            Mangas m = registros.get(i);
            indicePrimario.put(m.getISBN(), i);
            indiceSecundario.computeIfAbsent(m.getTitulo(), k -> new ArrayList<>()).add(i);
        }
    }

    private void salvarTudo() {
        salvarRegistros();
        salvarIndices();
    }

    private void salvarRegistros() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDados))) {
            for (Mangas m : registros) {
                String linha = String.join(";",
                        m.getISBN(),
                        m.getTitulo(),
                        m.getAutor(),
                        String.valueOf(m.getAnoInicio()),
                        String.valueOf(m.getAnoFim()),
                        m.getGenero(),
                        m.getRevista(),
                        m.getEditora(),
                        String.valueOf(m.getAnoEdicao()),
                        String.valueOf(m.getQuantidadeVolumes()),
                        String.valueOf(m.getQuantidadeVolumesAdquiridos()),
                        m.getListaVolumesAdquiridos().toString().replace("[", "").replace("]", "").trim()
                );
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar registros: " + e.getMessage());
        }
    }

    private void salvarIndices() {
        IndiceService.salvarIndicePrimario(indicePrimario, arquivoIndicePrimario);

        Map<String, List<String>> secundarioPorTitulo = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : indiceSecundario.entrySet()) {
            List<String> isbns = new ArrayList<>();
            for (Integer pos : entry.getValue()) {
                isbns.add(registros.get(pos).getISBN());
            }
            secundarioPorTitulo.put(entry.getKey(), isbns);
        }
        IndiceService.salvarIndiceSecundario(secundarioPorTitulo, arquivoIndiceSecundario);
    }

    private void carregarDados() {
        File file = new File(arquivoDados);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";", -1);

                if (partes.length < 11) {
                    System.err.println("Linha inválida (campos insuficientes): " + linha);
                    continue;
                }

                Mangas m = new Mangas();
                m.setISBN(partes[0].trim());
                m.setTitulo(partes[1].trim());
                m.setAutor(partes[2].trim());
                m.setAnoInicio(parseIntSafe(partes[3]));
                m.setAnoFim(parseIntSafe(partes[4]));
                m.setGenero(partes[5].trim());
                m.setRevista(partes[6].trim());
                m.setEditora(partes[7].trim());
                m.setAnoEdicao(parseIntSafe(partes[8]));
                m.setQuantidadeVolumes(parseIntSafe(partes[9]));
                m.setQuantidadeVolumesAdquiridos(parseIntSafe(partes[10]));

                List<Integer> volumes = new ArrayList<>();
                if (partes.length > 11) {
                    String volumesStr = partes[11].replace("[", "").replace("]", "").trim();
                    if (!volumesStr.isEmpty()) {
                        for (String v : volumesStr.split(",")) {
                            if (!v.trim().isEmpty()) {
                                volumes.add(parseIntSafe(v));
                            }
                        }
                    }
                }
                m.setListaVolumesAdquiridos(volumes);
                registros.add(m);
            }

            reindexar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar registros: " + e.getMessage());
        }
    }

    private int parseIntSafe(String s) {
        s = s.trim();
        if (s.equals("-") || s.isEmpty()) {
            return -1;
        }
        return Integer.parseInt(s);
    }
}
