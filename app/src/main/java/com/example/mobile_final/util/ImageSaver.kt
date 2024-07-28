//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.os.Environment
//import android.util.Log
//import java.io.File
//import java.io.FileInputStream
//import java.io.FileOutputStream
//import java.io.IOException
//
//class ImageSaver(private val context: Context) {
//    private var directoryName: String = "images"
//    private var fileName: String = "image.png"
//    private var external: Boolean = false
//
//    fun setFileName(fileName: String): ImageSaver {
//        this.fileName = fileName
//        return this
//    }
//
//    fun setExternal(external: Boolean): ImageSaver {
//        this.external = external
//        return this
//    }
//
//    fun setDirectory(directoryName: String): ImageSaver {
//        this.directoryName = directoryName
//        return this
//    }
//
//    fun save(bitmapImage: Bitmap): String {
//        var fileOutputStream: FileOutputStream? = null
//        try {
//            fileOutputStream = FileOutputStream(createFile())
//            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            try {
//                fileOutputStream?.close()
//                return fileOutputStream.
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    private fun createFile(): File {
//        val directory: File = if (external) {
//            getAlbumStorageDir(directoryName).apply {
//                if (!exists()) {
//                    mkdir()
//                }
//            }
//        } else {
//            File("${context.filesDir}/$directoryName").apply {
//                if (!exists()) {
//                    mkdir()
//                }
//            }
//        }
//
//        return File(directory, fileName)
//    }
//
//    private fun getAlbumStorageDir(albumName: String): File {
//        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName)
//        if (!file.mkdirs()) {
//            Log.e("ImageSaver", "Directory not created")
//        }
//        return file
//    }
//
//    companion object {
//        fun isExternalStorageWritable(): Boolean {
//            val state = Environment.getExternalStorageState()
//            return Environment.MEDIA_MOUNTED == state
//        }
//
//        fun isExternalStorageReadable(): Boolean {
//            val state = Environment.getExternalStorageState()
//            return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
//        }
//    }
//
//    fun load(): Bitmap? {
//        var inputStream: FileInputStream? = null
//        return try {
//            inputStream = FileInputStream(createFile())
//            BitmapFactory.decodeStream(inputStream)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        } finally {
//            try {
//                inputStream?.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    fun deleteFile(): Boolean {
//        val file = createFile()
//        return file.delete()
//    }
//}