package com.company.aptekainfo.repository

import android.app.Application
import com.company.aptekainfo.businesslogic.ProductRepository

class Repository {


companion object{
    var database: ProductRepository?=null
    fun  initDataBase(aplication:Application){


        if(database ==null){

            database = ProductRepository()
        }
    }
    fun getDataBase(): ProductRepository?{
        if(database ==null){

        }
        return  database
    }
}




}