package com.company.aptekainfo.viewmodel

import androidx.lifecycle.ViewModel
import com.company.aptekainfo.repository.Repository

class RegSignViewModel : ViewModel() {

    fun regInsertViewModel(name:String,phone:Int,pass:String){

        Repository.getDataBase()?.regInsert(name,phone,pass)

    }
    fun signSelectViewModel(phone:Int,pass:String): Boolean?{
        return Repository.getDataBase()?.singSelect(phone,pass)
    }

}