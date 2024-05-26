public class RegistroMedico extends Registro{
    private String especializacao;
    private int id;

    public RegistroMedico()
    {

    }
    
    public RegistroMedico(String nome, String especializacao, int id)
    {
        super();
        this.nome = nome;
        this.id = id;
        this.especializacao = especializacao;
    }

    public RegistroMedico(String nome, String BI, int idade, String morada, String especializacao, int id)
    {
        super(nome, BI, idade, morada);
        this.especializacao = especializacao;
        this.id = id;
    }

    public void setId(int id)
    {   
        this.id = id;
    }
    public void setEspecializacao(String especializacao)
    {
        this.especializacao = especializacao;
    }
    public String getEspecializacao()
    {
        return this.especializacao;
    }
    public int getId()
    {
        return this.id;
    }
    public String toString()
    {
        StringBuilder saida = new StringBuilder("-------------Dados Registro do Medico---------------\n");   
        saida.append("Nome do Medico......................:"+ this.nome);
        saida.append("\nEspecializacao do Medico............:"+ this.especializacao);
        saida.append("\nBI do Medico......................:"+ this.BI);
        saida.append("\nIdade do Medico...................:"+ this.idade);
        saida.append("\nMorada do Medico..................:"+ this.morada+"\n");

        return saida.toString();
    }
    
}
