import geb.Browser

class MeetupMembersFetcher {

    @SuppressWarnings('InsecureRandom')
    static randomDelay(def from, def to) {
        Math.abs(new Random().nextInt() % to) + from
    }

    static delay() {
        sleep(randomDelay(1000, 4000))
    }

    static Collection<MeetupMember> fetchMembers(String groupSlug) {

        def allMembers = harvestLinks(groupSlug)
        allMembers.each {
            populateMember(it, groupSlug)
            delay()
        }
        allMembers
    }

    static Set<MeetupMember> harvestLinks(String groupSlug) {
        def allMembers = [] as Set<MeetupMember>
        Browser.drive(baseUrl: MeetupWebsite.BASE_URL) {

            to MeetupMemberListPage, groupSlug

            for ( ;; ) {
                allMembers += persons()
                if ( pagination.isLastPage() ) {
                    break
                }
                delay()
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
