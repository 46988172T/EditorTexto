package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {
    public Button botonCortar;
    public Button botonCopiar;
    public Button botonPegar;
    private double fuenteMedida;
    public TextArea texto;
    private double fuente;
    public MenuItem copiar;
    public MenuItem pegar;

    public void initialize(){
        /*fuente = texto.getFont().getSize();
        botonCortar.setGraphic(new ImageView("cut.png"));
        botonCopiar.setGraphic(new ImageView("copy.png"));
        botonPegar.setGraphic(new ImageView("paste.png"));*/
    }

    public void deshabilitar(Event event){
        if(texto.getSelectedText().equals("")){
            copiar.setDisable(true);
            pegar.setDisable(true);
        }
        else{
            copiar.setDisable(false);
            pegar.setDisable(false);
        }
    }
    public void salir(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void copiar(ActionEvent actionEvent) {
        texto.copy();
    }

    public void cortar(ActionEvent actionEvent) {
        texto.cut();
    }

    public void pegar(ActionEvent actionEvent) {
        texto.paste();
    }

    public void deshacer(ActionEvent actionEvent) {
        texto.undo();
    }

    public void masTamano(ActionEvent actionEvent) {
        fuenteMedida = texto.getFont().getSize();
        fuenteMedida++;
        texto.setFont(new Font(fuenteMedida));
    }

    public void menosTamano(ActionEvent actionEvent) {
        fuenteMedida = texto.getFont().getSize();
        fuenteMedida--;
        texto.setFont(new Font(fuenteMedida));
    }

    public void nuevaFuente(ActionEvent actionEvent) {
        texto.setFont(new Font("Tahoma", fuente));
    }

    public void sobre(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre EDITOR DE TEXTO PRO");
        alert.setHeaderText("2015 - Leonardo Martínez");
        alert.setContentText("2n Curs DAM");
        alert.showAndWait();
    }

    public void guardarArchivo(ActionEvent actionEvent) throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Guardar archivo");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos", ".txt"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));

        Stage mainStage = new Stage();

        File selectedFile = chooser.showOpenDialog(mainStage);

        chooser.setTitle(selectedFile.getName());

        BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
        bw.write(texto.getText());
        bw.close();
    }

    public void abrirArchivo(ActionEvent actionEvent) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Abrir archivo");

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos", ".txt"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));

        Stage mainStage = new Stage();

        try{
            File selectedFile = chooser.showOpenDialog(mainStage);

            setStageTitle(selectedFile.getName());

            String linea = null;
            BufferedReader br = new BufferedReader(new FileReader(selectedFile));

            while ((linea = br.readLine()) != null) {
                texto.setText(texto.getText() + linea + "\n");
            }
        }catch(FileNotFoundException a){

        }catch (IOException b){}
    }

    public void setStageTitle(String newTitle){
        Main.getStage().setTitle(newTitle + " Editor de Texto PRO");
    }
}
