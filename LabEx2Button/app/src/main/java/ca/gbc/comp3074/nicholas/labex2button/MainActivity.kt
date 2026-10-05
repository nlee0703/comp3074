package ca.gbc.comp3074.nicholas.labex2button

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var count = 0
    private var stepSize = 1          // default behaviour: +/- 1

    private lateinit var outputText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        outputText = findViewById(R.id.outputText)
        val addButton: Button = findViewById(R.id.addButton)
        val subtractButton: Button = findViewById(R.id.subtractButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val stepButton: Button = findViewById(R.id.stepButton)

        // Restore state after rotation
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0)
            stepSize = savedInstanceState.getInt("stepSize", 1)
        }
        updateLabel()

        addButton.setOnClickListener {
            count += stepSize
            updateLabel()
        }

        subtractButton.setOnClickListener {
            count -= stepSize
            updateLabel()
        }

        resetButton.setOnClickListener {
            count = 0
            stepSize = 1              // back to default behaviour
            updateLabel()
        }

        stepButton.setOnClickListener {
            stepSize = 2              // new behaviour: +/- 2
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
        outState.putInt("stepSize", stepSize)
    }

    private fun updateLabel() {
        outputText.text = count.toString()
    }
}