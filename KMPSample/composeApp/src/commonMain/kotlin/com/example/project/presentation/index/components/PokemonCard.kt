package com.example.project.presentation.index.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.entities.PokemonEntity
import com.example.project.util.Constants
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource
import kmpproject.composeapp.generated.resources.Res
import kmpproject.composeapp.generated.resources._10
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun PokemonCard(pokemonEntity: PokemonEntity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        when(val resource = asyncPainterResource(Constants.getOfficialArtworkUrl(pokemonEntity.id))) {
            is Resource.Failure ->  {
                Image(
                    painter = painterResource(Res.drawable._10),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            is Resource.Loading -> {
                CircularProgressIndicator(
                    progress = { resource.progress },
                )
            }
            is Resource.Success -> {
                Image(
                    painter = resource.value,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .shadow(1.dp, shape = CircleShape)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
        }
        Text(
            text = pokemonEntity.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
        )
    }
}