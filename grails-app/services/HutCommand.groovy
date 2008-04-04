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
class HutCommand implements JabberCommand {
    def hutService

    HutCommand(HutService service) {
        this.hutService = service
    }

    String list(Person person) {
        def msg = new StringBuffer();

        hutService.visibleHuts(person, "").each {hut ->
            msg.append("${hut.id}: ${hut.name}").append("\n")
        }

        return msg.toString()
    }

    String get(Person person, Long id) {
        Hut hut = Hut.get(id)

        def msg = ""

        if (!hut) {
            msg = "Not found"
        } else {
            if (person.admin || hut.owner == person || hut.openHut) {
                msg += """Name: ${hut.name}
Location: ${hut.location}
Description: ${hut.description}
Beds: ${hut.beds}
Org: ${hut.owner.organization}
Contact: ${hut.owner.id}: ${hut.owner}
"""
            }
        }

        return msg
    }
}