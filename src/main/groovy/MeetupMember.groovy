import geb.Browser

class MeetupMember implements TraitAsCSV {
    String name
    String locality
    String twitter
    String facebook
    String tumblr
    String background
    String imageUrl
    String website

    static def populate(MeetupMember member ) {
        Browser.drive {
            go member.website
            at MeetupMemberPage
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
