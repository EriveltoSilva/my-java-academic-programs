import java.util.ArrayList;

public class Leitor implements Runnable{
    private GerenciadorArquivos gerente=null;
    private ArrayList<String> texto;
    
    public Leitor(GerenciadorArquivos gerente)
    {
        this.gerente = gerente;
        new Thread(this).start();
    }    
    

    public ArrayList<String> getDadosArquivos()
    {
        return this.gerente.lerFicheiro();
    }
    public ArrayList<String> getTextos()
    {
        return this.texto;
    }
    public String toString()
    {
        String saida = "";
        for (String  txt: this.texto) {
            saida += txt+"\n";
        }
        return saida;
    }

    @Override
    public void run()
    {
        while(true)
        {
            this.texto = getDadosArquivos();
            try{
                Thread.sleep(250); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}