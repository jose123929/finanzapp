

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.R
import com.example.finanzapp.data.cards


@Composable
fun PrincipalScreen(
    NavigateToEntryScreen: () -> Unit,
    NavigateToGraphScreen: () -> Unit,
    NavigateToCalendarScreen: () -> Unit,
) {
    var textMen by remember { mutableStateOf("$ 2000") }
    var textMen1 by remember { mutableStateOf("$ ") }
    val cleanText = { text: String -> text.replace("$", "").trim().toIntOrNull() ?: 0 }
    var product = cleanText(textMen) * 12
    var percent = (cleanText(textMen) * 200) / 1000
    var textMen2 by remember { mutableStateOf("$ ") }
    var textMen3 by remember { mutableStateOf("$ ") }
    var percent1 = (cleanText(textMen) * 200) / 1000 * 12
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
                IconButton(onClick = {}
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            Modifier.shadow(
                                10.dp,
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
                                10.dp,
                                spotColor = Color.White
                            )
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 86.dp, horizontal = 50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "acc",
                Modifier.size(size = 65.dp),
            )
            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text("Usuario") },
                modifier = Modifier
                    .shadow(10.dp, spotColor = Color.White)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 170.dp, horizontal = 70.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(140.dp)
                    .align(Alignment.CenterVertically)
                    .border(2.dp, color = Color.White, shape = RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.TopCenter,
            ) {
                Text(
                    text = "INGRESO MENSUAL =",
                    color = Color.White,
                    style = TextStyle.Default,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 5.dp)
                        .shadow(10.dp, spotColor = Color.White)
                        .align(Alignment.TopStart)
                )
                Text(
                    text = textMen,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .shadow(10.dp, spotColor = Color.White)
                )
                Text(
                    text = "INGRESO ANUAL =",
                    color = Color.White,
                    style = TextStyle.Default,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .shadow(10.dp, spotColor = Color.White)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = textMen1 + product,
                    color = Color.White,
                    style = TextStyle.Default,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .shadow(10.dp, spotColor = Color.White)
                        .align(Alignment.BottomCenter)
                )
                changeButton(
                    textMen = textMen,
                    onUpdate = { updateValue ->
                        textMen = updateValue
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 170.dp, horizontal = 45.dp)
        ) {
            val Cards = listOf(
                cards(R.drawable.ic_search, "INGRESOS", NavigateToEntryScreen),
                cards(R.drawable.ic_barchart, "GRAFICAS", NavigateToGraphScreen),
                cards(R.drawable.ic_calendar, "CALENDARIO", NavigateToCalendarScreen)
            )
            Cards.forEach { cards ->
                CardBox(
                    imageRes = cards.imageRes,
                    title = cards.title,
                    onClick = cards.onClick
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 450.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, color = Color.White, shape = RoundedCornerShape(24))
            ) {
                Text(
                    text = "% APROXIMADO AHORRADO AL MES, SIGUENDO LA REGLA 80-20:",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 50.dp, start = 20.dp)
                        .shadow(10.dp, spotColor = Color.White)
                )
                Text(
                    text = textMen2 + percent,
                    color = Color.White,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 120.dp)
                        .shadow(10.dp, spotColor = Color.White)
                )
                Text(
                    text = "% APROXIMADO AHORRADO AL AÑO, SIGUENDO LA REGLA 80-20:",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 160.dp, start = 20.dp)
                        .shadow(10.dp, spotColor = Color.White)
                )
                Text(
                    text = textMen3 + percent1,
                    color = Color.White,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 70.dp)
                        .shadow(10.dp, spotColor = Color.White)
                )
            }
        }
    }
}

@Composable
fun CardBox(imageRes: Int, title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(end = 15.dp, top = 150.dp)
            .size(85.dp)
            .clip(RoundedCornerShape(24))
            .border(2.dp, color = Color.White, shape = RoundedCornerShape(24.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .shadow(10.dp, spotColor = Color.White)
        )
        TextButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .shadow(10.dp, spotColor = Color.White)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                fontSize = 9.sp
            )
        }
    }
}

@Composable
fun changeButton(textMen: String, onUpdate: (String) -> Unit) {
    val currentAmount = textMen.replace("$", "").trim().toIntOrNull() ?: 0
    IconButton(
        onClick = {
            onUpdate("${currentAmount + 100}")
        },
        modifier = Modifier
            .padding(end = 150.dp, top = 25.dp)
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "",
            modifier = Modifier
                .size(30.dp)
                .border(2.dp, shape = RoundedCornerShape(24.dp), color = Color.White)
                .background(color = Color.White)
        )
    }
    IconButton(
        onClick = {
            onUpdate("${currentAmount - 100}")
        },
        modifier = Modifier
            .padding(start = 150.dp, top = 25.dp)
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "",
            modifier = Modifier
                .size(30.dp)
                .border(2.dp, shape = RoundedCornerShape(24.dp), color = Color.White)
                .background(color = Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun myPreScreen() {
    PrincipalScreen(
        NavigateToEntryScreen = {},
        NavigateToGraphScreen = {},
        NavigateToCalendarScreen = {},
    )
}
