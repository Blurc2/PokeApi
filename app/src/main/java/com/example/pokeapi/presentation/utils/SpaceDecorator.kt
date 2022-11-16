package com.example.pokeapi.presentation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceDecorator(
    private val verticalSpaceHeight: Int = 0,
    private val horizontalSpaceHeight: Int = 0,
    private val addSpaceAtEnd: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (verticalSpaceHeight != 0) {
            if (parent.getChildAdapterPosition(view) == state.itemCount - 1 && addSpaceAtEnd)
                outRect.bottom = verticalSpaceHeight
            else if (parent.getChildAdapterPosition(view) < state.itemCount - 1)
                outRect.bottom = verticalSpaceHeight
        }
        if (horizontalSpaceHeight != 0) {
            outRect.left = horizontalSpaceHeight
            outRect.right = horizontalSpaceHeight
        }
    }
}