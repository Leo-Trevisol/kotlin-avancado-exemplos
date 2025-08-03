package com.example.kotlinavancadoexemplos.coroutines

class UserRepository {
    suspend fun fetchUsers(): List<User> {
        return RetrofitInstance.api.getUsers()
    }
}
