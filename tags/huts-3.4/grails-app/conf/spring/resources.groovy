// Place your Spring DSL code here
beans = {
    multipartResolver(org.springframework.web.multipart.commons.CommonsMultipartResolver) {
        maxUploadSize = 200000
    }

    mailSender(org.springframework.mail.javamail.JavaMailSenderImpl) {
        host = "mail.chrissearle.org"
    }

    mailMessage(org.springframework.mail.SimpleMailMessage) {
        from = "noreply-huts@chrissearle.org"
    }
}