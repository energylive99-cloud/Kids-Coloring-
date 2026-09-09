package com.example.data

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import android.graphics.Region
import android.graphics.Path as AndroidPath
import com.example.model.ColoringShape

object ShapeGeometry {

    /**
     * Checks if a point (x, y) is inside a given Compose Path within the given width and height.
     */
    fun isPointInsidePath(path: Path, x: Float, y: Float, width: Float, height: Float): Boolean {
        // Fast bounds check
        val bounds = path.getBounds()
        if (x < bounds.left || x > bounds.right || y < bounds.top || y > bounds.bottom) {
            return false
        }

        return try {
            val androidPath = path.asAndroidPath()
            val region = Region()
            val clipBounds = android.graphics.Rect(
                bounds.left.toInt().coerceAtLeast(0),
                bounds.top.toInt().coerceAtLeast(0),
                bounds.right.toInt().coerceAtMost(width.toInt() + 1),
                bounds.bottom.toInt().coerceAtMost(height.toInt() + 1)
            )
            region.setPath(androidPath, Region(clipBounds))
            region.contains(x.toInt(), y.toInt())
        } catch (_: Exception) {
            // Fallback to bounding box if region creation fails
            true
        }
    }

    /**
     * Helper to create an ellipse path.
     */
    fun ovalPath(leftNorm: Float, topNorm: Float, rightNorm: Float, bottomNorm: Float, w: Float, h: Float): Path {
        return Path().apply {
            addOval(
                Rect(
                    left = leftNorm * w,
                    top = topNorm * h,
                    right = rightNorm * w,
                    bottom = bottomNorm * h
                )
            )
        }
    }

    /**
     * Helper to create a rounded rectangle path.
     */
    fun roundRectPath(leftNorm: Float, topNorm: Float, rightNorm: Float, bottomNorm: Float, radius: Float, w: Float, h: Float): Path {
        return Path().apply {
            addRoundRect(
                androidx.compose.ui.geometry.RoundRect(
                    rect = Rect(
                        left = leftNorm * w,
                        top = topNorm * h,
                        right = rightNorm * w,
                        bottom = bottomNorm * h
                    ),
                    radiusX = radius,
                    radiusY = radius
                )
            )
        }
    }

    /**
     * Helper to create polygon/star or multi-point path.
     */
    fun polygonPath(points: List<Pair<Float, Float>>, w: Float, h: Float): Path {
        return Path().apply {
            if (points.isNotEmpty()) {
                moveTo(points[0].first * w, points[0].second * h)
                for (i in 1 until points.size) {
                    lineTo(points[i].first * w, points[i].second * h)
                }
                close()
            }
        }
    }
}
