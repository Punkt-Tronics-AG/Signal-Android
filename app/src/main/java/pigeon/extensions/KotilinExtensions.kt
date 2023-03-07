package pigeon.extensions

import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import org.thoughtcrime.securesms.R


fun View.focusOnLeft(vararg childs: TextView) {

  if (!isSignalVersion()) {
    val BUTTON_SCALE_FOCUS = 1.3f
    val BUTTON_SCALE_NON_FOCUS = 1.0f
    val BUTTON_TRANSLATION_X_FOCUS = 12.0f
    val BUTTON_TRANSLATION_X_NON_FOCUS = 1.0f

    val originalWidth = 240

    val focus = View.OnFocusChangeListener { _, hasFocus ->
      val scale: Float = if (hasFocus) {
        BUTTON_SCALE_FOCUS
      } else {
        BUTTON_SCALE_NON_FOCUS
      }

      val translationX: Float = if (hasFocus) {
        BUTTON_TRANSLATION_X_FOCUS
      } else {
        BUTTON_TRANSLATION_X_NON_FOCUS
      }

      this.setupEllipsize(hasFocus)
      childs.forEach { it.setupEllipsize(hasFocus) }

      if (hasFocus) {
        val currentParams = this.layoutParams.apply {
          width = originalWidth - 12
        }
        this.layoutParams = currentParams
        this.requestLayout()
      } else {
        this.layoutParams.width = originalWidth
      }

      ViewCompat.animate(this)
        .scaleX(scale)
        .scaleY(scale)
        .translationX(translationX)
        .start()

    }
    this.onFocusChangeListener = focus
  }
}

fun View.setupEllipsize(hasFocus: Boolean) {
  val color = if (hasFocus) {
    R.color.white_focus
  } else {
    R.color.white_not_focus
  }
  if (this is TextView) {
    this.setTextColor(ContextCompat.getColor(this.context, color))
  }
  if (hasFocus) {
    if (this is TextView && this !is EditText) {
      this.ellipsize = TextUtils.TruncateAt.MARQUEE
      this.isSelected = true
    }
  } else {
    if (this is TextView) {
      this.ellipsize = TextUtils.TruncateAt.END
    }
  }
}

fun View.recyclerFocusOnLeft(vararg childs: TextView) {

  if (!isSignalVersion()) {
    val BUTTON_SCALE_FOCUS = 1.3f
    val BUTTON_SCALE_NON_FOCUS = 1.0f
    val BUTTON_TRANSLATION_X_FOCUS = 12.0f
    val BUTTON_TRANSLATION_X_NON_FOCUS = 1.0f

    val focus = View.OnFocusChangeListener { _, hasFocus ->
      val scale: Float = if (hasFocus) {
        BUTTON_SCALE_FOCUS
      } else {
        BUTTON_SCALE_NON_FOCUS
      }

      val translationX: Float = if (hasFocus) {
        BUTTON_TRANSLATION_X_FOCUS
      } else {
        BUTTON_TRANSLATION_X_NON_FOCUS
      }

      ViewCompat.animate(this)
        .scaleX(scale)
        .scaleY(scale)
        .translationX(translationX)
        .start()

      val color = if (hasFocus) {
        R.color.white_focus
      } else {
        R.color.white_not_focus
      }

      childs.forEach {
        it.setTextColor(ContextCompat.getColor(this.context, color))
      }
    }
    this.onFocusChangeListener = focus
  }
}

fun View.focusOnRight() {

  if (!isSignalVersion()) {
    val BUTTON_SCALE_FOCUS = 1.3f
    val BUTTON_SCALE_NON_FOCUS = 1.0f
    val BUTTON_TRANSLATION_X_FOCUS = 50.0f
    val BUTTON_TRANSLATION_X_NON_FOCUS = 1.0f

    val focus = View.OnFocusChangeListener { _, hasFocus ->
      val scale: Float = if (hasFocus) {
        BUTTON_SCALE_FOCUS
      } else {
        BUTTON_SCALE_NON_FOCUS
      }

      val translationX: Float = if (hasFocus) {
        BUTTON_TRANSLATION_X_FOCUS
      } else {
        BUTTON_TRANSLATION_X_NON_FOCUS
      }

      ViewCompat.animate(this)
        .translationX(translationX)
        .scaleX(scale)
        .scaleY(scale)
        .start()

    }
    this.onFocusChangeListener = focus
  }
}

fun TextView.setBigText() {
  this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
}

fun EditText.animateGroup(parent: TextView) {
  if (!isSignalVersion()) {
    val BUTTON_SCALE_FOCUS = 1.3f
    val BUTTON_SCALE_NON_FOCUS = 1.0f
    val BUTTON_TRANSLATION_X_FOCUS = 12.0f
    val BUTTON_TRANSLATION_X_NON_FOCUS = 1.0f
    val BUTTON_TRANSLATION_X_FOCUS_PARENT = -30.0f
    val BUTTON_TRANSLATION_X_NON_FOCUS_PARENT = 1.0f

    val focus = View.OnFocusChangeListener { _, hasFocus ->
      val scale: Float = if (hasFocus) {
        BUTTON_SCALE_FOCUS
      } else {
        BUTTON_SCALE_NON_FOCUS
      }

      val translationX: Float = if (hasFocus) {
        BUTTON_TRANSLATION_X_FOCUS
      } else {
        BUTTON_TRANSLATION_X_NON_FOCUS
      }

      val translationParentX: Float = if (hasFocus) {
        BUTTON_TRANSLATION_X_FOCUS_PARENT
      } else {
        BUTTON_TRANSLATION_X_NON_FOCUS_PARENT
      }

      ViewCompat.animate(this)
        .scaleX(scale)
        .scaleY(scale)
        .translationX(translationX)
        .start()

      ViewCompat.animate(parent)
        .translationX(translationParentX)
        .start()

      val parentColor = if (hasFocus) {
        R.color.white_focus
      } else {
        R.color.white_not_focus
      }

      parent.setTextColor(ContextCompat.getColor(this.context, parentColor))
      this.setTextColor(ContextCompat.getColor(this.context, parentColor))
    }
    this.onFocusChangeListener = focus
  }
}