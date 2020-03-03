package com.byron.giphyproto.app.ui.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.byron.giphyproto.app.R
import com.byron.giphyproto.app.common.NUM_COLUMNS
import com.byron.giphyproto.app.common.ui.FragmentBase
import com.byron.giphyproto.app.databinding.MainFragmentBinding
import com.byron.giphyproto.app.di.ViewModelFactory
import com.byron.giphyproto.app.ui.adapters.GiphyGridviewAdapater
import com.byron.giphyproto.app.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject


class MainFragment : FragmentBase() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: GiphyGridviewAdapater
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        binding.context = viewModel

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.giphyTrendingMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { response ->
                val dataList = response.data
                adapter.updateAdapter(dataList)
            }
        })

        searchButton.setOnClickListener { it ->
            hideSoftKeyBoard(it)
            searchTerm?.let {
                viewModel.doSearch(it.text.toString())
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView = binding.recyclerView
        adapter = GiphyGridviewAdapater(ArrayList())
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.itemAnimator = null
        recyclerView.adapter = adapter
    }

    private fun hideSoftKeyBoard(view: View) {
        val imm = view.context.getSystemService<InputMethodManager>()
        if (imm!!.isAcceptingText) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}