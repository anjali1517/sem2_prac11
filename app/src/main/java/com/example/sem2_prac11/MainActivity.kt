package com.example.sem2_prac11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sem2_prac11.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

   lateinit var binding: ActivityMainBinding
   lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            var username = binding.Username.text.toString()
            var firstname = binding.edtFirstname.text.toString()
            var lastname = binding.edtLastname.text.toString()

             database = FirebaseDatabase.getInstance().getReference("User")
            val user = user(firstname,lastname,username)
            database.child(username).setValue(user).addOnSuccessListener {
                binding.edtFirstname.text.clear()
                binding.edtLastname.text.clear()
                binding.Username.text.clear()

                Toast.makeText(this,"Successfully Register",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Not Register",Toast.LENGTH_LONG).show()
            }
        }

        binding.btnRead.setOnClickListener {
            startActivity(Intent(this,ActivityReadData::class.java))
        }
        binding.btnUpdate.setOnClickListener {
            startActivity(Intent(this,ActivityUpdate::class.java))
        }
        binding.btnDelete.setOnClickListener {
            startActivity(Intent(this,ActivityDelete::class.java))
        }
    }
}