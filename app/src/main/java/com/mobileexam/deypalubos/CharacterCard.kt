package com.mobileexam.deypalubos

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mobileexam.deypalubos.data.character

@Composable
fun CharacterCard(character: character) {
    var expanded by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = expanded, label = "cardExpansion")
    val scale by transition.animateFloat(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) },
        label = "scaleAnimation"
    ) { if (it) 1.05f else 1f }

    val offsetY by transition.animateDp(
        transitionSpec = { spring(stiffness = Spring.StiffnessMedium) },
        label = "offsetAnimation"
    ) { if (it) 0.dp else (-10).dp }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .offset(y = offsetY)
            .graphicsLayer(scaleX = scale, scaleY = scale),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = character.imageRes),
                    contentDescription = stringResource(id = character.nameRes),
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = stringResource(id = character.nameRes),
                        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSecondary)
                    )
                    Text(
                        text = stringResource(id = character.statusRes),
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSecondary)
                    )
                    Text(
                        text = stringResource(id = character.speciesRes),
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSecondary)
                    )
                    Text(
                        text = character.type,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSecondary)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.more_details),
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                modifier = Modifier
                    .clickable { expanded = !expanded }
                    .align(Alignment.CenterHorizontally)
            )
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(animationSpec = tween(300)) + slideInVertically(animationSpec = spring(stiffness = Spring.StiffnessMedium)) { it },
                //exit = fadeOut(animationSpec = tween(10)) + slideOutVertically(animationSpec = spring(stiffness = Spring.StiffnessMedium)) { it }
            ) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = character.gender,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSecondary)
                    )
                    Text(
                        text = character.origin,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSecondary)
                    )
                    Text(
                        text = character.location,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSecondary)
                    )
                }
            }
        }
    }
}