package isaac.datos.examenjuego21

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class historialPartidas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fondo = intent.getIntExtra("fondo", 0)
        setContent{
            HistorialScreen(fondo = fondo)
        }

    }

    @Composable
    fun HistorialScreen(fondo: Int) {
        val context = LocalContext.current
        val partidas = getPartidasFromFile(context)
        var color = 0xFFE2C777
            Image(
                painter = painterResource(id = fondo),
                contentDescription = "Fondo de mesa de poker",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .border(width = 12.dp, color = Color(0xFF853501))
            )


                if (partidas.isNotEmpty()) {
                    LazyColumn {
                        items(partidas) { partida ->
                            if (partida.Ganador == "Jugador"){
                                 color = 0xFFE2C777
                            }else{
                                 color = 0xFFD62C37
                            }
                            Card(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .background(Color(0xFFD62C37)),
                                elevation = 4.dp
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .background(Color(color))
                                ) {

                                    Text(text = "Fecha: ${partida.fecha}", fontSize = 20.sp)
                                    Text(text = "Jugador 1: ${partida.jugador1}", fontSize = 20.sp)
                                    Text(text = "Jugador 2: ${partida.jugador2}", fontSize = 20.sp)
                                    Text(text = "Puntaje 1: ${partida.puntaje1}", fontSize = 20.sp)
                                    Text(text = "Puntaje 2: ${partida.puntaje2}", fontSize = 20.sp)
                                    Text(text = "Ganador: ${partida.Ganador}", fontSize = 20.sp)

                                    LazyRow {
                                        items(partida.cartas) { carta ->
                                            Image(
                                                painter = painterResource(id = carta),
                                                contentDescription = null,
                                                modifier = Modifier.padding(4.dp)
                                            )
                                        }
                                    }
                                }
                            }

                        }

                    }
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "No hay partidas registradas.",
                            style = MaterialTheme.typography.h5
                        )
                    }
                }
    }

    private fun getPartidasFromFile(context: Context): List<juego.Partida> {
        val file = File(context.filesDir, "historial.json")
        val json = file.bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<juego.Partida>>() {}.type
        return Gson().fromJson(json, type)
    }
}




