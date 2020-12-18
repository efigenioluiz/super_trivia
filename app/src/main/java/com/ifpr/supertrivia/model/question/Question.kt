package com.ifpr.supertrivia.model.question

import com.ifpr.supertrivia.model.category.Category

class Question(
    val question: String,
    val difficulty: String,
    val category: Category,
    val answers: Answers
) {
}