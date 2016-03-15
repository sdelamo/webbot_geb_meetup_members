import spock.lang.Specification

class MeetupMembersFetcherSpec extends Specification {

    def "retriving every member shoudl retrieve the organizer too"() {

        given:
        def meetupGroupUrl = 'http://www.meetup.com/Warsaw-Groovy-User-Group/'

        when:
        def members = MeetupMembersFetcher.fetchMembers(meetupGroupUrl)

        then:
        members
        !members.isEmpty()

        and: 'an organizer is found among the retrieved users'
        members.find { it.name == 'Tomasz S.'}
    }
}
