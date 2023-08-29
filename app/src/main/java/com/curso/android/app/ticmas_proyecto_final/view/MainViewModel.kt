package com.curso.android.app.ticmas_proyecto_final.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.android.app.ticmas_proyecto_final.model.Message
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    val message: LiveData<Message> get() = _message
    val inputErrorText1: LiveData<String?> get() = _inputErrorText1
    val inputErrorText2: LiveData<String?> get() = _inputErrorText2


    private var _message = MutableLiveData<Message>(Message(""))
    private var _inputErrorText1 = MutableLiveData<String?>(null)
    private var _inputErrorText2 = MutableLiveData<String?>(null)


    fun validateString(text: String, firstInput: Boolean): Boolean {
        return if(text.isNotEmpty()) {
            if (firstInput) {
                _inputErrorText1.value = null
            } else {
                _inputErrorText2.value = null
            }
                true
            } else {
                if(firstInput){
                    _inputErrorText1.value = "El campo no puede estar vacío"

                } else {
                    _inputErrorText2.value = "El campo no puede estar vacío"
                }
                false
            }
    }

    fun compareStrings(firstInput: String, secondInput: String){
        val newMessage: String = when(firstInput == secondInput) {
            true -> "Son textos iguales"
            false -> "Son textos distintos"
        }
        updateMessage(newMessage)
    }

    private fun updateMessage(newMessage: String){
        viewModelScope.launch {
        _message.value = Message(newMessage)
        }
    }
}