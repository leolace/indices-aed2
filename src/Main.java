import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    MangasRepository repo = new MangasRepository();

    while (true) {
      System.out.println(
          "\n1. Adicionar Mangá\n2. Buscar por ISBN\n3. Buscar por Título\n4. Atualizar\n5. Remover\n6. Listar\n0. Sair");
      System.out.print("Escolha: ");
      int op = scan.nextInt();
      scan.nextLine();

      switch (op) {
        case 1:
          Mangas novo = MangaEntity.create(scan);
          repo.adicionarManga(novo);
          break;
        case 2:
          System.out.print("ISBN: ");
          String isbn = scan.nextLine();
          System.out.println(repo.buscarPorISBN(isbn));
          break;
        case 3:
          System.out.print("Título: ");
          String titulo = scan.nextLine();
          repo.buscarPorTitulo(titulo).forEach(System.out::println);
          break;
        case 4:
          System.out.print("ISBN: ");
          String isbnToFind = scan.nextLine();
          Mangas mangaToUpdate = repo.buscarPorISBN(isbnToFind);

          MangaEntity.update(mangaToUpdate, scan);
          repo.atualizarManga(mangaToUpdate);
          System.out.println(mangaToUpdate.toString());
          System.out.println("Mangá atualizado com sucesso!");
          break;
        case 5:
          System.out.print("ISBN para remover: ");
          String isbnRemover = scan.nextLine();
          repo.removerManga(isbnRemover, scan);
          break;
        case 6:
          repo.listarTodos().forEach(System.out::println);
          break;
        case 0:
          scan.close();
          return;
        default:
          System.out.println("Opção inválida.");
      }
    }
  }
}
