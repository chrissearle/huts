import groovy.text.SimpleTemplateEngine
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage

class EmailService {

    boolean transactional = true

    MailSender mailSender
    SimpleMailMessage mailMessage

    def sendMail(String templateName, Map binding, List toAddress, String subject) {

        def servletContext = ApplicationHolder.getApplication().getParentContext().getServletContext()

        def filename = File.separator +
                "WEB-INF" +
                File.separator +
                "mailTemplates" +
                File.separator +
                "${templateName}.gtpl"

        def url = servletContext.getResource(filename)

        def engine = new SimpleTemplateEngine()

        def template = engine.createTemplate(url).make(binding)

        def email = [
                to: toAddress,
                subject: subject,
                text: template.toString()
        ]


        sendEmails([email])
    }

    def sendEmails(mails) {

        def messages = []
        for (mail in mails) {
            SimpleMailMessage message = new SimpleMailMessage(mailMessage)

            message.to = mail.to
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
