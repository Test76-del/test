package com.online.test.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.online.test.R
import com.online.test.data.Category
import com.online.test.data.TitleData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_item.*

class ItemAdapter(val context: Context) :
    ListAdapter<Category, ItemAdapter.ViewHolder>(
        DiffCallback()
    ) {

    private lateinit var dataModel: DetailViewModel

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)

        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, AdapterView.OnItemClickListener {

        fun bind(item: Category) {
            with(item) {
                category_name.text = item.category

                // Setting up list view
                val list: List<String> = item.children.map { it.name }
                val adapter =
                    ArrayAdapter(context, android.R.layout.simple_list_item_1, list)
                item_list.adapter = adapter

                Log.e("resData", getItem(position).children.toString())

                add_child.setOnClickListener {
                    val name = nameChild.text.toString()

                    val activity = context as DashboardActivity
                    dataModel = ViewModelProvider(activity).get(DetailViewModel::class.java)

                    if (!name.equals("")) {
                        // Saving item inside database
                        val item = TitleData(0, name, getItem(position).category)
                        dataModel.saveTask(item)

                        val list: List<String> = getItem(position).children.map { it.name }
                        val adapter =
                            ArrayAdapter(context, android.R.layout.simple_list_item_1, list)
                        item_list.adapter = adapter
                    } else {
                        Toast.makeText(context, "SubTitle can't empty!", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.category == newItem.category
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}