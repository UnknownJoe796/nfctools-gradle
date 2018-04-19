package org.nfctools.test

import org.junit.Test
import org.nfctools.NfcAdapter
import org.nfctools.api.NfcTagListener
import org.nfctools.api.Tag
import org.nfctools.api.TagScannerListener
import org.nfctools.mf.classic.MfClassicNfcTagListener
import org.nfctools.ndef.NdefOperations
import org.nfctools.ndef.NdefOperationsListener
import org.nfctools.ndef.mime.MimeRecord
import org.nfctools.scio.Terminal
import org.nfctools.scio.TerminalHandler
import org.nfctools.scio.TerminalMode
import org.nfctools.spi.acs.AcsTerminal
import org.nfctools.spi.scm.SclTerminal
import java.io.DataInputStream
import java.io.DataOutputStream

class NFCTest {
    @Test
    fun example() {
        val adapter = NfcAdapter(getAvailableTerminal(), TerminalMode.INITIATOR, object : TagScannerListener {
            override fun onScanningEnded() {
                println("onScanningEnded")
            }

            override fun onScanningFailed(throwable: Throwable?) {
                println("onScanningFailed")
            }

            override fun onTagHandingFailed(throwable: Throwable?) {
                println("onTagHandingFailed")
            }
        }).apply {
            registerTagListener(object : NfcTagListener {
                override fun canHandle(tag: Tag?): Boolean {
                    println("canHandle: ${tag?.tagType?.name}")
                    return false
                }

                override fun handleTag(tag: Tag?) {
                    println("handleTag: ${tag?.tagType?.name}")
                }
            })
            registerTagListener(MfClassicNfcTagListener(NdefOperationsListener { ndefOperations ->
                println("onNdefOperations")
                println(ndefOperations.setRecords(NDEFMessage("text/plain", "HELLO")))
            }))
            startListening()
        }
        while (true) {

        }
    }
}

private fun getAvailableTerminal(preferredTerminalName: String? = null): Terminal? {
    val terminalHandler = TerminalHandler()
    terminalHandler.addTerminal(AcsTerminal())
    terminalHandler.addTerminal(SclTerminal())
    return terminalHandler.getAvailableTerminal(preferredTerminalName)
}

fun NdefOperations.getRecords(): List<NDEFMessage> = readNdefMessage().asSequence()
        .mapNotNull { it as? MimeRecord }
        .map {
            NDEFMessage(
                    mimeType = it.contentType,
                    data = it.contentAsBytes.toString(Charsets.UTF_8)
            )
        }
        .toList()

fun NdefOperations.setRecords(message: NDEFMessage) {
    writeNdefMessage(org.nfctools.ndef.mime.TextMimeRecord(message.mimeType, message.data))
}


data class NDEFMessage(
        var mimeType: String,
        var data: String
)

fun DataOutputStream.write(message: NDEFMessage) {
    writeUTF(message.mimeType)
    writeUTF(message.data)
}

fun DataInputStream.readNDEFMessage(): NDEFMessage = NDEFMessage(
        mimeType = readUTF(),
        data = readUTF()
)