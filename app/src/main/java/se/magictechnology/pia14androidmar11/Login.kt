package se.magictechnology.pia14androidmar11

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Login(shopvm : ShoppingViewModel = viewModel()) {

    var loginemail by remember { mutableStateOf("") }
    var loginpassword by remember { mutableStateOf("") }

    var errormessage by remember { mutableStateOf<String?>(null) }

    Column() {
        Text("Login")

        if(errormessage != null) {
            Text(errormessage!!)
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginemail,
            onValueChange = { loginemail = it }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginpassword,
            onValueChange = { loginpassword = it }
        )

        Button(onClick = {
            shopvm.login(loginemail, loginpassword) {
                errormessage = it
            }
        }) {
            Text("Login")
        }

        Button(onClick = {
            shopvm.register(loginemail, loginpassword) {
                errormessage = it
            }
        }) {
            Text("Register")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login()
}