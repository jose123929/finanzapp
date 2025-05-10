package com.example.finanzapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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


@Composable
fun EntryScreen(
    navigateBack:() -> Unit
) {
    var textCenter by remember { mutableStateOf("100") }
    var textCenter1 by remember { mutableStateOf("100") }
    var textCenter2 by remember { mutableStateOf("100") }
    var textCenter3 by remember { mutableStateOf("100") }
    val cleanText = { text: String -> text.replace("$", "").trim().toIntOrNull() ?: 0 }
    val sum = cleanText(textCenter) + cleanText(textCenter1) + cleanText(textCenter2) + cleanText(
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, color = Color.White, shape = RoundedCornerShape(24.dp))
                        .size(200.dp, 200.dp)
                ) {
                    Text(
                        text = "REFLEJO TOTAL DE PAGOS",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 30.dp)
                            .shadow(10.dp, spotColor = Color.White)
                    )
                    Text(
                        text = "${sum}",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .shadow(10.dp, spotColor = Color.White)
                    )
                    Text(
                        text = "= $",
                        fontSize = 25.sp,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(end = 95.dp)
                            .shadow(10.dp, spotColor = Color.White)
                    )
                }
            }

            EntryCard(
                description = textCenter,
                imageRes = R.drawable.ic_flatware,
                label = "GASTOS DE COMIDA",
                onUpdate = { newValue -> textCenter = newValue }
            )
            EntryCard(
                description = textCenter1,
                imageRes = R.drawable.ic_car,
                label = "GASTOS DE MOVILIDAD",
                onUpdate = { newValue -> textCenter1 = newValue }
            )
            EntryCard(
                description = textCenter2,
                imageRes = R.drawable.ic_home,
                label = "GASTOS BASICOS",
                onUpdate = { newValue -> textCenter2 = newValue }
            )
            EntryCard(
                description = textCenter3,
                imageRes = R.drawable.ic_airplane,
                label = "OTROS GASTOS",
                onUpdate = { newValue -> textCenter3 = newValue }
            )
        }
    }
}

@Composable
fun EntryCard(
    description: String,
    imageRes: Int,
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
                .size(150.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 25.dp)
                    .size(60.dp)
                    .border(2.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
            )

            Text(
                text = label,
                fontSize = 20.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp)
                    .shadow(10.dp, spotColor = Color.White)
            )

            Text(
                text = description,
                fontSize = 25.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.Center)
                    .shadow(10.dp, spotColor = Color.White)
            )
            Text(
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
                    .align(Alignment.TopEnd)
                    .padding(end = 30.dp, top = 20.dp)
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
                    .align(Alignment.BottomEnd)
                    .padding(end = 30.dp, bottom = 20.dp)
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

@Preview(showBackground = true)
@Composable
fun mySecondPrevScreen() {
    EntryScreen(
        navigateBack = {}
    )
}
