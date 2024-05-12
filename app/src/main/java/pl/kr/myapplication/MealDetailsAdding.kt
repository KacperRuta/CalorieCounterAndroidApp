package pl.kr.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.databinding.ActivityMealDetailsAddingBinding

class MealDetailsAdding : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailsAddingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details_adding)
    }
}