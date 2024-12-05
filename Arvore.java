public class Arvore{
    private No raiz;

    public Arvore(){
        raiz == null;
    }

    public No getRaiz(){
        return this.raiz;
    }

    public void setRaiz(No novoNo){
        this.raiz = novoNo;
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

    int getBalanco(No no){
        if(no == null){
            return -1;
        }

        return getAltura(no.getEsquerda()) - getAltura(no.getDireita);
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

    public No inserirNo(No raiz, No novoNo){
        if(raiz == null){
            raiz = novoNo;
            return raiz;
        }

        int idRaiz = raiz.getLivro().getId();
        int idNovo = novoNo.getLivro().getId();

        if(idNovo < idRaiz){
            raiz.setEsquerda(inserirNo(raiz.getEsquerda(), novoNo));
        } else if(idNovo > idRaiz){
            raiz.setDireita(inserirNo(raiz.getDireita(), novoNo));
        }else{
            return raiz;
        }
        
        int balanco = getBalanco(raiz);
        int idDirRaiz;
        int idEscRaiz;

        if(raiz.getDireita() != null){
            idDirRaiz = raiz.getDireita().getLivro().getId();
        }
        if(raiz.getEsq() != null){
            idDirRaiz = raiz.getEsquerda().getLivro().getId();
        }

        //Esquerda
        if(balanco > 1 && idNovo < idEscRaiz){
            return balancoDireita(raiz);
        }

        //Direita
        if(balanco < -1 && idNovo > idDirRaiz){
            return balancoEsquerda(raiz);
        }

        //esquerda-direita
        if(balanco > 1 && idNovo > idEscRaiz){
            raiz.setEsquerda(balancoEsquerda(raiz->getEsquerda()));
            return balancoDireita(raiz);
        }
        //direita-esquerda
        if(balanco < -1 && idNovo < idDirRaiz){
            raiz.setDireita(balancoDireita(raiz->getDireita()));
            return balancoEsquerda(raiz);
        }

        return raiz;
    
    }

    public No buscaNo(No raiz, int id){
          if(raiz == null){
            return raiz;
        }

        int idRaiz = raiz.getLivro().getId();
        if(idRaiz == id){
            return raiz;
        }else if(id < idRaiz){
            return buscaNo(raiz.getEsquerda(), id);
        }else{
            return buscaNo(raiz.getDireita(), id);
        }
    }

    public No valorminimo(No no){
        No noAtual = no;
        while(noAtual.getEsquerda() != null){
            noAtual = noAtual.getEsquerda();
        }

        return noAtual;
    }

    public No deletarNo(No raiz, int id){
        int idRaiz = raiz.getLivro().getId();
        if(raiz == null){
            return null;
        }else if(id < idRaiz){
            raiz.setEsquerda(deletarNo(raiz.getEsquerda(), id));
        }else if(id > idRaiz){
            raiz.setDireita(deletarNo(raiz.getDireita(), id));
        }else{
            if(raiz.getEsquerda() == null){
                return raiz.getDireita(); 
            }else if(raiz.getDireita() == null){
                return raiz.getEsquerda();
            }else{
                No temp = valorminimo(raiz.getDireita());
                raiz.setLivro(temp.getLivro());
                raiz.setDireita(deletarNo(raiz.getDireita(), temp.getLivro().getId()));
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