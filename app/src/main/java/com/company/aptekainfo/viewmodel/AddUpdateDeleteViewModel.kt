package com.company.aptekainfo.viewmodel

import androidx.lifecycle.ViewModel
import com.company.aptekainfo.repository.Repository

class AddUpdateDeleteViewModel : ViewModel(){

    fun addProductViewModel(name:String,price:Int,desc:String,active_chem:String,illness:String,link:String,address:String,image:String,idAdmin:Int){

        Repository.getDataBase()
            ?.productInsert(name,price,desc,active_chem,illness,link,address,image,idAdmin)

    }

    fun updateProductViewModel(id_product:Int,name:String,price:Int,desc:String,active_chem:String,illness:String,link:String,address:String,image:String,idAdmin:Int) : Boolean?{
        return Repository.getDataBase()?.productUpdate(id_product,name,price,desc,active_chem,illness,link,address,image,idAdmin)
    }

    fun deleteProductViewModel(id_product:Int) : Boolean?{
        return  Repository.getDataBase()?.productDelete(id_product)
    }


}