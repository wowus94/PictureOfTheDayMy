package com.example.pictureofthedaymy.view.layouts.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.pictureofthedaymy.R

class MyBehaviorButtonTwo(context: Context, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<Button>(context, attrs) {


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: Button,
        dependency: View
    ): Boolean {
        return dependency.id == R.id.btnOne
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: Button,
        dependency: View
    ): Boolean {
        if (dependency.id == R.id.btnOne)
            child.y = dependency.y
            child.x = dependency.y + child.width
        return super.onDependentViewChanged(parent, child, dependency)
    }

}