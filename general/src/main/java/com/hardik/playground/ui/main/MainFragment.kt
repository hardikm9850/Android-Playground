package com.hardik.playground.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hardik.playground.R
import java.util.Collections

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    fun countStringOccurance(){
        val array = listOf("ABC","ABC","ABC","CV","CV","D","E","E","E","D")
        array.filter { Collections.frequency(array,it) == 3 }.toList()
    }

    /**
     * Three integer arrays are given with duplicate numbers. Find the common elements among three arrays
     */
    fun repeatativeElement(){
        val array1 = intArrayOf(1,2,3,4)
        val array2 = intArrayOf(1,2,3)
        val array3 = intArrayOf(1,2,)

        val set = array1.toSet()
        val result = set.filter { array2.contains(it) }.filter { array3.contains(it) }.toList()
        print(result)
    }
}