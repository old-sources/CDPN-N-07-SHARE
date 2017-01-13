package crowdfundingRestAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
@Singleton
public class WebSocketAlert {

	private List<Session> sessions = new ArrayList<>();

	@OnOpen
	public void open(Session session) {
		sessions.add(session);
	}
	@OnClose
	public void close(Session session, CloseReason c) {
		sessions.remove(session);
	}
	
	public void ping(){
		for (Session session : this.sessions) {
			try {
				session.getBasicRemote().sendText("ping");
			} catch (IllegalArgumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
