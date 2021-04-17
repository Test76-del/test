package com.online.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.online.test.R
import kotlinx.android.synthetic.main.fragment_favourite_list.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.online.test.data.TitleData
import kotlinx.android.synthetic.main.fragment_favourite_list.name


class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var dataModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        dataModel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(card_list) {
            layoutManager = LinearLayoutManager(activity)
            adapter = ItemAdapter(requireContext())
        }

        viewModel.items.observe(viewLifecycleOwner, Observer {
            (card_list.adapter as ItemAdapter).submitList(it)
        })
        add_item.setOnClickListener {
            saveItem()
        }


    }

    private fun saveItem() {
        val name = name.text.toString()

        if (!name.equals("")) {
            // Saving item inside database
            val item = TitleData(0, "", name)
            dataModel.saveTask(item)


            viewModel.items.observe(viewLifecycleOwner, Observer {
                (card_list.adapter as ItemAdapter).submitList(it)
            })
        } else {
            Toast.makeText(activity, "Title can't empty!", Toast.LENGTH_SHORT).show()
        }

    }

}