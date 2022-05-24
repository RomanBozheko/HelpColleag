package com.example.mytestkotlin2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var presenter = Presenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRnd.text = presenter.btnRandTodo
        btnRnd.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val rnd = (1..10).random()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(presenter.randomKey, rnd)
        startActivityForResult(intent, 1)
    }

    @SuppressLint("MissingSuperCall", "SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (data == null) {
            return
        }
        val name = data.getStringExtra(presenter.nameKey)
        val task = data.getStringExtra(presenter.taskKey)

        textViewNameUser.text = "Name - $name"
        textViewTask.text = "Task - $task"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(presenter.saveStateName, textViewNameUser.text.toString())
            putString(presenter.saveStateTask, textViewTask.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textViewNameUser.text = savedInstanceState.getString(presenter.saveStateName)
        textViewTask.text = savedInstanceState.getString(presenter.saveStateTask)
    }
}
