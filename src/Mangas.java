import java.util.List;

public class Mangas {

    private String ISBN;
    private String titulo;
    private String autor;
    private int anoInicio;
    private int anoFim;
    private String genero;
    private String revista;
    private String editora;

    private int anoEdicao;
    private int quantidadeVolumes;
    private int quantidadeVolumesAdquiridos;

    private List<Integer> listaVolumesAdquiridos;

    /*
    public Mangas(String ISBN, String titulo, String autor, int anoInicio, int anoFim, String genero,
                  String revista, String editora, int anoEdicao,
                  int quantidadeVolumes, int quantidadeVolumesAdquiridos, List<Integer> listaVolumesAdquiridos) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.anoInicio = anoInicio;
        this.anoFim = anoFim;
        this.genero = genero;
        this.revista = revista;
        this.editora = editora;
        this.anoEdicao = anoEdicao;
        this.quantidadeVolumes = quantidadeVolumes;
        this.quantidadeVolumesAdquiridos = quantidadeVolumesAdquiridos;
        this.listaVolumesAdquiridos = listaVolumesAdquiridos;
    } */

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public int getAnoFim() {
        return anoFim;
    }

    public void setAnoFim(int anoFim) {
        this.anoFim = anoFim;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoEdicao() {
        return anoEdicao;
    }

    public void setAnoEdicao(int anoEdicao) {
        this.anoEdicao = anoEdicao;
    }

    public int getQuantidadeVolumes() {
        return quantidadeVolumes;
    }

    public void setQuantidadeVolumes(int quantidadeVolumes) {
        this.quantidadeVolumes = quantidadeVolumes;
    }

    public int getQuantidadeVolumesAdquiridos() {
        return quantidadeVolumesAdquiridos;
    }

    public void setQuantidadeVolumesAdquiridos(int quantidadeVolumesAdquiridos) {
        this.quantidadeVolumesAdquiridos = quantidadeVolumesAdquiridos;
    }

    public List<Integer> getListaVolumesAdquiridos() {
        return listaVolumesAdquiridos;
    }

    public void setListaVolumesAdquiridos(List<Integer> listaVolumesAdquiridos) {
        this.listaVolumesAdquiridos = listaVolumesAdquiridos;
    }
    @Override
    public String toString() {
        return "Título: " + titulo +
                ", ISBN: " + ISBN +
                ", Autor: " + autor +
                ", Ano de Início: " + anoInicio +
                ", Ano de Fim: " + anoFim +
                ", Gênero: " + genero +
                ", Revista: " + revista +
                ", Editora: " + editora +
                ", Ano da Edição: " + anoEdicao +
                ", Quantidade de Volumes: " + quantidadeVolumes +
                ", Quantidade de Volumes Adquiridos: " + quantidadeVolumesAdquiridos +
                ", Volumes Adquiridos: " + listaVolumesAdquiridos;
    }

}
