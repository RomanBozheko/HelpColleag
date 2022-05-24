package com.example.mytestkotlin2


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.textViewName
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class SecondActivity : AppCompatActivity() {
    private var presenter = Presenter()
//    private var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnTaskFirst.text = presenter.btnFirstTask
        btnTaskSecond.text = presenter.btnSecondTask

        showRandom()
    }

    override fun onPause() {
        super.onPause()
        
    }


    private fun showRandom() {
        val rand = intent.getIntExtra(presenter.randomKey, 1)
        val firstTask = (1..20).random()
        var secondTask = (1..20).random()

        if (firstTask == secondTask) {
            secondTask++
        }

        val userApi: UserApi? = presenter.getRetrofitInstance()?.create(UserApi::class.java)

        val userNameObservable = userApi?.getUser(rand)
        userNameObservable?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Subscriber<User>() {
                override fun onNext(user: User) {
                    textViewName.text = user.name
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {

                }


            })

        val firstTaskObservable = userApi?.getTask(rand)
        firstTaskObservable?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())

            ?.subscribe(object : Subscriber<List<Task?>?>() {
                override fun onNext(tasks: List<Task?>?) {
                    if (tasks != null) {
                        for (i in 0..tasks.size) {
                            if (i + 1 == firstTask) {
                                btnTaskFirst.text = tasks[i]?.title

                            }
                        }
                    }
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {

                }
            })

        val secondTaskObservable = userApi?.getTask(rand)
        secondTaskObservable?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Subscriber<List<Task?>?>() {
                override fun onNext(tasks: List<Task?>?) {
                    if (tasks != null) {
                        for (i in 0..tasks.size) {
                            if (i + 1 == secondTask) {
                                btnTaskSecond.text = tasks[i]?.title
                            }
                        }
                    }
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {

                }

            })

    }





    fun firstBtn(v: View?) {
        val intent = Intent()
        intent.putExtra(presenter.nameKey, textViewName.text)
        intent.putExtra(presenter.taskKey, btnTaskFirst.text)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun secondBtn(v: View?) {
        val intent = Intent()
        intent.putExtra(presenter.nameKey, textViewName.text)
        intent.putExtra(presenter.taskKey, btnTaskSecond.text)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}

