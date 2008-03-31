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
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage

class EmailService {
    def jabberService

    MailSender mailSender
    SimpleMailMessage mailMessage

    def sendMail(String subject, String message, List toPeople, List ccPeople) {
        def toAddresses = []
        def ccAddresses = []

        toPeople.each {person ->
            toAddresses.add(person.email)
            if (person.jabber) {
                jabberService.sendChat(person.jabber, message)
            }
        }

        ccPeople.each {person ->
            ccAddresses.add(person.email)
            if (person.jabber) {
                jabberService.sendChat(person.jabber, message)
            }
        }

        def email = [
                to: toAddresses,
                cc: ccAddresses,
                subject: subject,
                text: message
        ]

        sendEmails([email])
    }

    def sendEmails(mails) {

        def messages = []
        mails.each {mail ->
            SimpleMailMessage message = new SimpleMailMessage(mailMessage)

            message.to = mail.to
            if (mail.cc.size() > 0) {
                message.cc = mail.cc
            }
            message.text = mail.text
            message.subject = mail.subject
            messages << message
        }

        // Send them all together
        try {
            println "about to send ${messages.size()} messages to:\n${messages.to.join('\n')}"
            mailSender.send(messages as SimpleMailMessage[])
        } catch (MailException ex) {
            println "Failed to send emails"
            ex.printStackTrace()
        }
    }
}
