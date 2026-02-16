package com.example.testprojectmovil.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TimeSlotRow(
    hour: String,
    content: @Composable (BoxScope.() -> Unit)? = null // Permite meter una SubjectCard aquí dentro
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = hour,
            modifier = Modifier.width(60.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        VerticalDivider(modifier = Modifier.padding(horizontal = 12.dp).fillMaxHeight(0.6f))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp)
                .background(
                    if (content == null) Color.LightGray.copy(alpha = 0.1f) else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            if (content != null) {
                content()
            } else {
                Text(
                    text = "Libre",
                    modifier = Modifier.padding(start = 12.dp),
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}