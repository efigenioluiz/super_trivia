package com.ifpr.supertrivia.model.question.verify

import com.ifpr.supertrivia.model.question.Answer

class AnswerVerify(
    val status: String,
    val correct_answer: Answer,
    val score: Int
) {
}