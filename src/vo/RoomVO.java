package vo;

public class RoomVO {
	private String roomId;
	private String title;
	private String userId;
	private int personnel;
	private boolean roomPrivate;
	private String password;
	private boolean state;
	private String map;

	public RoomVO() {
		super();
	}

	public RoomVO(String roomId, String title, String userId, int personnel, boolean roomPrivate, String password,
			boolean state, String map) {
		super();
		this.roomId = roomId;
		this.title = title;
		this.userId = userId;
		this.personnel = personnel;
		this.roomPrivate = roomPrivate;
		this.password = password;
		this.state = state;
		this.map = map;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPersonnel() {
		return personnel;
	}

	public void setPersonnel(int personnel) {
		this.personnel = personnel;
	}

	public boolean isRoomPrivate() {
		return roomPrivate;
	}

	public void setRoomPrivate(boolean roomPrivate) {
		this.roomPrivate = roomPrivate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}
}
