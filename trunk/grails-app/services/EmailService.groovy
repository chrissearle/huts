import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage

class EmailService {
    def templateService

    MailSender mailSender
    SimpleMailMessage mailMessage

    def sendMail(String templateName, Map binding, List toAddress, List ccAddress, String subject) {
        def email = [
                to: toAddress,
                cc: ccAddress,
                subject: subject,
                text: templateService.processTemplate("mailTemplates", templateName, binding)
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
