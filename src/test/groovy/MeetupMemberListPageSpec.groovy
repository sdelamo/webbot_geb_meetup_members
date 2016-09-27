import meetup.geb.pages.MeetupMemberListPage
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MeetupMemberListPageSpec extends Specification {

    def "test #memberId for #href"(String memberId, String href) {

        when:
        def result = MeetupMemberListPage.memberIdFromHref(href)

        then:
        result == memberId

        where:
        memberId    || href
        '28938802'  || 'http://www.meetup.com/es-ES/Warsaw-Groovy-User-Group/members/28938802/'
        '28938802'  || 'http://www.meetup.com/es-ES/Warsaw-Groovy-User-Group/members/28938802'
    }
}
