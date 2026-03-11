package se.magictechnology.pia14androidmar11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.database.database
import se.magictechnology.pia14androidmar11.ui.theme.PIA14androidmar11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        testfirebase()

        enableEdgeToEdge()
        setContent {
            PIA14androidmar11Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Testingfb()
                    }
                }
            }
        }
    }
}

fun testfirebase() {
    val database = Firebase.database
    val myRef = database.getReference("androidtest")

    myRef.setValue("Hello, World!")
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PIA14androidmar11Theme {
        Greeting("Android")
    }
}