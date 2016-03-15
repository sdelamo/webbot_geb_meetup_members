import geb.Module

class Pagination extends Module {

    static content = {
        links(required: false) { $("a") }
        currentPage(required: false) { $(".selected")?.text()?.toInteger() ?: 1 }
        nextLink(required: false) { links.filter(".nav-next") }
        previousLink(required: false) { links.filter(".nav-prev") }
    }

    void toPage(int pageNumber) {
        def link = links.filter(text: "$pageNumber")
        if (!link) throw new IllegalArgumentException("Page number $pageNumber not present in pagination")
        link.click()
    }

    void next() {
        toPage (currentPage + 1)
    }

    void previous() {
        toPage (currentPage + -1)
    }

    boolean isFirstPage() {
        previousLink.empty
    }

    boolean isLastPage() {
        nextLink.empty
    }

}
