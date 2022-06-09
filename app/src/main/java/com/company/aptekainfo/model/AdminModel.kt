package com.company.aptekainfo.model

data class AdminModel(var id:Int,var name:String){
    companion object{
        var currentAdmin: AdminModel?=null
    }
}