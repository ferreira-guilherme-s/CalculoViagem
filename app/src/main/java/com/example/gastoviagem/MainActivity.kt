package com.example.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    //ViewBinding
    //Aqui usamos o lateinit para dizer ao kotlin esperar e só instanciar essa variável binding quando eu chamá-la
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botaoCalcular.setOnClickListener(this)
    }

    private fun isValid(): Boolean {
        return (binding.distancia.text.toString() != "" &&
                binding.preco.text.toString() != "" &&
                binding.autonomia.text.toString() != "" &&
                binding.distancia.text.toString().toFloat() != 0f &&
                binding.preco.text.toString().toFloat() != 0f &&
                binding.autonomia.text.toString().toFloat() != 0f)
    }

    private fun calculate() {
        if (isValid()) {
            val distance = binding.distancia.text.toString().toFloat()
            val price = binding.preco.text.toString().toFloat()
            val autonomy = binding.autonomia.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy
            val totalValueStr = "R$ ${"%.2f".format(totalValue)}"

            binding.valor.text = totalValueStr
        }else{
            //Toast notification
            Toast.makeText(this, R.string.required_field, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.botaoCalcular) {
            calculate()
        }
    }
}