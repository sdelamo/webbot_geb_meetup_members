This project allows you to download members of a Meetup group with Geb. 

## Generate fat Jar

    $ ./gradlew shadowJar

## How to run it in phantomJs:

    $ java -jar -Dgeb.env=phantomJs 
    -Dphantomjs.binary.path=/Users/groovycalamari/Documents/Applications/phantomjs-2.1.1-macosx/bin/phantomjs 
    build/libs/webbot_geb_meetup_members-all.jar /Users/groovycalamari/Downloads madrid-gug

## How to run it in Chrome:
    $ java -jar -Dgeb.env=chrome -Dgeb.env=chrome -Dwebdriver.chrome.driver=/Users/sdelamo/Applications/chromedriver build/libs/webbot_geb_meetup_members-all.jar /Users/sdelamo/Downloads madrid-gug

Note latest paramter must be a valid meetup group slug. 
