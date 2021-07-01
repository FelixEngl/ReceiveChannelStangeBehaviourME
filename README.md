# ReceiveChannelStangeBehaviourME
Minimal example of a strange behaviour of the JVM implementation of the ReceiveChannel::receiveCatching.

I found a bug with `ReceiveChannel.receiveCatching` in version 1.5.0 of `kotlinx.coroutines`.

Basically defining using `ReceiveChannel.receiveCatching` in a MPP results in inconsistent behaviour of the code, when compiled to different platforms. This example shows what happens when the code is compiled to JS and JVM.

With JS the code behaves like expected, but when I compile the same code to a JVM-11-target, the function returns a 
 `ChannelResult<ChannelResult<T>>` not a `ChannelResult<T>`. Which contradicts the return type of the function `ReceiveChannel.receiveCatching`.
