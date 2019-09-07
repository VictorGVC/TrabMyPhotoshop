package myphotoshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TelaSobreController implements Initializable {

    @FXML
    private Label lbnomearq;
    @FXML
    private Label lbdimimage;
    @FXML
    private Label lbtamarq;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(TelaPrincipalController.arq != null)
        {
            lbnomearq.setText(lbnomearq.getText()+" "+TelaPrincipalController.arq.getName());
            lbdimimage.setText(lbdimimage.getText()+" "+String.format("%.0f x %.0f", 
                    TelaPrincipalController.imageviewaux.getImage().getWidth(),
                    TelaPrincipalController.imageviewaux.getImage().getHeight()));
            lbtamarq.setText(lbtamarq.getText()+" "+TelaPrincipalController.arq.length());
        }
    }    
    
}
