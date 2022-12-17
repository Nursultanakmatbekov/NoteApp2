package com.example.noteapp.ui.fragments.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentSingUpBinding
import com.example.noteapp.utils.PreferenceHelper
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class SingUpFragment : Fragment() {

    private lateinit var binding: FragmentSingUpBinding
    private var auth: FirebaseAuth? = null

    private var strokeVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSingUpBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        binding.inputCode.isVisible = false
        PreferenceHelper.init(requireContext())
    }

    private fun setOnClickListeners() = with(binding) {
        getCode.setOnClickListener {
            startPhoneNumberVerification(edPhone.text.toString())
            getCode.isVisible = false
            inputCode.isVisible = true
        }

        inputCode.setOnClickListener {
            verifyPhoneNumberWithCode(strokeVerificationId, edCode.text.toString())
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val optios = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(optios)
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        singInWithPhoneAuthCredential(credential)
    }

    private var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            singInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.e("tag", "error")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            strokeVerificationId = verificationId
            resendToken = token
        }
    }

    private fun singInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.noteFragment)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(requireContext(), "Register is not", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
    }
}