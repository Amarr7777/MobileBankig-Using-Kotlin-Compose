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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.bankingapp.ui.theme.BankingAppTheme
import com.example.bankingapp.ui.theme.oranget
import com.example.bankingapp.ui.theme.pt

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankingAppTheme {
                // State variables declared outside setContent
                var name by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var confirmPassword by remember { mutableStateOf("") }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = pt
                ) {
                    Column(
                        modifier = Modifier
                            .background(pt)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Pass state variables to Register composable
                        Register(onNameEntered ={newName -> name = newName} ,
                            onEmailEntered ={newEmail -> email = newEmail} ,
                            onPasswordEntered ={newPassword -> password = newPassword},
                            onCPasswordEntered ={newCPassword -> confirmPassword = newCPassword}
                            )
                        RegisterBtn(name, email, password, confirmPassword)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    onNameEntered: (String) -> Unit,
    onEmailEntered: (String) -> Unit,
    onPasswordEntered: (String) -> Unit,
    onCPasswordEntered: (String) -> Unit,
) {
    Column {
        Text(text = "Name", color = Color.White)
        // Name TextField
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
            var name by remember { mutableStateOf("") }

        TextField(
            value = name,
            onValueChange = {
                name = it
                onNameEntered(it)
            },
            modifier = Modifier.padding(5.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = pt,
                unfocusedTextColor = Color.White,
                focusedTextColor = oranget,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        }
        Text(text = "Email", color = Color.White)
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
            // Email TextField
            var email by remember { mutableStateOf("") }

            TextField(
                value = email,
                onValueChange = {
                    email = it
                    onEmailEntered(it)
                },
                modifier = Modifier.padding(5.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = pt,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = oranget,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
        Text(text = "Password", color = Color.White)
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
            var password by remember { mutableStateOf("") }
            // Password TextField

            TextField(
                value = password,
                onValueChange = {
                    password = it
                    onPasswordEntered(it)
                },
                modifier = Modifier.padding(5.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = pt,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = oranget,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
        Text(text = "Confirm Password", color = Color.White)
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
            var confirmPassword by remember { mutableStateOf("") }
            // Confirm Password TextField

            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    onCPasswordEntered(it)
                },
                modifier = Modifier.padding(5.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = pt,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = oranget,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun RegisterBtn(
    name: String,
    email: String,
    password: String,
    confirmPassword: String
) {
    val context = LocalContext.current
    val dbHelper = MyDbHelper(context)
    val intent = remember {
        Intent(context, MainActivity::class.java)
    }
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Already a member?",
            color = Color.White
        )
        Box(modifier = Modifier
            .clickable {context.startActivity(intent) }) {
            Text(
                text = " Login",
                color = oranget
            )
        }
    }
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(oranget, shape = CircleShape)
            .clickable {
                val trimmedName = name.trim()
                val trimmedEmail = email.trim()
                val trimmedPassword = password.trim()
                val trimmedConfirmPassword = confirmPassword.trim()

                if (trimmedName.isNotEmpty() && trimmedEmail.isNotEmpty() && trimmedPassword.isNotEmpty() && trimmedPassword == trimmedConfirmPassword) {
                    // Insert data into the database
                    dbHelper.insertUser(trimmedName, trimmedEmail, trimmedPassword)
                    // Start MainActivity
                    context.startActivity(intent)
                } else {
                    Toast
                        .makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    ) {
        Text(
            text = "Register",
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }
    }
}
