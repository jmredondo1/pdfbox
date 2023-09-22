/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author José María Redondo
 */
public class PdfBox extends Application {

    static Window miStage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        miStage = stage;
        Scene scene = new Scene(root);
        //stage.getIcons().add(new Image("Iconos/PDF Viewer.ico"));
        stage.getIcons().add(new Image("Iconos/LOGOpdf.png"));
        stage.setTitle("PDFBox v1.6.2 ©JM.Redondo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
