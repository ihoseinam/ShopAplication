package ir.hoseinahmadi.shopapplication.Rom.db.dao

import androidx.room.*
import ir.hoseinahmadi.shopapplication.Rom.db.DBHandler
import ir.hoseinahmadi.shopapplication.Rom.db.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert
    
    fun insertUser(vararg user: UserEntity)

    @get:Query("SELECT * FROM ${DBHandler.USER_TABLE}")
    val getUsers: Flow<List<UserEntity>>

    @Query("SELECT * FROM ${DBHandler.USER_TABLE}")
    fun getShoppingCartItems(): Flow<List<UserEntity>>

    @Query("DELETE FROM ${DBHandler.USER_TABLE}")
    fun deleteAllUser()

   @get:Query("SELECT price FROM ${DBHandler.USER_TABLE}")
    val getzori: Flow<List<Int>>




}