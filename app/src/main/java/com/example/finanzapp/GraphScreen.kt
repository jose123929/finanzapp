package com.example.finanzapp

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GraphScreen(
    navigateBack: () -> Unit
) {
    var textCenter by remember { mutableStateOf("100") }
    var textCenter1 by remember { mutableStateOf("100") }
    var textCenter2 by remember { mutableStateOf("100") }
    var textCenter3 by remember { mutableStateOf("100") }

    var baseText by remember { mutableStateOf("2300") }
    val cleanBaseText = { text: String -> text.replace("$", "").trim().toIntOrNull() ?: 0 }
    val newBaseText = cleanBaseText(baseText) + 100
    val diference =
        cleanBaseText(textCenter) + cleanBaseText(textCenter1) + cleanBaseText(textCenter2) + cleanBaseText(
            textCenter3
        )
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
                .verticalScroll(rememberScrollState())
                .padding(paddings),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navigateBack() }
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
                    onClick = {}, Modifier
                        .padding(start = 290.dp)
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(120.dp)
                    .border(2.dp, color = Color.White, shape = RoundedCornerShape(24.dp))
            ) {
                Text(
                    text = "SUELDO BASE = $",
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 6.dp)
                        .shadow(10.dp, spotColor = Color.White)
                )
                Text(
                    text = "${newBaseText - diference}",
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .shadow(10.dp, spotColor = Color.White)
                )
                IconButton(
                    onClick = {
                        baseText = (cleanBaseText(baseText) + 100).toString()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "",
                        modifier = Modifier
                            .size(60.dp)
                            .border(2.dp, shape = RoundedCornerShape(24.dp), color = Color.White)
                            .background(color = Color.White)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .border(2.dp, color = Color.White, shape = RoundedCornerShape(24.dp))
            ) {
                val chartData = listOf(
                    "COMIDA" to cleanBaseText(textCenter).toFloat(),
                    "TRANSPORTE" to cleanBaseText(textCenter1).toFloat(),
                    "BASICOS" to cleanBaseText(textCenter2).toFloat(),
                    "OTROS" to cleanBaseText(textCenter3).toFloat()
                )

                FinanceBarChart(
                    data = chartData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            EntryCard1(
                description = textCenter,
                label = "GASTOS DE COMIDA",
                onUpdate = { newValue -> textCenter = newValue }
            )
            EntryCard1(
                description = textCenter1,
                label = "GASTOS DE TRANSPORTE",
                onUpdate = { newValue -> textCenter1 = newValue }
            )
            EntryCard1(
                description = textCenter2,
                label = "GASTOS BASICOS",
                onUpdate = { newValue -> textCenter2 = newValue }
            )
            EntryCard1(
                description = textCenter3,
                label = "OTROS GASTOS",
                onUpdate = { newValue -> textCenter3 = newValue }
            )
        }
    }
}

@Composable
fun EntryCard1(
    description: String,
    label: String,
    onUpdate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, color = Color.White, shape = RoundedCornerShape(24.dp)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, color = Color.White, shape = RoundedCornerShape(24.dp))
                .size(105.dp)
        ) {
            androidx.compose.material.Text(
                text = label,
                fontSize = 20.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp)
                    .shadow(10.dp, spotColor = Color.White)
            )

            androidx.compose.material.Text(
                text = description,
                fontSize = 25.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.Center)
                    .shadow(10.dp, spotColor = Color.White)
            )
            androidx.compose.material.Text(
                text = "= $",
                fontSize = 25.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(end = 75.dp)
                    .shadow(10.dp, spotColor = Color.White)
            )

            IconButton(
                onClick = {
                    val currentAmount = description.toIntOrNull() ?: 0
                    onUpdate("${currentAmount + 25}")
                },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20.dp)
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .border(2.dp, shape = RoundedCornerShape(24.dp), color = Color.White)
                        .background(color = Color.White)
                )
            }

            IconButton(
                onClick = {
                    val currentAmount = description.toIntOrNull() ?: 0
                    onUpdate("${currentAmount - 25}")
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp)
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .border(2.dp, shape = RoundedCornerShape(24.dp), color = Color.White)
                        .background(color = Color.White)
                )
            }
        }
    }
}

@Composable
fun FinanceBarChart(
    data: List<Pair<String, Float>>,
    modifier: Modifier = Modifier
) {
    val maxValue = (data.maxOfOrNull { it.second } ?: 0f) * 1.2f

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp)
                .height(180.dp)
        ) {
            val barWidth = size.width / (data.size * 2)
            val spaceBetween = barWidth

            data.forEachIndexed { index, (label, value) ->
                val barHeight = (value / maxValue) * size.height
                val x = index * (barWidth + spaceBetween)

                drawRect(
                    color = Color.Cyan,
                    topLeft = Offset(x, size.height - barHeight),
                    size = Size(barWidth, barHeight)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            data.forEach { (label, _) ->
                Text(
                    text = label,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(40.dp),
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun myGrapPrev() {
    GraphScreen(
        navigateBack = {}
    )
}