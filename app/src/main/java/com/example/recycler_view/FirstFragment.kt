package com.example.recycler_view

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler_view.Adapters.PhotoAdapter
import com.example.recycler_view.api.RetrofitAPIHandler
import com.example.recycler_view.databinding.FragmentFirstBinding
import com.example.recycler_view.models.photo
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
private val retrofitAPIHandler=RetrofitAPIHandler.create()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager=LinearLayoutManager(view.context)
        val photo = retrofitAPIHandler.getphotos()

        photo.enqueue(object : Callback<List<photo>> {

            override fun onResponse(call: Call<List<photo>>, response:Response<List<photo>>){
                val photoBody= response.body()
                val adapter= PhotoAdapter (photoBody!!,this,{position ->  onListItemClick(position)})
                binding.recyclerView.adapter=adapter
            }

            override fun onFailure(call: Call<List<photo>>, t: Throwable) {

            }

        })


        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun onListItemClick(position: Int) {
Snackbar.make(requireView(),"clicked on item ${position+1}",Snackbar.LENGTH_LONG)
    .setAction("action",null).show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}