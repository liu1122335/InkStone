package com.example.inkstonedemo1.viewmodel

import android.graphics.Bitmap
import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import com.example.inkstonedemo1.model.write.Bezier
import com.example.inkstonedemo1.model.write.ControllerPoint
import com.example.inkstonedemo1.utils.WritingBoardConfig.NORMAL_WIDTH
import com.example.inkstonedemo1.utils.WritingBoardConfig.STEP_FACTOR
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Math.exp

class WriteScreenViewModel : ViewModel() {
    private val _viewStates = MutableStateFlow(WritingBoardViewStates())
    val viewStates = _viewStates.asStateFlow()
    private val bezier = Bezier()

    fun dispatch(action: WritingBoardViewAction) {
        when (action) {
            is WritingBoardViewAction.ActionDown -> onActionDown(action.event)
            is WritingBoardViewAction.ActionMove -> onActionMove(action.event)
            is WritingBoardViewAction.ActionUp -> onActionUp(action.event)
            is WritingBoardViewAction.ConfirmItem -> confirmItem(action.bitmap)
            is WritingBoardViewAction.DeleteItem -> deleteItem()
        }
    }

    private fun deleteItem() {
        val bitmapList = viewStates.value.bitmapList.toMutableList()
        if (bitmapList.isNotEmpty()) {
            bitmapList.removeAt(bitmapList.size - 1)
            _viewStates.value = _viewStates.value.copy(bitmapList = bitmapList)
        }
    }

    private fun confirmItem(bitmap: Bitmap) {
        val bitmapList = viewStates.value.bitmapList.toMutableList().apply { add(bitmap) }
        _viewStates.value = viewStates.value.copy(bitmapList = bitmapList)
    }

    private fun onActionDown(event: MotionEvent) {
        val curPoint = ControllerPoint(event.x, event.y)
        curPoint.width = 0f
        _viewStates.value = _viewStates.value.copy(
            pointList = emptyList(),
            curPoint = curPoint
        )
    }

    private fun onActionMove(event: MotionEvent) {
        val lastPoint = viewStates.value.curPoint
        val curPoint = ControllerPoint(event.x, event.y)
        val lineWidth = calWidth(event = event)
        curPoint.width = lineWidth
        if (viewStates.value.pointList.size < 2) {
            bezier.init(lastPoint, curPoint)
        } else {
            bezier.addNode(curPoint)
        }
        val curDis = getDistance(event)
        val steps: Int = 1 + (curDis / STEP_FACTOR).toInt()
        val step = 1.0 / steps
        val list = mutableListOf<ControllerPoint>()
        var t = 0.0
        while (t < 1.0) {
            val point: ControllerPoint = bezier.getPoint(t)
            list.add(point)
            t += step
        }
        addPoints(list)
        _viewStates.value = _viewStates.value.copy(curPoint = curPoint)
    }

    private fun onActionUp(event: MotionEvent) {
        bezier.end()
        _viewStates.value = _viewStates.value.copy(pointList = emptyList())
    }

    private fun calWidth(event: MotionEvent): Float {
        val distance = getDistance(event)
        val calVel = distance * 0.002
        val width = NORMAL_WIDTH * maxOf(exp(-calVel), 0.2)
        return width.toFloat()
    }

    private fun getDistance(event: MotionEvent): Float {
        val lastX = viewStates.value.curPoint.x
        val lastY = viewStates.value.curPoint.y
        return (event.x - lastX) * (event.x - lastX) + (event.y - lastY) * (event.y - lastY)
    }

    private fun addPoints(pointList: List<ControllerPoint>) {
        val list = mutableListOf<ControllerPoint>()
        list.addAll(_viewStates.value.pointList)
        list.addAll(pointList)
        _viewStates.value = _viewStates.value.copy(pointList = list)
    }
}

data class WritingBoardViewStates(
    val pointList: List<ControllerPoint> = listOf(),
    val curPoint: ControllerPoint = ControllerPoint(),
    val bitmapList: List<Bitmap> = listOf()
)

sealed class WritingBoardViewAction {
    data class ActionDown(val event: MotionEvent) : WritingBoardViewAction()
    data class ActionMove(val event: MotionEvent) : WritingBoardViewAction()
    data class ActionUp(val event: MotionEvent) : WritingBoardViewAction()
    data class ConfirmItem(val bitmap: Bitmap) : WritingBoardViewAction()
    object DeleteItem : WritingBoardViewAction()
}