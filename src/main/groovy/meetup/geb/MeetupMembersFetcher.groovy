package meetup.geb

import geb.Browser
import meetup.geb.pages.MeetupMemberListPage
import meetup.geb.pages.MeetupMemberPage
import meetup.model.MeetupMember
import meetup.model.MeetupWebsite
import utils.Delayable

class MeetupMembersFetcher implements Delayable {

    @SuppressWarnings('Println')
    static Collection<MeetupMember> fetchMembers(String groupSlug, int stopAt = -1) {

        def allMembers = harvestLinks(groupSlug, stopAt)
        allMembers.each {
            println "visiting member ${it.memberId}"
            populateMember(it, groupSlug)
            delay()
        }
        allMembers
    }

    @SuppressWarnings('Println')
    static Set<MeetupMember> harvestLinks(String groupSlug, int stopAt = -1) {
        def allMembers = [] as Set<MeetupMember>
        int pages = 0
        Browser.drive(baseUrl: MeetupWebsite.BASE_URL) {

            to MeetupMemberListPage, groupSlug

            for ( ;; ) {
                allMembers += persons()
                if ( pagination.isLastPage() ) {
                    break
                }
                delay()
                println 'going to next page'
                pages++
                if ( stopAt > 0 && pages >= stopAt) {
                    break
                }
                pagination.nextPage()
            }
        }
        allMembers
    }

    @SuppressWarnings('UnnecessaryObjectReferences')
    static populateMember(MeetupMember member, String groupSlug) {

        Browser.drive(baseUrl: MeetupWebsite.BASE_URL) {
            to MeetupMemberPage, groupSlug, member.memberId
            member.name = fullName
            member.twitter = twitter
            member.facebook = facebook
            member.tumblr = tumblr
            member.imageUrl = imageUrl
            member.locality = locality
            member.background = background
        }
    }
}
