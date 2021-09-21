package com.alirezabdn.whyfinal.widget;

import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows for combining multiple {@link NonFinalViewPager2.PageTransformer} objects.
 *
 * @see NonFinalViewPager2#setPageTransformer
 * @see MarginPageTransformer
 */
public class CompositePageTransformer implements NonFinalViewPager2.PageTransformer {
    private final List<NonFinalViewPager2.PageTransformer> mTransformers = new ArrayList<>();

    /**
     * Adds a page transformer to the list.
     * <p>
     * Transformers will be executed in the order that they were added.
     */
    public void addTransformer(@NonNull NonFinalViewPager2.PageTransformer transformer) {
        mTransformers.add(transformer);
    }

    /** Removes a page transformer from the list. */
    public void removeTransformer(@NonNull NonFinalViewPager2.PageTransformer transformer) {
        mTransformers.remove(transformer);
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        for (NonFinalViewPager2.PageTransformer transformer : mTransformers) {
            transformer.transformPage(page, position);
        }
    }
}
