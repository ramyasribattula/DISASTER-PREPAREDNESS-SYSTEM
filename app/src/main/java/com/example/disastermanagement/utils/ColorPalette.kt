package com.example.disastermanagement.utils

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.example.disastermanagement.R

/**
 * Centralized UI color palette helper.
 *
 * How to customize:
 * - Edit [palette] to add/remove colors from your predefined set.
 * - Optionally create multiple palettes (e.g., light/dark/brand) and switch by feature flag.
 * - Use getColorForIndex for deterministic coloring per position.
 * - Use getRandomColor for varied visual appearances.
 */
object ColorPalette {

    /**
     * Predefined color resource IDs.
     * Tip: Replace or expand this list with your brand colors.
     */
    private val paletteRes: List<Int> = listOf(
        R.color.primary_color,       // Blue
        R.color.accent_color,        // Orange
        R.color.difficulty_beginner, // Green
        R.color.alert_high,          // Deep orange
        R.color.alert_critical,      // Red
        R.color.difficulty_intermediate, // Amber
        R.color.alert_medium         // Yellow/Amber variant
    )

    /**
     * Deterministic color based on adapter position or any index you pass.
     */
    @ColorInt
    fun getColorForIndex(context: Context, index: Int): Int {
        val resId = paletteRes[index.mod(paletteRes.size)]
        return ContextCompat.getColor(context, resId)
    }

    /**
     * Pseudo-random color based on a stable key (e.g., item ID) to keep color consistent across binds.
     * If you truly want different every time, pass a random int as key.
     */
    @ColorInt
    fun getColorForKey(context: Context, key: Int): Int {
        val idx = (key and 0x7fffffff) % paletteRes.size
        val resId = paletteRes[idx]
        return ContextCompat.getColor(context, resId)
    }
}


