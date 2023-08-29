package com.curso.android.app.ticmas_proyecto_final.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.curso.android.app.ticmas_proyecto_final.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.message.observe(this) {
            println("Mensaje recibido: $it" )
            binding.textResult.text = it.message
        }

        binding.compareButton.setOnClickListener {
            val inputText1Validated = mainViewModel.validateString(binding.textInput1.text.toString(), true)
            val inputText2Validated = mainViewModel.validateString(binding.textInput2.text.toString(), false)
            if ( inputText1Validated && inputText2Validated ) {
                mainViewModel.compareStrings(
                    binding.textInput1.text.toString(),
                    binding.textInput2.text.toString()
                )
            }
        }

        mainViewModel.inputErrorText1.observe(this) { errorMessage ->
            // Muestra el mensaje de error en la interfaz de usuario
            binding.textInput1.error = errorMessage
        }

        mainViewModel.inputErrorText2.observe(this) { errorMessage ->
            // Muestra el mensaje de error en la interfaz de usuario
            binding.textInput2.error = errorMessage
        }
    }
}