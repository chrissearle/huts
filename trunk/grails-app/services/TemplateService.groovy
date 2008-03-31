import groovy.text.SimpleTemplateEngine
import org.codehaus.groovy.grails.commons.ApplicationHolder

class TemplateService {

    boolean transactional = false

    def processTemplate(String templateSet, String templateName, Map binding) {

        def servletContext = ApplicationHolder.getApplication().getParentContext().getServletContext()

        def filename = File.separator +
                "WEB-INF" +
                File.separator +
                templateSet +
                File.separator +
                "${templateName}.gtpl"

        def url = servletContext.getResource(filename)

        Reader templateReader = new InputStreamReader(url.getContent(), "UTF-8")

        def engine = new SimpleTemplateEngine()

        def template = engine.createTemplate(templateReader).make(binding)

        return template.toString()
    }
}
