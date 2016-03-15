import geb.Page

class MeetupMemberListPage extends Page {
    static content = {
        personLinks { $("#memberList h4 a") }
        pagination {
            module Pagination, $("ul.nav-pagination")
        }
    }

    List<Map> persons() {
        personLinks.collect {
            def el = it.getElement(0)
           [name: el.text, href: el.getAttribute('href')]
        }
    }
}