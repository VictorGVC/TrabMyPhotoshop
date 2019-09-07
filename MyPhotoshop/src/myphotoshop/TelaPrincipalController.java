package myphotoshop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import myphotoshop.Tranformacoes.Basicas;
import myphotoshop.Tranformacoes.ImageJprocess;

public class TelaPrincipalController implements Initializable {

    @FXML
    private ImageView imageview;
    public static ImageView imageviewaux;
            
    private Image img;
    static public File arq = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageviewaux = imageview;
    }    

    @FXML
    private void evtSobre(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaSobre.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setTitle("Sobre");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void evtSalvar(ActionEvent event) {
    }

    @FXML
    private void evtAbrir(ActionEvent event) {
        FileChooser file = new FileChooser();
        //colocar filtros para extençõs de imagens
        arq = file.showOpenDialog(null);
        if(arq!=null)
        {
            img = new Image(arq.toURI().toString());
            imageview.setImage(img);
            imageview.setFitWidth(img.getWidth());
            imageview.setFitWidth(img.getHeight());
            ((Stage)(imageview.getScene().getWindow())).setTitle(arq.getAbsolutePath() + 
                    "- MyPhotoshop");
        }
    }

    @FXML
    private void evtFechar(ActionEvent event) {
    }

    @FXML
    private void evtTonsdecinza(ActionEvent event) {
        imageview.setImage(Basicas.tonsCinza(img));
    }

    @FXML
    private void evtPretoebranco(ActionEvent event) {
    }

    @FXML
    private void evtMedia(ActionEvent event) {
        imageview.setImage(Basicas.media(img,55));
    }


    @FXML
    private void evtSalvarComo(ActionEvent event) throws IOException {
        FileChooser file = new FileChooser();
        
        arq = file.showSaveDialog(null);
        if(arq!=null)
        {
            if(arq.getAbsolutePath().endsWith("png"))
            {
                ImageIO.write(SwingFXUtils.fromFXImage(imageview.getImage(), null),
                    "png", arq);
            }
            else
            {
                
            }
        }
    }

    @FXML
    private void evtPPrewitt(ActionEvent event) {
        imageview.setImage(Basicas.prewitt(img));
    }

    @FXML
    private void evtErosao(ActionEvent event) 
    {
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.erosao(img));
    }

    @FXML
    private void evtDilatacao(ActionEvent event) {
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.dilatacao(img));
    }

    @FXML
    private void evtDetecBorda(ActionEvent event) {
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.detborda(img));
    }

    
    
    
}
