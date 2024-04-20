package com.example.bankingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bankingapp.ui.theme.BankingAppTheme
import com.example.bankingapp.ui.theme.oranget
import com.example.bankingapp.ui.theme.pt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = pt
                ) {
                    Column(modifier = Modifier
                        .background(pt)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly) {
                    Login()
                        LoginBtn()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(){
    Column() {
        Text(text = "Email", color = Color.White)
        var email by remember { mutableStateOf("") }
        Box(
            modifier = Modifier
                .padding(5.dp, 10.dp)
                .background(pt)
                .border(
                    width = 1.dp,
                    color = oranget,
                    shape = CircleShape
                ) // Set the border color and width
        ) {

            TextField(
                value = email, onValueChange = {
                    email = it
//                    onNameEntered(it) // Invoke the lambda with the updated name
                }, modifier = Modifier.padding(0.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = pt,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = oranget,
                    focusedIndicatorColor = Color.Transparent, // Remove the focused indicator
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
        Text(text = "password", color = Color.White)
        var password by remember { mutableStateOf("") }
        Box(
            modifier = Modifier
                .padding(5.dp, 10.dp)
                .background(pt)
                .border(
                    width = 1.dp,
                    color = oranget,
                    shape = CircleShape
                ) // Set the border color and width
        ) {

            TextField(
                value = password, onValueChange = {
                    password = it
//                    onEmailEntered(it)
                }, modifier = Modifier.padding(0.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = pt,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = oranget,
                    focusedIndicatorColor = Color.Transparent, // Remove the focused indicator
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun LoginBtn(){
    val context = LocalContext.current
    val intent = remember {
        Intent(context,MainActivity2::class.java)
    }
    val intent2 = remember {
        Intent(context,MainActivity3::class.java)
    }

    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Not a member yet??",
                color = Color.White
            )
            Box(modifier = Modifier
                .clickable {context.startActivity(intent) }) {
                Text(
                    text = " Register",
                    color = oranget
                )
            }
        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(oranget)
                .clickable {
//                    if (isValidEmail && isBlankPassword){
                        context.startActivity(intent2)
//                    }else{
//                        val errorText = "Invalid email or password"
//                        Toast.makeText(context,errorText,Toast.LENGTH_LONG).show()
//                    }
                }
        ) {
            Text(
                text = "Login",
                Modifier.padding(20.dp, 10.dp)
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BankingAppTheme {

    }
}