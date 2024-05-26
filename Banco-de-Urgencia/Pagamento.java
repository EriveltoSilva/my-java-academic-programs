public class Pagamento implements Comparable<Pagamento>{
    private RastreioPaciente rastreio;
    private double valorPagamento;

    public Pagamento(RastreioPaciente rastreio)
    {
        setRastreio(rastreio);
    }
    public Pagamento(RastreioPaciente rastreio, double valorPagamento)
    {
        this(rastreio);
        setValorPagamento(valorPagamento);
    }

    public void setValorPagamento(double valorPagamento)
    {
        this.valorPagamento = valorPagamento;
    }

    public void setRastreio(RastreioPaciente rastreio)
    {
        this.rastreio = rastreio;
    }

    
    public RastreioPaciente getRastreio()
    {
        return this.rastreio;
    }
    public double getValorPagamento()
    {
        return this.valorPagamento;
    }

    public String toString()
    {
        StringBuilder saida = new StringBuilder("-------------Registro do Pagamento---------------\n");   
        saida.append(this.rastreio.toString());
        saida.append("\nValor do Pagamento:............:"+ this.valorPagamento+"\n");
        return saida.toString();
    }

    public int compareTo(Pagamento pagamento)
    {
        if(this.rastreio.getPriopridade() > pagamento.getRastreio().getPriopridade())
            return 1;
        else if(this.rastreio.getPriopridade() < pagamento.getRastreio().getPriopridade())
            return -1;
        return 0;
        //return (this.rastreio.getPriopridade() - pagamento.getRastreio().getPriopridade());    
    }
    
}
