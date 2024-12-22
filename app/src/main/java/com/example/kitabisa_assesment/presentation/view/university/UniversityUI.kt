package com.example.kitabisa_assesment.presentation.view.university

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kitabisa_assesment.presentation.view.university.model.University

@Composable
fun UniversityItem(
    data: University,
    onCardClick: ((data: University) -> Unit)? = null
) = with(data) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFf2c84b)
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onCardClick?.invoke(data)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = location,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = url,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
fun UniversityList(list: List<University>, onCardClick: ((data: University) -> Unit)? = null) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
            .background(Color.White),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list) { data ->
            UniversityItem(data) {
                onCardClick?.invoke(data)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUniversityList() {
    UniversityList(
        University.getPreviewListData()
    )
}