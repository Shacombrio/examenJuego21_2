package isaac.datos.examenjuego21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Reglas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fondo = intent.getIntExtra("fondo", 0)

        setContent{
            Reglas21Screen(fondo = fondo)
        }
    }
}

@Composable
fun Reglas21Screen(fondo: Int) {
Box (        modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
    content = {
    Image(
        painter = painterResource(id = fondo),
        contentDescription = "Fondo de mesa de poker",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .border(width = 12.dp, color = Color(0xFF853501))
    )
    LazyColumn {
        item {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Text(
                    text = "Reglas del 21 (Blackjack)",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Text(
                    text = "El objetivo del juego es tener una mano de cartas con un valor total más cercano a 21 que la mano del crupier sin pasarse de 21.",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Text(
                    text = "Los valores de las cartas son los siguientes:",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Column {
                    Text(
                        text = " - Las cartas del 2 al 10 valen su valor nominal.",
                        fontSize = 20.sp
                    )
                    Text(
                        text = " - Las cartas J, Q y K valen 10 puntos cada una.",
                        fontSize = 20.sp
                    )
                    Text(
                        text = " - El As puede valer 1 u 11 puntos, según convenga al jugador.",
                        fontSize = 20.sp
                    )
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Text(
                    text = "Desarrollo del juego:",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Column {
                    Text(text = "1. El jugador realiza su apuesta.", fontSize = 20.sp)
                    Text(
                        text = "2. El crupier reparte dos cartas al jugador y dos a sí mismo, una de ellas boca arriba.",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "3. Si el jugador tiene un 21 natural (un As y una carta con valor de 10 puntos), gana automáticamente a menos que el crupier también tenga un 21 natural.",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "4. Si el jugador no tiene un 21 natural, puede elegir pedir más cartas (hit) para intentar mejorar su mano, o plantarse (stand) con la mano que tiene.",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "5. Si el jugador supera los 21 puntos, pierde automáticamente.",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "6. Si el jugador se planta, es el turno del crupier. El crupier saca cartas hasta que su mano suma al menos 17 puntos.",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "7. Si el crupier supera los 21 puntos, el jugador gana automáticamente.",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "8. Si el crupier no supera los 21 puntos, se comparan las manos y gana el que tenga el valor total más cercano a 21.",
                        fontSize = 20.sp
                    )
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Text(
                    text = "¡Diviértete jugando al blackjack!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
    }
)
}