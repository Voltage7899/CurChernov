package com.company.aptekainfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.aptekainfo.R
import com.company.aptekainfo.databinding.ElementRecyclerviewBinding
import com.company.aptekainfo.model.ProductModel
import com.squareup.picasso.Picasso

class RecAdapter(val clickListener: ClickListener) :RecyclerView.Adapter<RecAdapter.ProductViewHolder>(){

    private var productListInAdapter= ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element__recyclerview,parent,false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productListInAdapter[position],clickListener)
    }

    override fun getItemCount(): Int {
        return productListInAdapter.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ElementRecyclerviewBinding.bind(itemView)
        fun bind(product: ProductModel, clickListener: ClickListener){
            binding.elementName.text=product.name
            binding.elementPrice.text=product.price.toString()
            binding.elementIllness.text=product.illness
            Picasso.get().load(product.image).fit().into(binding.elementImage)
            itemView.setOnClickListener{

                clickListener.onClick(product)
            }

        }
    }
    fun loadListToAdapter(productList:ArrayList<ProductModel>){
        productListInAdapter= productList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(product: ProductModel){

        }
    }
    fun deleteItem(i:Int):Int{
        var id=productListInAdapter.get(i).id_product

        productListInAdapter.removeAt(i)

        notifyDataSetChanged()

        return id
    }
}