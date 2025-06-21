import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MangaEntity {
  public static Mangas create(Scanner scan) {
    Mangas m = new Mangas();

    System.out.println("\nNovo Mangá:");

    System.out.print("ISBN: ");
    m.setISBN(scan.nextLine());

    System.out.print("Título: ");
    m.setTitulo(scan.nextLine());

    System.out.print("Autor: ");
    m.setAutor(scan.nextLine());

    System.out.print("Ano de Início: ");
    m.setAnoInicio(scan.nextInt());

    System.out.print("Ano de Fim: ");
    m.setAnoFim(scan.nextInt());
    scan.nextLine();

    System.out.print("Gênero: ");
    m.setGenero(scan.nextLine());

    System.out.print("Revista: ");
    m.setRevista(scan.nextLine());

    System.out.print("Editora: ");
    m.setEditora(scan.nextLine());

    System.out.print("Ano da Edição: ");
    m.setAnoEdicao(scan.nextInt());

    System.out.print("Quantidade de Volumes: ");
    m.setQuantidadeVolumes(scan.nextInt());

    System.out.print("Quantidade de Volumes Adquiridos: ");
    int qtdAdquiridos = scan.nextInt();
    m.setQuantidadeVolumesAdquiridos(qtdAdquiridos);

    List<Integer> volumes = new ArrayList<>();
    for (int j = 0; j < qtdAdquiridos; j++) {
      System.out.print("Número do volume adquirido #" + (j + 1) + ": ");
      volumes.add(scan.nextInt());
    }
    scan.nextLine();
    m.setListaVolumesAdquiridos(volumes);

    return m;
  }

  public static Mangas update(Mangas mangaToUpdate, Scanner scan) {
    if (mangaToUpdate == null) {
      System.out.println("Manga não encontrado.");
      return null;
    }

    boolean atualizando = true;
    while (atualizando) {
      System.out.println("\nQual propriedade deseja alterar?");
      System.out.println("1. Título");
      System.out.println("2. Autor");
      System.out.println("3. Ano de Início");
      System.out.println("4. Ano de Fim");
      System.out.println("5. Gênero");
      System.out.println("6. Revista");
      System.out.println("7. Editora");
      System.out.println("8. Ano da Edição");
      System.out.println("9. Quantidade de Volumes");
      System.out.println("10. Quantidade de Volumes Adquiridos e lista");
      System.out.println("0. Salvar e sair");
      System.out.print("Escolha: ");
      int escolha = scan.nextInt();
      scan.nextLine();
      switch (escolha) {
        case 1:
          System.out.print("Novo título: ");
          mangaToUpdate.setTitulo(scan.nextLine());
          break;
        case 2:
          System.out.print("Novo autor: ");
          mangaToUpdate.setAutor(scan.nextLine());
          break;
        case 3:
          System.out.print("Novo ano de início: ");
          mangaToUpdate.setAnoInicio(scan.nextInt());
          scan.nextLine();
          break;
        case 4:
          System.out.print("Novo ano de fim: ");
          mangaToUpdate.setAnoFim(scan.nextInt());
          scan.nextLine();
          break;
        case 5:
          System.out.print("Novo gênero: ");
          mangaToUpdate.setGenero(scan.nextLine());
          break;
        case 6:
          System.out.print("Nova revista: ");
          mangaToUpdate.setRevista(scan.nextLine());
          break;
        case 7:
          System.out.print("Nova editora: ");
          mangaToUpdate.setEditora(scan.nextLine());
          break;
        case 8:
          System.out.print("Novo ano da edição: ");
          mangaToUpdate.setAnoEdicao(scan.nextInt());
          scan.nextLine();
          break;
        case 9:
          System.out.print("Nova quantidade de volumes: ");
          mangaToUpdate.setQuantidadeVolumes(scan.nextInt());
          scan.nextLine();
          break;
        case 10:
          System.out.print("Nova quantidade de volumes adquiridos: ");
          int qtdAdq = scan.nextInt();
          mangaToUpdate.setQuantidadeVolumesAdquiridos(qtdAdq);
          List<Integer> novosVolumes = new java.util.ArrayList<>();
          for (int j = 0; j < qtdAdq; j++) {
            System.out.print("Número do volume adquirido #" + (j + 1) + ": ");
            novosVolumes.add(scan.nextInt());
          }
          scan.nextLine();
          mangaToUpdate.setListaVolumesAdquiridos(novosVolumes);
          break;
        case 0:
          atualizando = false;
          break;
        default:
          System.out.println("Opção inválida.");
      }
    }

    return mangaToUpdate;
  }
}
