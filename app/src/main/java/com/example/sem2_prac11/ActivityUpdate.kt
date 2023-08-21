package com.example.sem2_prac11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sem2_prac11.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivityUpdate : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnUpdateData.setOnClickListener {

            var username = binding.edtUpdateUsername.text.toString()
            var first = binding.edtUpdateFirstname.text.toString()
            var last = binding.edtUpdateLastname.text.toString()

            updateData(username,first,last)
        }
    }

    private fun updateData(username: String, first: String, last: String) {

        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        //hasMap in kotline
        val user = mapOf<String,String>(
        "firstname" to first,
        "lastname" to last)

        databaseReference.child(username).updateChildren(user).addOnSuccessListener {
            binding.edtUpdateUsername.text.clear()
            binding.edtUpdateFirstname.text.clear()
            binding.edtUpdateLastname.text.clear()

            Toast.makeText(this,"Successfully updated",Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Not Updated",Toast.LENGTH_LONG).show()
        }
    }
}