package com.example.calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.calculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. adding binding class
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //3. clearing input and output
        binding.btnClear.setOnClickListener {
            binding.input.text=""
            binding.output.text=""
        }

        //taking input
        binding.btnLeftBracket.setOnClickListener {
            binding.input.text = addToInputText("(")
        }
         binding.btnRightBracket.setOnClickListener {
            binding.input.text = addToInputText(")")
        }
         binding.btn0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }
         binding.btn1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }
         binding.btn2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }
         binding.btn3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }
         binding.btn4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }
         binding.btn5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }
         binding.btn6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }
         binding.btn7.setOnClickListener {
            binding.input.text = addToInputText("7")
        }
         binding.btn8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }
         binding.btn9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }

        //4. operation to do on the given input
         binding.btnAdd.setOnClickListener {
            binding.input.text = addToInputText("+")
        }
         binding.btnSub.setOnClickListener {
            binding.input.text = addToInputText("-")
        }
         binding.btnMultiply.setOnClickListener {
            binding.input.text = addToInputText("×")  // ALT + 0215
        }
         binding.btnDivide.setOnClickListener {
            binding.input.text = addToInputText(" ÷")  // ALT + 0247
        }
         binding.btnDecimal.setOnClickListener {
            binding.input.text = addToInputText(".")
        }


        //5. equating result
        binding.btnEquals.setOnClickListener {
            showResult()
        }
    }



    //2. function for adding input to the text
    private fun addToInputText(buttonValue: String): String{
return "${binding.input.text}$buttonValue"
    }

    // 6. function for getting input expression
/*    private fun getInputExpression():String{
        var expression= binding.input.text.replace(Regex("÷",),"/")
            expression= binding.input.text.replace(Regex("×",),"*")
        return expression
    }*/
    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/").replace(Regex("×"), "*")
        return expression
    }


    //7. function to show and calculate result
    // add implementation dependence:       implementation("org.mariuszgromada.math:MathParser.org.mXparser:4.4.2")
    private fun showResult() {
        try {
            val expression=getInputExpression()
            val result= Expression(expression).calculate()

            if(result.isNaN()){
                //show error msg
                binding.output.text="Error"
                binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
            }else{
                //show result
                binding.output.text= DecimalFormat("0.######").format(result).toString()
                binding.output.setTextColor(ContextCompat.getColor(this,R.color.green))
            }

        }
        catch(e:Exception){
            // showing error message
            binding.output.text="Error"
            binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
        }

    }
}