package pdfbox;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author José María
 */
public class Util {

    public Util() {
    }

    public String crearVacio(File filename) {
        try {
            String mensaje = "";
            PDDocument doc = new PDDocument(MemoryUsageSetting.setupTempFileOnly());
            doc.addPage(new PDPage());
            doc.save(filename);
            doc.close();
            mensaje = "Creado archivo vacío";
            return mensaje;
        } catch (IOException ex) {
            System.out.println("[ERROR]" + ex.getMessage());
        }
        return null;
    }

    public String crearTexto(File filename, String texto) throws IOException {

        PDDocument doc = new PDDocument(MemoryUsageSetting.setupTempFileOnly());
        PDPage page = new PDPage();
        doc.addPage(page);

        PDFont font = PDType1Font.HELVETICA_BOLD;
        PDPageContentStream content = new PDPageContentStream(doc, page);
        content.beginText();
        content.setFont(font, 12);
        //content.moveTextPositionByAmount(100, 700);
        content.moveTo(100, 700);
        //content.drawString(texto);

        content.endText();
        content.close();
        String mensaje = "";
        try {
            //doc.save(pathDesktop + filename + ".pdf");
            doc.save(filename);
            doc.close();
            mensaje = "Creado archivo de texto";
            System.out.println(texto);
        } catch (Exception e) {
            mensaje = "Ha habido un problema:\n" + e.getMessage();
        }
        return mensaje;
    }

    public void extraeAlgunas(File file, int desde, int hasta, int bloque) throws IOException {

        String pathDesktop = System.getProperty("user.home") + "/Desktop/";
        PDDocument pd = new PDDocument(MemoryUsageSetting.setupTempFileOnly());
        pd.load(file);

        //System.out.println("Procesando..." + file.getName());
        Splitter splitter = new Splitter();
        splitter.setStartPage(desde);
        splitter.setEndPage(hasta);
        splitter.setSplitAtPage(bloque);

        // We need this as split method returns a list
        List<PDDocument> listOfSplitPages;

        // We are receiving the split pages as a list of PDFs
        listOfSplitPages = splitter.split(pd);

        // We need an iterator to iterate through them
        Iterator<PDDocument> iterator = listOfSplitPages.listIterator();

        // I am using variable i to denote page numbers. 
        int i = 1;
        while (iterator.hasNext()) {
            PDDocument pdsalida = iterator.next();

            // Saving each page with its assumed page no.
            pdsalida.save(pathDesktop + i++ + ".pdf");

            // Something went wrong with a PDF object
            System.out.println("Problemas en la página " + (i - 1));
        }

    }

    public void extraeTodasPaginas(File file) throws IOException {

        String pathDesktop = System.getProperty("user.home") + "\\Desktop\\";
        //File f = new File(pathDesktop + file);
        //System.out.println(pathDesktop);
        try {
            PDDocument doc = PDDocument.load(file, MemoryUsageSetting.setupTempFileOnly());
            // Carpeta para almacenar las páginas
            File dir = new File(file.getAbsolutePath() + " - SEPARADAS");
            dir.mkdirs();
            //////////////////////////

            //////////////////////////
            int pageNumber = 1;
            String fileName = "";
            //List<PDPage> list = doc.getDocumentCatalog().getAllPages();
            PDPageTree list = doc.getDocumentCatalog().getPages();
            for (PDPage page : list) {
                PDDocument newDocument = new PDDocument(MemoryUsageSetting.setupTempFileOnly());
                newDocument.addPage(page);
                fileName = String.format("%06d", pageNumber) + ".pdf";
                //fileName = ("000000"+pageNumber).substring(0, 6) + ".pdf";
                File newFile = new File(fileName);
                //newFile.createNewFile();
                newDocument.save(dir + "\\" + newFile);
                newDocument.close();
                pageNumber++;
            }
        } catch (IOException ex) {
            System.out.println("[ERROR]" + ex.getMessage());
        } finally {

        }

    }

    public void uneArchivos(File carpeta) {

        try {

            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            PDDocument document;
            File fileUnido; // lo deja dentro de la carpeta con el nombre @UNIDOS
            ArrayList<String> listFileNames = new ArrayList<String>(); // listado de archivos
            ArrayList<Integer> listFilePages = new ArrayList<Integer>(); // listado de número de páginas

            // Borrar si existe, si no, lo acumula
            Path path = Paths.get(carpeta.getAbsolutePath() + "\\@UNIDOS.pdf");
            fileUnido = path.toFile();
            Files.deleteIfExists(path);
            // Lista de ficheros
            File[] files = carpeta.listFiles();

            // Unir ficheros en UNO SOLO
            for (File file : files) {

                listFileNames.add(file.getName());
                document = PDDocument.load(file);
                listFilePages.add(document.getNumberOfPages());
                pdfMerger.addSource(file);
            }

            pdfMerger.setDestinationFileName(fileUnido.toString());
            pdfMerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

        } catch (IOException ex) {
            System.out.println("[ERROR]" + ex.getMessage());
        }

    }

    public void uneIndexaArchivos(File carpeta) {
        try {
            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            PDDocument document;
            File fileUnido; // lo deja dentro de la carpeta con el nombre @UNIDOS
            ArrayList<String> alFileNames = new ArrayList<String>(); // listado de archivos
            ArrayList<Integer> alFilePages = new ArrayList<Integer>(); // listado de número de páginas

            // Borrar si existe, si no, lo acumula
            Path path = Paths.get(carpeta.getAbsolutePath() + "\\@UNIDOS.pdf");
            fileUnido = path.toFile();
            Files.deleteIfExists(path);
            // Lista de ficheros
            File[] files = carpeta.listFiles();

            // Unir ficheros en UNO SOLO
            for (File file : files) {
                // Almaceno los nombres de ficheros y los números de página de cada documento
                alFilePages.add(PDDocument.load(file).getNumberOfPages());
                alFileNames.add(file.getName());
                pdfMerger.addSource(file);
            }

            pdfMerger.setDestinationFileName(fileUnido.toString());
            pdfMerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

            //Índice de @UNIDOS
            PDDocumentOutline outline = new PDDocumentOutline();
            document = PDDocument.load(fileUnido);
            document.getDocumentCatalog().setDocumentOutline(outline);
            int pagina = 0; // Página del fichero @UNIDOS
            int numFile = 0; // Nombre de cada documento
            for (String fileName : alFileNames) {

                PDPage firstPage = (PDPage) document.getPages().get(pagina);
                PDOutlineItem firstPageItem = new PDOutlineItem();
                firstPageItem.setTitle(fileName.replace(".pdf", ""));
                firstPageItem.setDestination(firstPage);
                outline.addLast(firstPageItem);
                pagina += alFilePages.get(numFile);
                numFile++;

            }

            outline.openNode();

            document.save(fileUnido);
            document.close();

        } catch (IOException ex) {
            System.out.println("[ERROR]" + ex.getMessage());
        }
    }

    public void uneIndexaNumeraArchivos(File carpeta) {
        try {
            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            PDDocument document;
            ArrayList<String> alFileNames = new ArrayList<String>(); // listado de archivos
            ArrayList<Integer> alFilePages = new ArrayList<Integer>(); // listado de número de páginas
            ArrayList<String> aCabecera = new ArrayList<String>(); // Cabeceras de UNIDOS
            Path path;

            // Borrar si existe. Si no se borra, lo acumula al listado de ficheros
            Files.deleteIfExists(Paths.get(carpeta.getAbsolutePath() + "\\@UNIDOS.pdf"));
            Files.deleteIfExists(Paths.get(carpeta.getAbsolutePath() + "\\00-PORTADA.pdf"));
            Files.deleteIfExists(Paths.get(carpeta.getAbsolutePath() + "\\01-INDICE.pdf"));

            // Crear página de portada y de índice
            PDDocument docPortada = new PDDocument();
            docPortada.addPage(new PDPage(PDRectangle.A4));
            docPortada.save(carpeta.getAbsolutePath() + "\\00-PORTADA.pdf");
            docPortada.close();

            PDDocument docIndice = new PDDocument(MemoryUsageSetting.setupTempFileOnly());
            docIndice.addPage(new PDPage(PDRectangle.A4));
            docIndice.save(carpeta.getAbsolutePath() + "\\01-INDICE.pdf");
            docIndice.close();

            // Lista de ficheros, y ordenación
            File[] files = carpeta.listFiles();
            Arrays.sort(files, (f1, f2) -> f1.compareTo(f2));

            // Unir ficheros en UNO SOLO y componer las cabeceras
            String nombreArchivo;
            int numPag = 0;
            for (File file : files) { // PORTADA + INDICE + RESTO FICHEROS
                // Almaceno los nombres de ficheros y los números de página de cada documento
                alFilePages.add(PDDocument.load(file, MemoryUsageSetting.setupTempFileOnly()).getNumberOfPages());
                alFileNames.add(file.getName());
                pdfMerger.addSource(file);
                // recorrer las páginas y montar las cabeceras
                nombreArchivo = file.getName().replace(".pdf",
                        "                                                                       ")
                        .substring(0, 70);

                for (int i = 0; i < alFilePages.get(alFilePages.size() - 1); i++) {
                    numPag++;
                    aCabecera.add(nombreArchivo + "-" + numPag + "-");
                }
            }
            pdfMerger.setDestinationFileName(carpeta.getAbsolutePath() + "\\@UNIDOS.pdf");
            pdfMerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

            //Índice OUTLINE de @UNIDOS
            path = Paths.get(carpeta.getAbsolutePath() + "\\@UNIDOS.pdf");
            File fileUnidos = path.toFile();
            PDDocumentOutline outline = new PDDocumentOutline();
            document = PDDocument.load(fileUnidos, MemoryUsageSetting.setupTempFileOnly());
            document.getDocumentCatalog().setDocumentOutline(outline);
            int pagina = 0;
            int numFile = 0;
            for (String fileName : alFileNames) {
                PDPage firstPage = (PDPage) document.getPages().get(pagina);
                PDOutlineItem firstPageItem = new PDOutlineItem();
                firstPageItem.setTitle(fileName.replace(".pdf", ""));
                firstPageItem.setDestination(firstPage);
                outline.addLast(firstPageItem);
                pagina += alFilePages.get(numFile);
                numFile++;

            }
            outline.openNode();
            document.save(fileUnidos);
            document.close();

            // Paginación de cabeceras de página
            PDFont font = PDType1Font.COURIER_BOLD_OBLIQUE;
            float fontSize = 12;
            float fontHeight = fontSize;
            float leading = 20;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            document = PDDocument.load(fileUnidos, MemoryUsageSetting.setupTempFileOnly());
            PDPageTree listPages = document.getPages();

            for (PDPage page : listPages) {

                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
                contentStream.setFont(font, fontSize);
                contentStream.setStrokingColor(Color.RED);

                float yCordinate = page.getCropBox().getUpperRightY() - 30;
                float startX = page.getCropBox().getLowerLeftX() + 30;
                float endX = page.getCropBox().getUpperRightX() - 30;

                // Pintar la cabecera que está en el array aCabecera
                contentStream.beginText();
                contentStream.newLineAtOffset(startX, yCordinate);
                contentStream.setNonStrokingColor(255, 0, 0);
                contentStream.showText(aCabecera.get(listPages.indexOf(page)));
                contentStream.endText(); // End of text mode
                contentStream.moveTo(startX, yCordinate - 1);
                contentStream.lineTo(endX, yCordinate - 1);
                contentStream.stroke();
                yCordinate -= leading;
                contentStream.close();
            }

            /**
             * Crear el índice en la página 1, el título del documento como la
             * carpeta y los capítulos como el nombre de los ficheros
             */
            float yCordinate;
            float startX;

            // PORTADA
            PDPage pagPortada = document.getPage(0);
            PDPageContentStream cosPortada = new PDPageContentStream(document, pagPortada, PDPageContentStream.AppendMode.APPEND, true, true);
            font = PDType1Font.HELVETICA_BOLD_OBLIQUE;
            cosPortada.setFont(font, 18);
            yCordinate = pagPortada.getCropBox().getUpperRightY() - 400;
            startX = pagPortada.getCropBox().getLowerLeftX() + 35;
            cosPortada.beginText();
            cosPortada.newLineAtOffset(startX, yCordinate);
            cosPortada.showText(carpeta.getName());
            cosPortada.endText(); // End of text mode
            //dibujar bordes de pagina
            cosPortada.setStrokingColor(Color.BLACK);
            cosPortada.addRect(30, 20, 535, 805);
            cosPortada.stroke();
            cosPortada.close();

            // ÍNDICE
            PDPage pagIndice = document.getPage(1);
            PDPageContentStream cosIndice = new PDPageContentStream(document, pagIndice, PDPageContentStream.AppendMode.APPEND, true, true);
            font = PDType1Font.HELVETICA_BOLD_OBLIQUE;
            cosIndice.setFont(font, 12);

            yCordinate = pagIndice.getCropBox().getUpperRightY() - 60;
            startX = pagIndice.getCropBox().getLowerLeftX() + 35;

            cosIndice.beginText();
            cosIndice.newLineAtOffset(startX, yCordinate);
            cosIndice.showText("ÍNDICE  - " + carpeta.getName());
            cosIndice.endText(); // End of text mode

            float endX = pagIndice.getCropBox().getUpperRightX() - 30;
            cosIndice.moveTo(startX, yCordinate - 1);
            cosIndice.lineTo(endX, yCordinate - 1);
            cosIndice.stroke();

            font = PDType1Font.COURIER;
            cosIndice.setFont(font, 12);

            int saltoLinea = 15; //salto de cada línea del índice
            int cont = 0; //contador de ficheros
            int pag = 1; // num de pag indice
            int numPags = 0; // numero de pags del fichero
            String salida = "";
            int puntos = 70; //puntos de repetición
            for (String nombre : alFileNames) {
                puntos = 70;
                salida = "";
                cosIndice.beginText();
                cosIndice.newLineAtOffset(startX, yCordinate - saltoLinea * (cont + 1));
                //cosIndice.showText(nombre.replace(".pdf", "") + ".............." + alFilePages.get(cont));
                //Se pone el nombre del archivo + puntos + numero de pagina
                salida = nombre.replace(".pdf", "");
                puntos = puntos - salida.length();
                salida += String.join("", Collections.nCopies(puntos, "."));
                salida += pag;
                cosIndice.showText(salida);
                cosIndice.endText(); // End of text mode
                numPags = alFilePages.get(cont);
                pag += numPags;
                cont++;
            }

            cosIndice.setStrokingColor(Color.BLACK);
            cosIndice.addRect(30, 20, 535, 805);
            cosIndice.stroke();
            cosIndice.close();
            document.save(fileUnidos);
            document.close();

        } catch (IOException ex) {
            System.out.println("[ERROR]" + ex.getMessage());
        }

    }

    public String extraeTexto(File file) {
        try {
            PDDocument pd = PDDocument.load(file, MemoryUsageSetting.setupTempFileOnly());
            PDFTextStripper stripper = new PDFTextStripper();
            /*
             Falta escribirlo a un archivo de texto.
             */
            return stripper.getText(pd);
        } catch (IOException ex) {
            System.out.println("[ERROR]" + ex.getMessage());
        }

        return null;
    }

    public void rotarPaginas(File file, int grados) throws IOException {
        PDDocument document = PDDocument.load(file, MemoryUsageSetting.setupTempFileOnly());
        PDPageTree dPageTree = document.getPages();
        for (PDPage pDPage : dPageTree) {
            pDPage.setRotation(grados);
        }
        document.save(file.getAbsolutePath() + " - GIRADO.pdf");
        document.close();

    }

    public void eliminarPaginas(File file, String rango) throws IOException {
        PDDocument document = PDDocument.load(file, MemoryUsageSetting.setupTempFileOnly());
        PDDocument nuevo = new PDDocument();

        int numPaginas = document.getNumberOfPages();
        ArrayList<String> paginas = new ArrayList<String>();
        //[1, 2, 3, 4, 5, 6, 7, 8, 9, 10...]
        for (int i = 1; i <= numPaginas; i++) {
            paginas.add(i + "");
        }

        //String ent = " 1,14-19  , 3 , 5 - 7 ,9, 20";
        ArrayList<String> listBorrar = new ArrayList<>();
        String[] entrada = rango.trim().split("\\s*,\\s*");
        for (String str : entrada) {
            if (str.contains("-")) {
                String[] limites = str.trim().split("\\s*-\\s*");
                int inferior = Integer.parseInt(limites[0]);
                int superior = Integer.parseInt(limites[1]);
                for (int i = inferior; i <= superior; i++) {
                    listBorrar.add(i + "");
                }
            } else {
                listBorrar.add(str);
            }
        }

        paginas.removeAll(listBorrar);

        for (String str : paginas) {
            nuevo.addPage(document.getPage(Integer.parseInt(str) - 1));
        }

        nuevo.save(file.getAbsolutePath() + "-CON PÁGINAS ELIMINADAS-.pdf");
        document.close();
        nuevo.close();
        /*
        System.out.println("Todos " + paginas);
        System.out.println("Borrar " + listBorrar);
        System.out.println("Definitivo " + paginas);
         */
    }

    /**
     * Inserta un archivo en otro, al principio o al final
     *
     * @param file Archivo a insertar
     * @param file2 Archivo de destino
     * @param donde PRINCIPIO o FINAL
     * @throws IOException
     */
    public void insertarArchivo(File file, File file2, String donde) throws IOException {
        //donde = "principio" o "final"
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        if ("principio".equals(donde)) {
            pdfMerger.addSource(file);
            pdfMerger.addSource(file2);
        } else {
            pdfMerger.addSource(file2);
            pdfMerger.addSource(file);
        }
        pdfMerger.setDestinationFileName(file.getAbsolutePath() + "-INSERTADO-.pdf");
        //pdfMerger.mergeDocuments();
        pdfMerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

    }

    /**
     * Inserta un archivo en medio de otro, en una página concreta
     *
     * @param origen Archivo a insertar
     * @param destino Archivo de destino
     * @param pagina Número de página
     * @throws IOException
     */
    public void insertarArchivoPagina(File origen, File destino, String pagina) throws IOException {
        //Separar el fichero DESTINO en dos partes por la página de corte creando dos ficheros 

        String pathDesktop = System.getProperty("user.home") + "\\Desktop\\";

        PDDocument docDestino = PDDocument.load(destino, MemoryUsageSetting.setupTempFileOnly());
        PDDocument docOrigen = PDDocument.load(origen, MemoryUsageSetting.setupTempFileOnly());
        PDDocument docFinal = new PDDocument(MemoryUsageSetting.setupTempFileOnly());

        Splitter splitter = new Splitter();
        List<PDDocument> pagesDestino = splitter.split(docDestino);
        List<PDDocument> pagesOrigen = splitter.split(docOrigen);
        List<PDDocument> pagesFinal = splitter.split(docFinal);

        // Añadir páginas al documento FINAL
        // Primera parte 
        int i = 0;
        for (PDDocument document : pagesDestino) {
            i++;
            if (i < Integer.parseInt(pagina)) {
                pagesFinal.add(document);
            }
        }

        //Archivo insertado
        for (PDDocument document : pagesOrigen) {
            pagesFinal.add(document);
        }

        //Ultima parte
        i = 0;
        for (PDDocument document : pagesDestino) {
            i++;
            if (i >= Integer.parseInt(pagina)) {
                pagesFinal.add(document);
            }
        }

        for (PDDocument document : pagesFinal) {
            docFinal.addPage(document.getPage(0));
        }

        // Primero se guarda y luego se cierra
        //docFinal.save(System.getProperty("user.home") + "/Desktop/INSERTADO.pdf");
        docFinal.save(destino + " - INSERTADO.pdf");

        docFinal.close();
        docDestino.close();
        docOrigen.close();
    }

    public void comprimir(File file, float dpi) {

        try {
            PDDocument pdDocument = new PDDocument();
            PDDocument oDocument = PDDocument.load(file, MemoryUsageSetting.setupTempFileOnly());
            PDFRenderer pdfRenderer = new PDFRenderer(oDocument);
            int numberOfPages = oDocument.getNumberOfPages();
            PDPage page = null;
            for (int i = 0; i < numberOfPages; i++) {
                page = new PDPage(PDRectangle.A4);
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, Math.round(dpi), ImageType.RGB);
                PDImageXObject pdImage = JPEGFactory.createFromImage(pdDocument, bim);
                PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page);
                float newHeight = PDRectangle.A4.getHeight();
                float newWidth = PDRectangle.A4.getWidth();
                contentStream.drawImage(pdImage, 0, 0, newWidth, newHeight);
                contentStream.close();

                pdDocument.addPage(page);
            }

            pdDocument.save(file.getAbsolutePath() + " - COMPRIMIDO(" + Math.round(dpi) + "DPI).pdf");
            pdDocument.close();
            oDocument.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}///
