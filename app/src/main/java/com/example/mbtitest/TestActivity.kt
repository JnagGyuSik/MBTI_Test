package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2

class TestActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2

    val questionnaireResults = QuestionnaireResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false //화면 터치하면 페이지넘어가는 기능을 막음
    }

    //다음 페이지로 넘기는 함수
    fun moveToNext() {
        if (viewPager.currentItem==3){
            //마지막 페이지 -> 결과 화면으로 이동
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("results", ArrayList(questionnaireResults.result))
            startActivity(intent)
        }else{
            val nextItem = viewPager.currentItem + 1
            if (nextItem < viewPager.adapter?.itemCount?:0){
                viewPager.setCurrentItem(nextItem,true)
            }
        }
    }
}

class QuestionnaireResults {
    val result = mutableListOf<Int>()

    //답변이 저장되는 곳
    fun aadResponse(reponse: List<Int>){
        //같은 답변끼리 그룹으로 묶음 -> 카운트함 -> 가장많은 그룹을 찾음 -> 그 값을 키로 함
        val most = reponse.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        most?.let { result.add(it) }
    }
}