package socket;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class HttpSessionConfigurator extends Configurator {
	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		// HttpRequest로부터 Session을 받는다.
		HttpSession session = (HttpSession) request.getHttpSession();
		// HttpSession으로 부터 Context도 받는다.
		ServletContext context = session.getServletContext();
		config.getUserProperties().put("Session", session);
		config.getUserProperties().put("Context", context);
	}
}
