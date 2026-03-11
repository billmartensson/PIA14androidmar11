package se.magictechnology.pia14androidmar11

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ShopStart(shopvm : ShoppingViewModel = viewModel()) {

    var isLoggedin = false
    Column() {
        if(isLoggedin) {
            Shoppinglist()
        } else {
            Login()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShopStartPreview() {
    ShopStart()
}