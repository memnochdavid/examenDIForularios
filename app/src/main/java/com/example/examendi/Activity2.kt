package com.example.examendi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examendi.databinding.Activity1Binding
import com.example.examendi.databinding.Activity2Binding
import kotlin.io.path.name

class Activity2 : AppCompatActivity() {
    lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var lista_poke: MutableList<Pokemon> = mutableListOf()
        val sharedPref = getSharedPreferences("poke_datos", Context.MODE_PRIVATE)
        var nombreRecuperado = sharedPref.getString("nombre", "")!!
        var entrenadorRecuperado = sharedPref.getString("entrenador", "")!!
        var estaturaRecuperado = sharedPref.getString("estaura", "")!!.toDouble()
        var tipoRecuperado = sharedPref.getString("tipo", "")!!
        var fechaRecuperado = sharedPref.getString("fecha", "")!!

        var pokemonRecuperado = Pokemon(nombreRecuperado, entrenadorRecuperado, estaturaRecuperado, tipoRecuperado, fechaRecuperado)
        lista_poke+=pokemonRecuperado


        for (pokemon in lista_poke) {
            val pokemonView = LayoutInflater.from(this).inflate(R.layout.pokemon_item, binding.pokemon, false)
/*
            binding.nombre.text = pokemonRecuperado.nombre
            binding.tipo.text = pokemonRecuperado.tipo
            binding.estatura.text = pokemonRecuperado.estatura.toString()
            binding.fecha.text = pokemonRecuperado.fechaCaptura*/

            binding.pokemon.addView(pokemonView)
        }





    }
}