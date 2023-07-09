package com.github.csandiego.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items: List<MenuItem>) {
    val karlaFont = FontFamily(Font(R.font.karla_regular))
    LazyColumn {
        itemsIndexed(items) { index, menuItem ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = menuItem.title,
                    fontSize = 18.sp,
                    fontWeight = Bold,
                    fontFamily = karlaFont,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(Modifier.fillMaxWidth()) {
                    Column(Modifier.weight(1.0f)) {
                        Text(
                            text = menuItem.description,
                            modifier = Modifier.padding(top = 12.dp),
                            fontFamily = karlaFont
                        )
                        Text(
                            text = "$${String.format("%.2f", menuItem.price)}",
                            fontSize = 18.sp,
                            fontFamily = karlaFont,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                    GlideImage(
                        model = menuItem.image,
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
            if (index < items.size) {
                Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemsPreview() {
    MenuItems(
        items = listOf(
            MenuItem(
                1,
                "Greek Salad",
                "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
                10.0,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
                "starters"
            ),
            MenuItem(
                2,
                "Lemon Desert",
                "Traditional homemade Italian Lemon Ricotta Cake.",
                10.0,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true",
                "desserts"
            )
        )
    )
}