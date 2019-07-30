package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }


    /** Если ответы в нашем вопросе(question) содержит тот, ответ который мы получили в качестве аргумента в метод listenerAnswer,
     * то тогда bender вернет "отлично, это правильный ответ!", иначе вренет: "это не правильный ответ!"
     *
     */

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        return if (question.answer.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color // мы ожидаем пару, поэтому нам нужно вернуть еще и цвет
        } else {
            if (status == Status.CRITICAL) {
                status = Status.NORMAL
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
            status = status.nextStatus()
            "Это неправильный ответ\n${question.question}" to status.color // мы ожидаем пару, поэтому нам нужно вернуть еще и цвет
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) { // если теущий порядок объявления нашей переменной меньше, чем последний индекс во
                values()[this.ordinal + 1]                  // всех наших values(значения всех наших перечислений(NORMAL, WARNING, ..), то
                // тогда верни мне следующее значение,
            } else {                                        // иначе верни мне нулевое значение
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun validate(answer: String): Boolean =
                answer.trim().firstOrNull()?.isUpperCase() ?: false

        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun validate(answer: String): Boolean =
                answer.trim().firstOrNull()?.isLowerCase() ?: false
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun validate(answer: String): Boolean = answer.trim().contains(Regex("\\d")).not()
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun validate(answer: String): Boolean = answer.trim().contains(Regex("^[0-9]*$"))
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): Boolean = answer.trim().contains(Regex("^[0-9]{7}$"))
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): Boolean = true
        };

        abstract fun nextQuestion(): Question  // абстрактный метод, это нужно для того, чтобы переобределение нашей функции было внутри каждого из нашего пересислений
        abstract fun validate(answer: String): Boolean
    }
}