package myphotoshop.Tranformacoes;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageJprocess 
{
    public static Image erosao(Image img)
    {
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        ImagePlus imgplus = new ImagePlus();
        imgplus.setImage(bimagem);
        ImageProcessor ipr = imgplus.getProcessor();
        ipr.erode();
        
        return SwingFXUtils.toFXImage(imgplus.getBufferedImage(), null);
    }

    public static Image detborda(Image img) 
    {
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        ImagePlus imgplus = new ImagePlus();
        imgplus.setImage(bimagem);
        ImageProcessor ipr = imgplus.getProcessor();
        ipr.findEdges();
        
        return SwingFXUtils.toFXImage(imgplus.getBufferedImage(), null);
    }

    public static Image dilatacao(Image img) 
    {
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        ImagePlus imgplus = new ImagePlus();
        imgplus.setImage(bimagem);
        ImageProcessor ipr = imgplus.getProcessor();
        ipr.dilate();
        
        return SwingFXUtils.toFXImage(imgplus.getBufferedImage(), null);
    }
}
