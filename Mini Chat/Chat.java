import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Chat implements Runnable{
    GerenciadorArquivos gerente;
    private Leitor leitor;
    private Escritor escritor;
    private ArrayList<JanelaChat> participantes;
    
    public Chat()
    {
        String[] nomesPart = new String[Integer.parseInt(JOptionPane.showInputDialog("Quantos participantes do chat?"))];
        for(int i=0; i<nomesPart.length; i++)
            nomesPart[i] = JOptionPane.showInputDialog("Qual o nome do "+(i+1) +"º participante?").toUpperCase();
        
        this.gerente = new GerenciadorArquivos(this.getNomeArquivo(nomesPart));
        this.escritor = new Escritor(gerente);
        this.leitor = new Leitor(gerente); 
        participantes = new ArrayList<JanelaChat>();
        for(int i = 0;i < nomesPart.length; i++)
            participantes.add(new JanelaChat(nomesPart[i], escritor,leitor));
        new Thread(this).start();
    }

    private String getNomeArquivo(String[] nomes)
    {
        for(int i=0; i<nomes.length; i++)
            for(int y =i+1; y<nomes.length; y++)
                if(nomes[i].compareToIgnoreCase(nomes[y])>0)
                {
                    String aux = nomes[i];
                    nomes[i] = nomes[y];
                    nomes[y] = aux;
                }      
        String nomeArquivo = "";
        for (String nome: nomes)
            nomeArquivo +=nome;
        return ((nomeArquivo +=".txt").toUpperCase());
    }

    @Override
    public void run(){
        while(true)
        {
            try {
                Thread.sleep(150);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(int i=0; i<participantes.size(); i++)
                participantes.get(i).actualizar();
        }
    }
}