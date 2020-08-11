package com.wonhopark.memo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_memo.view.*

class MyAdapter(
    val context: Context,
    var list: List<MemoEntity>,
    var onDeleteListener: OnDeleteListener
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        // 갯수 리턴
        return list.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val tomato = LayoutInflater.from(context).inflate(R.layout.item_memo, p0, false)
        return MyViewHolder(tomato) // inner 클래스를 통한 객체 생성 후 리턴
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // onCreateView 에서 만든 객체(=MyViewHolder) 바인딩
        val memo = list[position]
        holder.iMemo.text = memo.memo
        // 리스트 아이템 롱 클릭 시 삭제 가능하도록
        holder.iRoot.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                onDeleteListener.onDeleteListener(memo)
                return true;
            }
        })
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iMemo = itemView.textview_memo;
        val iRoot = itemView.root;
    }
}