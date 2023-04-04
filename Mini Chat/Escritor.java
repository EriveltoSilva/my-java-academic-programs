public class Escritor implements Runnable
{
    private GerenciadorArquivos gerente = null;
    private String conteudo = null;

    public Escritor(GerenciadorArquivos gerente)
    {
        this.gerente = gerente;
        new Thread(this).start();
    }

    public synchronized void setDadosArquivo(String conteudo){
        this.conteudo = conteudo;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {Thread.sleep(50);} 
            catch (Exception e) { e.printStackTrace(); }
            if(conteudo!=null)
            {
                System.out.println("Conteudo:"+conteudo);
                this.gerente.anexarFicheiro(conteudo);
                conteudo=null;
            }
        }
    }
}
