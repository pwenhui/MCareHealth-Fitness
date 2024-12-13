package com.example.mcarehealthandfitness.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mcarehealthandfitness.models.Datasource
import com.example.mcarehealthandfitness.models.ScrollableFitnessList
import com.example.mcarehealthandfitness.ui.theme.widgets.dialogbox1

@Composable
fun ListofProgram() {
    ProgramList(FitnessList = Datasource().loadAffirmations())
}

@Composable
fun ProgramList(FitnessList: List<ScrollableFitnessList>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(FitnessList) { FitnessList ->
            ProgramCard(FitnessList)
        }
    }
}

@Composable
fun ProgramCard(listofFitness: ScrollableFitnessList, modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    Card(modifier = Modifier
        .padding(20.dp)
        .scrollable(
            state = scrollState,
            orientation = Orientation.Vertical
        ),
        elevation = 4.dp,
        backgroundColor = Color(0xFF238878),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(3.dp, Color(0xFF238878))
    ) {
        Column() {
            var showdialog by remember{ mutableStateOf(false) }
            Image(
                painter = painterResource(listofFitness.imageResourceId),
                contentDescription = stringResource(listofFitness.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clickable {
                        showdialog = true

                    }
            )
            if (showdialog) {
                dialogbox1 {
                    showdialog = false
                }
            }

            Row{
                Text(
                    text = LocalContext.current.getString(listofFitness.stringResourceId),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )


            }

        }
    }
}