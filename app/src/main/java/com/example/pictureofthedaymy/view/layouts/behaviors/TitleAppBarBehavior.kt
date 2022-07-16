package com.example.pictureofthedaymy.view.layouts.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class TitleAppBarBehavior(context: Context, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<TextView>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        if (dependency is AppBarLayout) {
            child.y = dependency.y + 400
            child.x = dependency.y + child.width/2
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}