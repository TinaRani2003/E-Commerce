package com.example.e_commerce.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

data class User(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val profilePictureUrl: String = ""
)

class UserSessionViewModel : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    init {
        fetchUserDetails()
    }

    fun fetchUserDetails() {
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()
        val userId = auth.currentUser?.uid

        userId?.let {
            firestore.collection("users").document(it).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val user = document.toObject(User::class.java)
                        _user.value = user
                    }
                }
                .addOnFailureListener {
                    // Handle failure
                }
        }
    }

    fun updateUserProfilePicture(url: String) {
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()
        val userId = auth.currentUser?.uid

        userId?.let {
            firestore.collection("users").document(it)
                .update("profilePictureUrl", url)
                .addOnSuccessListener {
                    _user.value = _user.value?.copy(profilePictureUrl = url)
                }
                .addOnFailureListener {
                    // Handle failure
                }
        }
    }

    fun logout(onLogoutComplete: () -> Unit) {
        viewModelScope.launch {
            FirebaseAuth.getInstance().signOut()
            onLogoutComplete()
        }
    }
}
