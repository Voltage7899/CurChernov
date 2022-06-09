package com.company.aptekainfo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.company.aptekainfo.viewmodel.AddUpdateDeleteViewModel
import com.company.aptekainfo.model.AdminModel
import com.company.aptekainfo.R
import com.company.aptekainfo.databinding.ActivityAddProductBinding
import com.squareup.picasso.Picasso

class AddProduct : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding
    private val addUpdateDeleteViewModel : AddUpdateDeleteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Picasso.get().load(R.drawable.question).fit().placeholder(R.drawable.question).error(R.drawable.error).into(binding.addImage)



        binding.addAdd.setOnClickListener {
            val name = binding.addName.text.toString()

            val desc = binding.addDesc.text.toString()
            val active_chem = binding.addActiveChem.text.toString()
            val illness = binding.addIllness.text.toString()
            val link = binding.addLink.text.toString()
            val address = binding.addAddress.text.toString()
            val image_link = binding.addImageLink.text.toString()
            val idAdmin= AdminModel.currentAdmin!!.id

            try {
                val price = binding.addPrice.text.toString().toInt()
                addUpdateDeleteViewModel.addProductViewModel(name,price,desc,active_chem,illness,link,address,image_link,idAdmin)
                startActivity(Intent(this, AdminList::class.java))
            }
            catch (ex:Exception){
                Toast.makeText(this,"Цена цифрами!!",Toast.LENGTH_SHORT).show()
            }



        }
        binding.addImage.setOnClickListener{

            try{
                Picasso.get().load(binding.addImageLink.text.toString()).fit().placeholder(R.drawable.question).error(
                    R.drawable.error
                ).into(binding.addImage)
            }
            catch (ex:Exception){
                Toast.makeText(this,"Нет ссылки на картинку",Toast.LENGTH_SHORT).show()
            }




        }
    }
}