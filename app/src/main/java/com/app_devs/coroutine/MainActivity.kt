package com.app_devs.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val TAG="mainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*GlobalScope.launch {
            Log.d(TAG,"Hello from coroutine ${Thread.currentThread().name}")
        }
        Log.d(TAG,"Hello from  ${Thread.currentThread().name}")*/
        /*CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG,"calling first function")
            doNetworkCall()
            Log.d(TAG,"finishing first function")
        }
        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG,"calling second function")
            doNetworkCallTwo()
            Log.d(TAG,"finishing second function")
        }*/

        /*GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG,"Starting coroutine in ${Thread.currentThread().name}")
            //network call in IO thread
            val answer=doNetworkCall()
            withContext(Dispatchers.Main){
                //setting UI in main thread
                Log.d(TAG,"Setting textview in ${Thread.currentThread().name}")
                tv_hello.text=answer
            }
        }*/

       /* Log.d(TAG,"before runBlocking")
        Log.d(TAG,"Hello from ${Thread.currentThread().name} thread")  //main
        runBlocking {
            //blocks the main thread and runs on it
            launch {
                //would not block the main
                delay(3000L)
                Log.d(TAG,"Hello from first coroutine running on ${Thread.currentThread().name} thread")  //main
                Log.d(TAG,"Finished coroutine 1 from launch")
            }
            launch {
                delay(3000L)
                Log.d(TAG,"Hello from second coroutine running on ${Thread.currentThread().name} thread")
                Log.d(TAG,"Finished coroutine 2 from launch")
            }

            Log.d(TAG,"start of runBlocking")
            Log.d(TAG,"runBlocking is on ${Thread.currentThread().name} thread") //main
            delay(5000L)
            Log.d(TAG,"end of runBlocking")
        }
        Log.d(TAG,"after runBlocking")*/
        CoroutineScope(Dispatchers.IO).launch {
            printFollowers()
        }

    }
    private suspend fun printFollowers()
    {
        var fbFollowers=0
        var instaFollowers=0
//        val job1=CoroutineScope(Dispatchers.IO).launch {
//            fbFollowers=getFbFollowers()
//        }
        val fb= CoroutineScope(Dispatchers.IO).async {
            getFbFollowers()
        }
        val insta= CoroutineScope(Dispatchers.IO).async {
            getInstaFollowers()
        }
//        val job2=CoroutineScope(Dispatchers.IO).launch {
//            instaFollowers=getInstaFollowers()
//        }
//        job1.join()
//        job2.join()
//        Log.d(TAG,"FB- $fbFollowers")
//        Log.d(TAG,"INSTA- $instaFollowers")
        Log.d(TAG,"FB- ${fb.await()}    Insta- ${insta.await()}")

    }
    private suspend fun doNetworkCall()
    {
        Log.d(TAG,"Before delay in first call",)
        delay(5000L);
        Log.d(TAG,"After delay in First Call")
    }
    private suspend fun doNetworkCallTwo()
    {
        Log.d(TAG,"Before delay in second call",)
        delay(3000L)
        Log.d(TAG,"After delay in second Call")

    }

    private suspend fun getFbFollowers():Int{
        delay(1000)
        return 100
    }
    private suspend fun getInstaFollowers():Int{
        delay(1000)
        return 500
    }


}