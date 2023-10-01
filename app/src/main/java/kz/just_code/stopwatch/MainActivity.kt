package kz.just_code.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import kz.just_code.stopwatch.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
private var seconds: Int =0
    private var running: Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e(this.javaClass.name, ">>> onCreate")
        with(binding){
            pauseButton.setOnClickListener {
                pauseClick()
            }
            startButton.setOnClickListener {
                startButton()
            }
            resetButton.setOnClickListener {
                resetClick()
            }
        }
        savedInstanceState?.let {
            seconds = it.getInt(State.SECONDS.name)
            running = it.getBoolean(State.RUNNING.name)
        }

        runTimer()

    }
    private fun runTimer(){
        val handler = Handler(Looper.getMainLooper())
      handler.post(object: Runnable{
          override fun run() {
              val minutes =( seconds % 3600)/60
              val time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds%60)
              binding.timeView.text = time

              if(running) seconds++
              handler.postDelayed(this, 1000)
          }
      })
    }

    override fun onSaveInstanceState(outState: Bundle) {
outState.putInt(State.SECONDS.name, seconds)
        outState.putBoolean(State.RUNNING.name, running)
        super.onSaveInstanceState(outState)
    }
      private fun pauseClick(){
//Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
          running=false
}
    private fun startButton(){
        //Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show()
        running = true
    }
    private fun resetClick(){
      //  Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
        seconds = 0
        running=false
    }

    override fun onStart() {
        super.onStart()
        Log.e(this.javaClass.name, ">>> onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(this.javaClass.name, ">>> onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(this.javaClass.name, ">>> onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(this.javaClass.name, ">>> onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(this.javaClass.name, ">>> onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(this.javaClass.name, ">>> onRestart")
    }
}
enum class State{
    RUNNING, SECONDS
}