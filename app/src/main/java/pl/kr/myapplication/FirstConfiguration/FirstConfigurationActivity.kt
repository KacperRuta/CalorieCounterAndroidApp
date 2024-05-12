package pl.kr.myapplication.FirstConfiguration

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.ViewModels.ConfigurationViewModel
import pl.kr.myapplication.databinding.ActivityFirstConfigurationBinding


class FirstConfigurationActivity : AppCompatActivity() {

    private val configVm by viewModels<ConfigurationViewModel>()


    // Minimalne oraz maksymalne dane wyjściowe:
    val min_age = 1
    val max_age = 99
    val min_height = 50
    val max_height = 250
    val min_weight = 20.0
    val max_weight = 500.0
    ////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////
    var age: Int = 0
    var height: Int = 0
    var weight: Double = 0.0
    var gender: Int = 0
    var activity_lvl: Int = 0
    var weight_goal: Double = 0.0
    ////////////////////////////////////////////////////////////

    private lateinit var binding: ActivityFirstConfigurationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ////////////////////////////////////////////////////////////
        // Obsługa dodawania wieku
        ////////////////////////////////////////////////////////////


        // Dodaj TextChangedListener do EditText, aby monitorować zmiany w tekście
        binding.editAge.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Sprawdź, czy wprowadzona liczba mieści się w prawidłowym zakresie
                val text = s.toString()
                if (text.isNotEmpty()) {
                    val enteredAge = text.toInt()
                    if (enteredAge in min_age until max_age) {
                        // Wprowadzona liczba mieści się w zakresie, ustaw kolor na zielony
                        binding.editAge.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                        age = enteredAge
                    } else {
                        // Wprowadzona liczba nie mieści się w zakresie, ustaw kolor na czerwony
                        binding.editAge.backgroundTintList = ColorStateList.valueOf(Color.RED)
                    }
                } else {
                    // Pole tekstowe jest puste, ustaw kolor na czerwony
                    binding.editAge.backgroundTintList = ColorStateList.valueOf(Color.RED)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        ////////////////////////////////////////////////////////////
        // Obsługa dodawania wzrostu
        ////////////////////////////////////////////////////////////

        binding.editHeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Sprawdź, czy wprowadzona liczba mieści się w prawidłowym zakresie
                val text = s.toString()
                if (text.isNotEmpty()) {
                    val enteredHeight = text.toInt()
                    if (enteredHeight in min_height until max_height) {
                        // Wprowadzona liczba mieści się w zakresie, ustaw kolor na zielony
                        binding.editHeight.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                        height = enteredHeight
                    } else {
                        // Wprowadzona liczba nie mieści się w zakresie, ustaw kolor na czerwony
                        binding.editHeight.backgroundTintList = ColorStateList.valueOf(Color.RED)
                    }
                } else {
                    // Pole tekstowe jest puste, ustaw kolor na czerwony
                    binding.editHeight.backgroundTintList = ColorStateList.valueOf(Color.RED)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        ////////////////////////////////////////////////////////////
        // Obsługa dodawania wagi
        ////////////////////////////////////////////////////////////

        binding.editWeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Sprawdź, czy wprowadzona liczba mieści się w prawidłowym zakresie
                val text = s.toString()
                if (text.isNotEmpty()) {
                    val enteredWeight = text.toDouble()
                    if (enteredWeight > min_weight && enteredWeight < max_weight) {
                        // Wprowadzona liczba mieści się w zakresie, ustaw kolor na zielony
                        binding.editWeight.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                        weight = enteredWeight
                    } else {
                        // Wprowadzona liczba nie mieści się w zakresie, ustaw kolor na czerwony
                        binding.editWeight.backgroundTintList = ColorStateList.valueOf(Color.RED)
                    }
                } else {
                    // Pole tekstowe jest puste, ustaw kolor na czerwony
                    binding.editWeight.backgroundTintList = ColorStateList.valueOf(Color.RED)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        ////////////////////////////////////////////////////////////
        // Obsługa wyboru plci
        ////////////////////////////////////////////////////////////

        binding.buttonChooseMan.setOnClickListener{
            binding.buttonChooseMan.setTextColor(Color.BLACK)
            binding.buttonChooseWoman.setTextColor(Color.BLACK)
            if(gender == 0){
                binding.buttonChooseMan.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                gender = 1
            }
            else if(gender == 2){
                binding.buttonChooseMan.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                gender = 1
                binding.buttonChooseWoman.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            }
        }

        binding.buttonChooseWoman.setOnClickListener{
            binding.buttonChooseMan.setTextColor(Color.BLACK)
            binding.buttonChooseWoman.setTextColor(Color.BLACK)
            if(gender == 0){
                binding.buttonChooseWoman.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                gender = 2
            }
            else if(gender == 1){
                binding.buttonChooseWoman.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                gender = 2
                binding.buttonChooseMan.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            }
        }

        ////////////////////////////////////////////////////////////
        // Obsługa wyboru wagi docelowej
        ////////////////////////////////////////////////////////////

        binding.editWeightGoal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Sprawdź, czy wprowadzona liczba mieści się w prawidłowym zakresie
                val text = s.toString()
                if (text.isNotEmpty()) {
                    val enteredWeight = text.toDouble()
                    if (enteredWeight > min_weight && enteredWeight < max_weight) {
                        // Wprowadzona liczba mieści się w zakresie, ustaw kolor na zielony
                        binding.editWeightGoal.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                        weight_goal = enteredWeight
                    } else {
                        // Wprowadzona liczba nie mieści się w zakresie, ustaw kolor na czerwony
                        binding.editWeightGoal.backgroundTintList = ColorStateList.valueOf(Color.RED)
                    }
                } else {
                    // Pole tekstowe jest puste, ustaw kolor na czerwony
                    binding.editWeightGoal.backgroundTintList = ColorStateList.valueOf(Color.RED)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        ////////////////////////////////////////////////////////////
        // Obsługa wyboru aktywnosci fiz
        ////////////////////////////////////////////////////////////

        binding.buttonActivityLvl0.setOnClickListener {
            binding.buttonActivityLvl0.setTextColor(Color.BLACK)
            binding.buttonActivityLvl1.setTextColor(Color.BLACK)
            binding.buttonActivityLvl2.setTextColor(Color.BLACK)
            binding.buttonActivityLvl3.setTextColor(Color.BLACK)
            if(activity_lvl == 0){
                binding.buttonActivityLvl0.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                activity_lvl = 1
            }
            if(activity_lvl > 1){
                binding.buttonActivityLvl0.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                if(activity_lvl == 2){
                    binding.buttonActivityLvl1.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(activity_lvl == 3){
                    binding.buttonActivityLvl2.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(activity_lvl == 4){
                    binding.buttonActivityLvl3.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}

                activity_lvl = 1
            }
        }

        binding.buttonActivityLvl1.setOnClickListener {
            binding.buttonActivityLvl0.setTextColor(Color.BLACK)
            binding.buttonActivityLvl1.setTextColor(Color.BLACK)
            binding.buttonActivityLvl2.setTextColor(Color.BLACK)
            binding.buttonActivityLvl3.setTextColor(Color.BLACK)
            if(activity_lvl == 0){
                binding.buttonActivityLvl1.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                activity_lvl = 2
            }
            if(activity_lvl == 1 || activity_lvl == 3 || activity_lvl == 4){
                binding.buttonActivityLvl1.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                if(activity_lvl == 1){
                    binding.buttonActivityLvl0.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(activity_lvl == 3){
                    binding.buttonActivityLvl2.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(activity_lvl == 4){
                    binding.buttonActivityLvl3.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}

                activity_lvl = 2
            }
        }

        binding.buttonActivityLvl2.setOnClickListener {
            binding.buttonActivityLvl0.setTextColor(Color.BLACK)
            binding.buttonActivityLvl1.setTextColor(Color.BLACK)
            binding.buttonActivityLvl2.setTextColor(Color.BLACK)
            binding.buttonActivityLvl3.setTextColor(Color.BLACK)
            if(activity_lvl == 0){
                binding.buttonActivityLvl2.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                activity_lvl = 3
            }
            if(activity_lvl == 1 || activity_lvl == 2 || activity_lvl == 4){
                binding.buttonActivityLvl2.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                if(activity_lvl == 2){
                    binding.buttonActivityLvl1.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(activity_lvl == 1){
                    binding.buttonActivityLvl0.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(activity_lvl == 4){
                    binding.buttonActivityLvl3.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}

                activity_lvl = 3
            }
        }

        binding.buttonActivityLvl3.setOnClickListener {
            binding.buttonActivityLvl0.setTextColor(Color.BLACK)
            binding.buttonActivityLvl1.setTextColor(Color.BLACK)
            binding.buttonActivityLvl2.setTextColor(Color.BLACK)
            binding.buttonActivityLvl3.setTextColor(Color.BLACK)
            if(activity_lvl == 0){
                binding.buttonActivityLvl3.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                activity_lvl = 4
            }
            if(activity_lvl == 1 || activity_lvl == 2 || activity_lvl == 3){
                binding.buttonActivityLvl3.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                if(activity_lvl == 2){
                    binding.buttonActivityLvl1.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(activity_lvl == 1){
                    binding.buttonActivityLvl0.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(activity_lvl == 3){
                    binding.buttonActivityLvl2.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}

                activity_lvl = 4
            }
        }

        ////////////////////////////////////////////////////////////
        // Obsługa przejścia dalej i pomocy w wyborze
        ////////////////////////////////////////////////////////////

        // Sprawdzanie poprawności
        binding.buttonProceedPressed.setOnClickListener {
            if (age < min_age || age > max_age) {
                binding.editAge.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if (height < min_height || height > max_height) {
                binding.editHeight.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if (weight < min_weight || weight > max_weight) {
                binding.editWeight.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if (weight_goal < min_weight || weight_goal > max_weight) {
                binding.editWeightGoal.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if (gender == 0) {
                binding.buttonChooseMan.setTextColor(Color.RED)
                binding.buttonChooseWoman.setTextColor(Color.RED)
            }
            if (activity_lvl == 0) {
                binding.buttonActivityLvl0.setTextColor(Color.RED)
                binding.buttonActivityLvl1.setTextColor(Color.RED)
                binding.buttonActivityLvl2.setTextColor(Color.RED)
                binding.buttonActivityLvl3.setTextColor(Color.RED)
            }
            // Warunek przejscia do nastepnej konfiguracji
            if (age in (min_age .. max_age) && weight in (min_weight .. max_weight) &&
                height in (min_height .. max_height) &&
                gender != 0 && activity_lvl != 0 &&
                weight_goal in(min_weight .. max_weight) &&
                gender != 0 && activity_lvl != 0) {
                configVm.age = age
                configVm.gender = gender
                configVm.weight = weight
                configVm.weight_goal = weight_goal
                configVm.height = height
                configVm.activity_lvl = activity_lvl
                configVm.updateConfig()


                val explicitIntent = Intent(applicationContext,SecondStepConfiguration::class.java)
                startActivity(explicitIntent)
                finish()
            }
        }
    }
}
