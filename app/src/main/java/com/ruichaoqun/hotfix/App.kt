package com.ruichaoqun.hotfix

import android.app.Application
import android.content.Context
import dalvik.system.DexClassLoader
import java.io.File
import java.util.*

class App:Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        var file:File = File(cacheDir,"hotfix.dex")
        if(!file.exists()){
            return
        }
        val dexClassLoader = DexClassLoader(file.path,cacheDir.path+"plugin",null,null)
        val originalClassLoader = classLoader
        val dexDexPathList = ReflectUtils.getField(dexClassLoader,"dalvik.system.BaseDexClassLoader","pathList")
        val dexElements = ReflectUtils.getField(dexDexPathList,"dexElements") as Array<Object>
        val originalDexPathList = ReflectUtils.getField(originalClassLoader,"dalvik.system.BaseDexClassLoader","pathList")
        val originalElements = ReflectUtils.getField(originalDexPathList,"dexElements") as Array<Object>
        val newElements:Array<Object> = Arrays.copyOf(dexElements,dexElements.size+originalElements.size)
        var index = dexElements.size
        for (element in originalElements){
            newElements[index] = element
        }
        ReflectUtils.setField(originalDexPathList,"dexElements",newElements)
    }
}