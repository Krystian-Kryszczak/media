package app.service.blob

import app.service.blob.media.MediaBlobService
import app.service.blob.media.audio.AudioBlobService
import app.service.blob.media.image.ImageBlobService
import app.service.blob.media.video.VideoBlobService
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.util.UUID

/**
 * An object that provides useful functions for testing BlobStorage interface implementations.
 */
object BlobServiceTest {

    private const val basePath = "./src/test/resources/media"

    private val files = listOf(
        "$basePath/video/world.mp4",
        "$basePath/image/java.png",
        "$basePath/audio/test.wav"
    )

    private fun testMediaBlobServiceSaveAndDownload(mediaIndex: Int, mediaBlobService: MediaBlobService) : Boolean {
        val file = File(files[mediaIndex])
        val randomCreatorId = UUID.randomUUID()
        return mediaBlobService.save(file.inputStream(), randomCreatorId, false)
            .flatMapMaybe { mediaBlobService.downloadById(it) }
            .observeOn(Schedulers.io())
            .map { file.readBytes().contentEquals(it.readAllBytes()) }
            .defaultIfEmpty(false)
            .blockingGet()
    }
    fun testVideoBlobServiceSaveAndDownload(videoBlobService: VideoBlobService) = testMediaBlobServiceSaveAndDownload(0, videoBlobService)
    fun testImageBlobServiceSaveAndDownload(imageBlobService: ImageBlobService) = testMediaBlobServiceSaveAndDownload(1, imageBlobService)
    fun testAudioBlobServiceSaveAndDownload(audioBlobService: AudioBlobService) = testMediaBlobServiceSaveAndDownload(2, audioBlobService)

    private fun testMediaBlobServiceSaveAndAndDeleteById(mediaIndex: Int, mediaBlobService: MediaBlobService) : Boolean {
        val file = File(files[mediaIndex])
        val randomCreatorId = UUID.randomUUID()
        return mediaBlobService.save(file.inputStream(), randomCreatorId, false)
            .flatMap { mediaBlobService.deleteById(it, randomCreatorId) }
            .blockingGet()
    }
    fun testVideoBlobServiceSaveAndDeleteById(videoBlobService: VideoBlobService) = testMediaBlobServiceSaveAndAndDeleteById(0, videoBlobService)
    fun testImageBlobServiceSaveAndDeleteById(imageBlobService: ImageBlobService) = testMediaBlobServiceSaveAndAndDeleteById(1, imageBlobService)
    fun testAudioBlobServiceSaveAndDeleteById(audioBlobService: AudioBlobService) = testMediaBlobServiceSaveAndAndDeleteById(2, audioBlobService)
}
