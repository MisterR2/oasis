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
            int alturaEsq = getAltura(raiz.getEsq());
            int alturaDir = getAltura(raiz.getDir());

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

        return getAltura(no.getEsq()) - getAltura(no.getDir);
    }

    public No balancoDireita(No no){
        No novaRaiz  = no.getEsq();
        No outroNo = novaRaiz.getDir();

        novaRaiz.setDir(no);
        no.setEsc(outroNo);

        return novaRaiz;
    }

    public No balancoEsquerda(No no){
        No novaRaiz  = no.getDir();
        No outroNo = novaRaiz.getEsq();

        novaRaiz.setEsc(no);
        no.setDir(outroNo);

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
            raiz.setEsc(inserirNo(raiz.getEsq(), novoNo));
        } else if(idNovo > idRaiz){
            raiz.setDir(inserirNo(raiz.getDir(), novoNo));
        }else{
            return raiz;
        }
        
        int balanco = getBalanco(raiz);
        int idDirRaiz;
        int idEscRaiz;

        if(raiz.getDir() != null){
            idDirRaiz = raiz.getDir().getLivro().getId();
        }
        if(raiz.getEsq() != null){
            idDirRaiz = raiz.getEsq().getLivro().getId();
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
            raiz.etEsc(balancoEsquerda(raiz->getEsc()));
            return balancoDireita(raiz);
        }
        //direita-esquerda
        if(balanco < -1 && idNovo < idDirRaiz){
            raiz.setDir(balancoDireita(raiz->getDir()));
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
            return buscaNo(raiz.getEsc(), id);
        }else{
            return buscaNo(raiz.getDir(), id);
        }
    }

    public No valorminimo(No no){
        No noAtual = no;
        while(noAtual.getEsc() != null){
            noAtual = noAtual.getEsc();
        }

        return noAtual;
    }

    public No deletarNo(No raiz, int id){
        int idRaiz = raiz.getLivro().getId();
        if(raiz == null){
            return null;
        }else if(id < idRaiz){
            raiz.setEsc(deletarNo(raiz.getEsc(), id));
        }else if(id > idRaiz){
            raiz.setDir(deletarNo(raiz.getDir(), id));
        }else{
            if(raiz.getEsc() == null){
                return raiz.getDir(); 
            } else if(raiz.getDir() == null){
                return raiz.getEsc();
            }else{
                No temp = valorminimo(raiz.getDir());
                raiz.setLivro(temp.getLivro());
                raiz.setDir(deletarNo(raiz.getDir(), temp.getLivro().getId()));
            }

        }

        int balanco = getBalanco(raiz);

        //Esquerda
        if(balanco == 2 && getBalanco(raiz.getEsc()) >= 0)
            return balancoDireita(raiz);
        
        //Dupla Direita
        else if (balanco == 2 && getBalanco(raiz.getEsc()) == -1){
            raiz.setEsc(balancoEsquerda(raiz.getEsc()));
            return balancoDireita(raiz);
        }
        //direita
        else if(balanco == -2 && getBalanco(raiz.getDir()) <= 0){
            return balancoEsquerda(raiz);
        }
        //dupla esquerda
        else if(balanco == -2 && getBalanco(raiz.getDir()) == 1){
            raiz.setDir(balancoDireita(raiz.getDir()));
            return balancoEsquerda(raiz);
        }

        return raiz;
    }

}