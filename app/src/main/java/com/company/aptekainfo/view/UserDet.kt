package com.company.aptekainfo.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company.aptekainfo.databinding.ActivityUserDetBinding
import com.company.aptekainfo.model.ProductModel
import com.squareup.picasso.Picasso

class UserDet : AppCompatActivity() {

    lateinit var binding: ActivityUserDetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityUserDetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item= intent.getSerializableExtra("chosenProduct") as ProductModel
        Picasso.get().load(item.image).fit().into(binding.detUserImage)
        binding.detUserName.setText(item.name)
        binding.detUserPrice.setText(item.price.toString())
        binding.detUserDesc.setText(item.description)
        binding.detUserActiveChem.setText(item.active_chem)
        binding.detUserIllness.setText(item.illness)
        binding.detUserLink.setText(item.link)
        binding.detUserAddress.setText(item.address)

        binding.detUserLink.setOnClickListener{

            try{
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(binding.detUserLink.text.toString())))
            }
            catch (ex:Exception){
                Toast.makeText(this,"Ссылка недействительна",Toast.LENGTH_SHORT).show()
            }

        }
        binding.detUserAddress.setOnLongClickListener {
            val clipboardManager=getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("",binding.detUserAddress.text.toString())
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this,"Адрес скопирован",Toast.LENGTH_SHORT).show()

            return@setOnLongClickListener true
        }

    }
}