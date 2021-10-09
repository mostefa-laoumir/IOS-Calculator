/*package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buNumberEvent(view: View) {
        if (isNewOp){
            editText.setText("")
        }
        isNewOp=false
        val buSelect= view as Button
        var buClicked:String = editText.text.toString()
        when(buSelect.id){
            R.id.n0->buClicked+="0"
            R.id.n1->buClicked+="1"
            R.id.n2->buClicked+="2"
            R.id.n3->buClicked+="3"
            R.id.n4->buClicked+="4"
            R.id.n5->buClicked+="5"
            R.id.n6->buClicked+="6"
            R.id.n7->buClicked+="7"
            R.id.n8->buClicked+="8"
            R.id.n9->buClicked+="9"
            R.id.ac->buClicked="0"
            R.id.plsmn->buClicked=(buClicked.toString().toDouble()*-1).toString()
            R.id.com->buClicked+="."
            R.id.com->buClicked+="."
            R.id.com->buClicked+="."
            return R.id.com
}
        editText.setText(buClicked)
        editText.setText(buClicked)editText.setText(buClicked)
        editText.setText(buClicked)
    }
enum class Bit {
  ZERO, ONE
}

val numericValue = when (getRandomBit()) {
    Bit.ZERO -> 0
    Bit.ONE -> 1
    // the 'else' clause is not required because all cases are covered
}
    var op="*"
    var oldNumber = ""
    var isNewOp = true
    fun buOpEvent(view:View){
        val buSelect= view as Button
        when(buSelect.id){
            R.id.mul -> {
                op="*"
            }
            R.id.pls -> {
                op="+"

            }
            R.id.min -> {
                op="-"

            }
            R.id.div ->{
                op="/"

            }
        }
        oldNumber = editText.text.toString()
        isNewOp=true
    }
    fun buEqualEvent(view: View){
        val newNumber = editText.text.toString()
        var finalNumber:Double? = null
        when(op){
            "*"->{
                finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            }
            "/"->{
                finalNumber = oldNumber.toDouble() / newNumber.toDouble()
            }
            "+"->{
                finalNumber = oldNumber.toDouble() + newNumber.toDouble()

            }
            "-"->{
                finalNumber = oldNumber.toDouble() - newNumber.toDouble()

            }
        }
        editText.setText(finalNumber.toString())
        isNewOp=true
    }
    fun buPercent(view: View){
        val number:Double = editText.text.toString().toDouble()/100
        editText.setText(number.toString() + "%")
        isNewOp=true

    }
}*/





package com.javahelps.kotlincalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    // TextView used to display the input and output
    lateinit var txtInput: TextView

    // Represent whether the lastly pressed key is numeric or not
    var lastNumeric: Boolean = false

    // Represent that current state is in error or not
    var stateError: Boolean = false

    // If true, do not allow to add another DOT
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtInput = findViewById(R.id.txtInput)
    }

    /**
     * Append the Button.text to the TextView
     */
    fun onDigit(view: View) {
        if (stateError) {
            // If current state is Error, replace the error message
            txtInput.text = (view as Button).text
            stateError = false
        } else {
            // If not, already there is a valid expression so append to it
            txtInput.append((view as Button).text)
        }
        // Set the flag
        lastNumeric = true
    }

    /**
     * Append . to the TextView
     */
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !stateError && !lastDot) {
            txtInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    /**
     * Append +,-,*,/ operators to the TextView
     */
    fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            txtInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false    // Reset the DOT flag
        }
    }


    /**
     * Clear the TextView
     */
    fun onClear(view: View) {
        this.txtInput.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    /**
     * Calculate the output using Exp4j
     */
    fun onEqual(view: View) {
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.
        if (lastNumeric && !stateError) {
            // Read the expression
            val txt = txtInput.text.toString()
            // Create an Expression (A class from exp4j library)
            val expression = ExpressionBuilder(txt).build()
            try {
                // Calculate the result and display
                val result = expression.evaluate()
                txtInput.text = result.toString()
                lastDot = true // Result contains a dot
            } catch (ex: ArithmeticException) {
                // Display an error message
                txtInput.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }
}
