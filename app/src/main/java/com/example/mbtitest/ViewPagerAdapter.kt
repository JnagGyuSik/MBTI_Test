package com.example.mbtitest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity : FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4 //질문지 4개의 페이지를 씀
    }

    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.newInstance(position)
    }
}