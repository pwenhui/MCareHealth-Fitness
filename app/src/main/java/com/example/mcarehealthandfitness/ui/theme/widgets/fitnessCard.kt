package com.example.mcarehealthandfitness.ui.theme.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun fitCard(Fitnessimage: Int,contentDescription:String,title:String,step:String){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFF238878),
        elevation = 7.dp)
    {
        Column{
            Row(){
                Image( painter = painterResource(Fitnessimage),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(10.dp) , Alignment.CenterStart)

                Column(){
                    Text(
                        title,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 15.sp
                    )

                    Text(step,
                        modifier = Modifier.padding(10.dp,top=0.dp),
                        fontSize = 15.sp
                    )


                }



            }

        }
    }
}
