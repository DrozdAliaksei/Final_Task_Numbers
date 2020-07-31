package com.example.stage2task6.data.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class FilmXmlResponse(
    @field: Element(name = "channel") var channel: Channel? = null
)

@Root(name = "channel", strict = false)
data class Channel(
    @field: ElementList(name = "item", inline = true)
    var itemsList: MutableList<FilmItem>? = null
)

@Root(name = "item", strict = false)
data class FilmItem(
    @field: Element(name = "title") var title: String = "",
    @field: Element(name = "image") var image: ImageUrl? = null,
    @field: Element(name = "duration") var duration: String = "",
    @field: Element(name = "group") var media: MediaContent? = null,
    @field: Element(name = "description") var description: String = ""
)

@Root(name = "image", strict = false)
data class ImageUrl(
    @field: Attribute(name = "url") var url: String = ""
)

@Root(name = "group", strict = false)
data class MediaContent(
    @field: ElementList(name = "content", inline = true) var content: MutableList<Content>? = null
)

@Root(name = "content", strict = false)
data class Content(
    @field: Attribute(name = "url") var mediaUrl: String = ""
)
