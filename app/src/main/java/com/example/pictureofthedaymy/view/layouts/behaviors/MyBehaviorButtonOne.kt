package com.example.pictureofthedaymy.view.layouts.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.pictureofthedaymy.R

class MyBehaviorButtonOne(context: Context, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<View>(context,attrs) {


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency.id == R.id.bottomSheetContainer
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (dependency.id == R.id.bottomSheetContainer)
            child.y = dependency.y
        return super.onDependentViewChanged(parent, child, dependency)
    }

}