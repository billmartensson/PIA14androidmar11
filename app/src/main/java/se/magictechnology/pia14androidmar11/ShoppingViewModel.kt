package se.magictechnology.pia14androidmar11

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class ShoppingItem(val name: String? = null, val amount: Int? = null)


class ShoppingViewModel : ViewModel() {

    private var database: DatabaseReference = Firebase.database.reference
    private var auth: FirebaseAuth = Firebase.auth

    private var _shopdata = MutableStateFlow(listOf<ShoppingItem>())
    val shopdata: StateFlow<List<ShoppingItem>> = _shopdata.asStateFlow()


    fun checklogin() {

    }

    fun addShop(addname : String, addamount : String) {

        var addnumber = addamount.toIntOrNull()
        if(addnumber == null) {
            return
        }

        val newshopitem = ShoppingItem(addname, addnumber)

        val myRef = database.child("androidshopping")

        myRef.push().setValue(newshopitem)

        loadshop()
    }

    fun loadshop() {
        val myRef = database.child("androidshopping")

        var templist = mutableListOf<ShoppingItem>()

        myRef.get().addOnSuccessListener {
            for(shopchild in it.children) {
                var shopitem = shopchild.getValue<ShoppingItem>()

                if(shopitem != null) {
                    templist.add(shopitem)
                }
            }
            _shopdata.value = templist
        }

    }
}