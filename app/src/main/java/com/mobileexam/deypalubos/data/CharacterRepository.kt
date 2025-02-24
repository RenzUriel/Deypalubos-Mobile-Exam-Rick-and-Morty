package com.mobileexam.deypalubos.data

import android.content.Context
import androidx.room.Room
import com.mobileexam.deypalubos.model.Character
import com.mobileexam.deypalubos.network.CharacterApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepository(context: Context) {
    private val apiService = CharacterApiService.create()
    private val db = Room.databaseBuilder(context, AppDatabase::class.java, "characters_db").build()
    private val characterDao = db.characterDao()

    fun getCharacters(): Flow<List<Character>> {
        return characterDao.getCharacters().map { entities ->
            entities.map { it.toCharacter() }
        }
    }

    suspend fun fetchAndStoreCharacters() {
        try {
            val response = apiService.getCharacters()
            val entities = response.results.map { it.toEntity() }
            characterDao.insertCharacters(entities)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun CharacterEntity.toCharacter() = Character(id, name, status, species, type, gender,
    origin = com.mobileexam.deypalubos.model.Origin(origin),
    location = com.mobileexam.deypalubos.model.Location(location),
    imageUrl = imageUrl)

fun Character.toEntity() = CharacterEntity(id, name, status, species, type, gender, origin.name, location.name, imageUrl)
