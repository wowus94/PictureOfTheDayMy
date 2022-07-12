package com.example.pictureofthedaymy.view.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pictureofthedaymy.view.picture.PictureOfTheDayFragment

class ViewPager2AdapterForPictureOfTheDayFragment(fr: Fragment) : FragmentStateAdapter(fr) {
    private val bundleToday =
        Bundle().apply { putInt(PictureOfTheDayFragment.BUNDLE_PICTURE_OF_THE_DAY, 0) }
    private val bundleYesterday =
        Bundle().apply { putInt(PictureOfTheDayFragment.BUNDLE_PICTURE_OF_THE_DAY, 1) }

    private val fragments = arrayOf(
        PictureOfTheDayFragment.newInstance(bundleToday),
        PictureOfTheDayFragment.newInstance(bundleYesterday)
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}