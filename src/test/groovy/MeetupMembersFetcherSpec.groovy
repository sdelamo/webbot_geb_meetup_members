import spock.lang.Specification

class MeetupMembersFetcherSpec extends Specification {

    def "every memeber fethed should have a memberId"() {
        when:
        def members = MeetupMembersFetcher.harvestLinks('Warsaw-Groovy-User-Group')

        then:
        members.each {
            assert it.memberId
        }

    }
    def "retriving every member should retrieve the organizer too"() {
        when:
        def members = MeetupMembersFetcher.fetchMembers('Warsaw-Groovy-User-Group')

        then:
        members
        !members.isEmpty()

        and: 'an organizer is found among the retrieved users'
        members.find { it.name == 'Tomasz S.'}
    }
}
