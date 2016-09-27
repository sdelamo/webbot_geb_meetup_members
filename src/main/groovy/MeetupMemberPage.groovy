import geb.Page

class MeetupMemberPage extends Page {

    static url = '/en-US/'

    String convertToPath(Object[] args) {
        if ( args.size() >= 2 ) {
            return "${ args[0] }/members/${ args[1] }"
        }
    }

    static at = { true }

    static final FACEBOOK_ROOT_URLS = [
            'https://facebook.com/',
            'http://facebook.com/',
            'https://www.facebook.com/',
            'http://www.facebook.com/'
    ]
    static final TWITTER_ROOT_URLS = [
            'https://twitter.com/',
            'http://twitter.com/',
            'https://www.twitter.com/',
            'http://www.twitter.com/'
    ]

    static content = {

         fullName(required: false) {
            def n
            $('span.memName').each {
                n = it.getElement(0).text
            }
            n
        }

        twitter(required: false) {
            def t
            $('#D_groupMemberProfile a').each {
                def webElement = it.getElement(0)
                def href = webElement.getAttribute('href')
                if ( TWITTER_ROOT_URLS.any { href?.startsWith(it) } ) {
                    t = href
                }
            }
            t
        }

        facebook(required: false) {
            def f
            $('#D_groupMemberProfile a').each {
                def webElement = it.getElement(0)
                def href = webElement.getAttribute('href')
                if ( FACEBOOK_ROOT_URLS.any { href?.startsWith(it) } ) {
                    f = href
                }
            }
            f
        }

        tumblr(required: false) {
            def t
            $('#D_groupMemberProfile a').each {
                def webElement = it.getElement(0)
                def href = webElement.getAttribute('href')
                def attrTitle = webElement.getAttribute('title')
                if ( attrTitle == 'Tumblr' ) {
                    t = href
                }
            }
            t
        }

        imageUrl(required: false) {
            def src
            $('img.D_memberProfilePhoto').each {
                def webElement = it.getElement(0)
                src = webElement.getAttribute('src')
            }
            src
        }

        locality(required: false) {
            def l
            $('#D_groupMemberProfile .locality').each {
                def webElement = it.getElement(0)
                l = webElement.text
            }
            l
        }

        background(required: false) {
            def memberBackground
            $('.D_memberProfileContentItem').each {
                def webElement = it.getElement(0)
                def text = webElement.text
                if (!text?.startsWith('Location') ) {
                    memberBackground += ' ' + text  ?: ''
                }

            }
            memberBackground?.replace('\n', ' ')
        }
    }
}
