package isaac.datos.examenjuego21

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pantallaPrincipal()
        }
    }
    @Composable
    fun pantallaPrincipal (){

        val currentActivity = LocalContext.current as? Activity
        val context = LocalContext.current

        var fondo by remember { mutableStateOf(R.drawable.fondo2) }


        Box(
            modifier = Modifier.fillMaxSize(),
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

                Column(
                    modifier = Modifier.fillMaxSize(), // Modificador para que la columna abarque toda la pantalla
                    verticalArrangement = Arrangement.Center, // Cambiar la dirección del eje principal a vertical
                    horizontalAlignment = Alignment.CenterHorizontally // Centrar los elementos horizontalmente
                ) {
                    Box(modifier = Modifier.padding(bottom = 60.dp)){
                        Image(
                            painter = painterResource(id = R.drawable.cartas_de_poker),
                            contentDescription = "Descripción de la imagen"
                        )
                        Image(
                            painter = painterResource(id = R.drawable._1),
                            contentDescription = "Descripción de la imagen",
                            modifier = Modifier
                                .size(90.dp)
                                .offset(x = 45.dp, y = 107.dp) // Ajusta la posición de la imagen _1
                        )
                    }

                    Button(
                        onClick = {
                            val intent = Intent(context, seleccionModoDeJuego::class.java).apply {
                                putExtra("fondo",fondo)
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
                            .padding(10.dp)
                            .width(160.dp)
                            .height(80.dp), // Ajusta la altura del botón a 100 dp,,
                        colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Black,
                            backgroundColor = Color(0xFFD62C37)
                        )

                    ) {
                        Text(text = "PLAY" , fontSize = 19.sp)
                    }

                    Button(
                        onClick = {
                            val intent = Intent(context, historialPartidas::class.java).apply {
                                putExtra("fondo",fondo)
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
                            .padding(10.dp)
                            .width(160.dp)
                            .height(80.dp), // Ajusta la altura del botón a 100 dp,,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Black,
                            backgroundColor = Color(0xFFD62C37)
                        )

                    ) {
                        Text(text = "SCORE" , fontSize = 19.sp)
                    }

                    Button(
                        onClick = {val intent = Intent(context, Reglas::class.java).apply {
                            putExtra("fondo",fondo)
                        }
                            currentActivity?.startActivity(intent)},
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
                            .padding(10.dp)
                            .width(160.dp)
                            .height(80.dp),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Black,
                            backgroundColor = Color(0xFFD62C37)
                        )

                    ) {
                        Text(text = "REGLAS" , fontSize = 19.sp)
                    }

                    Text(
                        text = "Fondos",
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


                            Row(modifier = Modifier.padding(16.dp)) {
                        IconButton(onClick = { fondo = R.drawable.fondo2},

                        ) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(5.dp)
                                    .border(4.dp, Color(0xFFE2C777))
                            ) {
                                Image(
                                    painterResource(id = R.drawable.fondo2),
                                    contentDescription = "Mi imagen",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }
                        IconButton(
                            onClick = {
                                      fondo = R.drawable.fondomadera
                                },

                            ) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(5.dp)
                                    .border(4.dp, Color(0xFFE2C777))
                            ) {
                                Image(
                                    painterResource(id = R.drawable.fondomadera),
                                    contentDescription = "Mi imagen",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }
                        IconButton(onClick = {fondo = R.drawable.fondotecate},

                            ) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(5.dp)
                                    .border(4.dp, Color(0xFFE2C777))
                            ) {
                                Image(
                                    painterResource(id = R.drawable.fondotecate),
                                    contentDescription = "Mi imagen",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }
                    }




                }
            }
        )
    }
}

