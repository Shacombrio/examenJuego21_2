package isaac.datos.examenjuego21

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class seleccionModoDeJuego : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fondo = intent.getIntExtra("fondo", 0)
        setContent {
            selectModo(fondo = fondo)
        }
    }
}

@Composable
fun selectModo(fondo: Int, ){
    val currentActivity = LocalContext.current as? Activity
    val context = LocalContext.current


            Image(
                painter = painterResource(id = fondo),
                contentDescription = "Fondo de mesa de poker",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .border(width = 12.dp, color = Color(0xFF853501))
            )

            Column(
                modifier = Modifier.fillMaxSize(), // Modificador para que la columna abarque toda la pantalla
                verticalArrangement = Arrangement.Center, // Cambiar la dirección del eje principal a vertical
                horizontalAlignment = Alignment.CenterHorizontally // Centrar los elementos horizontalmente
            ) {
                Text(
                    text = "Modo de juego",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape
                        )
                        .background(Color(0xFFE2C777))
                        .padding(13.dp)
                )
                Button(
                    onClick = {
                        val intent = Intent(context, juego::class.java).apply {
                            putExtra("fondo",fondo)
                            putExtra("valor",21)
                        }
                        currentActivity?.startActivity(intent)
                    },
                    shape = RoundedCornerShape(40),
                    border = BorderStroke(
                        width = 5.dp,
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFE2C777),
                                Color(0xFFFFA726)
                            )
                        )
                    ),

                    modifier = Modifier
                        .padding(16.dp)
                        .width(160.dp) // Ajusta el ancho del botón a 200 dp
                        .height(80.dp), // Ajusta la altura del botón a 100 dp,,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Black,
                        backgroundColor = Color(0xFFD62C37)
                    )

                ) {
                    Text(text = "Clasico (21)" , fontSize = 19.sp)
                }

                Button(
                    onClick = {
                        val intent = Intent(context, juego::class.java).apply {
                            putExtra("fondo",fondo)
                            putExtra("valor",25)
                        }
                        currentActivity?.startActivity(intent)
                    },
                    shape = RoundedCornerShape(40),
                    border = BorderStroke(
                        width = 5.dp,
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFE2C777),
                                Color(0xFFFFA726)
                            )
                        )
                    ),

                    modifier = Modifier
                        .padding(16.dp)
                        .width(160.dp) // Ajusta el ancho del botón a 200 dp
                        .height(80.dp), // Ajusta la altura del botón a 100 dp,,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Black,
                        backgroundColor = Color(0xFFD62C37)
                    )

                ) {
                    Text(text = "25" , fontSize = 19.sp)
                }

                Button(
                    onClick = {
                        val intent = Intent(context, juego::class.java).apply {
                            putExtra("fondo",fondo)
                            putExtra("valor",30)
                        }
                        currentActivity?.startActivity(intent)
                    },
                    shape = RoundedCornerShape(40),
                    border = BorderStroke(
                        width = 5.dp,
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFE2C777),
                                Color(0xFFFFA726)
                            )
                        )
                    ),

                    modifier = Modifier
                        .padding(16.dp)
                        .width(160.dp) // Ajusta el ancho del botón a 200 dp
                        .height(80.dp), // Ajusta la altura del botón a 100 dp,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Black,
                        backgroundColor = Color(0xFFD62C37)
                    )

                ) {
                    Text(text = "30" , fontSize = 19.sp)
                }

            }

}
