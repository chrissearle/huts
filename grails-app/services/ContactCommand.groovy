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
class ContactCommand implements JabberCommand {
    String list(Person person) {
        def msg = new StringBuffer();

        def people = Person.list()

        if (!person.admin) {
            people = Person.findAllByPublished(true)
        }

        people.each {contact ->
            msg.append("${contact.id}: ${contact.name}").append("\n")
        }

        return msg.toString()
    }

    String get(Person person, Long id) {
        Person contact = Person.get(id)

        def msg = ""

        if (!contact) {
            msg = "Not found"
        } else {
            if (person.admin || contact == person || contact.published) {
                msg += """Name: ${contact.name}
 Tlf/Mob: ${contact.phone}
 Email: ${contact.email}
 Jabber: ${contact.jabber}
"""
                if (person.admin) {
                    msg += """Admin: ${contact.admin}
 Approved: ${contact.approved}
 Confirmed: ${contact.confirmed}
"""
                }
            }
        }

        return msg
    }
}