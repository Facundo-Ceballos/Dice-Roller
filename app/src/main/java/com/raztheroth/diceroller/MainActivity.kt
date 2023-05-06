package com.raztheroth.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.raztheroth.diceroller.ui.theme.DiceRollerTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme{
                DiceRollerApp()
            }
        }
    }
}
// @Preview se utiliza para poder ver como quedan los elementos en vista previa sin necesidad de
// ejecutar el programa
@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}
// @Composable se utiliza para indicarle al programa que un elemento tiene que ser dibujado
// en pantalla
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    /* Se crea una variable que almacene el resultado para luego ser dibujado en pantalla.
     * by remember se utiliza para que luego de la recomposicion el valor se guarde
     *y no vuelva por defecto a 1
     * por defecto hay que poner el valor en 1 puesto que al principio no se aprieta el boton y
     * hay que mostrar el dado de igual manera */

    var result by remember { mutableStateOf(1)}

    /* se crea una value (constante) el cual depende de la variable result, en el caso de que
    * la variable sea de un valor del 1 al 6, devuelve la imagen correspondiente, es la manera de
    * relacionar al valor con la imagen del dado. Nótese que when funciona de manera similar a "if"
     */
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    //Hay que tener en cuenta que Column() ordena los elementos segun aparezcan en la funcion
    //Es decir, primero la imagen, despues el boton
    Column (
        modifier = modifier,
        //Permite el alineado horizontal, en este caso, en el centro
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /* Para importar imagenes hay que ir a la barra superior Seleccionar
         * Tools>ResourceManager después al simbolo "+" y en este caso como las imagenes
         * estan en formato .xml seleccionamos "Import drawables" seleccionamos las imagenes
         * correspondientes y listo.
         * Para que aparezca la ventana como antes vamos a View>Tool Windows>Project
         * Notese que se puede acceder al ResourceManager desde View>Tool Windows>ResourceManager
         */
        Image (
            //painter es un parametro que permite dibujar un recurso en pantalla
            painter = painterResource(id = imageResource),
            //para que Image() funcione necesita si o si un contentDescription
            //La descripcion debe ser un string por eso se convierte la variable "result" a string
            contentDescription = result.toString()
        )
        // El spacer establece un espaciado entre la imagen y el boton
        // En este caso el spacer es de 26dp
        Spacer(modifier = Modifier.height(26.dp))
        // Un boton el cual al ser clickeado da un resultado del 1 al 6
        // Modifica la Var "result"
        Button(onClick= { result = (1..6).random() }) {
            // El string resource se utiliza para poder traducir los textos a otros idiomas
            // Podría ser "Text("Roll")" pero no sería facil de traducir
            Text(stringResource(R.string.roll))
            //R.string.roll se encuentra en la carpeta res>values>strings.xml
        }
    }
}