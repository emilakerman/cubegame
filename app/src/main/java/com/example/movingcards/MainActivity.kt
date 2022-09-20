package com.example.movingcards
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var button1 : Button
    lateinit var textView2: TextView
    var points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        textView2 = findViewById(R.id.textView2)


        ////INITIAL ANIMATION
        val path = Path().apply {
            arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
        }
        ObjectAnimator.ofFloat(button1, View.X, View.Y, path).apply {
            duration = 2000
            start()
        }
        ////BUTTON1 CLICK
        button1.setOnClickListener {
            vibrate()
            changeColor()
            points++
            val points2 = findViewById<TextView>(R.id.points)
            points2.text = points.toString()
                when (points) {
                    10 -> textView2.text = "LEVEL 2"
                    20 -> textView2.text = "LEVEL 3"
                    35 -> textView2.text = "LEVEL 4"
                    50 -> textView2.text = "LEVEL 5 (you win at 70 points)"
                    70 -> textView2.text = "YOU WIN"
            }
        }
    }
    fun randomPosition() = Random.nextInt(-500, 500).toFloat()
    fun vibrate() {
        button1.animate()
            .translationX(randomPosition())
            .translationY(randomPosition())
            .setDuration(500)
            .withEndAction(::vibrate) //IF YOU REMOVE THIS IT WILL ONLY MOVE ONCE PER CLICK
            .start()
    }
    fun changeColor() {
        val color: Int =
        Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        button1.setBackgroundColor(color)
    }
}