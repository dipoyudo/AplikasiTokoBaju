package com.example.navigasitokoonline

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_product.view.*

class ProductAdapter ( val context :Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {
        var arraylist           =  ArrayList<ProductModel>()
        var ProductListFilter   =  ArrayList<ProductModel>()

    fun setData(arrayList: ArrayList<ProductModel>){
        this.arraylist          = arrayList
        this.ProductListFilter  = arrayList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(model: ProductModel){
            itemView.nameProduct.text = model.nmProduct
            itemView.ketProduct.text = model.dsProduct
            itemView.hargaProduct.text = model.priceofProduct.toString()
            itemView.imgProduct.setImageResource(model.picProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_product,parent,false)
        return ProductAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(arraylist[position])
        holder.itemView.setOnClickListener() {
            val model = arraylist.get(position)

            var gproduct: String = model.nmProduct
            var gdesc: String = model.dsProduct
            var gharga :Int = model.priceofProduct.toString().toInt()
            var gimg: Int = model.picProduct

            val intent = Intent(context, Order::class.java)
            intent.putExtra("pProduct",gproduct)
            intent.putExtra("pDesc",gdesc)
            intent.putExtra("pHarga",gharga)
            intent.putExtra("pImg",gimg)
            context.startActivity(intent)

        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResult = FilterResults()
                if (constraint == null || constraint.length < 0) {
                    filterResult.count = ProductListFilter.size
                    filterResult.values = ProductListFilter
                } else{
                var searchChr = constraint.toString()
                val productSearch = ArrayList<ProductModel>()
                for (item in ProductListFilter){
                    if (item.nmProduct.toLowerCase().contains(searchChr) || item.dsProduct.toLowerCase().contains(searchChr)){
                        productSearch.add(item)
                    }
                }
                filterResult.count = productSearch.size
                filterResult.values = productSearch
            }
            return filterResult
        }

            override fun publishResults(constraint: CharSequence?, filterResult: FilterResults?) {
                arraylist = filterResult!!.values as ArrayList<ProductModel>
                notifyDataSetChanged()
            }
     }
    }
}