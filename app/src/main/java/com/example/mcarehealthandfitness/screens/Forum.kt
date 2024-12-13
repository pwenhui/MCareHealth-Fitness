package com.example.mcarehealthandfitness.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcarehealthandfitness.models.Posts
import com.example.mcarehealthandfitness.models.ScrollablePostList
import com.example.mcarehealthandfitness.models.post
import com.example.mcarehealthandfitness.ui.theme.widgets.HyperLinkText
import com.example.mcarehealthandfitness.ui.theme.widgets.dialogbox2
import com.example.mcarehealthandfitness.viewmodel.PostCreationViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun communityPage(){
    ForumPage(Postlist = post().postexample())
}

@Composable
fun ForumPage(Postlist: List<ScrollablePostList>,vm:PostCreationViewModel= viewModel()){

    var createpost by rememberSaveable { mutableStateOf(false) }
    val postList by vm.postlist.collectAsState()
    val isRefreshing by vm.isRefreshing.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing) ,
        onRefresh = { vm.refresh()})
    {
        Column(){
            Box {

                Image(painter = painterResource(id = com.example.mcarehealthandfitness.R.drawable.community),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(206.dp),
                )
                Text(text = "M. Care Health and Fitness Forum",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .align(Alignment.Center)
                        .background(color = Color.White),
                    style = MaterialTheme.typography.body1,
                )
            }

            LazyColumn(){
                items(Postlist){ post->
                    postList(post)
                }

            }


            LazyColumn(){
                items(postList){ userpost->
                    UserExperiencePost(userpost)
                }

            }



            Row() {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)) {
                    // Add New Bulletin
                    FloatingActionButton(backgroundColor = Color.White,
                        onClick = {
                            createpost = true

                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 10.dp, bottom = 10.dp),
                        content = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                modifier = Modifier.background(color = Color.Black),
                                contentDescription = null
                            )
                        })
                }

            }

        }
    }

    if (createpost){
        dialogbox2() {
            createpost = false
        }
    }

}



@Composable
fun postList(Post:ScrollablePostList){


    Box(
        Modifier
            .border(width = 1.dp, color = Color.Black)
            .fillMaxWidth()){

        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()

        ) {
            Column(modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = stringResource(Post.stringauthor),
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                HyperLinkText(
                    fullText = stringResource(Post.stringcontent),
                    linkText = listOf( stringResource(Post.stringtile)),
                    hyperlinks = listOf(stringResource(Post.stringlink)),
                    fontSize = 15.sp
                )

            }
        }

    }
}

@Composable
fun UserExperiencePost(userpost: Posts)
{
        Box(
            Modifier
                .border(width = 1.dp, color = Color.Black)
                .fillMaxWidth()){

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()

            ) {
                Column(modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = userpost.author,
                        style = MaterialTheme.typography.caption,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = userpost.title,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = userpost.description,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.Black
                    )

                }
            }

        }


}