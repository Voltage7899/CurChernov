package com.company.aptekainfo.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.company.aptekainfo.R
import com.company.aptekainfo.databinding.ActivitySingOrRegBinding
import com.company.aptekainfo.viewmodel.RegSignViewModel

class SingOrReg : AppCompatActivity() {

    private lateinit var binding: ActivitySingOrRegBinding
    private val regSignViewModel : RegSignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySingOrRegBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.singOrRegBottomMenu.selectedItemId = R.id.admin;

        binding.singOrRegBottomMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.user -> {
                    val admin = Intent(this, MainActivity::class.java)
                    startActivity(admin)
                }

            }

            true
        }

        binding.register.setOnClickListener {
            startActivity(Intent(this, Registr::class.java))
        }
        binding.sing.setOnClickListener {

            val password = binding.password.text.toString()
            try {
                val phone = binding.phone.text.toString().toInt()


                if(regSignViewModel.signSelectViewModel(phone,password) == true){
                    Toast.makeText(this,"Добро пожаловать",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AdminList::class.java))
                }
                else{
                    Toast.makeText(this,"Неверные данные",Toast.LENGTH_SHORT).show()
                }
            }
            catch (ex: Exception){
                Toast.makeText(this,"Телефон цифрами!!",Toast.LENGTH_SHORT).show()
            }

        }
    }
}


