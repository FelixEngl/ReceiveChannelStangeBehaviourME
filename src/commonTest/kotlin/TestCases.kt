import example.Consumer
import example.FSWebsocketPackage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import toolkit.runBlockingTest
import kotlin.test.Test

class TestCases {
    @Test
    fun testFailing(){
        runBlockingTest {
            val channelIn = Channel<FSWebsocketPackage>()
            launch {
                channelIn.send(FSWebsocketPackage(true, byteArrayOf(1,2,3,4,5,6)))
                channelIn.close()
            }
            val consumer = Consumer<FSWebsocketPackage>()
            consumer.consumeReceiveCatching(channelIn)
        }
    }

    @Test
    fun testWorking1(){
        runBlockingTest {
            val channelIn = Channel<FSWebsocketPackage>()
            launch {
                channelIn.send(FSWebsocketPackage(true, byteArrayOf(1,2,3,4,5,6)))
                channelIn.close()
            }
            val consumer = Consumer<FSWebsocketPackage>()
            consumer.consumeReceive(channelIn)
        }
    }

    @Test
    fun testWorking2(){
        runBlockingTest {
            val channelIn = Channel<FSWebsocketPackage>()
            launch {
                channelIn.send(FSWebsocketPackage(true, byteArrayOf(1,2,3,4,5,6)))
                channelIn.close()
            }
            val consumer = Consumer<FSWebsocketPackage>()
            consumer.consumeFor(channelIn)
        }
    }


    @Test
    fun testWorkin3(){
        runBlockingTest {
            val channelIn = Channel<FSWebsocketPackage>()
            launch {
                channelIn.send(FSWebsocketPackage(true, byteArrayOf(1,2,3,4,5,6)))
                channelIn.close()
            }
            val consumer = Consumer<FSWebsocketPackage>()
            consumer.consumeReceiveCatchingWorking(channelIn)
        }
    }

}