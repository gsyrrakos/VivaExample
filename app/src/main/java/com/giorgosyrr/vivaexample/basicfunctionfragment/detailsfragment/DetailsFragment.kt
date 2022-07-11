package com.giorgosyrr.vivaexample.basicfunctionfragment.detailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.giorgosyrr.vivaexample.MainActivity
import com.giorgosyrr.vivaexample.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"


class DetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null
    private var mTextView: TextView? = null
    private var mTextViewDescr2: TextView? = null
    private var thumbnailImage: ImageView? = null
    private var mTextViewPrice: TextView? = null
    var returnButton: AppCompatButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getString(ARG_PARAM4)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val act = activity as MainActivity?
        mTextView = view.findViewById(R.id.item_title)
        mTextViewDescr2 = view.findViewById(R.id.item_description1)
        mTextViewPrice = view.findViewById(R.id.item_price)
        returnButton = view.findViewById(R.id.return_item_button)
        thumbnailImage = view.findViewById(R.id.item_thumbnail)
        returnButton?.setOnClickListener {
            act?.findViewById<Toolbar>(R.id.tb_main)?.findViewById<ImageView>(R.id.refresh1)
                ?.apply {
                    if (visibility == View.VISIBLE) {
                        visibility = View.GONE
                    } else {
                        visibility = View.VISIBLE
                    }
                }
            activity?.supportFragmentManager?.popBackStack()
        }
        Glide.with(this).load(param2).into(thumbnailImage!!)
        mTextView?.text = param1
        mTextViewDescr2?.text = param3
        mTextViewPrice?.text=param4

        act?.findViewById<Toolbar>(R.id.tb_main)?.findViewById<ImageView>(R.id.refresh1)?.apply {
            visibility = View.GONE}

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String, s: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, s)
                }
            }
    }
}