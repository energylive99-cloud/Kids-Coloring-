package com.example.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coloring.ColorPalette
import com.example.coloring.ColoringSessionState
import com.example.model.BrushSize
import com.example.model.ColoringTool
import com.example.model.DrawingStroke
import com.example.model.StrokePoint
import com.example.ui.viewmodel.ColoringViewModel
import kotlinx.coroutines.delay

@Composable
fun ColoringCanvasScreen(
    pageId: String,
    onBackClick: () -> Unit,
    onNavigateToGallery: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: ColoringViewModel = viewModel(
        key = "canvas_$pageId",
        factory = ColoringViewModel.provideFactory(pageId, context)
    )

    val state by viewModel.sessionState.collectAsStateWithLifecycle()

    var showClearDialog by remember { mutableStateOf(false) }
    var showColorPickerDialog by remember { mutableStateOf(false) }

    // Auto dismiss save message after 3 seconds
    LaunchedEffect(state.saveSuccessMessage) {
        if (state.saveSuccessMessage != null) {
            delay(3000)
            viewModel.clearSaveMessage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
            .statusBarsPadding()
            .navigationBarsPadding()
            .testTag("coloring_canvas_screen")
    ) {
        // 1. Top Controls Bar (Back, Undo, Redo, Clear, Save, Share)
        CanvasTopBar(
            title = state.page.title,
            canUndo = state.canUndo,
            canRedo = state.canRedo,
            isSaving = state.isSaving,
            onBack = onBackClick,
            onUndo = { viewModel.undo() },
            onRedo = { viewModel.redo() },
            onClear = { showClearDialog = true },
            onSave = {
                viewModel.saveDrawing {
                    // Saved successfully
                }
            },
            onShare = { viewModel.shareDrawing(context) }
        )

        // Success snackbar notification
        AnimatedVisibility(visible = state.saveSuccessMessage != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFE8F5E9),
                    shadowElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = state.saveSuccessMessage ?: "",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "View Gallery",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF1B5E20),
                            modifier = Modifier
                                .clickable { onNavigateToGallery() }
                                .padding(4.dp)
                        )
                    }
                }
            }
        }

        // 2. Interactive Drawing Canvas (Centered, Aspect Ratio 1:1, Max Width constrained)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            InteractiveCanvasView(
                state = state,
                onCanvasTap = { x, y, w, h ->
                    viewModel.handleCanvasTap(x, y, w, h)
                },
                onStrokeDrawn = { stroke ->
                    viewModel.addStroke(stroke)
                }
            )
        }

        // 3. Tool Selectors (Fill Bucket, Brush, Eraser) + Brush Sizes
        ToolModeSelectorBar(
            currentTool = state.currentTool,
            brushSize = state.brushSize,
            onToolSelected = { viewModel.selectTool(it) },
            onBrushSizeSelected = { viewModel.selectBrushSize(it) }
        )

        // 4. Color Palette Strip with Custom Picker "+" button
        ColorPaletteBar(
            selectedColor = state.selectedColor,
            onColorSelected = { viewModel.selectColor(it) },
            onOpenCustomPicker = { showColorPickerDialog = true }
        )
    }

    // Confirmation Dialog for Clearing Canvas
    if (showClearDialog) {
        AlertDialog(
            onDismissRequest = { showClearDialog = false },
            title = {
                Text(
                    text = "Start Over?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            text = {
                Text(
                    text = "Do you want to clear your drawing and start fresh? (You can always press Undo if you change your mind!)",
                    fontSize = 15.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.clearCanvas()
                        showClearDialog = false
                    },
                    modifier = Modifier.testTag("confirm_clear_button")
                ) {
                    Text(
                        text = "Clear Drawing",
                        color = Color(0xFFD32F2F),
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearDialog = false }) {
                    Text("Keep Coloring", fontWeight = FontWeight.SemiBold)
                }
            }
        )
    }

    // Custom Color Picker Dialog with large colorful swatches
    if (showColorPickerDialog) {
        AlertDialog(
            onDismissRequest = { showColorPickerDialog = false },
            title = {
                Text(
                    text = "Choose a Color 🌈",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            text = {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.height(260.dp)
                ) {
                    items(ColorPalette.extraColors) { color ->
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = if (state.selectedColor == color) 3.dp else 1.dp,
                                    color = if (state.selectedColor == color) Color(0xFF2D3748) else Color(0x33000000),
                                    shape = CircleShape
                                )
                                .clickable {
                                    viewModel.selectColor(color)
                                    showColorPickerDialog = false
                                }
                        ) {
                            if (state.selectedColor == color) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = if (color == Color.White) Color.Black else Color.White,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showColorPickerDialog = false }) {
                    Text("Close", fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

@Composable
private fun CanvasTopBar(
    title: String,
    canUndo: Boolean,
    canRedo: Boolean,
    isSaving: Boolean,
    onBack: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    onClear: () -> Unit,
    onSave: () -> Unit,
    onShare: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Back Button
        Surface(
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 2.dp,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .clickable(onClick = onBack)
                .testTag("canvas_back_button")
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF2D3748)
                )
            }
        }

        // Title
        Text(
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A202C),
            maxLines = 1
        )

        // Actions Group: Undo, Redo, Clear, Save, Share
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Undo
            IconButton(
                onClick = onUndo,
                enabled = canUndo,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("undo_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Undo,
                    contentDescription = "Undo",
                    tint = if (canUndo) Color(0xFF1976D2) else Color(0xFFB0BEC5)
                )
            }

            // Redo
            IconButton(
                onClick = onRedo,
                enabled = canRedo,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("redo_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Redo,
                    contentDescription = "Redo",
                    tint = if (canRedo) Color(0xFF1976D2) else Color(0xFFB0BEC5)
                )
            }

            // Clear
            IconButton(
                onClick = onClear,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("clear_button")
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteSweep,
                    contentDescription = "Clear All",
                    tint = Color(0xFFE53935)
                )
            }

            // Share
            IconButton(
                onClick = onShare,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("share_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color(0xFF43A047)
                )
            }

            // Save
            Surface(
                shape = CircleShape,
                color = Color(0xFFFFB300),
                shadowElevation = 2.dp,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .clickable(enabled = !isSaving, onClick = onSave)
                    .testTag("save_button")
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (isSaving) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "Save Drawing",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InteractiveCanvasView(
    state: ColoringSessionState,
    onCanvasTap: (Float, Float, Float, Float) -> Unit,
    onStrokeDrawn: (DrawingStroke) -> Unit
) {
    // Keep local active stroke points while finger is dragging
    val activeStrokePoints = remember { mutableStateListOf<StrokePoint>() }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .widthIn(max = 600.dp)
            .aspectRatio(1f)
            .shadow(6.dp, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .testTag("coloring_canvas_area")
    ) {
        val widthPx = constraints.maxWidth.toFloat()
        val heightPx = constraints.maxHeight.toFloat()

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(state.currentTool) {
                    if (state.currentTool == ColoringTool.FILL_BUCKET) {
                        detectTapGestures { offset ->
                            onCanvasTap(offset.x, offset.y, size.width.toFloat(), size.height.toFloat())
                        }
                    } else {
                        detectDragGestures(
                            onDragStart = { offset ->
                                activeStrokePoints.clear()
                                activeStrokePoints.add(StrokePoint(offset.x, offset.y))
                            },
                            onDrag = { change, _ ->
                                change.consume()
                                activeStrokePoints.add(StrokePoint(change.position.x, change.position.y))
                            },
                            onDragEnd = {
                                if (activeStrokePoints.size >= 2) {
                                    val newStroke = DrawingStroke(
                                        points = activeStrokePoints.toList(),
                                        color = state.selectedColor,
                                        strokeWidth = state.brushSize.strokeWidthPx,
                                        isEraser = state.currentTool == ColoringTool.ERASER
                                    )
                                    onStrokeDrawn(newStroke)
                                }
                                activeStrokePoints.clear()
                            },
                            onDragCancel = {
                                activeStrokePoints.clear()
                            }
                        )
                    }
                }
        ) {
            val w = size.width
            val h = size.height

            // 1. Draw all shape fills sorted by zIndex
            val sortedShapes = state.page.shapes.sortedBy { it.zIndex }
            for (shape in sortedShapes) {
                val fillColor = state.fills[shape.id] ?: shape.defaultFill
                val path = shape.pathBuilder(w, h)
                drawPath(path = path, color = fillColor)
            }

            // 2. Draw committed freehand strokes
            for (stroke in state.strokes) {
                if (stroke.points.size < 2) continue

                val strokeColor = if (stroke.isEraser) Color.White else stroke.color
                val strokePath = Path().apply {
                    moveTo(stroke.points[0].x, stroke.points[0].y)
                    for (i in 1 until stroke.points.size) {
                        lineTo(stroke.points[i].x, stroke.points[i].y)
                    }
                }

                drawPath(
                    path = strokePath,
                    color = strokeColor,
                    style = Stroke(
                        width = stroke.strokeWidth,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }

            // 3. Draw currently active in-progress drag stroke
            if (activeStrokePoints.size >= 2) {
                val activeColor = if (state.currentTool == ColoringTool.ERASER) Color.White else state.selectedColor
                val activePath = Path().apply {
                    moveTo(activeStrokePoints[0].x, activeStrokePoints[0].y)
                    for (i in 1 until activeStrokePoints.size) {
                        lineTo(activeStrokePoints[i].x, activeStrokePoints[i].y)
                    }
                }

                drawPath(
                    path = activePath,
                    color = activeColor,
                    style = Stroke(
                        width = state.brushSize.strokeWidthPx,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }

            // 4. Draw bold black outline strictly on top so line art stays pristine!
            for (shape in sortedShapes) {
                val path = shape.pathBuilder(w, h)
                drawPath(
                    path = path,
                    color = Color(0xFF212121),
                    style = Stroke(
                        width = 5.5f,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }
    }
}

@Composable
private fun ToolModeSelectorBar(
    currentTool: ColoringTool,
    brushSize: BrushSize,
    onToolSelected: (ColoringTool) -> Unit,
    onBrushSizeSelected: (BrushSize) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tool Buttons (Fill, Brush, Eraser)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Fill Bucket
            ToolChip(
                selected = currentTool == ColoringTool.FILL_BUCKET,
                icon = Icons.Default.FormatColorFill,
                label = "Tap Fill",
                onClick = { onToolSelected(ColoringTool.FILL_BUCKET) },
                testTag = "tool_fill_bucket"
            )

            // Brush
            ToolChip(
                selected = currentTool == ColoringTool.BRUSH,
                icon = Icons.Default.Brush,
                label = "Brush",
                onClick = { onToolSelected(ColoringTool.BRUSH) },
                testTag = "tool_brush"
            )

            // Eraser
            ToolChip(
                selected = currentTool == ColoringTool.ERASER,
                icon = Icons.Default.AutoFixHigh,
                label = "Eraser",
                onClick = { onToolSelected(ColoringTool.ERASER) },
                testTag = "tool_eraser"
            )
        }

        // Brush Size Buttons (Visible for Brush and Eraser)
        if (currentTool != ColoringTool.FILL_BUCKET) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushSize.entries.forEach { size ->
                    val isSelected = brushSize == size
                    Surface(
                        shape = CircleShape,
                        color = if (isSelected) Color(0xFF2D3748) else Color(0xFFE2E8F0),
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .clickable { onBrushSizeSelected(size) }
                            .testTag("brush_size_${size.name.lowercase()}")
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .size((size.displaySizeDp * 0.5f).dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) Color.White else Color(0xFF718096))
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ToolChip(
    selected: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
    testTag: String
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (selected) Color(0xFFFF5252) else Color.White,
        shadowElevation = if (selected) 3.dp else 1.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag(testTag)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (selected) Color.White else Color(0xFF4A5568),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                color = if (selected) Color.White else Color(0xFF4A5568)
            )
        }
    }
}

@Composable
private fun ColorPaletteBar(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    onOpenCustomPicker: () -> Unit
) {
    val scrollState = rememberScrollState()

    Surface(
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Standard colors
            ColorPalette.defaultPalette.forEach { namedColor ->
                val isSelected = selectedColor == namedColor.color
                ColorSwatch(
                    color = namedColor.color,
                    isSelected = isSelected,
                    onClick = { onColorSelected(namedColor.color) }
                )
            }

            // "+" Button for extended colors picker
            Surface(
                shape = CircleShape,
                color = Color(0xFFF1F5F9),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFCBD5E1)),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onOpenCustomPicker)
                    .testTag("custom_color_picker_button")
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "More Colors",
                        tint = Color(0xFF64748B),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ColorSwatch(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val size = if (isSelected) 48.dp else 42.dp

    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
            .border(
                width = if (isSelected) 3.dp else 1.dp,
                color = if (isSelected) Color(0xFF2D3748) else Color(0x33000000),
                shape = CircleShape
            )
            .clickable(onClick = onClick)
            .testTag("color_${color.value}"),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = if (color == Color.White || color == Color(0xFFFFCC00)) Color.Black else Color.White,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}
