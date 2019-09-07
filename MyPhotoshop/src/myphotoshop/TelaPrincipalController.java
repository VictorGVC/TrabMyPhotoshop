package myphotoshop;

import ij.gui.MessageDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import myphotoshop.Tranformacoes.Basicas;
import myphotoshop.Tranformacoes.ImageJprocess;

public class TelaPrincipalController implements Initializable {

    @FXML
    private ImageView imageview;
    public static ImageView imageviewaux;
    private boolean flag = false;        
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
    private void evtAbrir(ActionEvent event) {
        FileChooser file = new FileChooser();
        //colocar filtros para extençõs de imagens
        file.getExtensionFilters().addAll(new ExtensionFilter("imagem","*.png","*.jpg","*.jpeg","*.gif"));
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
    private void evtFechar(ActionEvent event){
        if(arq != null)
        {
            if(flag)
            {
                Alert info = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType btnSim = new ButtonType("Sim");
                ButtonType btnNao = new ButtonType("Não");
                info.setTitle("Aviso");
                info.setHeaderText("Imagem Modificada!");
                info.setContentText("Deseja salvar as alterações?");
                info.getButtonTypes().setAll(btnSim, btnNao);
                info.showAndWait().ifPresent(b -> {
                    if (b == btnSim) {
                        try {
                            evtSalvarComo(event);
                        } catch (IOException ex) {
                            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }    
            arq = null;
            img = null;
            imageview.setImage(img);
            ((Stage)(imageview.getScene().getWindow())).setTitle("MyPhotoshop");
        }
    }

    @FXML
    private void evtTonsdecinza(ActionEvent event) {
        flag = true;
        imageview.setImage(Basicas.tonsCinza(img));
    }

    @FXML
    private void evtPretoebranco(ActionEvent event) {
        flag = true;
    }

    @FXML
    private void evtMedia(ActionEvent event) {
        flag = true;
        imageview.setImage(Basicas.media(img,55));
    }

    @FXML
    private void evtPPrewitt(ActionEvent event) {
        flag = true;
        imageview.setImage(Basicas.prewitt(img));
    }

    @FXML
    private void evtErosao(ActionEvent event){
        flag = true;
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.erosao(img));
    }

    @FXML
    private void evtDilatacao(ActionEvent event) {
        flag = true;
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.dilatacao(img));
    }

    @FXML
    private void evtDetecBorda(ActionEvent event) {
        flag = true;
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.detborda(img));
    }

    @FXML
    private void evtinverter(ActionEvent event) {
        flag = true;
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.inverter(img));
    }

    @FXML
    private void evtSmooth(ActionEvent event) {
        flag = true;
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.suavizacao(img));
    }

    @FXML
    private void evtAfiar(ActionEvent event) {
        flag = true;
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.Afiar(img));
    }

    @FXML
    private void evtRuido(ActionEvent event) {
        flag = true;
        img = imageview.getImage();
        imageview.setImage(ImageJprocess.ruido(img));
    }
    
}
