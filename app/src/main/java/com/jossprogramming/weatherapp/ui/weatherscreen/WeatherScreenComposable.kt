package com.jossprogramming.weatherapp.ui.weatherscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherOptionSelector(
    /*onZipSelected: (String) -> Unit,
    onCoordinatesSelected: () -> Unit*/
) {
    var zipCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Weather", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        // Botón para consultar por coordenadas (más adelante puedes solicitar permisos aquí)
        Button(
            onClick = {
                //onCoordinatesSelected()
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("With Location")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Entrada para zip code
        OutlinedTextField(
            value = zipCode,
            onValueChange = { zipCode = it },
            label = { Text("With Zip Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (zipCode.isNotBlank()){
                    //onZipSelected(zipCode)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = zipCode.isNotBlank()
        ) {
            Text("Consultar por Zip Code")
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CurrentWeatherScreen(modifier: Modifier = Modifier) {
    val temperature:Int = 14
    val description = "Sunny"
    val iconUrl = "https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0001_sunny.png"

    Card(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                model = iconUrl,
                contentDescription = description,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "$temperature°C", style = MaterialTheme.typography.headlineMedium)
            Text(text = description, fontSize = 18.sp)
        }
    }
}