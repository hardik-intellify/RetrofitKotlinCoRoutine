package com.theintellify.kretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.theintellify.kretrofit.api.ApiWiki
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = ApiWiki()

        //NOTE: co-routine can be launched with withContext(Main/IO/..) also
        CoroutineScope(IO).launch{
            val wikiResponse = api.getResult("Google").await()
            updateText(wikiResponse.toString())
            println("debug: response => {${wikiResponse.query.searchinfo}}")
        }
    }

    private suspend fun updateText(message: String){
        withContext(Main){
            textView.text = message
        }
    }
}
