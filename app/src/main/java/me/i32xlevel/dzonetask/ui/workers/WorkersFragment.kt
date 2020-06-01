package me.i32xlevel.dzonetask.ui.workers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.workers_fragment.*
import me.i32xlevel.dzonetask.R
import me.i32xlevel.dzonetask.model.database.DzoneDatabase
import me.i32xlevel.dzonetask.ui.BaseFragment
import me.i32xlevel.dzonetask.ui.ProgressDialog
import me.i32xlevel.dzonetask.viewmodel.UiState
import me.i32xlevel.dzonetask.viewmodel.workers.WorkersViewModel
import me.i32xlevel.dzonetask.viewmodel.workers.WorkersViewModelFactory

class WorkersFragment : BaseFragment<WorkersViewModel>(R.layout.workers_fragment) {

    override val viewModel: WorkersViewModel by viewModels {
        val workerDao = DzoneDatabase.getInstance(requireActivity().application).workerDao
        WorkersViewModelFactory(workerDao, args.profession.id)
    }
    private val args: WorkersFragmentArgs by navArgs()
    private val adapter: WorkersAdapter by lazy {
        WorkersAdapter { worker ->
            findNavController().navigate(WorkersFragmentDirections.actionWorkersFragmentToWorkerFragment(worker, args.profession))
        }
    }

    override fun setupViews() {
        (activity as AppCompatActivity).supportActionBar?.title = "Профессия: ${args.profession.name}"

        workers_recycler.adapter = adapter
        workers_recycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun observeUiState(uiState: UiState) {
        when (uiState) {
            UiState.LOADING -> ProgressDialog.show(requireContext())
            UiState.EMPTY, UiState.ERROR -> {

            }
            UiState.SUCCESS -> {

            }
        }

        if (uiState != UiState.LOADING) ProgressDialog.dismiss()
    }

}