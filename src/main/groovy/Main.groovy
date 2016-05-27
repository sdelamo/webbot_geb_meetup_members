class Main {

    @SuppressWarnings('JavaIoPackageAccess')
    static void main(String[] args) {

        def directory = '/Users/softamo/Downloads'
        def fileName = 'Warsaw-Groovy-User-Group'
        def extension = '.csv'
        def filepath = "$directory/$fileName$extension"

        Collection<MeetupMember> allMembers = MeetupMembersFetcher.fetchMembers(fileName)

        if ( !allMembers.isEmpty() ) {
            new File(filepath).withWriter { out ->
                out println "${ allMembers.first().csvHeaders() }"
                allMembers.each {
                    out.println it.asCSV()
                }
            }
        }
    }
}
