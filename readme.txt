El .jar esta en ./dist

Guardar Imagen:

Para guardar tiene que agregarse la extension del archivo al final de su nombre.
Ejemplos:

hola.jpg 
hola.bmp
hola.png

Reduccion de Bits:
Se selecciona la opcion de reduccion de bits y se aplicara reduccion del 1er bit mas significativo, se puede realizar hasta los primeros 8 bits mas significativos.

Umbralizacion Custom:
Se utilizo el Umbral local o adaptivo que consiste en tomar una mascara y sacar el promedio de los pixeles de dicha mascara y este seria el umbral global (asumimos que la mascara seria del tamaño de la imagen)

Elemento Estructurante

Al seleccionar la operador morfológico a utilizar se revisa el text area
*   
Si está vacio, por default es una elipse 5 x 5
*   
Se puede rellenar para escoger entre
        

0- cruz
        
1- rectangulo
        
2- elipse
    colocando su numero correspondiente en el text area
*   

Se puede colocar un elemento estructurante custom usando el formato
        

<sizex> <sizey> <anchorx> <anchory>
        
<value> . . . . . . 
        
<value> . . . . . .

 
   
ejemplo1:
  
      
10 10 0 0
        
1 0 0 0 0 0 0 0 0 0
        
0 1 0 0 0 0 0 0 0 0
        
0 0 1 0 0 0 0 0 0 0
        
0 0 0 1 0 0 0 0 0 0
        
0 0 0 0 1 0 0 0 0 0
        
0 0 0 0 0 1 0 0 0 0
        
0 0 0 0 0 0 1 0 0 0
        
0 0 0 0 0 0 0 1 0 0
        
0 0 0 0 0 0 0 0 1 0
        
0 0 0 0 0 0 0 0 0 1
 
   
ejemplo2: 
  
      
15 15 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
  
  
ejemplo3:
        

15 15 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
       
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 
   
ejemplo4:

        
15 15 0 0
        
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 1 0 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 1 0 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 1 0 0 0 0 0 0 0 0 0
        
0 0 0 0 0 0 1 0 0 0 0 0 0 0 0
        
1 1 1 1 1 1 1 1 0 0 0 0 0 0 0
        
0 0 0 0 0 0 1 0 0 0 0 0 0 0 0
        
0 0 0 0 0 1 0 0 0 0 0 0 0 0 0
        
0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
        
0 0 0 1 0 0 0 0 0 0 0 0 0 0 0
        
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
        
0 1 0 0 0 0 0 0 0 0 0 0 0 0 0
        
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0