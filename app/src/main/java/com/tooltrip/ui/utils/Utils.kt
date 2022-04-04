package com.tooltrip.ui.utils

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//USED TO OBSERVE LIVEDATA OR FLOW

fun <T> LifecycleCoroutineScope.collectLatest(
    flowData: MutableSharedFlow<T>,
    action: (t: T) -> Unit
) {
    launch {
        flowData.collectLatest {
            action(it)
        }
    }
}

//fun <T> LifecycleCoroutineScope.collectLatest(flowData: MutableSharedFlow<T>, action: () -> Unit) {
//    launch {
//        flowData.collectLatest {
//            action()
//        }
//    }
//}