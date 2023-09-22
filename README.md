# pdfbox
**Guía operativa de PDFBOX**

La aplicación PDFBOX es una utilidad desarrollada en JAVA que reemplaza, en muchos casos, las operaciones que se pueden realizar con archivos PDF con el producto Adobe Acrobat® o Kofax®, cuyas licencias están limitadas. A pesar de que los dibujos y el *tooltip* es descriptivo, se edita esta guía a petición de algún usuario que lo solicitó. 

Requiere el *runtime* de JAVA 8 (el que tenemos instalado actualmente).

Por favor, reportar los problemas o sugerencias a José María Redondo, de la UPI de Córdoba.

*NOTA: Esta aplicación la desarrollé para uso personal y no era mi intención que se difundiera, pero ha ido extendiéndose sin pretenderlo. Lo comento porque adolece de todo tipo de cuidado de diseño y facilidad de uso, y solo se han tenido en cuenta las funcionalidades prácticas de la misma, por lo que pido disculpas.*

*EL AUTOR*

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

![](Aspose.Words.57cf8ae6-76b8-4fc3-8594-1d4611b341c0.003.png)

**UNIR, INDEXAR Y NUMERAR**: realiza la operación anterior y además realiza una numeración y el nombre del documento en cada página y un índice real. La portada lleva el nombre de la carpeta. Esto se utiliza para generar un expediente judicial. Las páginas de tamaño inferior al A4, puede no aparecer el número, por lo que se aconseja pasar a A4 los documentos; se considera que todas las páginas están en formato vertical. 

***SEPARAR PÁGINAS***

![](Aspose.Words.57cf8ae6-76b8-4fc3-8594-1d4611b341c0.004.png)**Separa un documento en páginas individuales**. Deja una carpeta en la ubicación del fichero original con el nombre del fichero y el texto –SEPARADAS (***nombre\_fichero –SEPARADAS***)

![](Aspose.Words.57cf8ae6-76b8-4fc3-8594-1d4611b341c0.005.png)

***ROTAR***

![](Aspose.Words.57cf8ae6-76b8-4fc3-8594-1d4611b341c0.006.png)
**Rota el documento** en +90°, 180° o -90°.

Lo deja en la misma ubicación del original con la palabra – GIRADO (***nombre\_fichero-GIRADO.pdf***)

***INCLUIR***

![](Aspose.Words.57cf8ae6-76b8-4fc3-8594-1d4611b341c0.007.png)
**Incluye un documento en otro**, a PRINCIPIO o al FINAL. Deja el nuevo fichero en la misma ubicación de los ficheros, con el nombre de –INSERTADO (***nombre\_de\_fichero-INSERTADO.pdf***)

***ELIMINAR PÁGINAS***

![](Aspose.Words.57cf8ae6-76b8-4fc3-8594-1d4611b341c0.008.png)
**Elimina páginas** de un fichero PDF. Se eligen las páginas en formato individual, separadas por comas o por rango, separadas por guion. Deja el fichero en el mismo lugar que el original más el nombre: -CON PÁGINAS ELIMINADAS (***nombre\_del\_fichero-CON PÁGINAS ELIMINADAS.pdf***).

**INSERTAR**

![](Aspose.Words.57cf8ae6-76b8-4fc3-8594-1d4611b341c0.009.png)
**Inserta un fichero en otro**, en una página determinada, que se debe especificar. Deja el fichero montado en la misma ubicación que el original con la palabra INSERTADO. (***nombre\_de\_fichero-INSERTADO.pdf***)

**COMPRIMIR**

**Comprime un PDF** mediante la técnica de convertir en imágenes cada una de las páginas. Los ficheros PDF ya suelen estar comprimidos de serie y poco se puede hacer, pero a veces, funciona. La calidad del documento puede ser inaceptable.

**EXTRAER TEXTO**

![](Aspose.Words.57cf8ae6-76b8-4fc3-8594-1d4611b341c0.011.png)
**Extrae el texto** de un documento PDF que sea legible. No hace OCR, es decir, que aquellos documentos que sean fotocopias o imagen, no extraerá ningún texto.

Una vez extraído, se puede seleccionar todo o parte, copiar y pegar.
