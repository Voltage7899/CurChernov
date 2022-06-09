package com.company.aptekainfo.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.aptekainfo.*
import com.company.aptekainfo.adapters.RecAdapter
import com.company.aptekainfo.databinding.ActivityMainBinding
import com.company.aptekainfo.model.ProductModel
import com.company.aptekainfo.repository.Repository
import com.company.aptekainfo.viewmodel.GetListProductViewModel

class MainActivity : AppCompatActivity() , RecAdapter.ClickListener {

    lateinit var binding: ActivityMainBinding
    private val getListProductViewModel : GetListProductViewModel by viewModels()
    val productListAdapter = RecAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var currentFilter:String
        Repository.initDataBase(application)




        binding.bottomMenu.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.admin ->{
                    val admin = Intent(this, SingOrReg::class.java)
                    startActivity(admin)
                }
            }

true
        }

        binding.navLeftMenu.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.illnessMenu ->{
                    binding.illnessSpinner.visibility= View.VISIBLE
                    binding.activeChemSpinner.visibility= View.INVISIBLE
                    binding.addressSpinner.visibility= View.INVISIBLE
                    initSpinnerIllness()

                }
                R.id.activeChemMenu ->{
                    binding.illnessSpinner.visibility= View.INVISIBLE
                    binding.activeChemSpinner.visibility= View.VISIBLE
                    binding.addressSpinner.visibility= View.INVISIBLE
                    initSpinnerActiveChem()

                }
                R.id.addressMenu ->{
                    binding.illnessSpinner.visibility= View.INVISIBLE
                    binding.activeChemSpinner.visibility= View.INVISIBLE
                    binding.addressSpinner.visibility= View.VISIBLE
                    initSpinnerAddress()
                }
            }
            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }


        initRec()






    }
    fun initSpinnerIllness() {

        val illnessList = arrayListOf("Все записи")
        illnessList.addAll(getListProductViewModel.getIllnessListViewModel()?: arrayListOf())
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,illnessList?: arrayListOf())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.illnessSpinner.adapter=adapter
        binding.illnessSpinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentFilter = parent?.getItemAtPosition(position).toString()
                productListAdapter.loadListToAdapter(getListProductViewModel.getProductOnIllnessViewModel(currentFilter)?: arrayListOf())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
    fun initSpinnerActiveChem() {

        val activeChemList = arrayListOf("Все записи")
        activeChemList.addAll(getListProductViewModel.getActiveChemListViewModel()?: arrayListOf())
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,activeChemList?: arrayListOf())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.activeChemSpinner.adapter=adapter
        binding.activeChemSpinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentFilter = parent?.getItemAtPosition(position).toString()
                productListAdapter.loadListToAdapter(getListProductViewModel.getProductOnActiveChemViewModel(currentFilter)?: arrayListOf())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
    fun initSpinnerAddress() {

        val addressList = arrayListOf("Все записи")
        addressList.addAll(getListProductViewModel.getAddressListViewModel()?: arrayListOf())
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,addressList?: arrayListOf())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.addressSpinner.adapter=adapter
        binding.addressSpinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentFilter = parent?.getItemAtPosition(position).toString()
                productListAdapter.loadListToAdapter(getListProductViewModel.getProductOnAddressViewModel(currentFilter)?: arrayListOf())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
    fun initRec() {

        binding.recyclerUser.layoutManager = LinearLayoutManager(this)
        binding.recyclerUser.adapter = productListAdapter

        getListProductViewModel.getAllProductViewModelFromViewModel().observe(this, Observer {

            productListAdapter.loadListToAdapter(it)

        })
    }
    override fun onClick(product: ProductModel) {
        startActivity(Intent(this, UserDet::class.java).apply {
            putExtra("chosenProduct",product)

        })
    }


}