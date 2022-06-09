package com.company.aptekainfo.model

import java.io.Serializable

data class ProductModel(val id_product:Int,val name:String,val description:String,val active_chem:String,val illness:String,val link:String,val address:String,val image:String,val id_admin:Int,val price:Int) :Serializable{
}