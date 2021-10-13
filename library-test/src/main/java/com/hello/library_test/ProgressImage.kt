package com.hello.library_test

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File

class ProgressImage@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
)  : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var isEditIcon: Boolean = true

    var addListenerListener: (() -> Unit?)? = null

    private var frameLayout: FrameLayout
    private var progress: ProgressBar
    private var imgImage: ShapeableImageView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.progress_image, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressImage, 0, 0)

        frameLayout = findViewById(R.id.frameLayout)
        progress = findViewById(R.id.progress)
        imgImage = findViewById(R.id.img_image)

        isEditIcon = typedArray.getBoolean(R.styleable.ProgressImage_isEditIcon, true)
        if (isEditIcon) frameLayout.show() else frameLayout.gone()
        typedArray.recycle()

        frameLayout.setOnClickListener {
            addListenerListener?.invoke()
        }
    }

     private var target = object : Target {
         override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
             progress.gone()
         }

         override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
             imgImage.setImageBitmap(bitmap)
             progress.gone()
         }

         override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
             progress.show()
         }
     }

    fun setImgURL(url: String?) {
        url?.apply {
            Picasso
                .get()
                .load(url)
                .into(target)
        }
    }

    fun setImgURI(uri: Uri?) {
        uri?.path?.apply {
            Picasso
                .get()
                .load(File(this))
                .into(target)
        }
    }

    fun setImgDefault() {
        imgImage.setImageResource(R.drawable.ic_round_account_circle)
        imgImage.setImageTint(R.color.colorTextToolbar)
    }
}