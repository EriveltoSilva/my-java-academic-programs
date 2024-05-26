import java.lang.IllegalArgumentException;
public class RastreioPaciente implements Comparable<RastreioPaciente>
{
    private RegistroPaciente paciente;
    private float temperatura;
    private float peso;
    private double pressaoArterial;
    private String estado;
    private int priopridade;

    public RastreioPaciente(RegistroPaciente paciente)
    {
        setPaciente(paciente);
    }

    public RastreioPaciente(RegistroPaciente paciente, float temperatura, float peso, double pressaoArterial)
    {
        this(paciente);
        setTemperatura(temperatura);
        setPeso(peso);
        setPressaoArterial(pressaoArterial);
    }

    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setPaciente(RegistroPaciente paciente)
    {
        this.paciente = paciente;
    }
    public void setTemperatura(float temperatura)
    {
        this.temperatura = temperatura;
    }
    public void setPeso(float peso)
    {
        this.peso = peso;
    }
    public void setPressaoArterial(double pressaoArterial)throws IllegalArgumentException
    {
        this.pressaoArterial = pressaoArterial;
        if(this.pressaoArterial>=140)
        {
            this.estado = "Grave";
            this.priopridade=1;
        }    
        else if(this.pressaoArterial > 110)
        {
            this.estado = "Urgente";
            this.priopridade=2;
        }   

        else if(this.pressaoArterial>80)
        {
            this.estado = "Moderado";
            this.priopridade=3;

        }
            
        else
            System.out.println("Valor de Pressao Invalido!");
    }       



    public RegistroPaciente getPaciente()
    {
        return this.paciente;
    }
    public float getTemperatura()
    {
        return this.temperatura;
    }
    public float getPeso()
    {
        return this.peso;
    }
    public double getPressaoArterial()
    {
        return this.pressaoArterial;
    }
    public String getEstado() {
        return this.estado;
    }
    public int getPriopridade() {
        return this.priopridade;
    }

    public String toString()
    {
        StringBuilder saida = new StringBuilder("-------------Dados do Rastreio do Paciente---------------\n");   
        saida.append(this.paciente);
        saida.append("\nTemperatura do Paciente.......:"+ this.temperatura);
        saida.append("\nPeso do Paciente..............:"+ this.peso);
        saida.append("\nPressao Arterial do Paciente..:"+ this.pressaoArterial);
        saida.append("\nEstado do Paciente............:"+ this.estado+"\n");
        return saida.toString();
    }

    public int compareTo(RastreioPaciente rastreio)
    {
        if(this.priopridade > rastreio.getPriopridade())
            return 1;
        else if(this.priopridade < rastreio.getPriopridade())
            return -1;
        return 0;
        // ou return (this.prioridade-rastreio.getPrioridade());    
    }

    
}
