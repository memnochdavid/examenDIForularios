package com.example.examendi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examendi.databinding.Activity1Binding
import com.google.android.material.datepicker.MaterialDatePicker


class Activity1 : AppCompatActivity() {
    lateinit var binding: Activity1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var nombre=""
        var entrenador=""
        var tipo=""
        var estatura=0.0
        var fechaCaptura=""
        var pokemon=Pokemon()


        binding = Activity1Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val tiposPoke= listOf("Agua", "Fuego", "Electrico", "Planta","Psíquico")

        val sharedPref = getSharedPreferences("poke_datos", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        //spinner
        val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tiposPoke)
        binding.spinnerTipo.adapter = adapter
        binding.spinnerTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tipo = parent?.getItemAtPosition(position) as String
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                tipo=""
            }
        }

        //fecha
        binding.textEditFecha.setOnClickListener {
            val builder= MaterialDatePicker.Builder.datePicker()
            val picker=builder.build()

            picker.addOnPositiveButtonClickListener {
                binding.textEditFecha.setText(picker.headerText)
                fechaCaptura=picker.headerText//guarda el string con la fecha
            }
            picker.show(supportFragmentManager,"wtf")
        }

        //estatura
        binding.textEditEstatura.setOnClickListener{
            estatura=binding.textEditEstatura.text.toString().toDouble()
        }



        //boton atras
        val intent_atras= Intent(this, MainActivity::class.java)
        binding.atras.setOnClickListener {
            startActivity(intent_atras)
        }

        binding.add.setOnClickListener {
            if(!validaNombre(nombre)) binding.textEditNombre.error="wtf - Nombre"
            else binding.textInputNombre.error=null
            if(!validaEntrenador(entrenador)) binding.textEditEntrenador.error="wtf - Nombre"
            else binding.textInputEntrenador.error=null
            if(fechaCaptura=="") binding.textEditFecha.error="wtf - Fecha"
            else binding.textInputFecha.error=null


            pokemon=Pokemon(nombre,entrenador,estatura,tipo,fechaCaptura)

            editor.putString("nombre", pokemon.nombre)
            editor.putString("entrenador", pokemon.entrenador)
            editor.putFloat("estatura", pokemon.estatura.toFloat())
            editor.putString("tipo", pokemon.tipo)
            editor.putString("fecha", pokemon.fechaCaptura)
            editor.apply()

        }




    }
}

fun validaNombre(input:String):Boolean{
    val regex = Regex("^([A-Z][a-zA-Z]{2,}|[a-z][a-zA-Z]{2,})\$")//no funciona
    return input.matches(regex)
}
fun validaEntrenador(input:String):Boolean{
    val regex = Regex("^(?=.*[aeiouAEIOU]).{1,25}\$")//no funciona
    return input.matches(regex)
}