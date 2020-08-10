package com.wonhopark.memo

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("StaticFieldLeak") // 메모리 누수 관련된 경고를 무시
class MainActivity : AppCompatActivity() {

    lateinit var db: MemoDatabase
    var memoList = listOf<MemoEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MemoDatabase.getInstance(this)!!

        button_add.setOnClickListener {
            val memo = MemoEntity(null, edittext_memo.text.toString()) // id is auto generated
            insertMemo(memo)
        }
    }

    // 1. InsertData
    // 2. GetData
    // 3. Delete Data
    // 4. Set Recycler View

    fun insertMemo(memo: MemoEntity) {
        // 1. MainThread vs WorkThread(Background Thread)
        val insertTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.memoDAO().insert(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemos()
            }
        }

        insertTask.execute()
    }


    fun getAllMemos() {
        val getTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                memoList = db.memoDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(memoList)
            }
        }).execute()
    }

    fun deleteMemo() {

    }

    fun setRecyclerView(memoList: List<MemoEntity>) {

    }
}
