package com.sifat.expensetracker

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.sifat.expensetracker.databinding.ActivityAddTransactionBinding

class AddTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.labelInput.addTextChangedListener {
            if (it!!.count() > 0){
                binding.labelLayout.error = null
            }
        }
        binding.amountInput.addTextChangedListener {
            if (it!!.count() > 0){
                binding.AmountLayout.error = null
            }
        }

        binding.submit.setOnClickListener {
            val label = binding.labelInput.text.toString()
            val amount = binding.amountInput.text.toString().toDoubleOrNull()

            if (label.isEmpty()){
                binding.labelLayout.error = "Please enter valid Number"
            }
            if (amount == null){
                binding.AmountLayout.error = "Please enter valid Number"
            }
        }

        binding.closeBtn.setOnClickListener {
            binding.labelInput.text?.clear()
            binding.amountInput.text?.clear()
            binding.DescriptionInput.text?.clear()
        }
    }
}