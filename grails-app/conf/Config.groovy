/*
   Copyright 2008 Chris Searle

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.types = [html: ['text/html', 'application/xhtml+xml'],
        xml: ['text/xml', 'application/xml'],
        text: 'text-plain',
        js: 'text/javascript',
        rss: 'application/rss+xml',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        all: '*/*',
        json: ['application/json', 'text/json'],
        form: 'application/x-www-form-urlencoded',
        multipartForm: 'multipart/form-data'
]
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.serverURL = "http://localhost:8080/huts"
        google.map.key = "ABQIAAAAXg65O3cup7wNZGjVeZYpsBTwM0brOpm-All5BF6PoaKBxRWWERTQsPYlehY-o2ihzdV8xcjkHihn8g"
        mail.host.name = "mail.chrissearle.org"
        js.mapiconfactory = "mapiconfactory.js"
        js.clustermarker = "clustermarker.js"
        js.jquery = "jquery-1.2.3.js"
        chat.username = "hut-bot-dev"
    }
    test {
        grails.serverURL = "http://localhost:8080/huts"
        google.map.key = "ABQIAAAAXg65O3cup7wNZGjVeZYpsBTwM0brOpm-All5BF6PoaKBxRWWERTQsPYlehY-o2ihzdV8xcjkHihn8g"
        mail.host.name = "mail.chrissearle.org"
        js.mapiconfactory = "mapiconfactory.js"
        js.clustermarker = "clustermarker.js"
        js.jquery = "jquery-1.2.3.js"
        chat.username = "hut-bot-test"
    }
    production {
        grails.serverURL = "http://huts.chrissearle.net/huts"
        google.map.key = "ABQIAAAAXg65O3cup7wNZGjVeZYpsBTSHwLzGy9_V0V5XWMiiVFSAkWTrxTK2ih4vDZUe0HMSlF-3ftZVQ3d4w"
        google.analytics.key = "UA-2221544-6"
        mail.host.name = "localhost"
        js.mapiconfactory = "mapiconfactory_packed.js"
        js.clustermarker = "clustermarker_packed.js"
        js.jquery = "jquery-1.2.3.min.js"
        chat.username = "hut-bot"
    }
}

mail.from.address = "no-reply-huts@chrissearle.net"
language.default = "nb"

// log4j configuration
log4j {
    appender.stdout = "org.apache.log4j.ConsoleAppender"
    appender.'stdout.layout' = "org.apache.log4j.PatternLayout"
    appender.'stdout.layout.ConversionPattern' = '[%r] %c{2} %m%n'
    appender.errors = "org.apache.log4j.FileAppender"
    appender.'errors.layout' = "org.apache.log4j.PatternLayout"
    appender.'errors.layout.ConversionPattern' = '[%r] %c{2} %m%n'
    appender.'errors.File' = "stacktrace.log"
    rootLogger = "debug,stdout"
    logger {
        grails {
            app {
                domain = "info"
                services = "info"
                controllers = "info"
                jobs = "debug"
            }
        }

        StackTrace = "error,errors"

        org {
            codehaus.groovy.grails.web.servlet = "error" //  controllers
            codehaus.groovy.grails.web.pages = "error" //  GSP
            codehaus.groovy.grails.web.sitemesh = "error" //  layouts
            codehaus.groovy.grails."web.mapping.filter" = "error" // URL mapping
            codehaus.groovy.grails."web.mapping" = "error" // URL mapping
            codehaus.groovy.grails.commons = "info" // core / classloading
            codehaus.groovy.grails.plugins = "error" // plugins
            codehaus.groovy.grails.orm.hibernate = "error" // hibernate integration
            springframework = "off"
            hibernate = "off"
        }
    }
    additivity.StackTrace = false
}


chat {
    serviceName = "jabber.chrissearle.org"
    host = "jabber.chrissearle.org"
    port = 5222
    password = "thahW6iu"
}