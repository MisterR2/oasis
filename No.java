public class No{
    private Livro livro;
    private No esquerda, direita;

    public No(Livro livro){
        this.livro = livro;
        this.esquerda = null;
        this.direita = null;
    }

    public No(){
        this.livro = null;
        this.esquerda = null;
        this.direita = null;
    }

    public Livro getLivro(){
        return this.livro;
    }

    public No getDireita(){
        return this.direita;
    }

    public No getEsquerda(){
        return this.esquerda;
    }

    public void setEsquerda(No novoNo){
        this.esquerda = novoNo;
    }

    public void setDireita(No novoNo){
        this.direita = novoNo;
    }

    public void setLivro(Livro novoLivro){
        this.livro = novoLivro;
    }
}