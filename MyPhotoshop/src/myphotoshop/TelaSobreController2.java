package myphotoshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TelaSobreController2 implements Initializable {

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setLbnomearq(Label lbnomearq) {
        this.lbnomearq = lbnomearq;
    }

    public void setLbdimimage(Label lbdimimage) {
        this.lbdimimage = lbdimimage;
    }

    public void setLbtamarq(Label lbtamarq) {
        this.lbtamarq = lbtamarq;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    @FXML
    private Label lbnomearq;
    @FXML
    private Label lbdimimage;
    @FXML
    private Label lbtamarq;
    private double cx,cy;
    private String nome;
    private int largura,altura,tamanho;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Platform.runLater(() -> {
            if(TelaPrincipalController.arq != null)
            {
                lbnomearq.setText(lbnomearq.getText()+" "+nome);
                lbdimimage.setText(lbdimimage.getText()+" "+String.format("%d x %d",largura,altura));
                lbtamarq.setText(lbtamarq.getText()+" "+tamanho);
            }
        }
    );
        
    }    

    @FXML
    private void evtFechar(ActionEvent event) {
        ((Button)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void evtMouseDragged(MouseEvent event) {
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.setX(event.getScreenX()+cx);
        stage.setY(event.getScreenY()+cy);

    }

    @FXML
    private void evtMousePressed(MouseEvent event) {
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        cx=stage.getX() - event.getScreenX();
        cy=stage.getY() - event.getScreenY();
    }   
}