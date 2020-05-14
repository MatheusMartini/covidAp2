package com.example.ap2_covid19

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ap2_covid19.API.HTTP_states
import com.example.ap2_covid19.adapters.AdapterStates
import com.example.ap2_covid19.classes.States
import kotlinx.android.synthetic.main.activity_state_list.*

class ActivityStateList : AppCompatActivity() {
    private var stateList = arrayListOf<States>()
    private var adapter = AdapterStates(stateList)
    private var asyncTask : StatesTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_list)
        carregaDados()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter
    }

    fun showProgress(show: Boolean){
        if(show){
            error_display.text = "Loading ..."
        }else{
            error_display.visibility = if(show) View.VISIBLE else View.GONE
            progress_bar.visibility = if(show) View.VISIBLE else View.GONE
        }
    }

    fun carregaDados(){
        stateList.clear()
        if(stateList.isNotEmpty()){
            showProgress(false)
        }else{
            if(asyncTask==null){
                if(HTTP_states.hasConnection(this)){
                    if(asyncTask?.status != AsyncTask.Status.RUNNING){
                        asyncTask = StatesTask()
                        asyncTask?.execute()
                    }
                }else{
                    progress_bar.visibility = View.GONE
                }
            }else if(asyncTask?.status==AsyncTask.Status.RUNNING){
                showProgress(true)
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class StatesTask: AsyncTask<Void, Void, List<States?>>(){
        override fun onPreExecute() {
            super.onPreExecute()
            showProgress(true)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<States>? {
            return HTTP_states.loadStates()
        }

        private fun update(result: List<States>?){
            if(result != null){
                stateList.clear()
                stateList.addAll(result.reversed())
            }else{
                error_display.text = "Loading Error"
            }
            adapter.notifyDataSetChanged()
            asyncTask = null
        }

        override fun onPostExecute(result: List<States?>?) {
            super.onPostExecute(result)
            showProgress(false)
            update(result as List<States>?)
        }
    }
}
