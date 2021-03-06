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
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.filter.PacketFilter
import org.jivesoftware.smack.filter.PacketTypeFilter
import org.jivesoftware.smack.packet.Message

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import org.jivesoftware.smack.packet.Packet
import org.jivesoftware.smack.PacketListener
import org.jivesoftware.smack.util.StringUtils


class JabberService {

    def hutService

    boolean transactional = false

    XMPPConnection connection

    def connect() {
        def commands = [hut: new HutCommand(hutService),
                contact: new ContactCommand(),
        booking: new BookingCommand()]

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

            PacketFilter pf = new PacketTypeFilter(Message.class)

            connection.addPacketListener({Packet packet ->
                try {
                    if (packet instanceof Message) {
                        Message message = (Message) packet

                        if (message.type == Message.Type.chat && message.from && message.body) {
                            Person person = Person.findByJabber(StringUtils.parseBareAddress(message.from))

                            log.info("${message.body}")

                            if (person) {
                                log.info("${person}: ${message.body}")

                                String command = message.body

                                if (command.indexOf(":") > 0) {
                                    command = command.substring(0, command.indexOf(":"))
                                }

                                JabberCommand jabberCommand = commands.get(command)

                                String messageText

                                if (message.body.indexOf(":") > 0) {
                                    def id = message.body.substring(message.body.indexOf(":") + 1)

                                    messageText = jabberCommand.get(person, Long.valueOf(id))
                                } else {
                                    messageText = jabberCommand.list(person)
                                }

                                log.debug("Response ${person.jabber} ${messageText}")
                                if (messageText != null && !(messageText == "")) {
                                    this.sendChat(person.jabber, messageText)
                                }
                            }
                        }
                    }
                }

                catch (Exception e) {
                    log.error("Failed to send response ${e}", e)
                }
            } as PacketListener, pf)

        } catch (Exception e) {
            log.error("Failed to connect ${e}", e)
        }
    }

    def disconnect() {
        connection.disconnect()
    }

    def sendChat(String to, String msg) {
        log.info("Chat to ${to} ${msg}")
        try {
            if (connection.getRoster().getPresence(to).isAvailable()) {
                log.debug("Available")

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
