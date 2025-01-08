import java.util.Scanner;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        Arvore biblioteca = new Arvore();
        int opcao;

        System.out.println("Bem-vindo ao Oasis!");
        do{
            Utils.clearConsole();
            System.out.println("O que você deseja fazer?");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Buscar por título");
            System.out.println("4. Remover Livros");
            System.out.println("5. Atualizar Livro");
            System.out.println("0. Sair do programa");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch(opcao){
                case -1:
                    biblioteca.renderizarArvore();
                    break;
                case 0:
                    System.out.println("Saindo do Oasis...");
                    break;
                case 1:
                    Livro novoLivro = new Livro().novoLivro(scanner);
                    No novoNo = new No();
                    novoNo.setLivro(novoLivro);
                    biblioteca.setRaiz(biblioteca.inserirNo(biblioteca.getRaiz(), novoNo));
                    break;
                case 2:
                    if (biblioteca.arvoreVazia()) {
                        Utils.clearConsole();
                        System.out.println("A biblioteca está vazia!");
                        System.out.println("Pressione qualquer tecla para continuar...");
                        scanner.nextLine();
                        
                    } else {
                        Utils.clearConsole();
                        System.out.println("LIVROS CADASTRADOS:");
                        biblioteca.listarLivros(biblioteca.getRaiz());
                        System.out.println("Pressione qualquer tecla para continuar...");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    if (biblioteca.arvoreVazia()) {
                        Utils.clearConsole();
                        System.out.println("A biblioteca está vazia!");
                        System.out.println("Pressione qualquer tecla para continuar...");
                        scanner.nextLine();
                    } else {
                        Utils.clearConsole();
                        System.out.println("Insira o título do livro que deseja buscar: ");
                        String titulo = scanner.nextLine();
                        No noBuscado = biblioteca.buscaNo(biblioteca.getRaiz(), titulo);
                        if (noBuscado != null) {
                            Utils.clearConsole();
                            System.out.println("Livro encontrado!");
                            System.out.println(noBuscado.getLivro());
                            System.out.println("Pressione qualquer tecla para continuar...");
                            scanner.nextLine();
                        } else {
                            Utils.clearConsole();
                            System.out.println("Livro não encontrado!");
                            System.out.println("Pressione qualquer tecla para continuar...");
                            scanner.nextLine();
                        }
                    }
                    break;
                case 4:
                    if (biblioteca.arvoreVazia()) {
                        Utils.clearConsole();
                        System.out.println("A biblioteca está vazia!");
                        System.out.println("Pressione qualquer tecla para continuar...");
                        scanner.nextLine();
                    } else {
                        Utils.clearConsole();
                        System.out.println("Insira o título do livro que deseja remover: ");
                        String titulo = scanner.nextLine();
                        No noRemovido = biblioteca.deletarNo(biblioteca.getRaiz(), titulo);
                        if (noRemovido != null) {
                            Utils.clearConsole();
                            System.out.println("Livro removido com sucesso!");
                            System.out.println("Pressione qualquer tecla para continuar...");
                            scanner.nextLine();
                        } else {
                            Utils.clearConsole();
                            System.out.println("Livro não encontrado!");
                            System.out.println("Pressione qualquer tecla para continuar...");
                            scanner.nextLine();
                        }
                    }
                    break;
                
                case 5:
                    if (biblioteca.arvoreVazia()) {
                        Utils.clearConsole();
                        System.out.println("A biblioteca está vazia!");
                        System.out.println("Pressione qualquer tecla para continuar...");
                        scanner.nextLine();
                    } else {
                        Utils.clearConsole();
                        System.out.println("Insira o título do livro que deseja atualizar: ");
                        String titulo = scanner.nextLine();
                        No noAtualizado = biblioteca.buscaNo(biblioteca.getRaiz(), titulo);
                        if (noAtualizado != null) {
                            Utils.clearConsole();
                            System.out.println("Livro encontrado!");
                            System.out.println("Insira a nova quantidade de páginas lidas (anteriormente - " + noAtualizado.getLivro().getPagLidas() + "): ");
                            int pagLidas = scanner.nextInt();
                            scanner.nextLine();
                            noAtualizado.getLivro().setPagLidas(pagLidas);
                            System.out.println("Livro atualizado com sucesso!");
                            System.out.println("Pressione qualquer tecla para continuar...");
                        } else {
                            Utils.clearConsole();
                            System.out.println("Livro não encontrado!");
                            System.out.println("Pressione qualquer tecla para continuar...");
                            scanner.nextLine();
                        }
                    }
                    break;
            }
        } while(opcao != 0);

        scanner.close();
    }

}