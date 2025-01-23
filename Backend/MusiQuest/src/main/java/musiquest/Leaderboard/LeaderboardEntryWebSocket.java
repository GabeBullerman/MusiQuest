package musiquest.Leaderboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import musiquest.websocket.MessageRepository;

import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@Controller
@ServerEndpoint(value = "/leaderboardWS")
public class LeaderboardEntryWebSocket {

    
    private static LeaderboardService leaderboardService;
    
    @Autowired
    public void setLeaderboardService(LeaderboardService service) {
    	leaderboardService = service;
    }

    @OnOpen
    public void onOpen(Session session) {
        // Send the top 10 scores when a user connects
        
        try {
			sendTopScoresWithNames(session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @OnMessage
    public void onMessage(Session session, String message) {
    	try {
			sendTopScoresWithNames(session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @OnClose
    public void onClose(Session session) {
        // Handle user disconnection, if necessary
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Handle WebSocket errors, if necessary
    }

//    // Method to send the top scores to the connected client
//    private void sendTopScoresToClient(Session session, List<LeaderboardEntry> topScores) {
//        try {
//            String scoresJson = createJsonForTopScores(topScores);
//            session.getBasicRemote().sendText(scoresJson);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // Create a JSON representation of the top scores
    private String createJsonForTopScores(List<LeaderboardEntry> topScores) {
        // Implement your JSON creation logic here
        // You can use a JSON library like Jackson to create JSON from the list of top scores
        // Example: return new ObjectMapper().writeValueAsString(topScores);
        try {
			return new ObjectMapper().writeValueAsString(topScores);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "";
    }
    
    private void sendTopScoresWithNames(Session session) throws IOException {
    	leaderboardService.fill();
        List<String> topScoresWithNames = leaderboardService.getTopScoresWithNames(10); // Get the top scores with user names
        String leaderboardData = String.join(",", topScoresWithNames); // Combine the scores into a single string
        session.getBasicRemote().sendText(leaderboardData); // Send the leaderboard data to the client
    }
}