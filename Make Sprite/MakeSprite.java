/* 
 * Autor: Erivelto Clénio da Costa e Silva
 * Data última actualização: 02/03/2023
 * 
 * Objectivo do Programa: Fazer um programa que:
 *  1-carrega quatro imagens,
 *  2-retira o fundos das quatro imagens com base numa cor definido como cor a remover,
 *  3-inverte as quatro imagens (efeito espelho)
 *  4-cria uma nova imagem que contem dentro de si as outras quatro com as alterações efectuadas
 *  5-guarda essa nova imagem como imagemFinal.png
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MakeSprite extends JFrame{
    int largura= 1050, altura = 850;
    public MakeSprite()
    {
        super("Fazendo Sprite...");
        setSize(largura, altura);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.red);
        setVisible(true);
    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        //Colocando Fundo de ecrã verde só para visualizar bem o contraste
        g2.setColor(Color.green);
        g2.fillRect(0, 0, largura, altura);

        //Carregando as 4 imagens
        BufferedImage imagem1 = carregarImagem("imagem1.png");
        BufferedImage imagem2 = carregarImagem("imagem2.png");
        BufferedImage imagem3 = carregarImagem("imagem3.png");
        BufferedImage imagem4 = carregarImagem("imagem4.png");
        
        //Retirano ou tornadno as cores escolhidas como transparentes
        imagem1 = retiraFundo(imagem1, new Color(25, 25, 25));
        imagem2 = retiraFundo(imagem2, new Color(25, 25, 25));
        imagem3 = retiraFundo(imagem3, new Color(25, 25, 25));
        imagem4 = retiraFundo(imagem4, new Color(25, 25, 25));

        //Invertendo as imagens
        imagem1 = inverterImagem(imagem1);
        imagem2 = inverterImagem(imagem2);
        imagem3 = inverterImagem(imagem3);
        imagem4 = inverterImagem(imagem4);

        //Juntar as 4 imagens numa só
        BufferedImage imagemFinal = juntarImagens(imagem1, imagem2, imagem3, imagem4);
        
        //Desenhando o resultado na tela
        g2.drawImage(imagemFinal, 0, 0, null);

        //Guardando a imagem final em um arquivo
        guardarImagem(imagemFinal, "imagemFinal.png");
    }

    
        /*
         * Objectivo: Carregar imagens no programa
         * Entrada: Uma string com o caminho absoluto ou relativo da imagem
         * Saida: Um BufferedImage contendo a imagem carregada
         */
        public BufferedImage carregarImagem(String caminho){
            BufferedImage img = null;
            try {
                    img = ImageIO.read(new File(caminho));
            } catch (IOException ex) {
                Logger.getLogger(MakeSprite.class.getName()).log(Level.SEVERE, null, ex);
            }
            return img;
        }

        /*
         * Objectivo: Retirar uma data cor na imagem, e tornar a area ocupada por aquela cor como uma área transparente
         * Entrada: Uma BufferedImage com a imagem carregada, e a cor que se deve procurar e tornar transparente na iamgens
         * Saida: Uma BufferedImage contendo a imagem com o fundo já transparente
         */
        public BufferedImage retiraFundo(BufferedImage img, Color colorProcura){
            int margemPrecisao = 5;
            for(int i=0; i < img.getWidth(null); i++)
            {
                for(int j=0; j< img.getHeight(null); j++)
                {
                    Color corPixel = new Color(img.getRGB(i, j)); 
                    if(
                        ((colorProcura.getRed()-margemPrecisao <= corPixel.getRed() && colorProcura.getRed()+margemPrecisao >= corPixel.getRed()))
                    && 
                    ((colorProcura.getGreen()-margemPrecisao <= corPixel.getGreen() && colorProcura.getGreen()+margemPrecisao >= corPixel.getGreen()))
                    && 
                    ((colorProcura.getBlue()-margemPrecisao <= corPixel.getBlue() && colorProcura.getBlue()+margemPrecisao >= corPixel.getBlue()))
                    )
                    {
                        img.setRGB(i,j,img.getTransparency());
                        //img.setRGB(i,j,new Color(0, 255, 255).getRGB());
                    }
                }
            }            
            return img;
        }

        /*
         * Objectivo: Inverter uma imagem criando um efeito espelho
         * Entrada: Um BufferedImage contendo a imagem carregada
         * Saida: Uma BufferedImage contendo a imagem já invertida
         */
        public BufferedImage inverterImagem(BufferedImage img){
            BufferedImage invertida = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g =  invertida.createGraphics();
            g.drawImage(img, img.getWidth(), 0, img.getWidth()* -1, img.getHeight(), null);
            g.dispose();
            return invertida;
        }

        /*
         * Objectivo: Criar uma imagem com a juntação de outras 4 imagens
         * Entrada: Quatro BufferedImage contendo as imagens já carregadas
         * Saida: Uma BufferedImage contendo a imagem fruto da junção
         */
        public BufferedImage juntarImagens(BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4){
            BufferedImage imagem = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB); 
            Graphics2D g =  imagem.createGraphics();
            g.drawImage(img1, 0, 0,  500, 300, null);
            g.drawImage(img2, 550, 0,  500, 300,null);
            g.drawImage(img3, 0,400,  500, 300, null);
            g.drawImage(img4, 550, 400,  500, 300, null);
            g.dispose();
            return imagem;
        }

        /*
         * Objectivo: Salvar uma imagem.
         * Entrada: Um BufferedImage contendo a imagem a ser salva e uma string contendo o caminho absoluto ou relativo da imagem
         * Saida: Nada.
         */
        public void guardarImagem(BufferedImage imagem, String caminho)
        {
            try 
            {
                 ImageIO.write(imagem, "png", new File(caminho));
            } catch (IOException ex) {
                Logger.getLogger(MakeSprite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public static void main(String[] args) 
    {
        new MakeSprite();
    }
}
