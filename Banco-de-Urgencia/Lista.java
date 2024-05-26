public class Lista<T>
{
    private No<T> primeiro;
    private No<T> ultimo;
    private int nElementos;

    public Lista()
    {
        this.nElementos=0;
    }

    private boolean isVazia()
    {
        return (this.nElementos==0);
    }
    public int totalElementos()
    {
        return this.nElementos;
    }

    public void adicionarFim(T elemento)
    {
        No<T> novo = new No<T>(elemento);
        if(isVazia())
        {
            this.primeiro = novo;
            this.ultimo = novo;
            nElementos++;
        }
        else
        {
            this.ultimo.setProximo(novo);
            novo.setAntecessor(this.ultimo);
            this.ultimo = novo;
            nElementos++;
        }
    }

    public T removerInicio()
    {
        if(isVazia())
        {
            throw new NullPointerException();
            //return null;
        }
        else
        {
            No<T> retirado = this.primeiro;
            No<T> sucessor = this.primeiro.getProximo();
            sucessor.setAntecessor(null);  //Dispensavel
            this.primeiro.setProximo(null);//Dispensavel
            this.primeiro = sucessor;
            this.nElementos--;
            return retirado.getElemento();
        }
    }

    public T removerFim()
    {
        if(isVazia())
        {
            //throw new NullPointerException();
            return null;
        }
        else
        {
            No<T> retirado = this.ultimo;
            No<T> novoUltimo = this.ultimo.getAntecessor();
            novoUltimo.setProximo(null);
            this.ultimo = novoUltimo;
            this.nElementos--;
            return retirado.getElemento();
        }
    }

    private boolean posicaoValida(int posicao)
    {
        return( posicao>=0 && posicao<nElementos);
    }
    public T pegar(T elemento, int posicao)
    {
        if(posicaoValida(posicao))
        {
            No<T> procurado = this.primeiro;
            for (int i = 0; i<nElementos; i++)
            {
                if(i==posicao)
                    return procurado.getElemento();
                procurado = procurado.getProximo();
            }   
            
        }
        else
        {
            System.out.println("Posicao Invalida!");
        }
        return null;
    }

    public String toString()
    {
        if(isVazia())
        {
            return (new StringBuilder("------------Lista Vazia------------").toString());
        }
        else
        {
            StringBuilder saida = new StringBuilder("--------------Lista de Elementos Atendidos");
            saida.append("------------------------------\n");
            No<T> referencia = this.primeiro;
            for(int i=0; i<this.nElementos; i++)
            {
                saida.append((int)(i+1));
                saida.append("."+referencia.getElemento());
                referencia = referencia.getProximo();
            }

            return saida.toString();
        }

    }
}