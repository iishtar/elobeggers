package nl.tuvok.elobeggers;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocketHandler {

	private String sender, msg;

	@OnWebSocketConnect
	public void onConnect(Session user) throws Exception {
		String username = "User" + Main.nextUserNumber++;
		Main.userUsernameMap.put(user, username);
		Main.broadcastMessage(sender = "Server", msg = (username + " joined the Main"));
	}

	@OnWebSocketClose
	public void onClose(Session user, int statusCode, String reason) {
		String username = Main.userUsernameMap.get(user);
		Main.userUsernameMap.remove(user);
		Main.broadcastMessage(sender = "Server", msg = (username + " left the Main"));
	}

	@OnWebSocketMessage
	public void onMessage(Session user, String message) {
		Main.broadcastMessage(sender = Main.userUsernameMap.get(user), msg = message);
	}

}