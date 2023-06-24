package ba.etf.rma23.projekat.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma23.projekat.GameReview

@Dao
interface GameReviewDAO {
    @Query("SELECT * FROM GameReview WHERE online = false")
    suspend fun getOffline(): List<GameReview>

    @Query("UPDATE GameReview SET online = true WHERE online = false")
    suspend fun setOnlineToTrue(): Int

    @Insert
    suspend fun insertAll(vararg GameReviews: GameReview)

    @Query("DELETE FROM GameReview WHERE 1 = 1")
    suspend fun deleteAll()

}