package socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import vo.ChattingVO;
import vo.UserVO;

@ServerEndpoint(value = "/chatting", configurator = HttpSessionConfigurator.class)
public class Chatting {
	private Map<Session, EndpointConfig> clients = Collections.synchronizedMap(new HashMap<>());
	private UserVO user = null;

	@OnMessage
	@SuppressWarnings("unlikely-arg-type")
	public void onMessage(String message, Session session) throws IOException {
		synchronized (clients) {
			for (Map.Entry<Session, EndpointConfig> client : clients.entrySet()) {
				if (!client.equals(session)) {
					String nickname = this.user.getNickname();
					String text = "{"
							+ "\"nickname\" : " + nickname + ", "
							+ "\"message\" : " + message
							+ "}";
					client.getKey().getBasicRemote().sendText(text);
				}
			}
		}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession userSession = (HttpSession) config.getUserProperties().get("Session");
		this.user = (UserVO) userSession.getAttribute("randomtour-user");
		System.out.println(session);
		clients.put(session, config);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println(session);
		clients.remove(session);
	}
}