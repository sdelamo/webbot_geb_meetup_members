import groovy.transform.CompileStatic
import meetup.geb.MeetupMembersFetcher
import meetup.model.MeetupMember

@CompileStatic
class Main {

    @SuppressWarnings('Println')
    static void usage() {
        println " java -jar build/libs/webbot_geb_meetup_members-all.jar outputdir groupSlug"
        println "e.g java -jar -Dgeb.env=phantomJs -Dphantomjs.binary.path=/Users/groovycalamari/Documents/Applications/phantomjs-2.1.1-macosx/bin/phantomjs build/libs/webbot_geb_meetup_members-all.jar /Users/groovycalamari/Downloads madrid-gug "
    }

    @SuppressWarnings('JavaIoPackageAccess')
    static void main(String[] args) {

        if ( args.length < 1) {
            usage()
            return
        }
        def directory = args[0]
        def dir = new File(directory)
        if ( !dir.exists() || !dir.isDirectory() ) {
            println "supplied argument ${args[0]} is not a directory"
            return
        }

        if ( args.length < 2) {
            usage()
            return
        }
        def groupSlug = args[1]
        def extension = '.csv'
        def filepath = "$directory/$groupSlug$extension"

        Collection<MeetupMember> allMembers = MeetupMembersFetcher.fetchMembers(groupSlug)

        if ( !allMembers.isEmpty() ) {
            new File(filepath).withWriter { out ->
                out.println "${ allMembers.first().csvHeaders() }"
                allMembers.each {
                    out.println it.asCSV()
                }
            }
        }
    }
}
