package com.ifpr.supertrivia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.adapters.AnswerApdater.*
import com.ifpr.supertrivia.model.category.Category
import com.ifpr.supertrivia.model.question.Answer
import com.ifpr.supertrivia.model.question.Answers
import kotlinx.android.synthetic.main.item_answer.view.*
import kotlinx.android.synthetic.main.item_category.view.*

class AnswerApdater () : RecyclerView.Adapter<ViewHolder>() {
    private var answers = listOf<Answer>()


    override fun getItemViewType(position: Int)= R.layout.item_answer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (answers.isNotEmpty()) {
            val answer = answers[position]
            holder.fillView(answer)
        }
    }

    override fun getItemCount() = answers.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(answer: Answer) {

            itemView.txtOrder.text = answer.order.toString()
        }

    }
    fun setAnswers(listAnswer: List<Answer>){
        answers = listAnswer
    }
}