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
