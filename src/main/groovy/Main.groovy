import geb.Browser

class Main {

    static void main(String[] args) {

        def meetupUrl = 'http://www.meetup.com/Warsaw-Groovy-User-Group/'

        def directory = '/Users/softamo/Downloads'
        def fileName = 'WarsawGroovyMeetupMembers'
        def extension = '.csv'
        def filepath = "$directory/$fileName$extension"

        List<MeetupMember> allMembers = MeetupMembersFetcher.fetchMembers(meetupUrl)

        if(!allMembers.isEmpty()) {
            new File(filepath).withWriter { out ->
                out println "${allMembers.first().csvHeaders()}"
                allMembers.each {
                    out.println it.asCSV()
                }
            }
        }
    }
}