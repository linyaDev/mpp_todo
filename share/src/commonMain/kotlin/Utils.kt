package utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

internal expect val dispatcher: CoroutineDispatcher
internal expect class MyScope: CoroutineScope
