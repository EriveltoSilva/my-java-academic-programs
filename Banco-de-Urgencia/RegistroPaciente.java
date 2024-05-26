import java.util.Date;

public class RegistroPaciente extends Registro{
    private Date data;

    public RegistroPaciente()
    {

    }

    public RegistroPaciente(String nome, String BI, int idade, String morada)
    {
        super(nome, BI, idade, morada);
        setData();

    }

    public void setData()
    {
        //Ou DateFormat data = DataFormat.getDateInstance(DateFormat.SHORT);--} import java.text.DateFormat;
        this.data = new Date();
    }
    public Date getData()
    {
        return this.data;
    }

    public String toString()
    {
        StringBuilder saida = new StringBuilder("-------------Dados de Registro do Paciente---------------\n");   
        saida.append("Nome do Paciente............:"+ this.nome);
        saida.append("\nBI do Paciente..............:"+ this.BI);
        saida.append("\nIdade do Paciente...........:"+ this.idade);
        saida.append("\nMorada do Paciente..........:"+ this.morada);
        if(this.data!= null)
            saida.append("\nData da Entrada na Clinica..:"+ this.data.toString()+"\n");
        return saida.toString();
    }
  
}
