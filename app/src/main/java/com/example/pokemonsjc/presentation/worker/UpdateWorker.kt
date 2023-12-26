package com.example.pokemonsjc.presentation.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pokemonsjc.domain.interactors.PokemonsInteractor
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

private const val UPDATE_WORKER = "Update Worker"

@HiltWorker
class UpdateWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val pokemonsInteractor: PokemonsInteractor,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            pokemonsInteractor.updateCache()
            Result.success()
        } catch (throwable: Throwable) {
            Log.e(UPDATE_WORKER, "Problem with updating")
            Result.failure()
        }
    }
}