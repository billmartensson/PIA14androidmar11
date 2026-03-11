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


data class ShoppingItem(var fbid : String? = null, val name: String? = null, val amount: Int? = null)


class ShoppingViewModel : ViewModel() {

    private var database: DatabaseReference = Firebase.database.reference
    private var auth: FirebaseAuth = Firebase.auth

    private var _shopdata = MutableStateFlow(listOf<ShoppingItem>())
    val shopdata: StateFlow<List<ShoppingItem>> = _shopdata.asStateFlow()

    private var _isloggedin = MutableStateFlow(false)
    val isloggedin: StateFlow<Boolean> = _isloggedin.asStateFlow()

    init {
        auth.addAuthStateListener {
            if(auth.currentUser == null) {
                _isloggedin.value = false
            } else {
                _isloggedin.value = true
            }
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun login(email : String, password : String, callback : (String?) -> Unit = {}) {
        if(email == "" || password == "") {
            callback("FYLL I ALLA FÄLT!!")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {}
            .addOnFailureListener {
                callback("LOGIN FAIL!!!!!!")
            }
    }

    fun register(email : String, password : String, callback: (String?) -> Unit) {
        if(email == "" || password == "") {
            callback("FYLL I ALLA FÄLT!!")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {}
            .addOnFailureListener {
                callback("REGISTER FAIL!!!!!!")
            }

    }


    fun addShop(addname : String, addamount : String) {

        if (auth.currentUser == null) {
            return
        }

        var addnumber = addamount.toIntOrNull()
        if(addnumber == null) {
            return
        }

        val newshopitem = ShoppingItem(name = addname, amount = addnumber)

        val myRef = database.child("androidshopping").child(auth.currentUser!!.uid)

        myRef.push().setValue(newshopitem)

        loadshop()
    }

    fun loadshop() {

        if (auth.currentUser == null) {
            return
        }

        val myRef = database.child("androidshopping").child(auth.currentUser!!.uid)

        var templist = mutableListOf<ShoppingItem>()

        myRef.get().addOnSuccessListener {
            for(shopchild in it.children) {
                var shopitem = shopchild.getValue<ShoppingItem>()

                if(shopitem != null) {
                    shopitem.fbid = shopchild.key
                    templist.add(shopitem)
                }
            }
            _shopdata.value = templist
        }

    }

    fun deleteshopitem(deleteitem : ShoppingItem) {
        if (auth.currentUser == null) {
            return
        }


        deleteitem.fbid?.let { fbid ->
            val myRef = database.child("androidshopping")
                .child(auth.currentUser!!.uid)
                .child(fbid)


            myRef.removeValue()
            loadshop()
        }


    }
}