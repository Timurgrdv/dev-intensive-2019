package ru.skillbranch.devintensive


import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage: ImageView // отложенная инициализация, что поле будет обязяательно будет проинициализировано, но позже
    lateinit var textTxt: TextView // отложенная инициализация, что поле будет обязяательно будет проинициализировано, но позже
    lateinit var messageEt: EditText // отложенная инициализация, что поле будет обязяательно будет проинициализировано, но позже
    lateinit var sendBtn: ImageView // отложенная инициализация, что поле будет обязяательно будет проинициализировано, но позже

    lateinit var benderObj: Bender

    /**
     * Вызывается при первом создании или перезапуске Activity.
     *
     * здесь задаётся внешний вид активности (UI) через метод setContentView().
     * инициализируются представления
     * представления связываются с необходимыми данными и ресурсами
     * связываются данные со списками
     *
     * Этот метод также представляет Bundle, содержащий ранее сохраненное
     * состояние Activity, если оно было
     *
     * Всегда сопровождается вызовом onStart().
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** проинициализируем наши поля*/
//        benderImage = findViewById(R.id.iv_bender)
        benderImage = iv_bender     // можно записать так, т.к. мы используем android extantion
        textTxt = tv_text     // можно записать так, т.к. мы используем android extantion
        messageEt = et_message     // можно записать так, т.к. мы используем android extantion
        sendBtn = iv_send     // можно записать так, т.к. мы используем android extantion


        makeSendActionDone(messageEt)
        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        Log.d("M_MainActivity", "onCreate $status $question")

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(
            Color.rgb(r, g, b),
            PorterDuff.Mode.MULTIPLY
        ) // применяем наложение цветового фильтра

//        textTxt.setText(benderObj.askQuestion())
        textTxt.text = benderObj.askQuestion()    // на котлине можно так
        sendBtn.setOnClickListener(this)  // повесим слушатель на кнопку
    }

    private fun makeSendActionDone(messageEt: EditText) {
        messageEt.setRawInputType(InputType.TYPE_CLASS_TEXT)
        messageEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) sendBtn.performClick()
            false
        }
    }

    /** Если Activity возвращается в приоритетный режим после вызова onStop(),
     * то в этом случае вызывается метод onRestart().
     * Т.е. вызввается после того, как Activity была остановлена и снова запущена пользователем.
     *  Всегда сопровождается вызовом метода onStart().
     *
     *  Используется для специальных действий, которые должный выполняться только при повторном запуске Activity
     */

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    /** При вызове onStart() окно еще не видно пользователю, но вскоре будет видно.
     * Вызывается непосредственно перед тем, как активность становится видимой пользователю.
     *
     * Чтение из бызы данных
     * Запуск сложной анимации
     * Запуск потоков, отслеживания показаний датчиков, запрос GPS, таймеров, сервисов и других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Затем следует onResume(), если Activity выходит на передний план.
     */
    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    /** Вызывается , когда Activity начианет взаимодействовать с пользователем.
     *
     * запуск воспроизведения анимации, аудио или видео
     * регистрация любых BroadcastReciever или других процессов, которые вы освободили/приостановилилв onPause()
     * выполнеие любых других инициализаций, которые должны происходить, когда Activity вновь активна (камера).
     *
     * Тут должен быть максимально легкий и быстрый код, чтобы приложение оставалось отзывчивым
     */

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    /**
     * Метод onPause() вызывается после сворачивания текущей активности или перехода к новому.
     * от onPause() можно перейти к вызову либо onResume(), либо onStop().
     *
     * остановка анимации, аудио и видое
     * сохранение состояния пользовательского ввода(легкие процессы)
     * сохранение в DB если данные должны быть доступны в новой Activity
     * остановка сервисов, подписок, BroadcastReciver
     *
     * Тут должен быть максимально быстрый и лёгкий код, чтобы приложение оставалось отзывчивым
     * */
    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    /** Методо onStop() вызывается, когда Activity становится невидимым для пользователя.
     * Это может произойти при её уничтожении, или если была запущена другая Activity (сущестующая или новая),
     * перекрывшая окно текущей Activity.
     *
     * запись в базу данных
     * приостановка сложных анимаций
     * приостановка потоков, остлеживания показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Не вызывается при вызове метода finish() у Activity()
     */
    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    /**
     * Метод вызывается по окончании работы Activity, при вызове метода finish() или в случае,
     * когда система уничтожает экземпляр активности для освобождения ресурсов.
     */

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")

    }

    /** этот метод сохраняет состояние представления в Bundle
     * для API Level < 28 (Android P) этот метод будет выполняться до onStop(), и нет никаких гарантий относительно того,
     * произойдет ли это до или после onPause().
     * Для API Level  >= 28 будет вызван после метода onStop()
     * Не будет вызван если Activity будет явно закрыто пользователем при нажатии на системную клавишу back
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        // что такое bundle - это объект самого Android'а , который позволяет сохранить состояние нашего UI - интерфейса,
        // если система уничтожит наш процесс
        // bundle, по сути - набор "ключ" - "значений"
        // в bundle не рекомендуется сохранять больше 50Кбайт
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)

        Log.d("M_MainActivity", "onSaveInsranceState ${benderObj.status.name} ${benderObj.question.name}")
    }

    private fun makeErrorMessage() {
        val errorMessage = when (benderObj.question) {
            Bender.Question.NAME -> "Имя должно начинаться с заглавной буквы"
            Bender.Question.PROFESSION -> "Профессия должна начинаться со строчной буквы"
            Bender.Question.MATERIAL -> "Материал не должен содержать цифр"
            Bender.Question.BDAY -> "Год моего рождения должен содержать только цифры"
            Bender.Question.SERIAL -> "Серийный номер содержит только цифры, и их 7"
            else -> "Ты справился\nНа этом все, вопросов больше нет"
        }
        textTxt.text = errorMessage + "\n" + benderObj.question.question
        messageEt.setText("")
    }

    private fun isAnswerValid(): Boolean {
        return benderObj.question.validate(messageEt.text.toString())
    }

    private fun sendAnswer() {
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase()) // toLowerCase() - приводим к нижнему регистру
        // метод listenAnswer() возвращает pair(phrase, color)
        messageEt.setText("")
        val (r, g, b) = color
        benderImage.setColorFilter(
            Color.rgb(r, g, b),
            PorterDuff.Mode.MULTIPLY
        ) // применяем наложение цветового фильтра
        textTxt.text = phrase
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {     // если данное view не null ("?"), и имеет id = R.id.iv_send
            if (isAnswerValid())
                sendAnswer()
            else makeErrorMessage()
        }
    }
}

