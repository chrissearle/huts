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
