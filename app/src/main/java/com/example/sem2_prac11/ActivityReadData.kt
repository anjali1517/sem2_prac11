package com.example.sem2_prac11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sem2_prac11.databinding.ActivityMainBinding
import com.example.sem2_prac11.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivityReadData : AppCompatActivity() {

    lateinit var binding: ActivityReadDataBinding
    lateinit var refernce:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReadDataBinding.inflate(getLayoutInflater())
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

            binding.btnReadData.setOnClickListener {
                var username = binding.edtUser.text.toString()
                if(!username.isEmpty()){
                    readData(username)
                }else{
                    Toast.makeText(this,"Please enter username",Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun readData(username: String) {
            refernce = FirebaseDatabase.getInstance().getReference("User")
        refernce.child(username).get().addOnCompleteListener {
            if(it.isSuccessful){

                if(it.getResult().exists()){
                    var datasnapshot = it.getResult()
                    var firstname = datasnapshot.child("firstname").getValue().toString()
                    var lastname = datasnapshot.child("lastname").getValue().toString()
                    binding.txtFirst.setText(firstname)
                    binding.txtLast.setText(lastname)
                }
                else{
                    Toast.makeText(this,"User doesn't exists",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Failed to read",Toast.LENGTH_LONG).show()
            }
        }
    }
}