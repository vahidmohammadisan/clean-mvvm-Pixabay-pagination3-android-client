package group.payback.pixabayclient.util

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import group.payback.pixabayclient.R

fun String.tagView(context: Context, tagContainer: ViewGroup): ViewGroup {

    val tag: List<String> = this.split(",").map { it.trim() }
    tag.forEach {
        val tv = TextView(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8)
        tv.apply {
            textSize = 15f
            layoutParams = params
            setTextColor(resources.getColor(R.color.dark_gray))
            setBackgroundResource(R.drawable.light_gray_rect)
            text = it
            setPadding(14)
        }
        tagContainer.addView(tv)
    }

    return tagContainer

}