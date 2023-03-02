package app.endpoints

import app.service.blob.media.MediaBlobService
import app.service.blob.media.audio.AudioBlobService
import app.service.blob.media.image.ImageBlobService
import app.service.blob.media.video.VideoBlobService
import io.kotest.core.spec.style.StringSpec
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID

@MicronautTest
class MediaControllersTest(
    private val videoBlobService: VideoBlobService,
    private val imageBlobService: ImageBlobService,
    private val audioBlobService: AudioBlobService,
    @Client("/") private val httpClient: HttpClient,
) : StringSpec({

    val basePath = "./src/test/resources/media"

    val files = mapOf(
        "/video" to "$basePath/video/world.mp4",
        "/images" to "$basePath/image/java.png",
        "/audio" to "$basePath/audio/test.wav"
    )

    suspend fun testMediaGetEndpoint(endpoint: String, mediaBlobService: MediaBlobService) {
        val file = File(files[endpoint]!!)
        val randomCreatorId = UUID.randomUUID()
        val savedBlobId =  withContext(Dispatchers.IO) {
            mediaBlobService.save(file.inputStream(), randomCreatorId, false)
                .blockingGet()
        }
        val resp = httpClient.toBlocking().exchange<ByteArray>("$endpoint/$savedBlobId")
        // then
        assert(resp.status == HttpStatus.OK)
    }

    "test video controller GET endpoint" {
        testMediaGetEndpoint("/video", videoBlobService)
    }

    "test image controller GET endpoint" {
        testMediaGetEndpoint("/images", imageBlobService)
    }

    "test audio controller GET endpoint" {
        testMediaGetEndpoint("/audio", audioBlobService)
    }
})
