package uz.gita.dima.palapan.data.local.datastore

interface DataStore {
    suspend fun putIntStep(key: String,value: Int)
    suspend fun getIntStep(key: String): Int?

    suspend fun putIntMenu(key: String,value: Int)
    suspend fun getIntMenu(key: String): Int?

    suspend fun putIntEasy(key: String,value: Int)
    suspend fun getIntEasy(key: String): Int?

    suspend fun putIntMedium(key: String,value: Int)
    suspend fun getIntMedium(key: String): Int?

    suspend fun putIntHard(key: String,value: Int)
    suspend fun getIntHard(key: String): Int?

    suspend fun putBooleanIsNew(key: String,value: Boolean)
    suspend fun getBooleanIsNew(key: String): Boolean?
}