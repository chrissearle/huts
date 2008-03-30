import org.jivesoftware.smack.Chat
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.packet.Message

class JabberService {

    boolean transactional = false

    XMPPConnection connection

    def connect() {
        ConnectionConfiguration cc = new ConnectionConfiguration(
                ConfigurationHolder.config.chat.host,
                ConfigurationHolder.config.chat.port,
                ConfigurationHolder.config.chat.serviceName)
        connection = new XMPPConnection(cc)

        try {

            connection.connect()
            connection.login(ConfigurationHolder.config.chat.username,
                    ConfigurationHolder.config.chat.password)

        } catch (Exception e) {
            log.error("Failed to connect ${e.getMessage()}")
        }
    }

    def disconnect() {
        connection.disconnect()
    }

    def sendChat(String to, String msg) {
        try {
            def chatmanager = connection.getChatManager()

            // we talk, but don't listen, how rude
            Chat chat = chatmanager.createChat(to, null)

            def msgObj = new Message(to, Message.Type.chat)
            msgObj.setBody(msg)
            chat.sendMessage(msgObj)
        } catch (Exception e) {
            log.error("Failed to send message ${e.getMessage()}")
        }
    }
}
