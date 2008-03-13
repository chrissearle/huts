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