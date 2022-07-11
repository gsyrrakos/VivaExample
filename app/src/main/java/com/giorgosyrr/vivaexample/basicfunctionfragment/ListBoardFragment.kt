package com.giorgosyrr.vivaexample.basicfunctionfragment


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giorgosyrr.vivaexample.BaseApplication
import com.giorgosyrr.vivaexample.MainActivity
import com.giorgosyrr.vivaexample.R
import com.giorgosyrr.vivaexample.basicfunctionfragment.adapter.ItemAdapter
import com.giorgosyrr.vivaexample.basicfunctionfragment.dialog.PersonalInfoDialog
import com.giorgosyrr.vivaexample.data.model.DataFromApi
import com.giorgosyrr.vivaexample.di.FragmentModule
import com.giorgosyrr.vivaexample.databinding.FragmentBasicFunctionBinding
import javax.inject.Inject


class ListBoardFragment : Fragment(), ListBoardContract.View {
    private var listCached: List<DataFromApi>? = null

    @set:Inject
    var presenter: ListBoardContract.Presenter? = null
    private var _binding: FragmentBasicFunctionBinding? = null
    private val binding get() = _binding!!
    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private val isNetworkAvailable: Boolean
        get() {
            val cm: ConnectivityManager =
                requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasicFunctionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (this.requireActivity().application as BaseApplication).appComponent
            ?.converterActivitySubcomponent(FragmentModule(this))
            ?.inject(this)
        val act = activity as MainActivity?

        recyclerView = binding.myRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()

        act?.findViewById<Toolbar>(R.id.tb_main)?.findViewById<ImageView>(R.id.refresh)?.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                PersonalInfoDialog().show(act.supportFragmentManager, "info-dialog")
            }
        }

        act?.findViewById<Toolbar>(R.id.tb_main)?.findViewById<ImageView>(R.id.back)?.apply {
            visibility = View.VISIBLE
            setOnClickListener {

                act.findViewById<Toolbar>(R.id.tb_main)?.findViewById<ImageView>(R.id.refresh1)
                    ?.apply {
                        if (visibility == View.VISIBLE) {
                            visibility = View.GONE
                        } else {
                            visibility = View.VISIBLE
                        }
                    }

                activity?.supportFragmentManager?.popBackStack()
            }
        }

        act?.findViewById<Toolbar>(R.id.tb_main)?.findViewById<ImageView>(R.id.refresh1)?.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                presenter?.deleteAllFromDb()
                listCached = null
                recyclerView?.adapter = adapter
                binding.myRecyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                presenter?.getData(isNetworkAvailable)
            }
        }

        if (listCached == null) {
            binding.progressBar.visibility = View.VISIBLE
            presenter?.getData(isNetworkAvailable)
        } else {
            adapter = ItemAdapter(listCached!!)
            recyclerView?.adapter = adapter
            binding.myRecyclerView.visibility = View.VISIBLE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter?.onDestroy()
    }

    override fun loadFirstData(listBoard: List<DataFromApi>) {
        binding.progressBar.visibility = View.GONE
        listCached = listBoard
        adapter = ItemAdapter(listBoard)
        recyclerView?.adapter = adapter
        binding.myRecyclerView.visibility = View.VISIBLE
    }

    override fun displayNetworkError(text: String?) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }

}