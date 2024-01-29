package ir.hoseinahmadi.shopapplication.Rom.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.hoseinahmadi.shopapplication.Rom.db.DBHandler

@Entity(tableName = DBHandler.USER_TABLE)
data class UserEntity(
  @PrimaryKey val id: Int?,
  @ColumnInfo val name: String,
  @ColumnInfo val price: Int,

  )
