import java.util.Scanner;

public class Main{
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        Arvore biblioteca;
        No novoNo = new No();
        Livro novoLivro = new Livro();
        int opcao, valor;

        System.out.println("Bem-vindo ao Skaab!");
        do{
            System.out.println("O que você deseja fazer?");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Buscar por título");
            System.out.println("4. Remover Livros");
            System.out.println("0. Sair do programa");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch(opcao){
                case 0:
                    System.out.println("Saindo do Skaab...");
                    break;
                case 1:

                    id = biblioteca.valorMaximo(biblioteca.getRaiz());
                    No no2 = new No();
                    no2.setLivro(novoLivro.Cadastro(id));
                    biblioteca.setRaiz(biblioteca.inserirNo(biblioteca.getRaiz(), no2));

            }
        } while(opcao != 0);
    }
}