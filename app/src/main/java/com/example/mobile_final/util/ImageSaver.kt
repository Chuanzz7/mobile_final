import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

class ImageSaver {
    companion object {
        public fun saveToInternalStorage(cr: Context, bitmapImage: Bitmap): String {
            val cw = ContextWrapper(cr)
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            val mypath = File(directory, UUID.randomUUID().toString() + ".jpg")

            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(mypath)
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return mypath.absolutePath
        }
    }
}