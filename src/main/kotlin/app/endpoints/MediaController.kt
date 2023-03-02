package app.endpoints

import app.service.blob.media.MediaBlobService
import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.server.types.files.StreamedFile
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.UUID

@Secured(SecurityRule.IS_ANONYMOUS)
@ExecuteOn(TaskExecutors.IO)
abstract class MediaController(private val mediaService: MediaBlobService) {
    @Get("/{id}")
    fun get(@NonNull id: UUID, @Nullable authentication: Authentication?): Single<out HttpResponse<StreamedFile>> =
        mediaService.downloadById(id, authentication.getClientId()).observeOn(Schedulers.io())
            .map { HttpResponse.ok(StreamedFile(it, MediaType.ALL_TYPE)) }
            .defaultIfEmpty(HttpResponse.status(HttpStatus.NOT_FOUND))
            .onErrorReturnItem(HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR))

    private fun Authentication?.getClientId(): UUID? {
        val id = this?.attributes?.get("id") ?: return null
        return UUID.fromString(id.toString())
    }
}
