package isaac.datos.examenjuego21

import android.app.Activity
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.key
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.random.Random
import androidx.compose.ui.platform.LocalContext
import android.content.res.Resources
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.*
import kotlinx.coroutines.sync.Semaphore
import kotlin.system.exitProcess
import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*
import android.content.SharedPreferences
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.shadow
import com.google.gson.reflect.TypeToken
import java.io.File


var resourceName3 = ""
var cont = true
var sumaJugador= 0
var sumaPc = 0
var sumaDealer = 0
var suma = 0
var suma2 = 0
var suma3 = 0
var c1= R.drawable._05_dos_de_espadas
var c2= R.drawable._05_dos_de_espadas
var c3= R.drawable._05_dos_de_espadas
var pib = false
var pib2 = false
var pib3 = false
var pib4 = false
var pib5 = false
var conteoCartaOculta = 0
var resourceName = ""
var d = 0
var valorNumerico2 = 0
var noSuma = true
var btnStart = true
var btnMoreCard = false
var btnDealer = false
var noSuma2 = true
var noSuma3 = true
var car2 = "_01as_de_diamantes"
var cartas2 =  mutableListOf<Int>()
var cartas3 = mutableListOf<Int>()
var cartas4 = mutableListOf<Int>()
var yaGano = true
class juego : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fondo = intent.getIntExtra("fondo", 0)
        val valor = intent.getIntExtra("valor", 0)

        setContent {
            juegoPrincipal(fondo,valor)
        }
    }



data class Partida(
    val fecha: String,
    val jugador1: String,
    val jugador2: String,
    val puntaje1: Int,
    val puntaje2: Int,
    val Ganador: String,
    val cartas: MutableList<Int> = mutableListOf<Int>()
)
@Composable
fun juegoPrincipal(fondo: Int, valor:Int) {
    yaGano = true
    var random = Random(System.currentTimeMillis())
    var currentImage by remember { mutableStateOf(R.drawable._02_as_de_espadas) }
    var currentImage2 by remember { mutableStateOf(R.drawable._11_tres_de_diamantes) }
    var currentImageDeeler by remember { mutableStateOf(R.drawable._52_rey_de_corazones) }
    var currentImagePc by remember { mutableStateOf(R.drawable._21_seis_de_espadas) }
    var currentImageRep by remember { mutableStateOf(R.drawable._21_seis_de_espadas) }
    var playerWon by remember { mutableStateOf(false) }
    var playerLost by remember { mutableStateOf(false) }
    var playerDraw by remember { mutableStateOf(false) }

    val images = listOf(
        R.drawable._01_as_de_diamantes,
        R.drawable._02_as_de_espadas, R.drawable._03_as_de_treboles,
        R.drawable._04_as_de_corazones, R.drawable._05_dos_de_espadas,
        R.drawable._06_dos_de_treboles, R.drawable._07_dos_de_diamantes,
        R.drawable._08_dos_de_corazones, R.drawable._09_tres_de_espadas,
        R.drawable._10_tres_de_treboles, R.drawable._11_tres_de_diamantes,
        R.drawable._12_tres_de_corazones, R.drawable._13_cuatro_de_espadas,
        R.drawable._14_cuatro_de_treboles, R.drawable._15_cuatro_de_diamantes,
        R.drawable._16_cuatro_de_corazones, R.drawable._17_cinco_de_espadas,
        R.drawable._18_cinco_de_treboles, R.drawable._19_cinco_de_diamantes,
        R.drawable._20_cinco_de_corazones, R.drawable._21_seis_de_espadas,
        R.drawable._22_seis_de_treboles, R.drawable._23_seis_de_diamantes,
        R.drawable._24_seis_de_corazones, R.drawable._25_siete_de_picas,
        R.drawable._26_siete_de_treboles, R.drawable._27_siete_de_diamantes,
        R.drawable._28_siete_de_corazones, R.drawable._29_ocho_de_picas,
        R.drawable._30_ocho_de_treboles, R.drawable._31_ocho_de_diamantes,
        R.drawable._32_ocho_de_corazones, R.drawable._33_nueve_de_picas,
        R.drawable._34_nueve_de_treboles, R.drawable._35_nueve_de_diamantes,
        R.drawable._36_nueve_de_corazones, R.drawable._37_diez_de_espadas,
        R.drawable._38_diez_de_treboles, R.drawable._39_diez_de_diamantes,
        R.drawable._40_diez_de_corazones, R.drawable._41_jota_de_picas,
        R.drawable._42_jota_de_treboles, R.drawable._43_jota_de_diamantes,
        R.drawable._44_jota_de_corazones, R.drawable._45_reina_de_espadas,
        R.drawable._46_reina_de_treboles, R.drawable._47_reina_de_diamantes,
        R.drawable._48_reina_de_corazones, R.drawable._49_rey_de_picas,
        R.drawable._50_rey_de_treboles, R.drawable._51_rey_de_diamantes,
        R.drawable._52_rey_de_corazones


    )
    val context = LocalContext.current



    val viewModel = remember { MyViewModel() }
    val imageList = viewModel.imageList
    val imageChunks = imageList.chunked(3) // Divide la lista en sublistas de 4 elementos

    val viewModel2 = remember { MyViewModel2() }
    val imageList2 = viewModel2.imageList2
    val imageChunks2 = imageList2.chunked(3)

    val viewModel3 = remember { MyViewModel2() }
    val imageList3 = viewModel3.imageList2
    val imageChunks3 = imageList3.chunked(3)

    var showDialog by remember { mutableStateOf(false) }
    var asValue by remember { mutableStateOf(1) }
    var car2 = LocalContext.current.resources.getResourceEntryName(c2)
    val car3 = LocalContext.current.resources.getResourceEntryName(c3)

    Image(
        painter = painterResource(id = fondo),
        contentDescription = "Fondo de mesa de poker",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .border(width = 12.dp, color = Color(0xFF853501))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
    ) {

        for (chunk in imageChunks) {
            Row {

                for (image in chunk) {

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .padding(2.dp)

                    )
                    resourceName = LocalContext.current.resources.getResourceEntryName(image)
                    val valorNumerico = getValueFromImage(resourceName)
                    if (cartas2.contains(image)) {
                        // El elemento ya existe en la lista, no hacer nada
                    } else {
                        // El elemento no existe en la lista, agregarlo
                        cartas2.add(image)
                    }
                    if (noSuma) {

                        if (pib4) {

                        } else {

                            if (pib) {
                                suma += valorNumerico
                                if (car2 == "_01_as_de_diamantes" || car2 == "_02_as_de_espadas" || car2 == "_03_as_de_treboles" || car2 == "_04_as_de_corazones"){
                                    suma += 11
                                    car2 = ""
                                }
                            } else {
                                suma = valorNumerico
                                if (car2 == "_01_as_de_diamantes" || car2 == "_02_as_de_espadas" || car2 == "_03_as_de_treboles" || car2 == "_04_as_de_corazones"){
                                    suma == 11
                                    car2 = ""
                                }
                            }
                        }
                    }else{
                    }

                    Log.d("Nombre de la imagen" ,valorNumerico.toString())
                }

            }

        }
        if(noSuma){
            if (pib4) {
            } else {


                if (pib) {
                    sumaJugador = suma
                } else {
                    sumaJugador += suma
                }
                if (sumaJugador == valor && yaGano){
                    playerWon = true
                    val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                    val partidasAnteriores = cargarHistorial(context)
                    val nuevaPartida = Partida(fecha, "Jugador", "PC", sumaJugador, sumaPc, "Jugador",cartas2)
                    val historialActualizado = partidasAnteriores + nuevaPartida
                    guardarHistorial(historialActualizado, context)
                }
                if (sumaJugador > valor && yaGano){
                    playerLost = true
                    val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                    val partidasAnteriores = cargarHistorial(context)
                    val nuevaPartida = Partida(fecha, "Jugador", "PC", sumaJugador, sumaPc, "PC",cartas4)
                    val historialActualizado = partidasAnteriores + nuevaPartida
                    guardarHistorial(historialActualizado, context)
                }
            }

        }else{
        }

        Text(
            text = "Jugador: $sumaJugador",
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

        Column(modifier = Modifier.padding(top = 40.dp)) {


            for (chunk in imageChunks3) {
                Row {
                    for (image3 in chunk) {
                        Image(
                            painter = painterResource(id = image3),
                            contentDescription = null,
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                                .padding(2.dp)

                        )
                        resourceName3 = LocalContext.current.resources.getResourceEntryName(image3)
                        valorNumerico2 = getValueFromImage(resourceName3)
                        Log.d("MiTag", "Valor actual de sumaDealer: $sumaDealer")
                        Log.d("MiTag", "Valor : $valorNumerico2")
                        if (cartas3.contains(image3)) {
                            // El elemento ya existe en la lista, no hacer nada
                        } else {
                            // El elemento no existe en la lista, agregarlo
                            cartas3.add(image3)
                        }
                        if (pib2){

                            Image(
                                painter = painterResource(R.drawable.signo),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(80.dp)
                                    .padding(2.dp)

                            )
                        }

                    }
                }
            }
            if (resourceName3 == "_01_as_de_diamantes" || resourceName3 == "_02_as_de_espadas" || resourceName3 == "_03_as_de_treboles" || resourceName3 == "_04_as_de_corazones") {
                valorNumerico2 = 11
            }
            if (noSuma){
                if (pib) {
                    if (sumaDealer < 17) {
                        sumaDealer += valorNumerico2
                    }
                    if (sumaDealer >= 17){

                        if (sumaDealer > sumaJugador && sumaDealer <= valor || sumaPc > sumaJugador && yaGano){
                            playerLost = true
                            val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                            val partidasAnteriores = cargarHistorial(context)
                            val nuevaPartida = Partida(fecha, "Jugador", "PC", sumaJugador, sumaPc,"PC",cartas4)
                            val historialActualizado = partidasAnteriores + nuevaPartida
                            guardarHistorial(historialActualizado, context)
                        }else{
                                playerWon = true
                            val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                            val partidasAnteriores = cargarHistorial(context)
                            val nuevaPartida = Partida(fecha, "Jugador", "PC", sumaJugador, sumaPc,"Jugador",cartas2)
                            val historialActualizado = partidasAnteriores + nuevaPartida
                            guardarHistorial(historialActualizado, context)
                        }

                        if(sumaJugador == sumaPc && yaGano){
                            playerDraw = true
                            playerWon = false
                            playerLost = false
                            val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                            val partidasAnteriores = cargarHistorial(context)
                            val nuevaPartida = Partida(fecha, "Jugador", "PC", sumaJugador, sumaPc,"Empate",cartas3)
                            val historialActualizado = partidasAnteriores + nuevaPartida
                            guardarHistorial(historialActualizado, context)
                        }
                    }

                }else{

                }
            }else{
                noSuma = true

            }

            Text(
                text = "Crupier: $sumaDealer",
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
        }

        Column(modifier = Modifier.padding(top = 40.dp)) {


            for (chunk in imageChunks2) {
                Row {
                    for (image2 in chunk) {

                        Image(
                            painter = painterResource(id = image2),
                            contentDescription = null,
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                                .padding(2.dp)

                        )
                        val resourceName2 =
                            LocalContext.current.resources.getResourceEntryName(image2)
                        var valorNumerico3 = getValueFromImage(resourceName2)
                        if (cartas4.contains(image2)) {
                            // El elemento ya existe en la lista, no hacer nada
                        } else {
                            // El elemento no existe en la lista, agregarlo
                            cartas4.add(image2)
                        }
                        if (resourceName2 == "_01_as_de_diamantes" || resourceName2 == "_02_as_de_espadas" || resourceName2 == "_03_as_de_treboles" || resourceName2 == "_04_as_de_corazones") {
                            valorNumerico3 = 11
                        }
                        if (pib4){}else{
                            if(sumaPc < 16 && noSuma) {
                                if (pib3) {
                                    suma2 += valorNumerico3

                                } else {
                                    suma2 = valorNumerico3
                                }
                            }
                        }
                    }
                }
            }
            if (pib4){
            }else{
                    if(sumaPc < 16 && noSuma) {
                    if (pib3) {
                        sumaPc = suma2
                        pib3 = false
                    } else {
                        sumaPc += suma2
                    }
                        if (sumaPc == valor && yaGano){
                            playerLost = true
                            val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                            val partidasAnteriores = cargarHistorial(context)
                            val nuevaPartida = Partida(fecha, "Jugador", "PC", sumaJugador, sumaPc,"PC",cartas4)
                            val historialActualizado = partidasAnteriores + nuevaPartida
                            guardarHistorial(historialActualizado, context)
                        }
                        if (sumaPc > valor && yaGano){
                            playerWon = true
                            val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                            val partidasAnteriores = cargarHistorial(context)
                            val nuevaPartida = Partida(fecha, "Jugador", "Jugador", sumaJugador, sumaPc,"PC",cartas2)
                            val historialActualizado = partidasAnteriores + nuevaPartida
                            guardarHistorial(historialActualizado, context)
                        }
                        if (sumaPc > valor && sumaJugador > valor && yaGano){
                            playerDraw = true
                            playerWon = false
                            playerLost = false
                            val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                            val partidasAnteriores = cargarHistorial(context)
                            val nuevaPartida = Partida(fecha, "Jugador", "PC", sumaJugador, sumaPc,"PC", cartas2)
                            val historialActualizado = partidasAnteriores + nuevaPartida
                            guardarHistorial(historialActualizado, context)
                        }
                }
            }

            Text(
                text = "PC: $sumaPc",
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
        }


    }




    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(onClick = {
            random = Random(System.currentTimeMillis())
            random = Random(System.currentTimeMillis())

            currentImage = images[random.nextInt(images.size)]
            currentImage2 = images[random.nextInt(images.size)]
            currentImageDeeler = images[random.nextInt(images.size)]
            currentImagePc = images[random.nextInt(images.size)]

            var randomImage = images[random.nextInt(images.size)]

            currentImage = randomImage
                viewModel.addImage(currentImage)

            imageList2.shuffled()

            var random2 = Random(System.currentTimeMillis())
            var randomImage2 = images[random2.nextInt(images.size)]
            currentImagePc = randomImage2
            if (sumaPc < 16) {
                viewModel2.addImage(currentImagePc)
            }
            c1 = currentImage
            btnStart = false
            pib = false
            noSuma = true

        },
            enabled = btnMoreCard,
            modifier = Modifier
            .align(Alignment.BottomStart) // Alinea el botón en la esquina inferior izquierda
            .offset(16.dp, (-16).dp) // Añade un espacio adicional de 16dp a la izquierda y abajo del botón

        ) {
            Text("Nueva carta")
        }
    }

    if (noSuma) {
        val car1 = LocalContext.current.resources.getResourceEntryName(c1)
        if (car1 == "_01_as_de_diamantes" || car1 == "_02_as_de_espadas" || car1 == "_03_as_de_treboles" || car1 == "_04_as_de_corazones") {
            showDialog = true
        } else {
            showDialog = false
        }

        if (showDialog) {
            AsValueDialog(
                onConfirm = { value ->
                    asValue = value
                    showDialog = false // se actualiza showDialog al confirmar

                    sumaJugador += asValue

                },
                onDismiss = {
                    showDialog = false // se actualiza showDialog al cancelar o cerrar
                }
            )
        }
    }




    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(onClick = {
            random = Random(System.currentTimeMillis())

            currentImage = R.drawable._40_diez_de_corazones
            currentImage2 = R.drawable._01_as_de_diamantes
            currentImageDeeler = images[random.nextInt(images.size)]
            currentImagePc = images[random.nextInt(images.size)]

            var randomImage = images[random.nextInt(images.size)]
            currentImage = randomImage
            c2 = currentImage
            viewModel.addImage(currentImage)
            Log.d("current" ,currentImage.toString())

            currentImage = images[random.nextInt(images.size)]
            currentImage2 = images[random.nextInt(images.size)]
            currentImageDeeler = images[random.nextInt(images.size)]
            currentImagePc = images[random.nextInt(images.size)]

            val random2 = Random(System.currentTimeMillis())
            var randomImage2 = images[random2.nextInt(images.size)]
            currentImage2 = randomImage2
            viewModel.addImage(currentImage2)
            c3 = currentImage2
            imageList2.shuffled()
            var random3 = Random(System.currentTimeMillis())
            var randomImage3 = images[random3.nextInt(images.size)]
            currentImagePc = randomImage3
            viewModel2.addImage(currentImagePc)

            currentImage = images[random.nextInt(images.size)]
            currentImage2 = images[random.nextInt(images.size)]
            currentImageDeeler = images[random.nextInt(images.size)]
            currentImagePc = images[random.nextInt(images.size)]

            viewModel2.addImage(currentImagePc)

            currentImage = images[random.nextInt(images.size)]
            currentImage2 = images[random.nextInt(images.size)]
            currentImageDeeler = images[random.nextInt(images.size)]
            currentImagePc = images[random.nextInt(images.size)]
            conteoCartaOculta ++
            currentImage = images[random.nextInt(images.size)]
            currentImage2 = images[random.nextInt(images.size)]
            currentImageDeeler = images[random.nextInt(images.size)]
            currentImagePc = images[random.nextInt(images.size)]
            viewModel3.addImage(currentImageDeeler)
            conteoCartaOculta ++
            pib = true
            pib2= true
            pib3= true
            pib5 = true
            btnMoreCard = true
            btnDealer = true
            btnStart = false


        },
            enabled = btnStart,
            modifier = Modifier
            .align(Alignment.BottomStart) // Alinea el botón en la esquina inferior izquierda
            .offset(160.dp, (-16).dp) // Añade un espacio adicional de 16dp a la izquierda y abajo del botón

        ) {
            Text("Empezar" )
        }
    }


    if (car3 == "_01_as_de_diamantes" || car3 == "_02_as_de_espadas" || car3 == "_03_as_de_treboles" || car3 == "_04_as_de_corazones"){
        showDialog = true
    }else{
        showDialog = false
    }

    if(showDialog){
        noSuma = false

        AsValueDialog(
            onConfirm = { value ->
                asValue = value
                showDialog = false // se actualiza showDialog al confirmar

                    sumaJugador += asValue

                if (sumaJugador == 21){
                    playerWon = true
                    val fecha = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                    val partidasAnteriores = cargarHistorial(context)
                    val nuevaPartida = Partida(fecha, "Jugador", "PC", sumaJugador, sumaPc,"Jugador",cartas2)
                    val historialActualizado = partidasAnteriores + nuevaPartida
                    guardarHistorial(historialActualizado, context)
                }

            },
            onDismiss = {
                showDialog = false // se actualiza showDialog al cancelar o cerrar
            }
        )
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(onClick = {
            pib = true
            pib5 = true
            pib2 = false
            pib4 = true

                    random = Random(System.currentTimeMillis())
                    currentImageDeeler = images[random.nextInt(images.size)]

                    if (sumaDealer < 17) {
                        viewModel3.addImage(currentImageDeeler)
                    }


            btnMoreCard = false

        },
            enabled = btnDealer,
            modifier = Modifier
            .align(Alignment.BottomStart) // Alinea el botón en la esquina inferior izquierda
            .offset(278.dp, (-16).dp) // Añade un espacio adicional de 16dp a la izquierda y abajo del botón

        ) {
            Text("Crupier")
        }
    }

    LaunchedEffect(imageList) {
        snapshotFlow { imageList.toList() }
            .distinctUntilChanged()
            .collect { newList ->
                viewModel.imageList = newList.toMutableList()
            }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val isPaused = remember { mutableStateOf(false) }
    if (playerLost) {
        noSuma = false
        ModalWindow(result = "Perdiste XD")
    }
    if (playerWon) {
        noSuma = false
        ModalWindow(result = "Ganaste :D")
    }

    if (playerDraw) {
        noSuma = false
        ModalWindow(result = "Empate :O")
    }



}
    fun guardarHistorial(partidas: List<Partida>, context: Context) {
        val json = Gson().toJson(partidas)
        context.openFileOutput("historial.json", Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }
    fun cargarHistorial(context: Context): List<Partida> {
        val file = File(context.filesDir, "historial.json")
        if (!file.exists()) {
            return emptyList()
        }
        val json = file.readText()
        val typeToken = object : TypeToken<List<Partida>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

}

class MyViewModel: ViewModel() {
    private val _imageList = mutableStateListOf<Int>()
    var imageList: List<Int>
        get() = _imageList
        set(value) { _imageList.clear(); _imageList.addAll(value) }

    fun addImage(image: Int) {
        _imageList.add(image)
    }
}

class MyViewModel2: ViewModel() {
    private val _imageList2 = mutableStateListOf<Int>()
    var imageList2: List<Int>
        get() = _imageList2
        set(value) { _imageList2.clear(); _imageList2.addAll(value) }

    fun addImage(image: Int) {
        _imageList2.add(image)
    }
}

fun getValueFromImage(imageName: String): Int {
    return when (imageName) {
        "_05_dos_de_espadas", "_06_dos_de_treboles", "_07_dos_de_diamantes", "_08_dos_de_corazones" -> 2
        "_09_tres_de_espadas", "_10_tres_de_treboles", "_11_tres_de_diamantes", "_12_tres_de_corazones" -> 3
        "_13_cuatro_de_espadas", "_14_cuatro_de_treboles", "_15_cuatro_de_diamantes", "_16_cuatro_de_corazones" -> 4
        "_17_cinco_de_espadas", "_18_cinco_de_treboles", "_19_cinco_de_diamantes", "_20_cinco_de_corazones" -> 5
        "_21_seis_de_espadas", "_22_seis_de_treboles", "_23_seis_de_diamantes", "_24_seis_de_corazones" -> 6
        "_25_siete_de_picas", "_26_siete_de_treboles", "_27_siete_de_diamantes", "_28_siete_de_corazones" -> 7
        "_29_ocho_de_picas", "_30_ocho_de_treboles", "_31_ocho_de_diamantes", "_32_ocho_de_corazones" -> 8
        "_33_nueve_de_picas", "_34_nueve_de_treboles", "_35_nueve_de_diamantes", "_36_nueve_de_corazones" -> 9
        "_37_diez_de_espadas", "_38_diez_de_treboles", "_39_diez_de_diamantes", "_40_diez_de_corazones",
        "_41_jota_de_picas", "_42_jota_de_treboles", "_43_jota_de_diamantes", "_44_jota_de_corazones",
        "_45_reina_de_espadas", "_46_reina_de_treboles", "_47_reina_de_diamantes", "_48_reina_de_corazones",
        "_49_rey_de_picas", "_50_rey_de_treboles", "_51_rey_de_diamantes", "_52_rey_de_corazones" -> 10
        else -> 0 // Si la imagen no se reconoce como una carta, devuelve 0 como valor.
    }
}

@Composable
fun AsValueDialog(
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var asValue by remember { mutableStateOf(1) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("¿Cuánto quieres que valga la carta AS?") },
        text = {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = asValue == 1,
                        onClick = { asValue = 1 }
                    )
                    Text(text = "1 punto")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = asValue == 11,
                        onClick = { asValue = 11 }
                    )
                    Text(text = "11 puntos")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(asValue)
                    c1 = R.drawable.signo
                    c2 = R.drawable.signo
                    c3 = R.drawable.signo}

            ) {
                Text(text = "Confirmar")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Composable
fun ModalWindow(result: String) {
    var isModalVisible by remember { mutableStateOf(true) }
    val context = LocalContext.current

    if (isModalVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center, // Cambiar la dirección del eje principal a vertical
                    horizontalAlignment = Alignment.CenterHorizontally // Centrar los elementos horizontalmente
                ) {
                    Text(text = result, fontSize = 35.sp)
                    Box (Modifier.padding(16.dp)){
                        Text(
                            text = "Jugador: $sumaJugador",
                            fontSize = 24.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .shadow(
                                    elevation = 2.dp,
                                    shape = CircleShape
                                )
                                .background(Color(0xFFE2C777))
                                .padding(10.dp)

                        )
                    }

                    Box(Modifier.padding(16.dp)) {
                        Text(
                            text = "Crupier: $sumaDealer",
                            fontSize = 24.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .shadow(
                                    elevation = 2.dp,
                                    shape = CircleShape
                                )
                                .background(Color(0xFFE2C777))
                                .padding(10.dp)
                        )
                    }

                    Box(Modifier.padding(16.dp)) {
                        Text(
                            text = "PC: $sumaPc",
                            fontSize = 24.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .shadow(
                                    elevation = 2.dp,
                                    shape = CircleShape
                                )
                                .background(Color(0xFFE2C777))
                                .padding(10.dp)
                        )
                    }
                }
                Button(onClick = {
                    (context as? Activity)?.recreate()
                    sumaJugador = 0
                    sumaDealer = 0
                    sumaPc = 0
                    suma = 0
                    suma2 = 0
                    suma3 = 0
                    btnStart = true
                    btnDealer = false
                    btnMoreCard = false
                    valorNumerico2 = 0
                    pib = false
                    pib2 = false
                    pib3 = false
                    pib4 = false
                    pib5 = false
                    noSuma = false
                    cartas2 = mutableListOf<Int>()
                    cartas3 = mutableListOf<Int>()
                    cartas4 = mutableListOf<Int>()
                    yaGano = false
                    isModalVisible = false

                }) {
                    Text(text = "Reiniciar")
                }
            }
        }
    }

}











