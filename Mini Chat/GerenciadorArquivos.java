/**
 * Chat
 * Author: Erivelto Clenio da Costa e Silva
 * Data: 31-03-2023
 */

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GerenciadorArquivos
{
    private boolean novo = false;
    private String CAMINHO_FICHEIRO = "anonima.txt";
    
    public GerenciadorArquivos(String caminhoArquivo)
    {
        this.CAMINHO_FICHEIRO = caminhoArquivo;
        if(!arquivoExiste(caminhoArquivo))
            criarFicheiro(caminhoArquivo);
    }

    private boolean arquivoExiste(String caminho){
        File ficheiro = new File(caminho);
        return (ficheiro.exists() && !ficheiro.isDirectory());
    }

    private synchronized void criarFicheiro(String caminhoFicheiro)
    {
        try(BufferedWriter w = Files.newBufferedWriter(Paths.get(caminhoFicheiro))){ 
            w.write("");
            w.flush();
            w.close();
            System.out.println("----------------ARQUIVO DA CONVERSA CRIADO!-----------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void anexarFicheiro(String conteudo)
    {
        try
        {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO, StandardCharsets.UTF_8, true));
            escritor.write(conteudo);
            escritor.write("\n");
            escritor.flush();
            escritor.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public synchronized ArrayList<String> lerFicheiro()
    {
        ArrayList<String> conteudo = new ArrayList<String>();
        try(BufferedReader leitor = Files.newBufferedReader(Paths.get(CAMINHO_FICHEIRO), StandardCharsets.UTF_8)) {
            String linha;
            while((linha = leitor.readLine())!=null)
                conteudo.add(linha);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo;
    }



    public synchronized boolean getNovo()
    {
        return this.novo;
    }
    
    /*private synchronized void escreverFicheiro(String caminhoFicheiro, String conteudo)
    {
        Path caminho = Paths.get(caminhoFicheiro);
        Charset utf8 = StandardCharsets.UTF_8;
        BufferedWriter w = null;
        try{
            w = Files.newBufferedWriter(caminho, utf8);    
            w.write(conteudo);
            w.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(w!=null)
                try {
                    w.close();   
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }*/
}