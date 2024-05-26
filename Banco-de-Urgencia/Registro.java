public abstract class Registro{
    protected String nome;
    protected String BI;
    protected String morada; 
    protected int idade;

    public Registro()
    {

    }
    
    public Registro(String nome, String BI, int idade, String morada)
    {
        setNome(nome);
        setBI(BI);
        setIdade(idade);
        setMorada(morada);
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public void setBI(String BI)
    {
        this.BI = BI;
    }
    public void setMorada(String morada)
    {
        this.morada = morada;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    

    public String getNome()
    {
        return this.nome;
    }
    public String getBI()
    {
        return this.BI;
    }
    public int getIdade()
    {
        return this.idade;
    }
    public String getMorada()
    {
        return this.morada;
    }
   



}
