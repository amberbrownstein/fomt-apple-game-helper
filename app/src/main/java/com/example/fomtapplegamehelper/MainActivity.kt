package com.example.fomtapplegamehelper

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity

val validArrangements = listOf("SHA", "SAH", "ASH", "AHS", "HAS", "HSA")

class MainActivity : ComponentActivity() {
    private var previousArrangement = validArrangements[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickUpdateArrangement(sequenceButton: View) {
        val currArrangementEditText = findViewById<EditText>(R.id.editArrangementText)
        val currArrangementText = currArrangementEditText.getText().toString()
        val currArrangementKey = validArrangements.indexOf(currArrangementText)
        val rawSequenceKey = sequenceButton.tag

        require(rawSequenceKey != null)
        val parsedSequenceKey = rawSequenceKey.toString().toInt()
        var newArrangementKey: Int

        if (currArrangementKey % 2 == 0) {
            newArrangementKey = currArrangementKey + parsedSequenceKey

            if (newArrangementKey > 5)
                newArrangementKey -= 6
        } else {
            newArrangementKey = currArrangementKey - parsedSequenceKey

            if (newArrangementKey < 0)
                newArrangementKey += 6
        }

        previousArrangement = currArrangementText
        currArrangementEditText.setText(validArrangements[newArrangementKey])
    }

    fun onClickUndo(undoButton: View) {
        val currArrangementEditText = findViewById<EditText>(R.id.editArrangementText)
        currArrangementEditText.setText(previousArrangement)
    }

    fun onClickReset(resetButton: View){
        val currArrangementEditText = findViewById<EditText>(R.id.editArrangementText)
        val currArrangementText = currArrangementEditText.getText().toString()
        previousArrangement = currArrangementText
        currArrangementEditText.setText(validArrangements[0])
    }
}