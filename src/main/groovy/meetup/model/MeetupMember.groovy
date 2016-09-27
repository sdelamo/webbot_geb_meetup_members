package meetup.model

import groovy.transform.Canonical
import utils.TraitAsCSV

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
