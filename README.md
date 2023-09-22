# pdfbox
**Guía operativa de PDFBOX**
![PRINCIPAL](img/img1.png)
La aplicación PDFBOX es una utilidad desarrollada en JAVA que reemplaza, en muchos casos, las operaciones que se pueden realizar con archivos PDF con el producto Adobe Acrobat® o Kofax®, cuyas licencias están limitadas. A pesar de que los dibujos y el *tooltip* es descriptivo, se edita esta guía a petición de algún usuario que lo solicitó. 

Requiere el *runtime* de JAVA 8.

Por favor, reportar los problemas o sugerencias a jmredondo1@gmail.com.

**INSTALACIÓN**

PDFBox no requiere instalación, basta con copiar a una carpeta de red y poner un acceso directo o poner un link en una web, para que funcione mediante http.

Requiere que tanto *el fichero JAR como la carpeta LIB estén en la misma ubicación*.

**OPERACIONES**

***UNIR DOCUMENTOS***

![UNIR](img/img2.png)
**Une los documentos que se encuentran en una carpeta**, ordenados alfabéticamente, tal y como se ven ordenados por nombre en el administrador de archivos de Windows, y lo deja dentro de la carpeta con el nombre **@UNIDOS.pdf**

Esta opción tiene tres posibilidades:

**UNIR**: Simplemente une los archivos

**UNIR E INDEXAR**. Une y crea un índice embebido en el propio documento que se puede desplegar en Adobe Reader o Adobe Acrobat y que se conoce como marcadores

![UNIR](img/img3.png)

**UNIR, INDEXAR Y NUMERAR**: realiza la operación anterior y además realiza una numeración y el nombre del documento en cada página y un índice real. La portada lleva el nombre de la carpeta. Esto se utiliza para generar un expediente judicial. Las páginas de tamaño inferior al A4, puede no aparecer el número, por lo que se aconseja pasar a A4 los documentos; se considera que todas las páginas están en formato vertical. 

***SEPARAR PÁGINAS***

![UNIR](img/img4.png)
**Separa un documento en páginas individuales**. Deja una carpeta en la ubicación del fichero original con el nombre del fichero y el texto –SEPARADAS (***nombre\_fichero –SEPARADAS***)

![UNIR](img/img5.png)

***ROTAR***

![ROTAR](img/img6.png)
**Rota el documento** en +90°, 180° o -90°.

Lo deja en la misma ubicación del original con la palabra – GIRADO (***nombre\_fichero-GIRADO.pdf***)

***INCLUIR***

![INCLUIR](img/img7.png)
**Incluye un documento en otro**, a PRINCIPIO o al FINAL. Deja el nuevo fichero en la misma ubicación de los ficheros, con el nombre de –INSERTADO (***nombre\_de\_fichero-INSERTADO.pdf***)

***ELIMINAR PÁGINAS***

![ELIMINAR](img/img8.png)
**Elimina páginas** de un fichero PDF. Se eligen las páginas en formato individual, separadas por comas o por rango, separadas por guion. Deja el fichero en el mismo lugar que el original más el nombre: -CON PÁGINAS ELIMINADAS (***nombre\_del\_fichero-CON PÁGINAS ELIMINADAS.pdf***).

**INSERTAR**

![INSERTAR](img/img9.png)
**Inserta un fichero en otro**, en una página determinada, que se debe especificar. Deja el fichero montado en la misma ubicación que el original con la palabra INSERTADO. (***nombre\_de\_fichero-INSERTADO.pdf***)

**COMPRIMIR**
![comprimir](img/img10.png)
**Comprime un PDF** mediante la técnica de convertir en imágenes cada una de las páginas. Los ficheros PDF ya suelen estar comprimidos de serie y poco se puede hacer, pero a veces, funciona. La calidad del documento puede ser inaceptable.

**EXTRAER TEXTO**

![EXTRAER](img/img11.png)
**Extrae el texto** de un documento PDF que sea legible. No hace OCR, es decir, que aquellos documentos que sean fotocopias o imagen, no extraerá ningún texto.

Una vez extraído, se puede seleccionar todo o parte, copiar y pegar.
