package com.example.finanzapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.data.DayState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(
    navigateBack:() -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent
    ) { paddings ->
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.fin),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {navigateBack()}
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            Modifier.shadow(
                                elevation = 5.dp,
                                shape = RectangleShape,
                                spotColor = Color.White
                            )
                        )
                    }
                }
                IconButton(
                    onClick = {},
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Men",
                            Modifier.shadow(
                                elevation = 5.dp,
                                shape = RectangleShape,
                                spotColor = Color.White
                            )
                        )
                    }
                }
            }
            boxInfo(
                title = "INGRESOS (SUELDOS, COBROS, ETC)",
                image = (R.drawable.ic_square_green)
            )
            boxInfo(
                title = "PAGOS (LUZ, ROPA, ETC)",
                image = (R.drawable.ic_square_red)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, shape = RoundedCornerShape(24.dp), color = Color.White)
            ) {
                val currentDate = remember { mutableStateOf(LocalDate.now()) }
                val firstDayOfMonth = currentDate.value.withDayOfMonth(1)
                val lastDayOfMonth =
                    currentDate.value.withDayOfMonth(currentDate.value.lengthOfMonth())
                val daysInMonth =
                    (firstDayOfMonth.dayOfWeek.value..7).map { it }.take(7) // Adjust accordingly
                val dayState = remember { mutableStateOf(mutableMapOf<Int, DayState>()) }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Mostrar el mes actual
                    Text(
                        text = "${currentDate.value.month} ${currentDate.value.year}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .shadow(10.dp, spotColor = Color.White),
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )

                    // Grilla de días del calendario
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(42) { index ->
                            val day = index + 1
                            if (day <= lastDayOfMonth.dayOfMonth) {
                                DayCell(
                                    day = day,
                                    state = dayState.value[day] ?: DayState.NONE,
                                    onStateChange = { newState ->
                                        //cambia estado del dia en el mapa
                                        dayState.value = dayState.value.toMutableMap().apply {
                                            put(day, newState)
                                        }
                                    }
                                )
                            } else {
                                Spacer(modifier = Modifier.size(50.dp))
                            }
                        }
                    }

                    // Botón para ir al mes siguiente
                    Button(
                        onClick = {
                            currentDate.value = currentDate.value.plusMonths(1)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            "SIGUIENTE MES",
                            color = Color.White,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun boxInfo(title: String, image: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(70.dp)
            .border(2.dp, shape = RoundedCornerShape(24.dp), color = Color.White)
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            color = Color.White,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 40.dp)
                .shadow(10.dp, spotColor = Color.White)
        )
        Image(
            painter = painterResource(image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(30.dp)
        )
    }
}

@Composable
fun DayCell(day: Int, state: DayState, onStateChange: (DayState) -> Unit) {
    val backgroundColor = when (state) {
        DayState.INCOME -> Color.Green
        DayState.PAYMENT -> Color.Red
        else -> Color.Transparent
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp) // Aumentamos el tamaño de la celda
            .padding(4.dp)
            .border(1.dp, Color.White)
            .background(backgroundColor) // Aplica el color de fondo
    ) {
        // Número del día
        Text(
            text = day.toString(),
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .shadow(10.dp, spotColor = Color.White)
        )

        // Fila con los botones para marcar "Ingreso" o "Pago"
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp), // Espacio entre los botones
            modifier = Modifier
                .align(Alignment.BottomCenter) // Colocamos los botones en la parte inferior
                .padding(bottom = 8.dp) // Añadimos un pequeño margen
        ) {
            // Botón para marcar como Ingreso
            Button(
                onClick = { onStateChange(DayState.INCOME) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                modifier = Modifier
                    .fillMaxWidth() // Aseguramos que el botón ocupe todo el ancho
                    .height(35.dp) // Ajustamos la altura del botón
                    .padding(7.dp),
            ) {
                Text(
                    text = "I",
                    fontSize = 1.sp,
                    color = Color.White
                )
            }

            // Botón para marcar como Pago
            Button(
                onClick = { onStateChange(DayState.PAYMENT) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(7.dp),
            ) {
                Text(
                    text = "P",
                    fontSize = 1.sp,
                    color = Color.White
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun myCalendarPrevScreen() {
    CalendarScreen(
        navigateBack = {}
    )
}