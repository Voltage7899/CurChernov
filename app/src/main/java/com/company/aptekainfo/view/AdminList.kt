package com.company.aptekainfo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.aptekainfo.viewmodel.GetListProductViewModel
import com.company.aptekainfo.model.ProductModel
import com.company.aptekainfo.R
import com.company.aptekainfo.adapters.RecAdapter
import com.company.aptekainfo.databinding.ActivityAdminListBinding
import com.company.aptekainfo.viewmodel.AddUpdateDeleteViewModel

class AdminList : AppCompatActivity() , RecAdapter.ClickListener {
    lateinit var binding: ActivityAdminListBinding
    private val getListProductViewModel : GetListProductViewModel by viewModels()
    private val addUpdateDeleteViewModel : AddUpdateDeleteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAdminListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.adminBottomMenu.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.admin ->{
                    val admin = Intent(this, SingOrReg::class.java)
                    startActivity(admin)
                }
                R.id.user->{
                    val user = Intent(this, MainActivity::class.java)
                    startActivity(user)
                }
            }

            true
        }

        binding.adminNavLeftMenu.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.add_product ->{startActivity(Intent(this, AddProduct::class.java))}
                R.id.admin_show_list ->{

                }
            }
            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }
        initRec()


    }
    fun initRec(){

        binding.adminRecyclerUser.layoutManager=LinearLayoutManager(this)
        val productListAdapter= RecAdapter(this)
        binding.adminRecyclerUser.adapter=productListAdapter

        getListProductViewModel.getAllProductViewModelFromViewModel()?.observe(this, Observer {

            productListAdapter.loadListToAdapter(it)

        })
        val simpleCallback =object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id =productListAdapter.deleteItem(viewHolder.adapterPosition)
                addUpdateDeleteViewModel.deleteProductViewModel(id)
            }

        }
        val itemTouchHelper=ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.adminRecyclerUser)

    }

    override fun onClick(product: ProductModel) {
        startActivity(Intent(this, AdminDetProduct::class.java).apply {
            putExtra("chosenProduct",product)

        })
    }

}