package com.example.mymemorygame.data.local.sharedPref.impl

import android.content.SharedPreferences
import com.example.mymemorygame.data.local.sharedPref.SharedPref
import javax.inject.Inject

class SharedPrefImpl @Inject constructor(private val pref: SharedPreferences): SharedPref {
//    private val pref = App.instance.getSharedPreferences("MemoryGame", Context.MODE_PRIVATE)


    override var level : Int
        get() = pref.getInt("level", 1)
        set(value) = pref.edit().putInt("level", value).apply()

    override var steps : Int
        get() = pref.getInt("steps", 1)
        set(value) = pref.edit().putInt("steps", value).apply()

    override var attempt : Int
        get() = pref.getInt("attempt", 1)
        set(value) = pref.edit().putInt("attempt", value).apply()

    override var isNew : Boolean
        get() = pref.getBoolean("isNew", true)
        set(value) = pref.edit().putBoolean("isNew", value).apply()

    override var menu : Int
        get() = pref.getInt("menu", 0)
        set(value) = pref.edit().putInt("menu", value).apply()

    override var easy : Int
        get() = pref.getInt("easy", 0)
        set(value) = pref.edit().putInt("easy", value).apply()

    override var medium : Int
        get() = pref.getInt("medium", 0)
        set(value) = pref.edit().putInt("medium", value).apply()

    override var hard : Int
        get() = pref.getInt("hard", 0)
        set(value) = pref.edit().putInt("hard", value).apply()

}