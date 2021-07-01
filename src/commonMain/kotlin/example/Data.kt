package example

expect class FSWebsocketPackage(fin: Boolean, data: ByteArray) {
    val fin: Boolean
    val data: ByteArray
}