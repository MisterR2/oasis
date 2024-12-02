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

}