package myphotoshop.Tranformacoes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Basicas {
    
    
    public static Image tonsCinza(Image img)
    {
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        WritableRaster raster = bimagem.getRaster();
        int[] pixel = {0,0,0,0};
        int cinza;
        
        for (int i = 0; i < img.getHeight(); i++) 
        {
            for (int j = 0; j < img.getWidth(); j++) 
            {
                raster.getPixel(j, i, pixel);
                cinza = (int)(pixel[0]*0.3 + pixel[1]*0.59 + pixel[2]*0.11);
                pixel[0] = pixel[1] = pixel[2] = cinza;
                raster.setPixel(j, i, pixel);
            }
        }
        return SwingFXUtils.toFXImage(bimagem, null);
    }
    
    public static Image pretoebranco(Image img)
    {
        BufferedImage bimagem = SwingFXUtils.fromFXImage(img, null);
        
        int larg = bimagem.getWidth();
        int alt = bimagem.getHeight();
        byte[] comp = { 0, -1 };
        IndexColorModel cm = new IndexColorModel(2, 2, comp, comp, comp);
        BufferedImage bimagem2 = new BufferedImage(larg, alt, BufferedImage.TYPE_BYTE_INDEXED, cm);
        Graphics2D mudado = bimagem2.createGraphics();
        
        mudado.drawRenderedImage(bimagem, null);
        mudado.dispose();
        
        return SwingFXUtils.toFXImage(bimagem2, null);
    }
    
    public static Image media(Image img,int dim)
    {
        img = tonsCinza(img);
        BufferedImage bimagem,bimagedest;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        bimagedest = new BufferedImage(bimagem.getWidth(),
            bimagem.getHeight(),
            bimagem.getType());
        WritableRaster raster = bimagem.getRaster();
        WritableRaster rasterdest = bimagedest.getRaster();
        int[] pixel = {0,0,0,0};
        int cinza;
        
        for (int i = dim/2; i < img.getHeight()-dim/2; i++) 
        {
            for (int j = dim/2; j < img.getWidth()-dim/2; j++) 
            {
                cinza = 0;
                for(int y = 0; y < dim; y++)
                {
                    for(int x = 0; x < dim; x++)
                    {
                        raster.getPixel(j+x-dim/2, i+y-dim/2, pixel);
                        cinza += pixel[0];
                    }
                }
                pixel[0] = pixel[1] = pixel[2] = cinza/(dim*dim);
                rasterdest.setPixel(j, i, pixel);
            }
        }
        return SwingFXUtils.toFXImage(bimagedest, null);
    }
    
    public static Image prewitt(Image img)
    {
        img = media(img,3);
        BufferedImage bimagem,bimagedest;
        
        bimagem = SwingFXUtils.fromFXImage(img, null);
        bimagedest = new BufferedImage(bimagem.getWidth(),
            bimagem.getHeight(),
            bimagem.getType());
        WritableRaster raster = bimagem.getRaster();
        WritableRaster rasterdest = bimagedest.getRaster();
        int[] pixel = {0,0,0,0};
        int z1,z2,z3,z4,z5,z6,z7,z8,z9;
        for (int i = 1; i < img.getHeight()-1; i++) 
        {
            for (int j = 1; j < img.getWidth()-1; j++) 
            {
                raster.getPixel(j-1, i-1, pixel);   z1 = pixel[0];
                raster.getPixel(j, i-1, pixel);     z2 = pixel[0];
                raster.getPixel(j+1, i-1, pixel);   z3 = pixel[0];
                raster.getPixel(j-1, i, pixel);     z4 = pixel[0];
                raster.getPixel(j+1, i, pixel);     z6 = pixel[0];
                raster.getPixel(j-1, i+1, pixel);   z7 = pixel[0];
                raster.getPixel(j, i+1, pixel);     z8 = pixel[0];
                raster.getPixel(j+1, i+1, pixel);   z9 = pixel[0];
                
                pixel[0] = pixel[1] = pixel[2] = (int)
                        Math.sqrt(Math.pow((z7+z8+z9)-(z1+z2+z3),2) + 
                                Math.pow((z3+z6+z9)-(z1+z4+z7), 2));
                rasterdest.setPixel(j, i, pixel);
            }
        }
        return SwingFXUtils.toFXImage(bimagedest, null);
    }

    public static Image sobel(Image img)
    {
        img = media(img,3);
        BufferedImage bimagem,bimagedest;
        
        bimagem = SwingFXUtils.fromFXImage(img, null);
        bimagedest = new BufferedImage(bimagem.getWidth(),
            bimagem.getHeight(),
            bimagem.getType());
        WritableRaster raster = bimagem.getRaster();
        WritableRaster rasterdest = bimagedest.getRaster();
        int[] pixel = {0,0,0,0};
        int z1,z2,z3,z4,z5,z6,z7,z8,z9;
        for (int i = 1; i < img.getHeight()-1; i++) 
        {
            for (int j = 1; j < img.getWidth()-1; j++) 
            {
                raster.getPixel(j-1, i-1, pixel);   z1 = pixel[0];
                raster.getPixel(j, i-1, pixel);     z2 = pixel[0];
                raster.getPixel(j+1, i-1, pixel);   z3 = pixel[0];
                raster.getPixel(j-1, i, pixel);     z4 = pixel[0];
                raster.getPixel(j+1, i, pixel);     z6 = pixel[0];
                raster.getPixel(j-1, i+1, pixel);   z7 = pixel[0];
                raster.getPixel(j, i+1, pixel);     z8 = pixel[0];
                raster.getPixel(j+1, i+1, pixel);   z9 = pixel[0];
                
                pixel[0] = pixel[1] = pixel[2] = (int)
                        Math.sqrt(Math.pow((z7+2*z8+z9)-(z1+2*z2+z3),2) + 
                                Math.pow((z3+2*z6+z9)-(z1+2*z4+z7), 2));
                rasterdest.setPixel(j, i, pixel);
            }
        }
        
        return SwingFXUtils.toFXImage(bimagedest, null);
    }
    
}
