package se.magictechnology.pia14androidmar11

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.database.database

@Composable
fun Testingfb() {

    var addname by remember { mutableStateOf("") }

    var testloadtext by remember { mutableStateOf("START") }

    fun savefb() {
        val database = Firebase.database
        val myRef = database.getReference("androidtest")

        myRef.setValue("Nytt test")
    }

    fun loadfb() {
        val database = Firebase.database
        val myRef = database.getReference("androidtest")

        myRef.get().addOnSuccessListener {

            testloadtext = it.value.toString()
            
            Log.i("PIA14DEBUG", "Value is ${it.value}")

        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("TEST")

        Text(testloadtext)

        Row {
            TextField(
                modifier = Modifier.weight(1f),
                value = addname,
                onValueChange = { addname = it }
            )

            Button(onClick = {

            }) {
                Text("Add")
            }
        }

        Button(onClick = {
            savefb()
        }) {
            Text("Spara")
        }

        Button(onClick = {
            loadfb()
        }) {
            Text("Ladda")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestingfbPreview() {
    Testingfb()
}