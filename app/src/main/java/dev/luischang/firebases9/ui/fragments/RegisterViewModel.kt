package dev.luischang.firebases9.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.luischang.firebases9.database.CustomerEntity
import dev.luischang.firebases9.database.CustomerRepository

class RegisterViewModel(application: Application)
    : AndroidViewModel(application) {

        private var repository = CustomerRepository(application)

    fun saveCustomer(customerEntity: CustomerEntity){
        repository.insert(customerEntity)
    }
}