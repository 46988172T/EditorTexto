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
    public Button botonMasTamano;
    public Button botonMenosTamano;
    public TextArea texto;
    public MenuItem copiar;
    public MenuItem pegar;
    private double fuenteMedida;
    private double fuente;

    //commit ...
    public void initialize() {
        botonCortar.setGraphic(new ImageView("/image/corta.png"));
        botonCopiar.setGraphic(new ImageView("/image/copia.png"));
        botonPegar.setGraphic(new ImageView("/image/pega.png"));
        botonMasTamano.setGraphic(new ImageView("/image/mas.png"));
        botonMenosTamano.setGraphic(new ImageView("/image/menos.png"));
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

        FileChooser guarda = new FileChooser();
        guarda.setTitle("Guardar archivo");
        guarda.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos", ".txt"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));

        Stage mainStage = new Stage();

        File selectedFile = guarda.showSaveDialog(mainStage);

        guarda.setTitle(selectedFile.getName());

        BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
        bw.write(texto.getText());
        bw.close();
    }

    public void abrirArchivo(ActionEvent actionEvent) {

        FileChooser abre = new FileChooser();
        abre.setTitle("Abrir archivo");

        abre.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos", ".txt"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));

        Stage mainStage = new Stage();

        try{
            File selectedFile = abre.showOpenDialog(mainStage);

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
