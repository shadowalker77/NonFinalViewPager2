package com.alirezabdn.whyfinal.widget;

import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adds space between pages via the {@link NonFinalViewPager2.PageTransformer} API.
 * <p>
 * Internally relies on {@link View#setTranslationX} and {@link View#setTranslationY}.
 * <p>
 * Note: translations on pages are not reset when this adapter is changed for another one, so you
 * might want to set them manually to 0 when dynamically switching to another transformer, or
 * when switching ViewPager2 orientation.
 *
 * @see NonFinalViewPager2#setPageTransformer
 * @see CompositePageTransformer
 */
public class MarginPageTransformer implements NonFinalViewPager2.PageTransformer {
    private final int mMarginPx;

    /**
     * Creates a {@link MarginPageTransformer}.
     *
     * @param marginPx non-negative margin
     */
    public MarginPageTransformer(@Px int marginPx) {
        Preconditions.checkArgumentNonnegative(marginPx, "Margin must be non-negative");
        mMarginPx = marginPx;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        NonFinalViewPager2 viewPager = requireViewPager(page);

        float offset = mMarginPx * position;

        if (viewPager.getOrientation() == NonFinalViewPager2.ORIENTATION_HORIZONTAL) {
            page.setTranslationX(viewPager.isRtl() ? -offset : offset);
        } else {
            page.setTranslationY(offset);
        }
    }

    private NonFinalViewPager2 requireViewPager(@NonNull View page) {
        ViewParent parent = page.getParent();
        ViewParent parentParent = parent.getParent();

        if (parent instanceof RecyclerView && parentParent instanceof NonFinalViewPager2) {
            return (NonFinalViewPager2) parentParent;
        }

        throw new IllegalStateException(
                "Expected the page view to be managed by a ViewPager2 instance.");
    }
}
