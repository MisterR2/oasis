import java.util.Random;
import java.util.Scanner;
public class Livro{
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int qntdPag;
    private int pagLidas;
    
    public int getPagLidas() {
        return pagLidas;
    }

    public void setPagLidas(int pagLidas) {
        this.pagLidas = pagLidas;
    }

    public int rndID(){
        int rndId;
        String concatenatedNumbers = "";
        int[] randomSequence = new Random().ints(5, 0, 9).toArray();
        StringBuilder sb = new StringBuilder();
        for (int num : randomSequence) {
            sb.append(num);
        }
        concatenatedNumbers = sb.toString();
        rndId = Integer.parseInt(concatenatedNumbers);

        return rndId;
    }

    public Livro() {
        this.id = rndID();
        this.qntdPag = 0;
        this.pagLidas = 0;
    }

    public Livro(String titulo, String autor, String genero, int qntdPag, int pagLidas) {
        this.id = rndID();
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.qntdPag = qntdPag;
        this.pagLidas = pagLidas;
    }

    public Livro novoLivro(Scanner scanner) {
        Utils.clearConsole();
        String titulo, autor, genero;
        int quantPaginas, paginasLidas;

        System.out.println("Insira o título do livro: ");
        titulo = scanner.nextLine();
        System.out.println("Insira o autor do livro: ");
        autor = scanner.nextLine();
        System.out.println("Insira o gênero do livro: ");
        genero = scanner.nextLine();
        System.out.println("Insira a quantidade de páginas do livro: ");
        while (true) {
            try {
            quantPaginas = Integer.parseInt(scanner.nextLine());
            break;
            } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
            while (true) {
            try {
                System.out.println("Insira a quantidade de páginas lidas (ou deixe vazio para nenhuma):");
                String input = scanner.nextLine();
                
                if (input.isEmpty()) {
                    paginasLidas = 0;
                    break;
                } else {
                    int paginas = Integer.parseInt(input);
                    if (paginas > quantPaginas) {
                        System.out.println("A quantidade de páginas lidas não pode ser maior que a quantidade de páginas do livro.");
                    } else {
                        paginasLidas = paginas;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
        
        System.out.println("Livro inserido com sucesso!");
        System.out.println("Pressione qualquer tecla para continuar...");
        scanner.nextLine();

        return new Livro(titulo, autor, genero, quantPaginas, paginasLidas);
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }

    public String getGenero(){
        return genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }

    public int getQntdPag(){
        return qntdPag;
    }
    public void setQntdPag(int qntdPag){
        this.qntdPag = qntdPag;
    }

    public float getProgress(){
        return (float)pagLidas/qntdPag*100;
    }

    @Override
    public String toString(){
        return "ID: " + id + ", Título: " + titulo + ", Autor: " + autor + ", Gênero: " + genero + ", Páginas: " + qntdPag + ", Progresso: " + getProgress() + "%";
    }
}
