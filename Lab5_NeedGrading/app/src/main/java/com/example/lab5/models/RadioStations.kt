package com.example.lab5.models

class RadioStations {
    val urls = listOf(
        "http://stream.rtlradio.de/rnb/mp3-192/",

        "https://ilm-stream12.radiohost.de/ilm_ilovemusicandchill_mp3-192?_art=dD0xNzExNjU2NDk5JmQ9ZDMyZTg4Y2Q5NA",
        "https://stream.0nlineradio.com/edm-festival",

        "https://live.amperwave.net/direct/saga-wsnyfmmp3-ibc2",
        "https://streams.ilovemusic.de/iloveradio17.mp3",

    )

    private var currentUrl = -1

    fun getNextUrl(): String {
        currentUrl = (currentUrl + 1) % urls.size
        return urls[currentUrl]
    }

    fun getUrl(index: Int): String {
        currentUrl = index
        return urls[currentUrl]
    }

    val size get() = urls.size
}
