package com.alirezabdn.library;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Dispatches {@link NonFinalViewPager2.OnPageChangeCallback} events to subscribers.
 */
class CompositeOnPageChangeCallback extends NonFinalViewPager2.OnPageChangeCallback {
    @NonNull
    private final List<NonFinalViewPager2.OnPageChangeCallback> mCallbacks;

    CompositeOnPageChangeCallback(int initialCapacity) {
        mCallbacks = new ArrayList<>(initialCapacity);
    }

    /**
     * Adds the given callback to the list of subscribers
     */
    void addOnPageChangeCallback(NonFinalViewPager2.OnPageChangeCallback callback) {
        mCallbacks.add(callback);
    }

    /**
     * Removes the given callback from the list of subscribers
     */
    void removeOnPageChangeCallback(NonFinalViewPager2.OnPageChangeCallback callback) {
        mCallbacks.remove(callback);
    }

    /**
     * @see NonFinalViewPager2.OnPageChangeCallback#onPageScrolled(int, float, int)
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, @Px int positionOffsetPixels) {
        try {
            for (NonFinalViewPager2.OnPageChangeCallback callback : mCallbacks) {
                callback.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        } catch (ConcurrentModificationException ex) {
            throwCallbackListModifiedWhileInUse(ex);
        }
    }

    /**
     * @see NonFinalViewPager2.OnPageChangeCallback#onPageSelected(int)
     */
    @Override
    public void onPageSelected(int position) {
        try {
            for (NonFinalViewPager2.OnPageChangeCallback callback : mCallbacks) {
                callback.onPageSelected(position);
            }
        } catch (ConcurrentModificationException ex) {
            throwCallbackListModifiedWhileInUse(ex);
        }
    }

    /**
     * @see NonFinalViewPager2.OnPageChangeCallback#onPageScrollStateChanged(int)
     */
    @Override
    public void onPageScrollStateChanged(@NonFinalViewPager2.ScrollState int state) {
        try {
            for (NonFinalViewPager2.OnPageChangeCallback callback : mCallbacks) {
                callback.onPageScrollStateChanged(state);
            }
        } catch (ConcurrentModificationException ex) {
            throwCallbackListModifiedWhileInUse(ex);
        }
    }

    private void throwCallbackListModifiedWhileInUse(ConcurrentModificationException parent) {
        throw new IllegalStateException(
                "Adding and removing callbacks during dispatch to callbacks is not supported",
                parent
        );
    }

}
