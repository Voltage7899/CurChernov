package com.company.aptekainfo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.company.aptekainfo.model.AdminModel
import com.company.aptekainfo.model.ProductModel
import com.company.aptekainfo.R
import com.company.aptekainfo.databinding.ActivityAdminDetProductBinding
import com.company.aptekainfo.viewmodel.AddUpdateDeleteViewModel
import com.squareup.picasso.Picasso

class AdminDetProduct : AppCompatActivity() {
    lateinit var binding: ActivityAdminDetProductBinding
    private val addUpdateDeleteViewModel : AddUpdateDeleteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityAdminDetProductBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val item= intent.getSerializableExtra("chosenProduct") as ProductModel
        Picasso.get().load(item.image).fit().into(binding.detImage)
        binding.detName.setText(item.name)
        binding.detPrice.setText(item.price.toString())
        binding.detDesc.setText(item.description)
        binding.detActiveChem.setText(item.active_chem)
        binding.detIllness.setText(item.illness)
        binding.detLink.setText(item.link)
        binding.detAddress.setText(item.address)
        binding.detImageLink.setText(item.image)


        binding.detUpdate.setOnClickListener {
            val id_product=item.id_product
            val name = binding.detName.text.toString()
            val desc=binding.detDesc.text.toString()
            val active_chem=binding.detActiveChem.text.toString()
            val illness=binding.detIllness.text.toString()
            val link=binding.detLink.text.toString()
            val address=binding.detAddress.text.toString()
            val image_link=binding.detImageLink.text.toString()
            val idAdmin= AdminModel.currentAdmin!!.id

            try {
                val price = binding.detPrice.text.toString().toInt()
                val status =addUpdateDeleteViewModel.updateProductViewModel(id_product,name,price,desc,active_chem,illness,link,address,image_link,idAdmin)
                if(status == true){
                    Toast.makeText(this,"Обновлено",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AdminList::class.java))
                }
                else{
                    Toast.makeText(this,"Проверьте соединение с интернетом",Toast.LENGTH_SHORT).show()
                }

            }
            catch (ex:Exception){
                Toast.makeText(this,"Цена цифрами!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.detImage.setOnClickListener{
            try{
                Picasso.get().load(binding.detImageLink.text.toString()).fit().placeholder(R.drawable.question).error(
                    R.drawable.error
                ).into(binding.detImage)
            }
            catch (ex:Exception){
                Toast.makeText(this,"Нет ссылки на картинку",Toast.LENGTH_SHORT).show()
            }
        }

    }
}