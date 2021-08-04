package com.example.coins.ui.listing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.coins.theme.CoinTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class FirstFragment : Fragment() {

    private val viewModel : ListingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.liveData.observe(this@FirstFragment,Observer{
                if (it.progress) Log.i("mylogger","progress true")
            })
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val a = ComposeView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )


            setContent {
                val viewState by viewModel.liveData.observeAsState()

                CoinTheme(useDarkColors = false) {
                }
            }
        }

        return a
    }

}