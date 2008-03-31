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
import org.jivesoftware.smack.packet.Message

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class JabberService {

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

        } catch (Exception e) {
            log.error("Failed to connect ${e.getMessage()}")
        }
    }

    def disconnect() {
        connection.disconnect()
    }

    def sendChat(String to, String msg) {
        try {
            if (connection.getRoster().getPresence(to).isAvailable()) {
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
