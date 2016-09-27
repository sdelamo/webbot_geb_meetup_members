package meetup.geb.modules

import geb.Module
import utils.Paginator

class Pagination extends Module implements Paginator {

    static content = {
        links(required: false) { $('a') }
        currentPage(required: false) { $('.selected')?.text()?.toInteger() ?: 1 }
        nextLink(required: false) { links.filter('.nav-next') }
        previousLink(required: false) { links.filter('.nav-prev') }
    }

    void toPage(int pageNumber) {
        def link = links.filter(text: "$pageNumber")
        if (!link) {
            def exceptionMsg = "Page number $pageNumber not present in pagination"
            throw new IllegalArgumentException(exceptionMsg)
        }
        link.click()
    }

    @Override
    boolean isLastPage() {
        nextLink.empty
    }

    @Override
    void nextPage() {
        toPage (currentPage + 1)
    }

    @Override
    void previousPage() {
        toPage (currentPage + -1)
    }

    @Override
    boolean isFirstPage() {
        previousLink.empty
    }
}
