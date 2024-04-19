package com.example.inkstonedemo1.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.inkstonedemo1.data.allInkStoneClasses
import com.example.inkstonedemo1.ml.InkstoneModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.FileDescriptor
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

//通过协程
suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        },ContextCompat.getMainExecutor(this))
    }
}


private const val fileNameFormat = "yyyy-MM-dd-HH-mm-ss-SSS"
//拍照并进行保存
fun startTakePhoto(
    imageCapture: ImageCapture,
    executor: Executor,
    contentResolver : ContentResolver,
    onSuccess: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
){
    val imageName = SimpleDateFormat(fileNameFormat,Locale.CHINA).format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME,imageName)
        put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
            put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/CameraX-Image")
        }
    }

    val outputOptions = ImageCapture.OutputFileOptions.Builder(
        contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    ).build()

    imageCapture.takePicture(outputOptions,executor,object : ImageCapture.OnImageSavedCallback{
        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            val savedUri = outputFileResults.savedUri
            onSuccess(savedUri!!)
        }

        override fun onError(exception: ImageCaptureException) {
            onError(exception)
        }

    })
}

//调用tensorflow lite模型进行判断
fun identifyPhoto(
    context : Context,
    bitmap: Bitmap
): String{

    val model = InkstoneModel.newInstance(context)

    val inputFeature = TensorBuffer.createFixedSize(intArrayOf(1,224,224,3), DataType.FLOAT32)
    val resizeBitmap = Bitmap.createScaledBitmap(bitmap,224,224,true)
    var tensorImage = TensorImage(DataType.FLOAT32)
    tensorImage.load(resizeBitmap)
    val byteBuffer = tensorImage.buffer
    inputFeature.loadBuffer(byteBuffer)

    val outputs = model.process(inputFeature)
    val outputFeature = outputs.outputFeature0AsTensorBuffer

    model.close()

    var index = 0
    val maxFloat = outputFeature.floatArray.max()
    for (i in outputFeature.floatArray){
        if (i == maxFloat){
            break
        }else{
            index ++
        }
    }

    return allInkStoneClasses[index]
}

fun rotateIfRequired(bitmap: Bitmap,fileDescriptor: FileDescriptor) : Bitmap{
    val exif = ExifInterface(fileDescriptor)
    val orientation =  exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL)
    return when (orientation){
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap,90)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap,180)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap,270)
        else -> bitmap
    }
}

fun rotateBitmap(bitmap: Bitmap,degree: Int) : Bitmap{
    val matrix = Matrix()
    matrix.postRotate(degree.toFloat())
    val rotatedBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
    bitmap.recycle()
    return rotatedBitmap
}