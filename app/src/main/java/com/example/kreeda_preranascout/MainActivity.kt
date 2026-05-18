package com.example.kreeda_preranascout

import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    data class Athlete(
        val name: String,
        val age: String,
        val sport: String,
        val sprintTime: String,
        val jumpDistance: String,
        val badge: String
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                // LOGIN STATE
                var isLoggedIn by remember {
                    mutableStateOf(false)
                }

                // LOGIN INPUTS
                var username by remember {
                    mutableStateOf("")
                }

                var password by remember {
                    mutableStateOf("")
                }

                // ATHLETE INPUTS
                var athleteName by remember {
                    mutableStateOf("")
                }

                var athleteAge by remember {
                    mutableStateOf("")
                }

                var athleteSport by remember {
                    mutableStateOf("")
                }

                var jumpDistance by remember {
                    mutableStateOf("")
                }

                // ATHLETE LIST
                var athleteList by remember {
                    mutableStateOf(listOf<Athlete>())
                }

                // TIMER VARIABLES
                var running by remember {
                    mutableStateOf(false)
                }

                var startTime by remember {
                    mutableStateOf(0L)
                }

                var elapsedTime by remember {
                    mutableStateOf(0L)
                }

                // TIMER LOGIC
                LaunchedEffect(running) {

                    while (running) {

                        elapsedTime =
                            SystemClock.elapsedRealtime() - startTime

                        delay(10)
                    }
                }

                val sprintTime =
                    String.format("%.2f", elapsedTime / 1000.0)

                // LOGIN SCREEN
                if (!isLoggedIn) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),

                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Kreeda-Prerana Scout",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        OutlinedTextField(
                            value = username,
                            onValueChange = {
                                username = it
                            },
                            label = {
                                Text("Username")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            label = {
                                Text("Password")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            onClick = {

                                if (
                                    username == "admin" &&
                                    password == "1234"
                                ) {

                                    isLoggedIn = true
                                }
                                else {

                                    Toast.makeText(
                                        this@MainActivity,
                                        "Invalid Login",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },

                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text("Login")
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Demo Login: admin / 1234",
                            fontSize = 14.sp
                        )
                    }
                }

                // DASHBOARD SCREEN
                else {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {

                        Text(
                            text = "Kreeda-Prerana Dashboard",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // DASHBOARD BUTTONS
                        DashboardCard(
                            title = "Athletes"
                        ) {

                            Toast.makeText(
                                this@MainActivity,
                                "Athletes Clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        DashboardCard(
                            title = "Leaderboard"
                        ) {

                            Toast.makeText(
                                this@MainActivity,
                                "Leaderboard Clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        DashboardCard(
                            title = "Analytics"
                        ) {

                            Toast.makeText(
                                this@MainActivity,
                                "Analytics Clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        DashboardCard(
                            title = "Badges"
                        ) {

                            Toast.makeText(
                                this@MainActivity,
                                "Badges Clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // ATHLETE PROFILE
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    text = "Athlete Profile",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                OutlinedTextField(
                                    value = athleteName,
                                    onValueChange = {
                                        athleteName = it
                                    },
                                    label = {
                                        Text("Athlete Name")
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                OutlinedTextField(
                                    value = athleteAge,
                                    onValueChange = {
                                        athleteAge = it
                                    },
                                    label = {
                                        Text("Age")
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                OutlinedTextField(
                                    value = athleteSport,
                                    onValueChange = {
                                        athleteSport = it
                                    },
                                    label = {
                                        Text("Primary Sport")
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                OutlinedTextField(
                                    value = jumpDistance,
                                    onValueChange = {
                                        jumpDistance = it
                                    },
                                    label = {
                                        Text("Long Jump Distance")
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // TIMER
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    text = "Sprint Trial Logger",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$sprintTime sec",
                                    fontSize = 32.sp,
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Row {

                                    Button(
                                        onClick = {

                                            startTime =
                                                SystemClock.elapsedRealtime() - elapsedTime

                                            running = true
                                        }
                                    ) {

                                        Text("Start")
                                    }

                                    Spacer(
                                        modifier = Modifier.width(10.dp)
                                    )

                                    Button(
                                        onClick = {
                                            running = false
                                        }
                                    ) {

                                        Text("Stop")
                                    }

                                    Spacer(
                                        modifier = Modifier.width(10.dp)
                                    )

                                    Button(
                                        onClick = {

                                            running = false
                                            elapsedTime = 0L
                                        }
                                    ) {

                                        Text("Reset")
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // SAVE BUTTON
                        Button(
                            onClick = {

                                if (
                                    athleteName.isNotEmpty() &&
                                    athleteAge.isNotEmpty() &&
                                    athleteSport.isNotEmpty() &&
                                    jumpDistance.isNotEmpty()
                                ) {

                                    val badge =

                                        if (
                                            sprintTime.toDouble() <= 12.00
                                        ) {

                                            "District Level Ready"
                                        }
                                        else {

                                            "Practice More"
                                        }

                                    val athlete = Athlete(
                                        athleteName,
                                        athleteAge,
                                        athleteSport,
                                        sprintTime,
                                        jumpDistance,
                                        badge
                                    )

                                    athleteList =
                                        athleteList + athlete

                                    Toast.makeText(
                                        this@MainActivity,
                                        "Athlete Saved",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    athleteName = ""
                                    athleteAge = ""
                                    athleteSport = ""
                                    jumpDistance = ""

                                    running = false
                                    elapsedTime = 0L
                                }
                                else {

                                    Toast.makeText(
                                        this@MainActivity,
                                        "Please Fill All Fields",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },

                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = "Save Athlete",
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        // LEADERBOARD
                        Text(
                            text = "School Leaderboard",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        athleteList.forEachIndexed { index, athlete ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),

                                shape = RoundedCornerShape(16.dp)
                            ) {

                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {

                                    Text(
                                        text =
                                            "#${index + 1} ${athlete.name}",

                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(
                                        modifier = Modifier.height(8.dp)
                                    )

                                    Text("Age: ${athlete.age}")

                                    Text("Sport: ${athlete.sport}")

                                    Text(
                                        "Sprint: ${athlete.sprintTime} sec"
                                    )

                                    Text(
                                        "Jump: ${athlete.jumpDistance} m"
                                    )

                                    Spacer(
                                        modifier = Modifier.height(10.dp)
                                    )

                                    Box(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFE8F5E9),
                                                RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                    ) {

                                        Text(
                                            text = athlete.badge,
                                            color = Color(0xFF1B5E20),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // ANALYTICS
                        Text(
                            text = "Talent Analytics",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        if (athleteList.isNotEmpty()) {

                            val bestAthlete =
                                athleteList.minByOrNull {
                                    it.sprintTime.toDouble()
                                }

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp)
                            ) {

                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {

                                    Text(
                                        text = "Top Performer",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(
                                        modifier = Modifier.height(10.dp)
                                    )

                                    Text(
                                        text =
                                            "Name: ${bestAthlete?.name}"
                                    )

                                    Text(
                                        text =
                                            "Sport: ${bestAthlete?.sport}"
                                    )

                                    Text(
                                        text =
                                            "Best Sprint: ${bestAthlete?.sprintTime} sec"
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // LOGOUT
                        Button(
                            onClick = {

                                isLoggedIn = false

                                username = ""
                                password = ""
                            },

                            modifier = Modifier.fillMaxWidth(),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {

                            Text("Logout")
                        }

                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }
    }

    @Composable
    fun DashboardCard(
        title: String,
        onClick: () -> Unit
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    onClick()
                },

            shape = RoundedCornerShape(16.dp)
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}