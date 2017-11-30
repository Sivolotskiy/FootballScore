package com.footballscore.footballscore.ui.adapters.decorators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.footballscore.footballscore.R;


public class ItemListDividerDecorator extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mHorizontalPadding;

    public ItemListDividerDecorator(Context context, int drawable) {
        mDivider = context.getResources().getDrawable(drawable);
    }

    public ItemListDividerDecorator(Context context, int drawable, int leftPadding) {
        mDivider = context.getResources().getDrawable(drawable);
        mHorizontalPadding = context.getResources().getDimensionPixelOffset(R.dimen.divider_left_margin);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft() + mHorizontalPadding;
        int right = parent.getWidth();
        int childCount = parent.getChildCount() - 1;

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

        }
    }
}
