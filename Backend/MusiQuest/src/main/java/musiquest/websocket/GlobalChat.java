package musiquest.websocket;

import org.apache.tomcat.websocket.WsSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
@ServerEndpoint(value = "/chat/{username}")
public class GlobalChat {
        private static MessageRepository messageRepository;
        private final String[] badWords = {"meanie","fuck","shit","bitch", "fucking", "shiting", "poopyface", "Fuck", "Shit"}; //feel free to add words :)

        @Autowired
        public void setMessageRepository(MessageRepository mr) {
                messageRepository = mr;
        }

        private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
        private static Map<String, Session> usernameSessionMap = new Hashtable<>();

        private final Logger logger = LoggerFactory.getLogger(GlobalChat.class);

        /**
         *
         * a function to add a user to some seesion.
         *
         * @param session session object that is manged main by springboot
         * @param username String of the user joining the chat
         * @throws IOException LOL
         */
        @OnOpen
        public void onOpen(Session session, @PathParam("username") String username) throws IOException, DeploymentException {

                //update logger
                logger.info("Opened GLOBAL chat");

                // store connecting user information
                sessionUsernameMap.put(session, username);
                usernameSessionMap.put(username, session);

                //Send chat history to the newly connected user
                //sendMessageToPArticularUser(username, getChatHistory());

                // broadcast that new user joined
                String message = "User:" + username + " has Joined the Chat";
                broadcast(message);

        }

        /**
         *
         * @param session
         * @param message
         * @throws IOException
         */
        @OnMessage
        public void onMessage(Session session, String message) throws IOException {

            logger.info("<Global> Got Message:" + message);

            String username = sessionUsernameMap.get(session);

            message = checkMessage(message);

            broadcast(username + ": " + message);

            // Saving chat history to repository
            messageRepository.save(new Message(username, message));

        }

        /**
         * Helpper method to checkMessage.
         * @param index is the index of the bad word
         * @param message is the split string list of message
         * @return updated String[]
         */
        private String[] censor(int index, String[] message) {
                int n = message[index].length();

                String temp = "";

                for (int i = 0; i < n; i++){
                    temp = temp + "*";
                }

                message[index] = temp;

                return message;
        }

        /**
         * Goes through a message censoring all bad words
         * @param message is a String
         * @return String of a new message with no bad words
         */
        private String checkMessage(String message) {

                String[] splitMessage = message.split(" ");

                for(int i = 0; i < splitMessage.length; i++){
                    String x = splitMessage[i];

                        for(int j = 0; j < badWords.length; j++){
                            String y = badWords[j];

                            if(x.equals(y)){
                                splitMessage = censor(i,splitMessage);
                            }
                        }

                }
                message = "";

                for(String x: splitMessage){
                    message = message + " " + x;
                }

                return message;
        }

        /**
         * Disconnects users and also closes websocket if there is no users in chat.
         *
         * @param session
         * @throws IOException
         */
        @OnClose
        public void onClose(Session session) throws IOException {
                logger.info("Closed global chat");

                String username = sessionUsernameMap.get(session);

                // broadcast that the user disconnected
                String message = username + " disconnected";
                broadcast(message);

                // remove the user connection information
                sessionUsernameMap.remove(session);
                usernameSessionMap.remove(username);
        }


        @OnError
        public void onError(Session session, Throwable throwable) {
                // Do error handling here
                logger.info("ERROR in Global chat");
                throwable.printStackTrace();
                throwable.getCause();
        }


        private void sendMessageToPArticularUser(String username, String message) {
                try {
                        usernameSessionMap.get(username).getBasicRemote().sendText(message);
                }
                catch (IOException e) {
                        logger.info("Exception: " + e.getMessage().toString());
                        e.printStackTrace();
                }
        }


        private void broadcast(String message) {
                sessionUsernameMap.forEach((session, username) -> {
                        try {
                                if(!session.isOpen()){session.close(); return;}
                                session.getBasicRemote().sendText(message);
                        }
                        catch (IOException e) {
                                logger.info("Exception: " + e.getMessage().toString());
                                e.printStackTrace();
                        }

                });

        }


        // Gets the Chat history from the repository
        private String getChatHistory() {
                List<Message> messages = messageRepository.findAll();

                // convert the list to a string
                StringBuilder sb = new StringBuilder();

                if(messages != null && !messages.isEmpty()) {
                        for (Message message : messages) {
                                sb.append(message.getUserName() + ": " + message.getContent() + "\n");
                        }
                }
                return sb.toString();
        }


}
