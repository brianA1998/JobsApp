package com.example.jobsapplication.data.remote.home

import com.example.jobsapplication.data.model.Publication
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.example.jobsapplication.core.Result
import kotlinx.coroutines.channels.awaitClose

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class HomeRemoteDataSource {

    suspend fun getAllPublications(): Flow<Result<List<Publication>>> = callbackFlow {
        val publicationList = mutableListOf<Publication>()


        var publicationReference: Query? = null

        try {
            publicationReference = FirebaseFirestore.getInstance().collection("publications")
                .orderBy("created_at", Query.Direction.DESCENDING)
        } catch (e: Throwable) {
            close(e)
        }

        val subscription = publicationReference?.addSnapshotListener { value, error ->
            if (value == null) return@addSnapshotListener

            try {
                publicationList.clear()
                for (publication in value?.documents) {
                    publication.toObject(Publication::class.java)?.let { publicationFirebase ->
                        publicationFirebase.apply {
                            created_at = publication.getTimestamp(
                                "created_at",
                                DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                            )?.toDate()
                        }
                        publicationList.add(publicationFirebase)
                    }
                }

            } catch (e: Exception) {
                close(e)
            }
            offer(Result.Success(publicationList))
        }
        awaitClose { subscription?.remove() }
    }
}