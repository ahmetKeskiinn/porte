package com.ahmetkeskin.porte

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    lateinit var binding: B
    abstract fun onInitDataBinding()
    private var isCreatedOnce = false
    private var viewModelStoreOwner: ViewModelStoreOwner? = null

    //private val viewModel by viewModels<VM>()
    lateinit var viewModel: VM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vmClass = this.javaClass.findGenericWithType<VM>(ViewModel::class.java)
        if (vmClass != null)
            viewModel = ViewModelProvider(this).get(vmClass)

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        onInitDataBinding()
        return binding.root
    }

    private fun hideKeyboardOutsideOfEditText(ev: MotionEvent?) {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            if (isVisible) {
                val v: View? = requireActivity().currentFocus
                if (v is EditText) {
                    val outRect = Rect()
                    v.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                        v.clearFocus()
                        val imm: InputMethodManager =
                            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                    }
                }
            }
        }
    }

    open fun showToast(body: String?) {
        Toast.makeText(context, body, Toast.LENGTH_LONG).show()
    }

    /**
     * Call this method before to use the vm (ViewModel)
     */
    fun setViewModelStoreOwner(owner: ViewModelStoreOwner) {
        viewModelStoreOwner = owner
    }

    private fun getViewModelStoreOwner(): ViewModelStoreOwner {
        return viewModelStoreOwner ?: requireActivity()
    }

    private var progressDialog: BaseProgressDialog? = null

    open fun showProgress() {
        progressDialog = BaseProgressDialog()
        progressDialog?.show(childFragmentManager, "PROGRESS")
    }

    open fun hideProgress() {
        progressDialog?.dismiss()
    }
}