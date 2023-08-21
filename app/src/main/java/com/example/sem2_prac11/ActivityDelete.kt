package com.example.sem2_prac11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sem2_prac11.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivityDelete : AppCompatActivity() {

    lateinit var binding: ActivityDeleteBinding
    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnDeleteData.setOnClickListener {
            var username = binding.edtDeleteUser.text.toString()
            if(!username.isEmpty()) {
                deleteData(username)

            }else{
                Toast.makeText(this,"Please enter username",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun deleteData(username: String) {

        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(username).removeValue().addOnSuccessListener {
            binding.edtDeleteUser.text.clear()
            Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this," Not Deleted!!",Toast.LENGTH_LONG).show()

        }
    }
}