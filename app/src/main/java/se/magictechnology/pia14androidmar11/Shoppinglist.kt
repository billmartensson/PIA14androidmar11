package se.magictechnology.pia14androidmar11

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Shoppinglist(shopvm : ShoppingViewModel = viewModel()) {

    var addname by remember { mutableStateOf("") }
    var addamount by remember { mutableStateOf("1") }

    var shopdata = shopvm.shopdata.collectAsState()

    LaunchedEffect(true) {
        shopvm.loadshop()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            TextField(
                modifier = Modifier.weight(1f),
                value = addname,
                onValueChange = { addname = it }
            )

            TextField(
                modifier = Modifier.width(80.dp).padding(horizontal = 5.dp),
                value = addamount,
                onValueChange = { addamount = it }
            )

            Button(onClick = {
                shopvm.addShop(addname, addamount)
            }) {
                Text("Add")
            }
        }

        LazyColumn() {
            items(shopdata.value.size) { index ->
                Row(modifier = Modifier.height(80.dp)) {
                    Text(shopdata.value[index].name.toString())
                    Text(shopdata.value[index].amount.toString())

                    Button(onClick = {
                        shopvm.deleteshopitem(shopdata.value[index])
                    }) {
                        Text("Radera")
                    }
                }
            }
        }

        Button(onClick = {
            shopvm.logout()
        }) {
            Text("Logga ut")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppinglistPreview() {
    Shoppinglist()
}