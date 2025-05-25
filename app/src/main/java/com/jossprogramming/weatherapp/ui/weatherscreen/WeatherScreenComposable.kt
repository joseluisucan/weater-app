package com.jossprogramming.weatherapp.ui.weatherscreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.jossprogramming.weatherapp.network.models.Current

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: WeatherViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = modifier.fillMaxSize()) {
        WeatherOptionSelector(
            onZipSelected = { zip -> viewModel.loadWeatherByZip(zip) },
            onCoordinatesSelected = {
                viewModel.loadWeatherFromLocation()
            }
        )
        when(uiState){
            is WeatherUiState.Error -> {
                val error = uiState as WeatherUiState.Error
                ErrorStateComposable(errorMessage = error.message)
            }
            WeatherUiState.Idle -> Unit
            WeatherUiState.Loading -> {
                LoadingStateComposable()
            }
            is WeatherUiState.Success -> {
                val success = uiState as WeatherUiState.Success
                CurrentWeatherScreen(
                    modifier,
                    temperature = success.data.current.temperature,
                    description = success.data.current.weather_descriptions.firstOrNull()?:"",
                    iconUrl = success.data.current.weather_icons.firstOrNull()?:"",
                    region = success.data.location.region,
                    country = success.data.location.country,
                    timeZone = success.data.location.timezone_id
                )
            }
        }
    }
}


@Composable
fun WeatherOptionSelector(
    onZipSelected: (String) -> Unit,
    onCoordinatesSelected: () -> Unit
) {
    var zipCode by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Get Weather", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onCoordinatesSelected() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get by Location")
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = zipCode,
            onValueChange = { zipCode = it },
            label = { Text("With Zip Code") },
            placeholder = { Text("99501") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (zipCode.isNotBlank()){
                    onZipSelected(zipCode)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = zipCode.isNotBlank()
        ) {
            Text("Get by Zip Code")
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    temperature: Int,
    description: String,
    iconUrl: String,
    region:String,
    country:String,
    timeZone:String
) {
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
            Text(text = "$temperature°C", style = MaterialTheme.typography.titleLarge)
            Text(text = description, fontSize = 18.sp)
            Text(text = "$region. $country", style = MaterialTheme.typography.bodyMedium)
            Text(text = timeZone, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ErrorStateComposable(modifier: Modifier = Modifier, errorMessage: String) {
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
            Text(text = errorMessage, style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun LoadingStateComposable(modifier: Modifier = Modifier) {
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
            CircularProgressIndicator()
        }
    }
}