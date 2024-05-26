public class FilaPrioridade<T>{

    private T[] filaGrave;
    private T[] filaUrgente;
    private T[] filaModerada;
    private int nElementosGrave;
    private int nElementosUrgente;
    private int nElementosModerado;
    private String nomeFila;
    
    public FilaPrioridade()
    {
        this.nElementosGrave = 0;
        this.nElementosUrgente = 0;
        this.nElementosModerado = 0;
        this.filaGrave =(T[]) new Object[10];
        this.filaUrgente =(T[]) new Object[10];
        this.filaModerada =(T[]) new Object[10];
    }

    public FilaPrioridade(String nomeFila)
    {
        this();
        this.nomeFila = nomeFila;
    }
    
    public boolean isVazia()
    {
        return (nElementosGrave==0 && nElementosUrgente==0 && nElementosModerado==0);
    }

    public int totalElementos()
    {
        return (this.nElementosGrave+this.nElementosUrgente+ this.nElementosModerado);
    }

    private void aumentarCapacidade(T[] fila)
    {
        T[] novaFila = (T[]) new Object[fila.length*2];
        for(int i=0; i<fila.length; i++)
            novaFila[i]= fila[i];
        fila = novaFila;
    }

    public void enfileirar(T elemento, String estado)
    {
        
        if(estado.equalsIgnoreCase("Grave"))
        {
            if(nElementosGrave==filaGrave.length)
                aumentarCapacidade(filaGrave);
            filaGrave[nElementosGrave++] = elemento;

        }
        else if(estado.equalsIgnoreCase("Urgente"))
        {
            if(nElementosUrgente==filaUrgente.length)
                aumentarCapacidade(filaUrgente);
            filaUrgente[nElementosUrgente++] = elemento;
        }
        else if(estado.equalsIgnoreCase("Moderado"))
        {   
            if(nElementosModerado==filaModerada.length)
                aumentarCapacidade(filaModerada);
            filaModerada[nElementosModerado++] = elemento;  
        }

    }


    private void realocarPosicaoInical(T[] fila, int nElem)
    {
        for(int i = 0; i<=nElem-2; i++)
            fila[i] = fila[i+1];
    }


    public T desenfileirar()
    {
        if(isVazia())
        {
            return null;
        }
        else
        {
            if(nElementosGrave>0)
            {
                T removido = filaGrave[0];
                realocarPosicaoInical(filaGrave, nElementosGrave);
                nElementosGrave--;
                return removido;
            }
            else if(nElementosUrgente>0)
            {
                T removido = filaModerada[0];
                realocarPosicaoInical(filaUrgente, nElementosUrgente);
                nElementosUrgente--;
                return removido;
            }
            else if(nElementosModerado>0)
            {
                T removido = filaModerada[0];
                realocarPosicaoInical(filaModerada, nElementosModerado);
                nElementosModerado--;
                return removido;
            }
        }
        return null;
        
    }

    public T primeiroNaFila()
    {
        if(nElementosGrave>0)
            return filaGrave[0];
        else if(nElementosUrgente>0)
            return filaUrgente[0];
        else if(nElementosModerado>0)
            return filaModerada[0];
        else 
            return null;        
    }





    public int getnElementosGrave() {
        return nElementosGrave;
    }
    public int getnElementosUrgente() {
        return nElementosUrgente;
    }
    public int getnElementosModerado() {
        return nElementosModerado;
    }
    

    public String toString()
    {
        if(isVazia())
        {
            return (new StringBuilder("------------Lista Vazia-"+nomeFila+ "-----------").toString());
        }
        else
        {
            StringBuilder saida = new StringBuilder("--------------Lista de Elementos na Fila");
            if(this.nomeFila == null) saida.append("");
            else saida.append(nomeFila);
            saida.append("------------------------------\n");
           
            int i;
            for (i=0; i<this.nElementosGrave; i++)
            {
                saida.append((int)(i+1));
                saida.append("."+filaGrave[i]);
                saida.append("\n");
            }
            int j=0;
            for(j=0; j<this.nElementosUrgente; j++)
            {
                saida.append((int)(j+i+1));
                saida.append("."+filaUrgente[j]);
                saida.append("\n");
            }
            int k=0;
            for(k=0; k<this.nElementosModerado; k++)
            {
                saida.append((int)(k+j+i+1));
                saida.append("."+filaModerada[k]);
                saida.append("\n");
            }

            return saida.toString();
        }
    }

}

