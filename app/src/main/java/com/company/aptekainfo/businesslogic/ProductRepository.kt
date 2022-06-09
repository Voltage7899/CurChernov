package com.company.aptekainfo.businesslogic

import android.util.Log
import com.company.aptekainfo.data.ConnectionHelper
import com.company.aptekainfo.model.ProductModel
import com.company.aptekainfo.model.AdminModel
import java.sql.ResultSet
import java.sql.Statement

class ProductRepository {

    private var statement :Statement?=null
    private val productList=ArrayList<ProductModel>()
    private val illnessList= arrayListOf<String>()
    private val activeChemtList=ArrayList<String>()
    private val addressList=ArrayList<String>()


    init {
        val connectionHelper = ConnectionHelper()
        val connection = connectionHelper.databaseConnect()
        statement = connection?.createStatement()
    }




    fun regInsert(name:String,phone:Int,pass:String) {
        val querry = "insert into admin (name,phone,pass) values ('$name','$phone','$pass')"
        try{

            statement?.executeUpdate(querry)
        }
        catch (ex : Exception){
            Log.e("Error : ", ex.message.toString())
        }

    }

    fun productInsert(name:String,price:Int,desc:String,active_chem:String,illness:String,link:String,address:String,image:String,idAdmin:Int) {
        val querry = "insert into product(name,price,description,active_chem,illness,link,address,image,id_admin) values ('$name','$price','$desc','$active_chem','$illness','$link','$address','$image','$idAdmin')"

        try {
            statement?.executeUpdate(querry)
        }
        catch (ex : Exception){
            Log.e("Error : ", ex.message.toString())
        }

    }

    fun singSelect(phone:Int,pass:String):Boolean{
        val querry = "select * from admin where phone='$phone'"
        try{
            val resultSet: ResultSet?=statement?.executeQuery(querry)
            while (resultSet?.next()!!)
            {
                if(resultSet.getInt(3)==phone && resultSet.getString(4)==pass){
                    AdminModel.currentAdmin= AdminModel(resultSet.getInt(1),resultSet.getString(2))

                    return true


                }
                else{
                    return false
                }

            }
        }
        catch (ex:Exception){
            Log.e("Error : ", ex.message.toString())
        }



        return false
    }

    fun productAllSelect() : ArrayList<ProductModel>{
        val querry = "select * from product order by price"
        productList.clear()
        try{
            val resultSet: ResultSet?=statement?.executeQuery(querry)
            while (resultSet?.next()!!)
            {
                val productModel= ProductModel(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(9),resultSet.getInt(10))

                productList.add(productModel)
            }

        }
        catch (ex: Exception)
        {
            Log.e("Error : ", ex.message.toString())
        }


        return productList
    }

    fun productUpdate(id_product:Int,name:String,price:Int,desc:String,active_chem:String,illness:String,link:String,address:String,image:String,idAdmin:Int): Boolean{

        val querry = "UPDATE product SET name ='$name'  ,description ='$desc'  ,active_chem = '$active_chem' ,illness ='$illness'  ,link ='$link'  ,address ='$address'  ,image ='$image'  ,id_admin =$idAdmin  ,price =$price  WHERE id_product=$id_product "

        try {
            statement?.executeUpdate(querry)
            return true
        }
        catch (ex: Exception){
            Log.e("Error : ", ex.message.toString())
            return false
        }

    }

    fun productDelete(id_product:Int): Boolean{
        val querry = "delete from product where id_product=$id_product"
        try {
            statement?.executeUpdate(querry)
            return true
        }
        catch (ex: Exception){
            Log.e("Error : ", ex.message.toString())
            return false
        }
    }

    fun illnessSelect() : ArrayList<String>{
        illnessList.clear()
        val querry = "select illness from product"
        try {
            val resultSet: ResultSet?=statement?.executeQuery(querry)
            while (resultSet?.next()!!){
                illnessList.add(resultSet.getString(1))
            }

        }
        catch (ex: Exception){
            Log.e("Error : ", ex.message.toString())

        }
        return illnessList
    }
    fun activeChemSelect():ArrayList<String>{
        activeChemtList.clear()
        val querry = "select active_chem from product"
        try {
            val resultSet: ResultSet?=statement?.executeQuery(querry)
            while (resultSet?.next()!!){
                activeChemtList.add(resultSet.getString(1))
            }

        }
        catch (ex: Exception){
            Log.e("Error : ", ex.message.toString())

        }
        return activeChemtList
    }
    fun addressSelect():ArrayList<String>{
        addressList.clear()
        val querry = "select address from product"
        try {
            val resultSet: ResultSet?=statement?.executeQuery(querry)
            while (resultSet?.next()!!){
                addressList.add(resultSet.getString(1))
            }

        }
        catch (ex: Exception){
            Log.e("Error : ", ex.message.toString())

        }
        return activeChemtList
    }


    fun getProductOnIllnessSelect(illness:String):ArrayList<ProductModel>{
        productList.clear()
        var querry:String
        when(illness){
            "Все записи"-> querry="select * from product order by price"
            else->querry="select * from product where illness='$illness' order by price"

        }

        try {
            val resultSet: ResultSet?=statement?.executeQuery(querry)
            while (resultSet?.next()!!){
                val productModel= ProductModel(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(9),resultSet.getInt(10))
                productList.add(productModel)
            }

        }
        catch (ex: Exception){
            Log.e("Error : ", ex.message.toString())

        }
        return productList
    }

    fun getProductOnActiveChemSelect(activeChem:String):ArrayList<ProductModel>{
        productList.clear()
        var querry:String
        when(activeChem){
            "Все записи"-> querry="select * from product order by price"
            else->querry="select * from product where active_chem='$activeChem' order by price"

        }

        try {
            val resultSet: ResultSet?=statement?.executeQuery(querry)
            while (resultSet?.next()!!){
                val productModel= ProductModel(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(9),resultSet.getInt(10))
                productList.add(productModel)
            }

        }
        catch (ex: Exception){
            Log.e("Error : ", ex.message.toString())

        }
        return productList
    }

    fun getProductOnAddressSelect(address:String):ArrayList<ProductModel>{
        productList.clear()
        var querry:String
        when(address){
            "Все записи"-> querry="select * from product order by price"
            else->querry="select * from product where address='$address' order by price"

        }

        try {
            val resultSet: ResultSet?=statement?.executeQuery(querry)
            while (resultSet?.next()!!){
                val productModel= ProductModel(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(9),resultSet.getInt(10))
                productList.add(productModel)
            }

        }
        catch (ex: Exception){
            Log.e("Error : ", ex.message.toString())

        }
        return productList
    }

}

