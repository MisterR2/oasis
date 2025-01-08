import javax.swing.*;

public class Arvore{
    private No raiz;
    private TreePanel treePanel;

    public Arvore(){
        raiz = null;
        treePanel = new TreePanel(raiz);
    }

    public void listarLivros(No no) {
        if (no != null) {
            listarLivros(no.getEsquerda());
            System.out.println("\n");
            System.out.println(no.getLivro());
            System.out.println("\n");
            listarLivros(no.getDireita());
        }
    }

    public No getRaiz(){
        return this.raiz;
    }

    public void setRaiz(No novoNo){
        this.raiz = novoNo;
        treePanel.setRaiz(raiz);
    }

    public boolean arvoreVazia(){
        if(raiz == null){
            return true;
        } else {
            return false;
        }
    }

    public int getAltura(No raiz){
        if(raiz == null){
            return -1;
        }else{
            int alturaEsq = getAltura(raiz.getEsquerda());
            int alturaDir = getAltura(raiz.getDireita());

            if(alturaEsq > alturaDir){
                return alturaEsq + 1;
            }else{
                return alturaDir + 1;
            }
        }
    }

    public void renderizarArvore() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Árvore Binária");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(800, 600);
                frame.add(treePanel);
                frame.setVisible(true);
            }
        });
    }

    public int getBalanco(No no){
        if(no == null){
            return -1;
        }

        return getAltura(no.getEsquerda()) - getAltura(no.getDireita());
    }

    public No balancoDireita(No no){
        No novaRaiz  = no.getEsquerda();
        No outroNo = novaRaiz.getDireita();

        novaRaiz.setDireita(no);
        no.setEsquerda(outroNo);

        return novaRaiz;
    }

    public No balancoEsquerda(No no){
        No novaRaiz  = no.getDireita();
        No outroNo = novaRaiz.getEsquerda();

        novaRaiz.setEsquerda(no);
        no.setDireita(outroNo);

        return novaRaiz;
    }

    public int comparar(String antiga, String nova){
        int compare = antiga.compareToIgnoreCase(nova);
        return compare;
    }

    public No inserirNo(No raiz, No novoNo){
        if(raiz == null){
            raiz = novoNo;
            return raiz;
        }

        String TituloRaiz = raiz.getLivro().getTitulo();
        String TituloNovo = novoNo.getLivro().getTitulo();

        if(comparar(TituloRaiz, TituloNovo) > 0){
            raiz.setEsquerda(inserirNo(raiz.getEsquerda(), novoNo));
        } else if(comparar(TituloRaiz, TituloNovo) < 0){
            raiz.setDireita(inserirNo(raiz.getDireita(), novoNo));
        }else{
            return raiz;
        }
        
        int balanco = getBalanco(raiz);
        String TituloDirRaiz = null;
        String TituloEscRaiz = null;

        if(raiz.getDireita() != null){
            TituloDirRaiz = raiz.getDireita().getLivro().getTitulo();
        }
        if(raiz.getEsquerda() != null){
            TituloEscRaiz = raiz.getEsquerda().getLivro().getTitulo();
        }

        //Esquerda
        if(balanco > 1 && TituloEscRaiz != null && comparar(TituloEscRaiz, TituloNovo) > 0){
            return balancoDireita(raiz);
        }

        //Direita
        if(balanco < -1 && TituloDirRaiz != null && comparar(TituloDirRaiz,TituloNovo) < 0){
            return balancoEsquerda(raiz);
        }

        //esquerda-direita
        if(balanco > 1 && comparar(TituloEscRaiz, TituloNovo) < 0){
            raiz.setEsquerda(balancoEsquerda(raiz.getEsquerda()));
            return balancoDireita(raiz);
        }
        //direita-esquerda
        if(balanco < -1 && comparar(TituloDirRaiz,TituloNovo) > 0){
            raiz.setDireita(balancoDireita(raiz.getDireita()));
            return balancoEsquerda(raiz);
        }

        return raiz;
    
    }

    public No buscaNo(No raiz, String titulo){
        if(raiz == null){
            return raiz;
        }

        String tituloRaiz = raiz.getLivro().getTitulo();
        comparar(tituloRaiz, titulo);
        if(tituloRaiz.equalsIgnoreCase(titulo)){
            return raiz;
        }else if(comparar(tituloRaiz, titulo) > 0){
            return buscaNo(raiz.getEsquerda(), titulo);
        }else{
            return buscaNo(raiz.getDireita(), titulo);
        }
    }

    public No valorminimo(No no){
        No noAtual = no;
        while(noAtual.getEsquerda() != null){
            noAtual = noAtual.getEsquerda();
        }

        return noAtual;
    }

    public No deletarNo(No raiz, String titulo){
        if(raiz == null){
            return null;
        }
        String tituloRaiz = raiz.getLivro().getTitulo();
        if(comparar(tituloRaiz, titulo) > 0){
            raiz.setEsquerda(deletarNo(raiz.getEsquerda(), titulo));
        }else if(comparar(tituloRaiz, titulo) < 0){
            raiz.setDireita(deletarNo(raiz.getDireita(), titulo));
        }else{
            if(raiz.getEsquerda() == null){
                return raiz.getDireita(); 
            }else if(raiz.getDireita() == null){
                return raiz.getEsquerda();
            }else{
                No temp = valorminimo(raiz.getDireita());
                raiz.setLivro(temp.getLivro());
                raiz.setDireita(deletarNo(raiz.getDireita(), temp.getLivro().getTitulo()));
            }

        }

        int balanco = getBalanco(raiz);

        //Esquerda
        if(balanco == 2 && getBalanco(raiz.getEsquerda()) >= 0)
            return balancoDireita(raiz);
        
        //Dupla Direita
        else if (balanco == 2 && getBalanco(raiz.getEsquerda()) == -1){
            raiz.setEsquerda(balancoEsquerda(raiz.getEsquerda()));
            return balancoDireita(raiz);
        }
        //direita
        else if(balanco == -2 && getBalanco(raiz.getDireita()) <= 0){
            return balancoEsquerda(raiz);
        }
        //dupla esquerda
        else if(balanco == -2 && getBalanco(raiz.getDireita()) == 1){
            raiz.setDireita(balancoDireita(raiz.getDireita()));
            return balancoEsquerda(raiz);
        }

        return raiz;
    }

}