import js.externals.jquery.`$`
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.round

data class PopupConfig(
        val width: Int,
        val height: Int,
        val title: String,
        val content: String,
        val max_height: Int? = null
)

class Popup(val config: PopupConfig) {
    private val titleHeight = 29
    private val selectors = mapOf(
        "main" to ".box",
        "body" to ".box .body",
        "close" to ".box .title .close",
        "title" to ".box .title .text",
        "shadow" to ".box_shadow"
    )
    private val templates = mapOf(
        "wrap" to "<div class=\"box_shadow\"></div><div class=\"box\" style=\"width:{1}px;opacity:0.0;\"><div class=\"title\"><a href=\"#\" class=\"close\"></a><div class=\"text\">{2}</div></div><div class=\"body\" style=\"max-height:{3}px;\">{4}</div></div>",
        "loading" to "<div class=\"loading\"><img src=\"/images/loading.gif\" alt=\"\" /></div>"
    )

    private val width = `$`(document.body!!).width() as Int - if (50 < config.width) `$`(document.body!!).width() as Int - 50 else config.width

    fun open() {
        `$`(this.selectors["main"]!!).remove()
        this.templates["wrap"]!!.format(config.width, config.title, config.height - titleHeight, config.content)
        this.calculate_sizes {
            `$`(".box").fadeTo(500, 1.0)
        }

        if (config.query != null) {
            this.query_execute()
        }
    }

    fun calculate_sizes(callback: (() -> Unit)?) {
        val window_height = `$`(window).height() as Int
        val window_width = `$`(window).width() as Int
        val max_height = if (config.max_height != null) (if (config.max_height > (window_height - 50)) window_height - 50 else config.max_height) else window_height - 50
        `$`(selectors["main"]!!).css("maxHeight",  "`${max_height}px")
        `$`(selectors["body"]!!).css("maxHeight", "${max_height - titleHeight}px")
        val own_height = `$`(selectors["main"]!!).height()
        val own_width = `$`(selectors["main"]!!).width()
        `$`(selectors["main"]!!).css(
                arrayOf(
                    "top", "${round((window_height / 2 - own_height as Float) / 2)}px",
                    "left", "${round((window_width / 2 - own_width as Float) / 2)}px"
                )
        )
        if (callback != null) {
            callback()
        }
    }

    fun close() {
        `$`(selectors["main"]!!).remove()
        `$`(selectors["shadow"]!!).remove()
    }
}