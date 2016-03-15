import geb.Browser

class MeetupMembersFetcher {

    static randomDelay(def from, def to) {
        Math.abs(new Random().nextInt() % to) + from
    }

    static delay() {
        sleep(randomDelay(1000, 4000))
    }

    static List<MeetupMember> fetchMembers(String meetupUrl) {

        List<MeetupMember> allMembers = []

        Browser.drive {
            go "${meetupUrl}members/"

            at MeetupMemberListPage

            for(;;) {
                allMembers += persons().collect { new MeetupMember(name:it.name,  website:it.href) }
                if(pagination.isLastPage()) {
                    break
                }
                delay()
                pagination.next()
            }
        }

        allMembers.each {
            MeetupMember.populate(it)
            delay()
        }

        allMembers
    }

}
