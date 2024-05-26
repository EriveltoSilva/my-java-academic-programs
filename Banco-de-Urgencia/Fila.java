public class Fila<T>{
    private No<T> primeiro;
    private No<T> ultimo;
    private int nElementos;
    private String nomeFila;
    
    public Fila()
    {
        this.nElementos = 0;
    }

    public Fila(String nomeFila)
    {
        this();
        this.nomeFila = nomeFila;
    }
    
    private boolean isVazia()
    {
        return (nElementos==0);
    }

    public int totalElementos()
    {
        return this.nElementos;
    }

    public T primeiroNaFila()
    {
        if(nElementos>0)
            return primeiro.getElemento();
        else 
            return null;        
    }

    void enfileirar(T elemento)
    {
        if(isVazia())
        {
            No<T> novo = new No<T>(elemento);
            this.primeiro = novo;
            this.ultimo = novo;
        }
        else
        {
            No<T> novo = new No<T>(elemento);
            this.ultimo.setProximo(novo);
            novo.setAntecessor(this.ultimo);
            this.ultimo = novo;
        }
        this.nElementos++;

    }

    public T desenfileirar()
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
            //sucessor.setAntecessor(null);  //Dispensavel
            //this.primeiro.setProximo(null);//Dispensavel
            this.primeiro = sucessor;
            this.nElementos--;
            return retirado.getElemento();
        }
    }

    public void setPrimeiro(No<T> primeiro) {
        this.primeiro = primeiro;
    }
    public void setUltimo(No<T> ultimo) {
        this.ultimo = ultimo;
    }
    public void setnElementos(int nElementos) {
        this.nElementos = nElementos;
    }

    public No<T> getPrimeiro() {
        return primeiro;
    }
    public No<T> getUltimo() {
        return ultimo;
    }
    public int getnElementos() {
        return nElementos;
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
            
            if(this.nomeFila == null) 
                saida.append("");
            else 
                saida.append(nomeFila);
            
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