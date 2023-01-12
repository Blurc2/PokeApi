package com.gshp.desktop.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gshp.desktop.BuildConfig
import com.gshp.desktop.DesktopApplication
import com.gshp.desktop.R
import com.gshp.desktop.data.login.LoginRepositoryImpl
import com.gshp.desktop.databinding.LoginFragmentBinding
import com.gshp.desktop.domain.login.LoginUseCase
import com.gshp.desktop.framework.login.LoginDataSourceImpl
import com.gshp.desktop.presentation.utils.autoCleared
import com.raer.utils.LoadingUtils.Companion.hideLottieLoader
import com.raer.utils.LoadingUtils.Companion.showLottieLoader
import com.raer.utils.enums.CustomToastType
import com.raer.utils.getViewModel
import com.raer.utils.makeCustomToastShort
import com.raer.utils.setOnDebounceClickListener

class LoginFragment : Fragment() {
    private val serviceLocator by lazy { DesktopApplication.serviceLocator }
    private val viewModel by lazy {
        getViewModel {
            LoginViewModel(
                LoginUseCase(LoginRepositoryImpl(LoginDataSourceImpl(serviceLocator.provideLoginService()))),
                DesktopApplication.appPreferenceRepository
            )
        }
    }

    private var binding by autoCleared<LoginFragmentBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUi()
    }

    private fun setupUi() {
        binding.tvVersion.text = getString(R.string.text_version, BuildConfig.VERSION_NAME)

        binding.btnLogin.setOnDebounceClickListener {
//            viewModel.login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWorkingDayFragment())
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            onError.observe(viewLifecycleOwner) {
                requireContext().makeCustomToastShort(it, CustomToastType.ERROR)
            }
            onDisplayLoader.observe(viewLifecycleOwner) {
                toggleLoader(it)
            }
        }
    }

    private fun toggleLoader(status: Boolean) {
        if (status) {
            requireContext().showLottieLoader()
        } else {
            requireContext().hideLottieLoader()
        }
    }
}