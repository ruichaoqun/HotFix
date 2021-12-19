package com.ruichaoqun.hotfix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import okio.buffer
import okio.sink
import okio.source
import java.io.File
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var tvTest:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.test).setOnClickListener(this)
        findViewById<View>(R.id.add_plugin).setOnClickListener(this)
        findViewById<View>(R.id.delete_plugin).setOnClickListener(this)
        findViewById<View>(R.id.exit).setOnClickListener(this)
        tvTest = findViewById(R.id.tv_text)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.test -> {
                tvTest.text = Test().test()
            }
            R.id.add_plugin -> {
                val inputStream = assets.open("plugin.dex")
                val file = File(cacheDir,"hotfix.dex")
                if(file.exists()){
                    file.delete()
                }
                val source = inputStream.source()
                val buffer = file.sink().buffer()
                buffer.writeAll(source)
                buffer.flush()
            }
            R.id.delete_plugin -> {
                val file = File(cacheDir,"hotfix.dex")
                if(file.exists()){
                    file.delete()
                }
            }
            R.id.exit -> {
                exitProcess(0)
            }

        }
    }
}