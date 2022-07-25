package socket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dao.FriendDAO;
import dao.MyPageDAO;
import vo.ChattingVO;

@ServerEndpoint(value = "/chatting/friend", configurator = HttpSessionConfigurator.class)
public class FriendChatting {
	private static Map<Session, EndpointConfig> clients = Collections.synchronizedMap(new HashMap<>());

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		clients.put(session, config);
	}

	@OnMessage
	public void onMessage(String data, Session session) throws IOException {
		synchronized (clients) {
			try {
				FriendDAO friendDao = new FriendDAO();
				MyPageDAO mypageDao = new MyPageDAO();
				JSONObject obj = (JSONObject) new JSONParser().parse(data);
				String userId = obj.get("sendId").toString();
				String nickname = obj.get("accept").toString();
				String message = obj.get("message").toString();
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				ChattingVO vo = new ChattingVO(userId, friendDao.getUserId(nickname), message, dateFormat.format(now), "");
				mypageDao.insertChat(vo);

				for (Map.Entry<Session, EndpointConfig> client : clients.entrySet()) {
					if (!client.getKey().equals(session)) {
						JSONObject sendMessage = new JSONObject();
						sendMessage.put("sendId", userId);
						sendMessage.put("sendNickname", friendDao.getNickname(userId));
						sendMessage.put("accept", friendDao.getUserId(nickname));
						sendMessage.put("message", obj.get("message").toString());
						sendMessage.put("date", dateFormat.format(now));
						client.getKey().getBasicRemote().sendText(sendMessage.toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
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
