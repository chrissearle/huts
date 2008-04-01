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

import org.jivesoftware.smack.Chat
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.Roster
import org.jivesoftware.smack.PacketListener
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.filter.PacketFilter
import org.jivesoftware.smack.filter.PacketTypeFilter
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.packet.Packet
import org.jivesoftware.smack.util.StringUtils

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class JabberService {

    def hutService

    boolean transactional = false

    XMPPConnection connection

    def connect() {
        ConnectionConfiguration cc = new ConnectionConfiguration(
                CH.config.chat.host,
                CH.config.chat.port,
                CH.config.chat.serviceName)
        connection = new XMPPConnection(cc)

        try {

            connection.connect()
            connection.login(CH.config.chat.username,
                    CH.config.chat.password)

            connection.getRoster().setSubscriptionMode(Roster.SubscriptionMode.accept_all)

            PacketListener pl = {Packet packet ->
                try {
                    if (packet instanceof Message) {
                        Message message = (Message) packet

                        if (message.type == Message.Type.chat && message.from && message.body) {
                            Person person = Person.findByJabber(StringUtils.parseBareAddress(message.from))

                            log.debug("${message.body}")

                            if (person) {
                                log.debug("${person}: ${message.body}")

                                if (message.body == "huts") {
                                    log.debug("huts")

                                    def msg = new StringBuffer();

                                    hutService.visibleHuts(person, "").each {hut ->
                                        msg.append("${hut.id}: ${hut.name}").append("\n")
                                    }
                                    this.sendChat(person.jabber, msg.toString())
                                }
                                if (message.body == "contacts") {
                                    log.debug("contacts")

                                    def msg = new StringBuffer();

                                    if (person.admin) {
                                        Person.list().each {contact ->
                                            msg.append("${contact.id}: ${contact.name}").append("\n")
                                        }
                                        this.sendChat(person.jabber, msg.toString())
                                     }
                                }
                                if (message.body.startsWith("hut:")) {
                                    def id = message.body.substring(message.body.indexOf(":") + 1)

                                    log.debug("hut ${id}")

                                    Hut hut = Hut.get(id)

                                    if (person.admin || hut.owner == person) {
                                        this.sendChat(person.jabber, """Name: ${hut.name}
Location: ${hut.location}
Description: ${hut.description}
Beds: ${hut.beds}
Org: ${hut.owner.organization}
Contact: ${hut.owner.id}: ${hut.owner}
""")
                                    }
                                }
                                if (message.body.startsWith("contact:")) {
                                    def id = message.body.substring(message.body.indexOf(":") + 1)

                                    log.debug("contact ${id}")

                                    Person contact = Person.get(id)

                                    if (person.admin || contact == person) {
                                        this.sendChat(person.jabber, """Name: ${contact.name}
Tlf/Mob: ${contact.phone}
Email: ${contact.email}
Jabber: ${contact.jabber}
Admin: ${contact.admin}
Approved: ${contact.approved}
Confirmed: ${contact.confirmed}
""")
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("Failed to send response ${e.getMessage()} ")
                }
            } as PacketListener

            PacketFilter pf = new PacketTypeFilter(Message.class)

            connection.addPacketListener(pl, pf)

        } catch (Exception e) {
            log.error("Failed to connect ${e.getMessage()}")
        }
    }

    def disconnect() {
        connection.disconnect()
    }

    def sendChat(String to, String msg) {
        log.error("Chat to ${to} ${msg}")
        try {
            if (connection.getRoster().getPresence(to).isAvailable()) {
                log.error("Available")

                def chatmanager = connection.getChatManager()

                // we talk, but don't listen, how rude
                Chat chat = chatmanager.createChat(to, null)

                def msgObj = new Message(to, Message.Type.chat)
                msgObj.setBody(msg)
                chat.sendMessage(msgObj)
            }
        } catch (Exception e) {
            log.error("Failed to send message ${e.getMessage()}")
        }
    }

}
