package toolkit

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

// Common code:
expect fun runBlockingTest(block: suspend CoroutineScope.() -> Unit)
expect val testCoroutineContext: CoroutineContext