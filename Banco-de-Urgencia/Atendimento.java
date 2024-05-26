import java.util.Date;

public class Atendimento {
    private Pagamento pagamento;
    private String doencaDiagnosticada;
    private RegistroMedico medico;
    private Date dataAtendimento;


    public Atendimento()
    {

    }
    
    public Atendimento(Pagamento pagamento)
    {
        this.pagamento = pagamento;
    }

    public void setDoencaDiagnosticada(String doencaDiagnosticada) {
        this.doencaDiagnosticada = doencaDiagnosticada;
    }
    public void setMedico(RegistroMedico medico) {
        this.medico = medico;
    }
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    public void setDataAtendimento() {
        this.dataAtendimento = new Date();
    }


    public String getDoencaDiagnosticada() {
        return doencaDiagnosticada;
    }
    public RegistroMedico getMedico() {
        return medico;
    }
    public Pagamento getPagamento() {
        return pagamento;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public String toString()
    {
        StringBuilder saida = new StringBuilder("-------------Registro de Pacientes Atendidos---------------\n");   
        saida.append(this.pagamento.toString());
        saida.append(this.medico.toString()+"\n");
        return saida.toString();
    }
    
}
