package com.example.calculator

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
        }
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
}
