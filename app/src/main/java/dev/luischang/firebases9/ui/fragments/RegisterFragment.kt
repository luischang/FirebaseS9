package dev.luischang.firebases9.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import dev.luischang.firebases9.R
import dev.luischang.firebases9.database.CustomerEntity

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View= inflater.inflate(R.layout.fragment_register, container, false)

        val etFirstName : EditText = view.findViewById(R.id.etFirstName)
        val etLastName: EditText = view.findViewById(R.id.etLastName)
        val etPhoneNumber: EditText = view.findViewById(R.id.etPhoneNumber)
        val btSaveCustomer: Button = view.findViewById(R.id.btSaveCustomer)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        btSaveCustomer.setOnClickListener {
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()

            val customerEntity = CustomerEntity(firstName,lastName,phoneNumber)
            viewModel.saveCustomer(customerEntity)
        }

        return view
    }
}