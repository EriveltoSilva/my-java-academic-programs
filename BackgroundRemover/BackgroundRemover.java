/*
 * Author: Erivelto Clénio da Costa e Silva
 * ID: 1000026877
 * University: Universidade Católica de Angola (UCAN)
 * Course: Engenharia Informática
 * Discipline: Graphics Computing (Compurtação Gráfica)
 * By: 2º Chamada da Frequencia
 * Date: 12-01-2023
 * About this program: This is a java program that load one imagem, 
 * and remove it background( removing a specific color choosen as a background color) 
 */

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class BackgroundRemover extends JFrame{

    int frameWidth = 1000;
    int frameHeight = 800;
    final String imagePath ="Sirene.png";
    final String finalImagePath ="SireneFinal.png";
    final Color backColor = new Color(0,0,0);// White color
    
    public BackgroundRemover()
    {
        super("Background Remover...");
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white);
        setVisible(true);
    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage img = null;
        int margemPrecisao = 95;
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            Logger.getLogger(BackgroundRemover.class.getName()).log(Level.SEVERE, null, ex);
        }

        for(int i = 0; i < img.getWidth(null); i++)
        {
            for(int j = 0; j < img.getHeight(null); j++)
            {
                Color corPixel = new Color(img.getRGB(i, j)); 
                if(
                    ((backColor.getRed()-margemPrecisao <= corPixel.getRed() && backColor.getRed()+margemPrecisao >= corPixel.getRed()))
                && 
                ((backColor.getGreen()-margemPrecisao <= corPixel.getGreen() && backColor.getGreen()+margemPrecisao >= corPixel.getGreen()))
                && 
                ((backColor.getBlue()-margemPrecisao <= corPixel.getBlue() && backColor.getBlue()+margemPrecisao >= corPixel.getBlue()))
                )
                {
                    // Change the rbg color in the below line to change the back color for other you choose
                    //img.setRGB(i,j,new Color(0, 255, 255).getRGB());
                    
                    // If you want remove de background uncomment the follow line and comment the line above
                    img.setRGB(i,j,img.getTransparency());
                }
            }
        }

            g2d.drawImage(img, 0, 0, null);
            saveNewImage(img, finalImagePath);
        }

        /*
         * Inputs: BufferedImage with the image to save, and a path that the image should be saved
         * Output: Nothing
         * Objective: Save any image in the hard drive acording to the path specified.
         */
        public void saveNewImage(BufferedImage img, String pathNewImage)
        {
            try 
            {
                 ImageIO.write(img, "png", new File(pathNewImage));
            } catch (IOException ex) {
                Logger.getLogger(BackgroundRemover.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public static void main(String[] args) {
        new BackgroundRemover();
    }
}