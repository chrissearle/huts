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
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

// Place your Spring DSL code here
beans = {
    multipartResolver(org.springframework.web.multipart.commons.CommonsMultipartResolver) {
        maxUploadSize = 200000
    }

    mailSender(org.springframework.mail.javamail.JavaMailSenderImpl) {
        host = CH.config.mail.host.name
    }

    mailMessage(org.springframework.mail.SimpleMailMessage) {
        from = CH.config.mail.from.address
    }
}