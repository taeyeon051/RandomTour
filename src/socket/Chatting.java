package socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import vo.UserVO;

@ServerEndpoint(value = "/chatting", configurator = HttpSessionConfigurator.class)
public class Chatting {
	private static Map<Session, EndpointConfig> clients = Collections.synchronizedMap(new HashMap<>());
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		clients.put(session, config);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		EndpointConfig config = clients.get(session);
		synchronized (clients) {
			for (Map.Entry<Session, EndpointConfig> client : clients.entrySet()) {
				HttpSession userSession = (HttpSession) config.getUserProperties().get("Session");
				if (!client.getKey().equals(session)) {					
					UserVO user = (UserVO) userSession.getAttribute("randomtour-user");
					String nickname = user != null ? user.getNickname() : "";
					String text = "{"
							+ "\"nickname\" : \"" + nickname + "\", "
							+ "\"message\" : \"" + message
							+ "\"}";
					System.out.println(text);
					client.getKey().getBasicRemote().sendText(text);
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		e.printStackTrace();
	}
}