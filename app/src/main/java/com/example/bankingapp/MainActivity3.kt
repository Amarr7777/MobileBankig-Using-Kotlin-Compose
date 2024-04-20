package com.example.bankingapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.bankingapp.ui.theme.BankingAppTheme
import com.example.bankingapp.ui.theme.oranget
import com.example.bankingapp.ui.theme.pt
import com.example.bankingapp.ui.theme.st
import kotlinx.coroutines.launch

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = pt
                ) {
                    Drawer()
                }
            }
        }
    }
}
data class Item(val id: Int, val name: String)

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Define a state to manage which fragment is being displayed
    var currentScreen by remember { mutableStateOf("Home") }

    val itemsList = mutableStateListOf<Item>()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(modifier = Modifier.background(Color.DarkGray)) {
                ModalDrawerSheet {
                    Column {
                        Text("Your Banking App", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
                    }
                    Divider()
                    val items = listOf("Home","Transactions", "Invest")
                    items.forEach { item ->
                        Row(modifier = Modifier.padding(5.dp, 0.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "icon")
                            NavigationDrawerItem(
                                label = { Text(text = item) },
                                selected = false,
                                onClick = {
                                    // Update current screen based on item clicked
                                    currentScreen = item
                                    scope.launch { drawerState.close() } // Close the drawer when an item is clicked
                                }
                            )
                        }
                    }
                }
            }
        }
    ) {
        Column {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(pt)) {
                IconButton(onClick = {
                    if (drawerState.isClosed) {
                        scope.launch { drawerState.open() }
                    } else {
                        scope.launch { drawerState.close() }
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu", tint = Color.White)
                }
            }
            // Render different screens based on the current state
            when (currentScreen) {
                "Home" -> {
                    BalanceCard()
                    Payment(itemsList = itemsList)
                    Transactions(itemsList)
                }
                "Transactions" -> TransactionsFrag(itemsList)
                "Invest" -> InvestFrag()
            }
        }
    }
}




@Composable
fun BalanceCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = st,
        ),
        modifier = Modifier
            .padding(10.dp)
            .size(width = 440.dp, height = 200.dp)
    ) {
        Column {
        Text(
            text = "Your Balance",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = oranget
        )
            Text(
            text = "₹767681672.43",
            modifier = Modifier
                .padding(16.dp,5.dp),
            textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 30.sp
        )
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(
                    text = "Income",
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = oranget
                )
                Text(
                    text = "₹767131.43",
                    modifier = Modifier
                        .padding(16.dp,5.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            Column {
                Text(
                    text = "Expense",
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = oranget
                )
                Text(
                    text = "₹767131.43",
                    modifier = Modifier
                        .padding(16.dp,5.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Payment(itemsList: MutableList<Item>){
    var showSendDialog by remember { mutableStateOf(false) }
    var showRequestDialog by remember { mutableStateOf(false) }

    if (showSendDialog) {
        Dialog(onDismissRequest = { showSendDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = st,
                ),
            ) {
                Text(text = "Enter the Amount ", color = Color.White, modifier = Modifier.padding(10.dp,0.dp))
                var sendAmount by remember { mutableStateOf("") }
                Box(
                    modifier = Modifier
                        .padding(5.dp, 10.dp)
                        .background(st)
                        .border(
                            width = 1.dp,
                            color = oranget,
                            shape = CircleShape
                        ) // Set the border color and width
                ) {

                    TextField(
                        value = sendAmount, onValueChange = {
                            sendAmount = it
                        }, modifier = Modifier.padding(0.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = st,
                            unfocusedTextColor = Color.White,
                            focusedTextColor = oranget,
                            focusedIndicatorColor = Color.Transparent, // Remove the focused indicator
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                }
                TextButton(
                    onClick = {
                        if (sendAmount.isNotEmpty()) {
                            val newId = (itemsList.maxByOrNull { it.id }?.id ?: 0) + 1
                            itemsList.add(Item(newId, "₹ $sendAmount"))
                            showSendDialog = false // Dismiss dialog
                        }
                    }
                ) {
                    Text("Confirm", color = oranget)
                }

            }
        }
    }

    if (showRequestDialog) {
        Dialog(onDismissRequest = { showRequestDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = st,
                ),
            ) {
                Text(text = "Enter the Amount ", color = Color.White,modifier = Modifier.padding(10.dp,0.dp))
                var requestAmount by remember { mutableStateOf("") }
                Box(
                    modifier = Modifier
                        .padding(5.dp, 10.dp)
                        .background(st)
                        .border(
                            width = 1.dp,
                            color = oranget,
                            shape = CircleShape
                        ) // Set the border color and width
                ) {

                    TextField(
                        value = requestAmount, onValueChange = {
                            requestAmount = it
                        }, modifier = Modifier.padding(0.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = st,
                            unfocusedTextColor = Color.White,
                            focusedTextColor = oranget,
                            focusedIndicatorColor = Color.Transparent, // Remove the focused indicator
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
                TextButton(
                    onClick = {
                        if (requestAmount.isNotEmpty()) {
                            val newId = (itemsList.maxByOrNull { it.id }?.id ?: 0) + 1
                            itemsList.add(Item(newId, "₹ $requestAmount"))
                            showRequestDialog = false // Dismiss dialog
                        }
                    }
                ) {
                    Text("Confirm", color = oranget)
                }

            }
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = { showSendDialog = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, // Makes the button background transparent
                contentColor = Color.Transparent // Ensures that the content (icons, text) does not change color
            ) ) {
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = st,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .size(width = 120.dp, height = 100.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send", tint = Color.Green)
                Text(
                    text = "Send",
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                )
                }
            }
        }
        Button(onClick = { showRequestDialog = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, // Makes the button background transparent
                contentColor = Color.Transparent // Ensures that the content (icons, text) does not change color
            ) // Removes padding inside the button
             ) {
        ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = st,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .size(width = 120.dp, height = 100.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Request", tint = Color.Green)
                Text(
                    text = "Request",
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                )
                }
            }
        }


    }
}


@Composable
fun Transactions(items: MutableList<Item>) {
    Text(text = "Transactions", color = Color.White, fontSize = 25.sp,
        modifier = Modifier.padding(10.dp))
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(0.dp, 10.dp)
    ) {
        items(items) { item ->
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = st,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .size(width = 440.dp, height = 50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "message", tint = Color.White)
                    Text(text = item.name, modifier = Modifier.padding(16.dp), color = Color.White)
                }
            }
        }
    }
}


@Composable
fun TransactionsFrag(items: MutableList<Item>) {
    Text(text = "Transactions", color = Color.White, fontSize = 25.sp,
        modifier = Modifier.padding(10.dp))
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(0.dp, 10.dp)
    ) {
        items(items) { item ->
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = st,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .size(width = 440.dp, height = 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "message", tint = Color.White)
            Text(text = item.name, modifier = Modifier.padding(16.dp), color = Color.White)
        }
    }
        }
    }
}

data class StockInfo(
    val name: String,
    val shares: String,
    val percentageChange: String,
    val portfolioValue: String,
    val profits: String
)

val stocks = listOf(
    StockInfo("Apple", "0.67 Shares", "+4.5%", "₹ 5000.56", "₹ 3298.96"),
    StockInfo("Google", "0.34 Shares", "+6.1%", "₹ 8000.75", "₹ 4902.31"),
    StockInfo("Amazon", "0.45 Shares", "+3.2%", "₹ 7000.88", "₹ 3100.59"),
    StockInfo("Microsoft", "0.25 Shares", "+2.7%", "₹ 6500.99", "₹ 2001.65"),
    StockInfo("Tesla", "0.53 Shares", "+8.9%", "₹ 9000.10", "₹ 6800.43")
)

@Composable
fun StockCard(stock: StockInfo) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = st, // Substitute your 'st' variable here
        ),
        modifier = Modifier
            .padding(vertical = 5.dp)
            .size(width = 440.dp, height = 200.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = stock.name, color = Color.White)
                    Text(text = stock.shares, color = Color.White)
                }
                Column {
                    Text(text = stock.percentageChange, color = oranget) // Substitute your 'oranget' variable here
                    Text(text = "per year", color = Color.White)
                }
            }
            Divider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Portfolio Value", color = Color.White)
                    Text(text = stock.portfolioValue, color = oranget) // Substitute your 'oranget' variable here
                }
                Column {
                    Text(text = "Profits", color = Color(0xFFF28500)) // Substitute your 'oranget' variable here
                    Text(text = stock.profits, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun InvestFrag() {
    Text(text = "Investments", color = Color.White, fontSize = 25.sp,
        modifier = Modifier.padding(10.dp))
    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(stocks) { stock ->
            StockCard(stock)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    BankingAppTheme {
        InvestFrag()
    }
}