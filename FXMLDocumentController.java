package pdfbox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author José María
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label labelMsg;
    @FXML
    private TextArea textArea;
    @FXML
    private RadioButton extraetodas;
    @FXML
    private RadioButton extraetexto;
    @FXML
    private RadioButton une;
//    @FXML
//    private Button btnAceptar;

    @FXML
    private Button Unir;

    @FXML
    private Button UnirIndexar;

    @FXML
    private Button UnirIndexarNumerar;

    @FXML
    private Button btnSeparar;

    @FXML
    private Button btnRotar;

    @FXML
    private Button btnExtraerTexto;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private Button btnEliminarPaginas;

    @FXML
    private SplitMenuButton btnIncluir;

    @FXML
    private TextField txtEliminarPaginas;

    @FXML
    private Button btnInsertar;

    @FXML
    private TextField txtInsertarEnPagina;

    @FXML
    private ImageView imgEspera;

    @FXML
    private ImageView imgOk;

    @FXML
    private ImageView imgCancel;
    
    @FXML
    private Button btnComprimir;

   @FXML
   private Slider sldCalidad;
   

    // referencia al Stage principal
    // Stage s = (Stage) PdfBox.miStage; se puede hacer con (new Stage)
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     * Abre un cuadro de diálogo para seleccionar CARPETA o ARCHIVO
     *
     * @param String tipo "carpeta" o "archivo"
     *
     * @param String cabecera Título de la ventana
     * @return
     */
    private File openDialog(String tipo, String cabecera) {

        File pathDesktop = new File(System.getProperty("user.home") + "/Desktop");
        File file = null;

        if (tipo == "carpeta") {

            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setInitialDirectory(pathDesktop);
            dirChooser.setTitle(cabecera);
            file = dirChooser.showDialog(new Stage());
        } else { //"archivo"
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(pathDesktop);
            fileChooser.setTitle(cabecera);
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pdf pdf(*.pdf)", "*.pdf"));
            file = fileChooser.showOpenDialog(new Stage());
        }
        return file;
    }

    @FXML
    private void handleUnir(ActionEvent event) {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);
        File carpeta = openDialog("carpeta", "Seleccionar carpeta");
        if (carpeta != null) {
            new Util().uneArchivos(carpeta);
            labelMsg.setText("Ficheros unidos (en la carpeta)");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleUnirIndexar(ActionEvent event) {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File carpeta = openDialog("carpeta", "Seleccionar carpeta");
        if (carpeta != null) {
            new Util().uneIndexaArchivos(carpeta);
            labelMsg.setText("Unidos e indexados (en la carpeta)");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleUnirIndexarNumerar(ActionEvent event) {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File carpeta = openDialog("carpeta", "Seleccionar carpeta");
        if (carpeta != null) {
            new Util().uneIndexaNumeraArchivos(carpeta);
            labelMsg.setText("Unidos, indexados y numerados (en la carpeta)");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleBtnSeparar(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File file = openDialog("archivo", "Seleccionar fichero");
        if (file != null) {
            new Util().extraeTodasPaginas(file);
            labelMsg.setText("Carpeta con páginas separadas en la misma ubicación");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleBtnRotar(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File file = openDialog("archivo", "Seleccionar fichero");
        if (file != null) {
            new Util().rotarPaginas(file, 90);
            labelMsg.setText("Fichero rotado");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleRota90(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File file = openDialog("archivo", "Seleccionar fichero");
        if (file != null) {
            new Util().rotarPaginas(file, 90);
            labelMsg.setText("Fichero con las páginas rotadas 90º (misma ubicación)");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleRota180(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File file = openDialog("archivo", "Seleccionar fichero");
        if (file != null) {
            new Util().rotarPaginas(file, 180);
            labelMsg.setText("Fichero rotado 180º (misma ubicación)");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleRota270(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File file = openDialog("archivo", "Seleccionar fichero");
        if (file != null) {
            new Util().rotarPaginas(file, 270);
            labelMsg.setText("Fichero rotado -90º (misma ubicación)");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleIncluyePrincipio(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File origen = openDialog("archivo", "Seleccionar fichero A INCLUIR");
        File destino = openDialog("archivo", "Seleccionar fichero DESTINO");

        if (origen != null && destino != null) {
            new Util().insertarArchivo(origen, destino, "principio");
            labelMsg.setText("Fichero incluido");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleIncluyeFinal(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File file = openDialog("archivo", "Seleccionar fichero A INCLUIR");
        File file2 = openDialog("archivo", "Seleccionar fichero DESTINO");
        if (file != null && file2 != null) {
            new Util().insertarArchivo(file, file2, "final");
            labelMsg.setText("Fichero incluido");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleBtnEliminarPaginas(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        if (txtEliminarPaginas.getText() == null || txtEliminarPaginas.getText().trim().isEmpty()) {
            labelMsg.setText("Debe decir las páginas que desea eliminar");
            imgEspera.setVisible(false);
            return;
        }

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File file = openDialog("archivo", "Seleccionar fichero");
        if (file != null) {
            //System.out.println(strRango);
            String rango = txtEliminarPaginas.getText().trim();
            new Util().eliminarPaginas(file, rango);
            labelMsg.setText("Creado nuevo fichero con las páginas eliminadas");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);

    }

    @FXML
    private void handleInsertar(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        String pagina = txtInsertarEnPagina.getText().trim();
        File origen = openDialog("archivo", "Seleccionar fichero A INSERTAR");
        File destino = openDialog("archivo", "Seleccionar fichero DESTINO");

        if (!pagina.isEmpty() && origen != null && destino != null) {
            new Util().insertarArchivoPagina(origen, destino, pagina);
            labelMsg.setText("Fichero insertado en página " + pagina);
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }

    @FXML
    private void handleBtnExtraerTexto(ActionEvent event) {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        File file = openDialog("archivo", "Seleccionar fichero");
        if (file != null) {
            textArea.setText(new Util().extraeTexto(file));
            labelMsg.setText("Texto extraido");
            imgOk.setVisible(true);
            textArea.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }
    
     @FXML
    private void handleBtnComprimir(ActionEvent event) throws IOException {

        labelMsg.setText("PROCESANDO...");
        imgEspera.setVisible(true);
        imgOk.setVisible(false);
        imgCancel.setVisible(false);

        
        File file = openDialog("archivo", "Seleccionar fichero");
        if (file != null) {
            new Util().comprimir(file, (float) sldCalidad.getValue());
            labelMsg.setText("Fichero comprimido");
            imgOk.setVisible(true);
        } else {
            labelMsg.setText("OPERACIÓN NO EJECUTADA");
            imgCancel.setVisible(true);
        }
        imgEspera.setVisible(false);
    }
    
    


///////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
    }

}
