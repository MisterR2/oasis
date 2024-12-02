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
        
    }



}