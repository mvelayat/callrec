package com.example.callrec
private fun saveToMusic(file: File) {
    val values = ContentValues().apply {
        put(MediaStore.Audio.Media.DISPLAY_NAME, file.name)
        put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp4")
        put(MediaStore.Audio.Media.RELATIVE_PATH, "Music/CallRecordings")
    }
    val uri = contentResolver.insert(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values
    ) ?: return
    contentResolver.openOutputStream(uri)?.use { out ->
        file.inputStream().use { it.copyTo(out) }
    }
    file.delete()
}
private fun stopRecording() {
    val f = outFile
    var ok = true
    try { recorder?.stop() } catch (_: RuntimeException) { ok = false }
    recorder?.release()
    recorder = null
    outFile = null
    if (f != null) { if (ok) saveToMusic(f) else f.delete() }
}