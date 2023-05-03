package uz.gita.dima.palapan.data.local.datastore.impl

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import uz.gita.dima.palapan.app.App
import kotlinx.coroutines.flow.first
import uz.gita.dima.palapan.data.local.datastore.DataStore


private const val PREFERENCES_NAME = "my_preferences"

private val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_NAME
)

class DataStoreImpl private constructor() : DataStore {

    /*private val pref =
        App.instance.getSharedPreferences("My SharedPreference", Context.MODE_PRIVATE)


    companion object {
        private lateinit var pref: DataStoreImpl

        fun getSharedPreference(): DataStoreImpl {
            if (!Companion::pref.isInitialized) {
                pref = DataStoreImpl()
            }
            return pref
        }
    }*/

    companion object {
        private lateinit var instance: DataStoreImpl
        fun getInstance(): DataStoreImpl {
            if (!(Companion::instance.isInitialized)) {
                instance = DataStoreImpl()
            }
            return instance
        }
    }

    override suspend fun putIntStep(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        App.instance.applicationContext.dataStore.edit { step ->
            step[preferencesKey] = value
        }
    }

    override suspend fun getIntStep(key: String): Int? {
        val preferencesKey = intPreferencesKey(key)
        val preferences = App.instance.applicationContext.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun putIntMenu(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        App.instance.applicationContext.dataStore.edit { step ->
            step[preferencesKey] = value
        }
    }

    override suspend fun getIntMenu(key: String): Int? {
        val preferencesKey = intPreferencesKey(key)
        val preferences = App.instance.applicationContext.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun putIntEasy(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        App.instance.applicationContext.dataStore.edit { easy ->
            easy[preferencesKey] = value
        }
    }

    override suspend fun getIntEasy(key: String): Int? {
        val preferencesKey = intPreferencesKey(key)
        val preferences = App.instance.applicationContext.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun putIntMedium(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        App.instance.applicationContext.dataStore.edit { medium ->
            medium[preferencesKey] = value
        }
    }

    override suspend fun getIntMedium(key: String): Int? {
        val preferencesKey = intPreferencesKey(key)
        val preferences = App.instance.applicationContext.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun putIntHard(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        App.instance.applicationContext.dataStore.edit { hard ->
            hard[preferencesKey] = value
        }
    }

    override suspend fun getIntHard(key: String): Int? {
        val preferencesKey = intPreferencesKey(key)
        val preferences = App.instance.applicationContext.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun putBooleanIsNew(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        App.instance.applicationContext.dataStore.edit { hard ->
            hard[preferencesKey] = value
        }
    }

    override suspend fun getBooleanIsNew(key: String): Boolean? {
        val preferencesKey = booleanPreferencesKey(key)
        val preferences = App.instance.applicationContext.dataStore.data.first()
        return preferences[preferencesKey]
    }
}