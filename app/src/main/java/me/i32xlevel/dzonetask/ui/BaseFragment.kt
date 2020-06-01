package me.i32xlevel.dzonetask.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.i32xlevel.dzonetask.viewmodel.BaseViewModel
import me.i32xlevel.dzonetask.viewmodel.UiState

abstract class BaseFragment<T: BaseViewModel>(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    abstract val viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            observeUiState(it)
        })

        setupViews()
    }

    abstract fun observeUiState(uiState: UiState)

    abstract fun setupViews()
}