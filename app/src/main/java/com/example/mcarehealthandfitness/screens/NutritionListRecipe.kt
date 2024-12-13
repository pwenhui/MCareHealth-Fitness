package com.example.mcarehealthandfitness.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mcarehealthandfitness.models.Datasource
import com.example.mcarehealthandfitness.models.ScrollableLowCarbList
import com.example.mcarehealthandfitness.models.ScrollableRecipeList

@Composable
fun ListofRecipe() {
    RecipeList(Recipelist = Datasource().GlutenfreeRecipe(), lowcarb = Datasource().LowCarbRecipe())
}

@Composable
fun RecipeList(Recipelist: List<ScrollableRecipeList>, lowcarb: List<ScrollableLowCarbList>) {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        //gluten free
        Row() {
            Text(
                text = "Gluten-Free",
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .padding(10.dp),
                color = Color.Black
            )
        }


        Row()
        {
            LazyRow()
            {
                items(Recipelist) { Recipelist ->
                    GlutenFreeRecipe(Recipelist)
                }
            }
        }

        Row() {
            Text(
                text = "Low Carb",
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .padding(10.dp),
                color = Color.Black
            )
        }

        Row()
        {
            LazyRow()
            {
                items(lowcarb) { lowcarb ->
                    LowCarbRecipe(lowcarb)
                }
            }

        }
    }
}


@Composable
fun GlutenFreeRecipe(listofRecipe: ScrollableRecipeList)
{
    val scrollState = rememberScrollState()


    Card(modifier = Modifier
        .padding(top = 50.dp, start = 15.dp, end = 15.dp, bottom = 50.dp)
        .scrollable(
            state = scrollState,
            orientation = Orientation.Vertical
        ),
        elevation = 4.dp,
        backgroundColor = Color(0xFF238878),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, Color(0xFF238878))
    ) {
        Column() {
            //var showdialog by remember{ mutableStateOf(false) }
            Image(
                painter = painterResource(listofRecipe.imageResourceId),
                contentDescription = stringResource(listofRecipe.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)

            )

            Row{
                Text(
                    text = LocalContext.current.getString(listofRecipe.stringResourceId),
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Row{
                Text(
                    text = LocalContext.current.getString(listofRecipe.stringDescription),
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun LowCarbRecipe(listofRecipe: ScrollableLowCarbList)
{
    val scrollState = rememberScrollState()

    Card(modifier = Modifier
        .padding(top = 50.dp, start = 15.dp, end = 15.dp, bottom = 50.dp)
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
            //var showdialog by remember{ mutableStateOf(false) }
            Image(
                painter = painterResource(listofRecipe.imageResourceId),
                contentDescription = stringResource(listofRecipe.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)

            )

            Row{
                Text(
                    text = LocalContext.current.getString(listofRecipe.stringResourceId),
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Row{
                Text(
                    text = LocalContext.current.getString(listofRecipe.stringDescription),
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}