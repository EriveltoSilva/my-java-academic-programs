public class No <T>{

    private No<T> proximo;
    private No<T> antecessor;
    private T elemento;

    public No(T elemento)
    {
        this.elemento = elemento;
    }
    public No(No<T> proximo, T elemento)
    {
        this(elemento);
        this.proximo = proximo;
    }

    
    public void setElemento(T elemento)
    {
        this.elemento = elemento;
    }
    public void setProximo(No<T> proximo)
    {
        this.proximo = proximo;
    }
    public void setAntecessor(No<T> antecessor)
    {
        this.antecessor = antecessor;
    }



    public T getElemento()
    {
        return this.elemento;
    }
    public No<T> getProximo()
    {
        return this.proximo;
    }
    public No<T> getAntecessor()
    {
        return this.antecessor;
    }
}
