public class ListaLinear<T> {
    private T[] lista;
    private int nElementos=0;

    public ListaLinear()
    {
        this.nElementos=0;
        lista =  (T[]) new Object[10];
    }

    private boolean isVazia()
    {
        return (this.nElementos==0);
    }

    public int totalElementos()
    {
        return this.nElementos;
    }
    
    private boolean isCheia()
    {
         return (nElementos== lista.length);
    }

    public T pegarElemento(int posicao)
    {
        if(posicaoOcupada(posicao))
            return lista[posicao];
        else
            System.out.println("Posicao Invalida!");
        return null;
    }

    private T[] aumentarCapacidade(T[] lista)
    {
        T[] novaLista = (T[]) new Object[lista.length*2];
        for(int i=0; i<lista.length; i++)
            novaLista[i] = lista[i];
        return novaLista;
    }

    public void adicionarFim(T elemento)
    {
        if(isCheia())
            lista = aumentarCapacidade(this.lista);
        else 
        {
            this.lista[nElementos] = elemento;
            nElementos++;
        }
    }

    public void alterarPosicao(T elemento, int posicao) 
    {
        if(posicaoOcupada(posicao))
        {
            lista[posicao] = elemento;
        }
        else
        {   
            System.out.println("ERRO:POSICAO INVALIDA!");
        } 
    }

    private void realocarPosicao(int posicao)
    {
        for(int i = posicao; i<=this.nElementos-2; i++)
            this.lista[i] = this.lista[i+1];
    }

    public T removerInicio()
    {
        if(!isVazia())
        {
            T retirado = this.lista[0];
            realocarPosicao(0);
            this.nElementos--;
            return retirado;
        }
        return null;
    }

    public T removerPosicao(int posicao)
    {
        if(posicaoOcupada(posicao))
        {
            T retirado = this.lista[posicao];
            realocarPosicao(posicao);
            this.nElementos--;
            return retirado;
        }
        return null;
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
            T retirado = lista[nElementos-1];
            this.nElementos--;
            return retirado;
        }
    }

    private boolean posicaoOcupada(int posicao)
    {
        return (posicao>=0 && posicao<nElementos);
    }

    private boolean posicaoValida(int posicao)
    {
        return( posicao>=0 && posicao<=nElementos);
    }

    

    public String toString()
    {
        if(isVazia())
        {
            return (new StringBuilder("------------Lista Vazia-----------").toString());
        }
        else
        {
            StringBuilder saida = new StringBuilder("--------------Lista de Elementos ");
            if(this.lista == null) saida.append("");
            saida.append("------------------------------\n");
           
            int i;
            for (i=0; i<this.nElementos; i++)
            {
                saida.append((int)(i+1));
                saida.append("."+lista[i]);
                saida.append("\n");
            }

            return saida.toString();
        }
    }

}
