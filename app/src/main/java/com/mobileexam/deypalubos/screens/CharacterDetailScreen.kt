package com.mobileexam.deypalubos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mobileexam.deypalubos.R
import com.mobileexam.deypalubos.viewmodel.CharacterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(navController: NavController, viewModel: CharacterViewModel, characterId: Int) {
    val character = viewModel.characters.collectAsState().value.find { it.id == characterId }

    character?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Back to List", color = Color.Black) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFF59D))
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(0xFFBEFD73),
                    tonalElevation = 0.dp,
                    modifier = Modifier.height(50.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rick_and_morty_logo),
                            contentDescription = "Rick and Morty Logo",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberImagePainter(it.imageUrl),
                        contentDescription = it.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFA6CCCC)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        InfoText("Status", it.status)
                        InfoText("Species", it.species)
                        InfoText("Gender", it.gender)
                        InfoText("Location", it.location.name)
                        InfoText("Origin", it.origin.name)
                        InfoText("Type", if (it.type.isEmpty()) "   -   " else it.type)
                    }
                }
            }
        }
    }
}

@Composable
fun InfoText(label: String, value: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) { append("$label: ") }
            withStyle(style = SpanStyle(color = Color.Black)) { append(value) }
        },
        style = MaterialTheme.typography.bodyMedium
    )
}
