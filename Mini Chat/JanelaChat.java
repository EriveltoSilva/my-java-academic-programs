/**
 * File name : Chat
 * Author: Erivelto Clenio da Costa e Silva
 * Date: 31-03-2023
 * Objective: Criar um mini chat feito usando a linguagem java, e um arquivo
 *          para guardar as mensagens e actualizar as informações a cada usuario
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class JanelaChat extends JFrame{
    private final int largura = 600;
    private final int altura = 600;
    private int indice = 0;
    private String nome;
    private JPanel painelEntradas;
    private JTextField caixaTexto;
    private JTextArea visualizador;
    private JButton botaoEnviar;
    private Escritor escritor = null; 
    private Leitor leitor = null;
    
    public JanelaChat(String nome,Escritor escritor,Leitor leitor)
    {
        this.nome = nome;
        this.escritor = escritor;
        this.leitor = leitor;
        configJanela();
        inicializarComponentes();
        this.setVisible(true);;
    }
    
    private void configJanela()
    {
        this.setSize(largura, altura);
        this.setTitle("Chat-" + this.nome);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void enviarInfo()
    {
        escritor.setDadosArquivo(nome+":"+caixaTexto.getText());
        caixaTexto.setText("");
    }

    private void inicializarComponentes()
    {  
        caixaTexto = new JTextField(20); 
        botaoEnviar = new JButton("Enviar");
        painelEntradas = new JPanel(new FlowLayout());
        visualizador = new JTextArea();
        visualizador.setEditable(false);
        painelEntradas.add(caixaTexto);
        painelEntradas.add(botaoEnviar);
        this.add(BorderLayout.CENTER, new JScrollPane(visualizador));
        this.add(BorderLayout.SOUTH, painelEntradas);
        botaoEnviar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    enviarInfo();
                }    
            }
        );
        caixaTexto.addKeyListener(
            
            new KeyListener() {
                @Override
                public void keyReleased( KeyEvent event ){
                    if(event.getKeyChar()=='\n')
                        enviarInfo();
                }
                
                @Override
                public void keyPressed(KeyEvent event){
                }
                @Override
                public void keyTyped( KeyEvent event )
                {
                }
            }
        );
    }

    public void actualizar() 
    {
        ArrayList<String> texto =  this.leitor.getTextos(); 
        int tam = texto.size();
        for(; indice<tam;this.indice++)
        {
            if(texto.get(indice).contains(nome))// Direita - meu
                visualizador.setText(visualizador.getText()+"\t\t\t\t"+texto.get(indice).replace(nome, "Eu")+"\n\n");                
            else
                visualizador.setText(visualizador.getText()+texto.get(indice)+"\n\n");  
        }   
    }
    
}