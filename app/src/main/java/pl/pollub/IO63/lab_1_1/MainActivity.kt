package pl.pollub.IO63.lab_1_1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import pl.pollub.IO63.lab_1_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        addListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addListeners()
    {
        val nameTextField = findViewById<EditText>(R.id.nameTextField)
        val surnameTextField = findViewById<EditText>(R.id.surnameTextField)
        val marksField = findViewById<EditText>(R.id.marksField)
        val button = findViewById<Button>(R.id.ocenyButton)
        val fields = listOf(nameTextField, surnameTextField, marksField)
        for(field in fields)
        {
            field.addTextChangedListener { text ->
                if(text.toString().isNotEmpty())
                {
                    if(field == marksField)
                    {
                        val value = field.text.toString().toIntOrNull()
                        if(value != null)
                        {
                            if(value < 5 || value > 15)
                            {
                                field.error = "Liczba ocen jest spoza zakresu"
                                Toast.makeText(applicationContext, "Liczba ocen jest spoza zakresu", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                else
                {
                    if(field == nameTextField)
                    {
                        nameTextField.error = "Imię nie może być puste"
                        Toast.makeText(applicationContext, "Imię nie może być puste", Toast.LENGTH_SHORT).show()
                    }
                    if(field == surnameTextField)
                    {
                        surnameTextField.error = "Nazwisko nie może być puste"
                        Toast.makeText(applicationContext, "Nazwisko nie może być puste", Toast.LENGTH_SHORT).show()
                    }
                }
                for(field2 in fields)
                {
                    if(field2.text.toString().isNotEmpty())
                        button.visibility = View.VISIBLE
                    else
                    {
                        button.visibility = View.GONE
                        break
                    }
                }
            }
        }

    }

}