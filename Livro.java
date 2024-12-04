public class Livro{
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int qntdPag;

    public Livro(int id, String titulo, String autor, String genero, int qntdPag){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.qntdPag = qntdPag;
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

    @Override
    public String toString(){
        return "ID: " + id + ", Título: " + titulo + ", Autor: " + autor + ", Gênero: " + genero + ", Páginas: " + qntdPag;
    }
}
