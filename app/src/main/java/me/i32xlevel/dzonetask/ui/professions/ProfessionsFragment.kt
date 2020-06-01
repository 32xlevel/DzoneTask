package me.i32xlevel.dzonetask.ui.professions

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.notify_layout.*
import kotlinx.android.synthetic.main.professions_fragment.*
import me.i32xlevel.dzonetask.R
import me.i32xlevel.dzonetask.model.database.DzoneDatabase
import me.i32xlevel.dzonetask.model.remote.workersApi
import me.i32xlevel.dzonetask.ui.BaseFragment
import me.i32xlevel.dzonetask.ui.ProgressDialog
import me.i32xlevel.dzonetask.viewmodel.UiState
import me.i32xlevel.dzonetask.viewmodel.professions.ProfessionsViewModel
import me.i32xlevel.dzonetask.viewmodel.professions.ProfessionsViewModelFactory

class ProfessionsFragment : BaseFragment<ProfessionsViewModel>(R.layout.professions_fragment) {

    override val viewModel: ProfessionsViewModel by viewModels {
        val application = requireActivity().application
        val database = DzoneDatabase.getInstance(application)

        ProfessionsViewModelFactory(workersApi, database.professionDao, database.workerDao)
    }
    private val adapter: ProfessionsAdapter by lazy {
        ProfessionsAdapter { profession ->
            findNavController().navigate(ProfessionsFragmentDirections.actionProfessionsFragmentToWorkersFragment(profession))
        }
    }

    override fun setupViews() {
        professions_recycler.adapter = adapter
        professions_recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        notify_button.setOnClickListener { viewModel.getNewData() }

        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun observeUiState(uiState: UiState) {
        when (uiState) {
            UiState.LOADING -> ProgressDialog.show(requireContext())

            UiState.EMPTY, UiState.ERROR -> {
                notify_text.isVisible = true
                notify_button.isVisible = true
                professions_recycler.isVisible = false
            }
        }

        if (uiState != UiState.LOADING) ProgressDialog.dismiss()
        if (uiState != UiState.EMPTY && uiState != UiState.ERROR) {
            notify_text.isVisible = false
            notify_button.isVisible = false
            professions_recycler.isVisible = true
        }
    }
}