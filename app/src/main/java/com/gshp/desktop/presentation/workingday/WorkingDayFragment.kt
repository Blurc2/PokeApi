package com.gshp.desktop.presentation.workingday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gshp.desktop.DesktopApplication
import com.gshp.desktop.R
import com.gshp.desktop.data.workingday.WorkingDayRepositoryImpl
import com.gshp.desktop.databinding.WorkingDayFragmentBinding
import com.gshp.desktop.domain.workingday.WorkingDayUseCase
import com.gshp.desktop.framework.workingday.WorkingDayDataSourceImpl
import com.gshp.desktop.presentation.utils.autoCleared
import com.raer.utils.enums.CustomToastType
import com.raer.utils.getViewModel
import com.raer.utils.makeCustomToastShort
import com.raer.utils.setOnDebounceClickListener
import com.raer.utils.showToast

class WorkingDayFragment : Fragment() {
    private val viewModel by lazy {
        getViewModel {
            WorkingDayViewModel(WorkingDayUseCase(WorkingDayRepositoryImpl(WorkingDayDataSourceImpl(DesktopApplication.appPreferenceRepository))))
        }
    }

    private var binding by autoCleared<WorkingDayFragmentBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = WorkingDayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        if (viewModel.getWorkingDayStatus().not()) {
            binding.tvWorkdayTitle.text = getString(R.string.text_begin_workday_title)
            binding.tvWorkdayMessage.text = getString(R.string.text_begin_workday)
            binding.btnActionWorkday.text = getString(R.string.text_begin_workday_title)
        } else {
            binding.tvWorkdayTitle.text = getString(R.string.text_end_workday_title)
            binding.tvWorkdayMessage.text = getString(R.string.text_end_workday)
            binding.btnActionWorkday.text = getString(R.string.text_end_workday_title)
        }

        binding.btnActionWorkday.setOnDebounceClickListener {
            viewModel.toggleWorkingDayStatus()
            requireContext().showToast("New workday status = ${viewModel.getWorkingDayStatus()}")
        }
    }
}