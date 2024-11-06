package com.example.ejercicobuttoncheckboxetc_franciscomarin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ejercicobuttoncheckboxetc_franciscomarin.ui.theme.Ejercicobuttoncheckboxetc_FranciscoMarinTheme
import kotlinx.coroutines.delay
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicobuttoncheckboxetc_FranciscoMarinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Aplicacion() {
    var mensaje by remember { mutableStateOf("") }
    var progreso by remember { mutableStateOf(false) }
    var comprobacion by remember { mutableStateOf(false) }
    var boton by remember { mutableStateOf(false) }
    var textoBoton by remember { mutableStateOf("PULSAME") }
    var interruptor by remember { mutableStateOf(false) }
    var opcionseleccionada by remember { mutableStateOf(0) }
    var mostrarradiobuttons by remember { mutableStateOf(false) } // Estado para mostrar los RadioButtons

    val imagenes = arrayOf(
        painterResource(id = R.drawable.imagen1),
        painterResource(id = R.drawable.imagen2),
        painterResource(id = R.drawable.imagen3)
    )
    var imagenseleccionada by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // parte1
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Checkbox(
                    checked = comprobacion,
                    onCheckedChange = { comprobacion = it }
                )
                Text(text = "Activar")
            }

            if (comprobacion) {
                Text(
                    text = "ESTABA ESCONDIDO, YA NO", // mensaje estático para que no dependa del botón
                    modifier = Modifier.padding(top = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    boton = true
                    textoBoton = "YA PULSADO"
                }
            ) {
                Text(textoBoton)
            }

            if (boton) {
                LaunchedEffect(boton) {
                    progreso = true
                    delay(5000)
                    progreso = false
                    boton = false
                    textoBoton = "PULSAME"
                }
            }

            if (progreso) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }


        // parte 2
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Icono de información",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )


            Switch(
                checked = interruptor,
                onCheckedChange = {
                    interruptor = it
                    mostrarradiobuttons = it
                }
            )
            Text(text = if (interruptor) "Interruptor Activado" else "Interruptor Desactivado")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (mostrarradiobuttons) {
                Text(text = "Selecciona una opción:")
                Row {
                    RadioButton(
                        selected = opcionseleccionada == 1,
                        onClick = {
                            opcionseleccionada = 1
                            mensaje = "Opción 1 seleccionada"
                        }
                    )
                    Text("Opción 1")
                }
                Row {
                    RadioButton(
                        selected = opcionseleccionada == 2,
                        onClick = {
                            opcionseleccionada = 2
                            mensaje = "Opción 2 seleccionada"
                        }
                    )
                    Text("Opción 2")
                }
                Row {
                    RadioButton(
                        selected = opcionseleccionada == 3,
                        onClick = {
                            opcionseleccionada = 3
                            mensaje = "Opción 3 seleccionada"
                        }
                    )
                    Text("Opción 3")
                }
            }
        }


        // parte 3
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) 
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .padding(16.dp)
                    .clickable {
                        imagenseleccionada =
                            if (imagenseleccionada < 3) imagenseleccionada + 1 else 1
                    }
            ) {
                Image(
                    painter = imagenes[imagenseleccionada - 1],
                    contentDescription = "Imagen que cambia",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Aplicacion()
}

