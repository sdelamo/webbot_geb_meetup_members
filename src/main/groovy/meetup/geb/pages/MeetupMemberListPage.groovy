package meetup.geb.pages

import geb.Page
import meetup.model.MeetupMember

class MeetupMemberListPage extends Page {

    static url = '/es-ES/'

    String convertToPath(Object[] args) {
        if ( args.size() >= 1 ) {
            return "${ args[0] }/members"
        }
    }

    static content = {
        personLinks { $('#memberList h4 a') }
        pagination {
            module Pagination, $('ul.nav-pagination')
        }
    }

    List<Map> persons() {
        personLinks.collect {
            def el = it.getElement(0)
            def href = el.getAttribute('href')
            new MeetupMember(name: el.text, memberId: memberIdFromHref(href))
        }
    }

    static String memberIdFromHref(String href) {
        href.split('/').last()
    }
}
