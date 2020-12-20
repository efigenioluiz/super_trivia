package com.ifpr.supertrivia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.model.category.Category
import com.ifpr.supertrivia.model.question.Answer
import kotlinx.android.synthetic.main.item_answer.view.*

class AnswerAdapter () : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    private var answers = listOf<Answer>()

    private var answerSelect: Int = 0
    private var select: Boolean = false


    override fun getItemViewType(position: Int): Int {
        return if (select && position == answerSelect) {
            R.layout.item_answer_select
        } else {
            R.layout.item_answer
        }
    }

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
            itemView.txtDescription.text = answer.description.replace("&quot;"," ' ").replace("&#039;"," ' ")


            itemView.setOnClickListener {
                select = !select

                val position = answers.indexOf(answer)

                answerSelect = position
                notifyItemChanged(position)

            }


        }

    }
    fun setAnswers(list: List<Answer>){
        answers = list
        notifyDataSetChanged()
    }
    fun getAnswer(): Answer? {
        if (select) {
            return answers[answerSelect]
        }
        return null
    }
}