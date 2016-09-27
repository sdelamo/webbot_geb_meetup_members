import meetup.geb.MeetupMembersFetcher
import meetup.model.MeetupGroup
import meetup.model.MeetupMember
import spock.lang.Specification

class MeetupMemberSpec extends Specification {

    def "populate a member who has all of its data filled"() {
        given:
        def meetupGroup = new MeetupGroup(groupSlug: 'Warsaw-Groovy-User-Group')
        def member = new MeetupMember(memberId: '81585302')

        when:
        MeetupMembersFetcher.populateMember(member,meetupGroup.groupSlug)

        then:
        member.locality == 'Warsaw'
        member.imageUrl == 'http://photos3.meetupstatic.com/photos/member/5/5/6/6/member_163221862.jpeg'
        member.facebook == 'https://www.facebook.com/app_scoped_user_id/720931600/'
        member.twitter == 'http://twitter.com/cosmoz/'
        member.tumblr == 'http://t.perspektiv.it/'
        member.name == 'Paweł S. P.'

        when:
        member = new MeetupMember(memberId: '28938802')
        MeetupMembersFetcher.populateMember(member, meetupGroup.groupSlug)

        then:
        member.locality == 'Warsaw'
        member.imageUrl == 'http://photos2.meetupstatic.com/photos/member/6/5/1/0/member_28465872.jpeg'
        member.facebook == null
        member.twitter == null
        member.tumblr == null
        member.name == 'Tomasz S.'
    }

    def "test trait as csv works"() {

        given:
        def m = new MeetupMember()
        m.name = 'Sergio del Amo'
        m.locality = 'Guadalajara'
        m.twitter = 'https://twitter.com/sdelamo'
        m.facebook = null
        m.tumblr = null
        m.imageUrl = 'http://photos4.meetupstatic.com/photos/member/c/d/6/b/member_254392587.jpeg'
        m.website = 'http://www.meetup.com/es-ES/Warsaw-Groovy-User-Group/members/200767921/'

        when:
        String csvHeaders = m.csvHeaders()

        then:
        csvHeaders == 'imageUrl;locality;twitter;memberId;tumblr;facebook;background;name;website'

        when:
        def csv = m.asCSV()

        then:
        csv == "http://photos4.meetupstatic.com/photos/member/c/d/6/b/member_254392587.jpeg;Guadalajara;https://twitter.com/sdelamo;;;;;Sergio del Amo;http://www.meetup.com/es-ES/Warsaw-Groovy-User-Group/members/200767921/"
    }
}