import groovy.transform.Canonical

@Canonical
class MeetupMember implements TraitAsCSV {
    String memberId
    String name
    String locality
    String twitter
    String facebook
    String tumblr
    String background
    String imageUrl
    String website
}
