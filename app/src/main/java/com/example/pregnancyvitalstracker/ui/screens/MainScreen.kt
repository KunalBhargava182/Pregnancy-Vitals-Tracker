package com.example.pregnancyvitalstracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pregnancyvitalstracker.ui.components.AddVitalsDialog
import com.example.pregnancyvitalstracker.ui.components.VitalsCard
import com.example.pregnancyvitalstracker.viewmodel.VitalsViewModel
import com.example.pregnancyvitalstracker.ui.theme.Purple80
import com.example.pregnancyvitalstracker.ui.theme.PurpleDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: VitalsViewModel, modifier: Modifier = Modifier) {
    val vitals by viewModel.vitalsList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            Surface(
                color = Purple80,
                tonalElevation = 60.dp,
                shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp)
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Track My Pregnancy",
                            color = PurpleDark,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = PurpleDark,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Vitals")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(vitals) { vital ->
                    VitalsCard(vitals = vital)
                }
            }
        }

        if (showDialog) {
            AddVitalsDialog(
                onDismiss = { showDialog = false },
                onSubmit = { sys, dia, hr, wt, kicks ->
                    viewModel.addVitals(
                        com.example.pregnancyvitalstracker.data.model.Vitals(
                            systolic = sys,
                            diastolic = dia,
                            heartRate = hr,
                            weight = wt,
                            babyKicks = kicks
                        )
                    )
                    showDialog = false
                }
            )
        }
    }
}
