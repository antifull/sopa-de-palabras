### Problema
En este punto queremos conocer cómo desarrollas un algoritmo.

“Necesitamos una engine que genere aleatoriamente una sopa de letras, con la
posibilidad de que estas palabras estén ubicadas horizontalmente, verticalmente
y en diagonal de izquierda a derecha o derecha a izquierda, de arriba hacia
abajo o de abajo hacia arriba.

Un parámetro a tener en cuenta en la generación, es que la sopa de letras puede
ser de distintos tamaños tanto en ancho como en alto sin necesidad de que la
misma sea cuadrada, esto dos valores (ancho y alto) no pueden ser menores a
15 letras y mayores a 80.

Para esto necesitamos interactuar con el engine de forma de poder indicarle toda
la parametrización, tanto las alternativas válidas para acomodar las palabras
como el tamaño de la sopa de letras.

Una vez generada la sopa de letras hay que poder visualizar la misma en forma
de caracteres de la siguiente manera
```
|h|s|d|f|g|
|a|o|d|f|g|
|a|s|l|f|g|
|a|s|d|a|g|
```
Una vez que se pueda visualizar la sopa de letras hay que poder indicarle al
sistema que ha encontrado una palabra: indicando el par fila y columna inicial de
la palabra y el par final; el sistema tiene que poder indicar si la palabra es correcta
y si el path de la palabra es correcto.

Si la palabra es correcta hay que transformar los caracteres a su
correspondientes mayúsculas.

Ejemplo:
```
|H|s|d|f|g|
|a|O|d|f|g|
|a|s|L|f|g|
|a|s|d|A|g|
```
### Web services

En este punto queremos ver qué herramientas utilizas; cómo desarrollas un web
service para publicar tu algoritmo desarrollado en el punto anterior.

### Endpoint para poder crear la sopa de letras
**POST** http://host/alphabetSoup/
```json
{
"w":15,
"h":15,
"ltr":true,
"rtl":true,
"ttb":true,
"btt":true,
"d":true
}
```
* **w** - Ancho de la sopa de letras, valor opcional por defecto debe ser 15
* **h** - Largo de la sopa de letras, valor opcional pode defecto debe ser 15
* **ltr** - Habilitar o deshabilitar palabras que van de izquierda a derecha, valor
opcional por defecto debe ser true
* **rtl** - Habilitar o deshabilitar palabras que van de derecha a izquierda, valor
opcional por defecto debe ser false
* **ttb** - Habilitar o deshabilitar palabras que van desde arriba hacia abajo, valor
opcional por defecto debe ser true
* **btt** - Habilitar o deshabilitar palabras que van desde abajo hacia arriba, valor
opcional por defecto debe ser false
* **d** - Habilitar o deshabilitar palabras diagonales, valor por opcional por defecto
debe ser false

Este endpoint debe devolver un json que indique el id autogenerado para la sopa
que se acaba de crear, este id debe tener formato `UUID`, en caso de que falle
debe indicarse en el código http como un `error 400` y en el body un json que
indique el mensaje del error.

En caso satisfactorio:
  ```json
  {
  "id":"d041eaf2-0ac2-4376-812b-3e08be0bfd65"
  }
  ```
  En caso de error:
  ```json
  {
  "message":"Mensaje de error"
  }
  ```
### Endpoint para visualizar la lista de palabras
**GET** http://host/alphabetSoup/list/d041eaf2-0ac2-4376-812b-3e08be0bfd65

Este endpoint debe devolver la lista de palabras que se encuentran en la sopa
de letras en formato json

### Endpoint para visualizar la sopa de letras
**GET** http://host/alphabetSoup/view/d041eaf2-0ac2-4376-812b-3e08be0bfd65

Este endpoint debe devolver el formato de texto plano indicado en los puntos
anteriores con las palabras ya encontradas hasta el momento.

### Endpoint para indicar que hemos encontrado una palabra
**PUT** http://host/alphabetSoup/d041eaf2-0ac2-4376-812b-3e08be0bfd65
```json
{
"sr":0,
"sc":0,
"er":10,
"ec":10
}
```
* **sr** - Fila donde comienza la palabra encontrada
* **sc** - Columna donde comienza la palabra encontrada
* **er** - Fila donde termina la palabra encontrada
* **ec** - Columna donde termina la palabra encontrada

Este endpoint debe devolver un json con un mensaje indicando si la palabra
encontrada es correcta o no y modificar el estado de la sopa de letras.
