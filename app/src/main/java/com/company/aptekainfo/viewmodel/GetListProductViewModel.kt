package com.company.aptekainfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.aptekainfo.model.ProductModel
import com.company.aptekainfo.repository.Repository

class GetListProductViewModel : ViewModel() {

    private val productList=MutableLiveData<ArrayList<ProductModel>>()


    fun getAllProductViewModelFromViewModel(): MutableLiveData<ArrayList<ProductModel>> {


        loadAllProductToViewModel()
        return productList
    }

    fun loadAllProductToViewModel() {
        productList.value= Repository.getDataBase()?.productAllSelect()

    }

    fun getIllnessListViewModel(): ArrayList<String>?{
        return Repository.getDataBase()?.illnessSelect()
    }
    fun getActiveChemListViewModel(): ArrayList<String>?{
        return Repository.getDataBase()?.activeChemSelect()
    }
    fun getAddressListViewModel(): ArrayList<String>?{
        return Repository.getDataBase()?.addressSelect()
    }


    fun getProductOnIllnessViewModel(illness:String):ArrayList<ProductModel>?{
        return Repository.getDataBase()?.getProductOnIllnessSelect(illness)
    }
    fun getProductOnActiveChemViewModel(activeChem:String):ArrayList<ProductModel>?{
        return Repository.getDataBase()?.getProductOnActiveChemSelect(activeChem)
    }
    fun getProductOnAddressViewModel(address:String):ArrayList<ProductModel>?{
        return Repository.getDataBase()?.getProductOnAddressSelect(address)
    }
}