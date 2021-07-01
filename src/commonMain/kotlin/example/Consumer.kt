package example

import kotlinx.coroutines.channels.*

class Consumer<T: Any> {
    suspend inline fun consumeReceiveCatching(
            receiveFS: ReceiveChannel<T>,
            onCloseEvent: ()->Unit = {}
    ){
        val x: ChannelResult<T> = receiveFS.receiveCatching()
        println(x)
        x
                .onSuccess {
                    when(it) {
                        is ChannelResult<*> -> error("This should not be a ChannelResult!")
                        else -> println("PASS")
                    }
                }
                .onClosed {
                    onCloseEvent()
                }
                .onFailure {
                    throw it ?: error("It failed and i don't know why. :(")
                }
    }

    suspend inline fun consumeReceiveCatchingWorking(
            receiveFS: ReceiveChannel<T>,
            onCloseEvent: ()->Unit = {}
    ){
        val x: ChannelResult<T> = receiveFS.receiveCatching()
        println(x)
        x
                .onSuccess {
                    when(it) {
                        is ChannelResult<*> -> it.onSuccess {
                            when(it){
                                is FSWebsocketPackage -> println("PASS 2")
                                is ChannelResult<*> -> error("This should definetly not be a ChannelResult!")
                                else -> error("Nope.")
                            }
                        }
                        else -> println("PASS 1")
                    }
                }
                .onClosed {
                    onCloseEvent()
                }
                .onFailure {
                    throw it ?: error("It failed and i don't know why. :(")
                }
    }

    suspend inline fun consumeReceive(
            receiveFS: ReceiveChannel<T>,
            onCloseEvent: ()->Unit = {}
    ){
        try {
            while (true){
                val x = receiveFS.receive()
                when(x){
                    is ChannelResult<*> -> error("This should not be a ChannelResult!")
                    else -> println("PASS")
                }
            }
        } catch (e: ClosedReceiveChannelException) {
            onCloseEvent()
        }
    }

    suspend inline fun consumeFor(
            receiveFS: ReceiveChannel<T>,
            onCloseEvent: ()->Unit = {}
    ){

        for(x in receiveFS){
            when(x){
                is ChannelResult<*> -> error("This should not be a ChannelResult!")
                else -> println("PASS")
            }
        }
        onCloseEvent()
    }
}
