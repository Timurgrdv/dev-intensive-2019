package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import ru.skillbranch.devintensive.R

/** класс для custom'ной view
 *  здесь аннотация @JvmOverloads нужна для того, чтобы при компиляции в byte-код было создано именно три конструктора,
 *  для каждого из возможных аргументов
 */
class AspectRatioImageView @JvmOverloads constructor(

    /** конструктор*/
    context: Context,
    attrs: AttributeSet? = null,  // set атрибутов
    defStyleAttr: Int = 0         // атрибуты по "умолчанию"
) : ImageView(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_ASPECT_RATIO = 1.78f
    }

    private var aspectRatio = DEFAULT_ASPECT_RATIO

    init {
        if (attrs != null) { // если attrs(атрибуты) != null,
            val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.AspectRatioImageView
            )             // то обращаемся к контексту и считываем эти аттрибуты
            aspectRatio = a.getFloat(R.styleable.AspectRatioImageView_aspectRatio, DEFAULT_ASPECT_RATIO)
            a.recycle()     // необходимо вызвать метод recycle(), если мы его вызовем, то это нас защитит от неэффективного расходования ресурсов
            // нужен, чтобы высвободить ресурс и больше не обращаться к нему
        }
    }

    /** переопределим метод onMeasure() */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val newHeight =
            (measuredWidth / aspectRatio).toInt() // вычислим новую высоту, относительно заданного соотношения сторон
        // (для этого разделим ширину на коэффициент соотношения сторон)

        setMeasuredDimension(                    // в этот метод передаём нашу ширину и новую вычислинную высоту
            measuredWidth,
            newHeight
        )
    }
}
