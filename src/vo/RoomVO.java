package vo;

public class RoomVO {
	private int roomId;
	private String title;
	private String userId;
	private int personnel;
	private boolean roomPrivate;
	private boolean state;
	private String map;

	public RoomVO() {
		super();
	}

	public RoomVO(int roomId, String title, String userId, int personnel, boolean roomPrivate, boolean state,
			String map) {
		super();
		this.roomId = roomId;
		this.title = title;
		this.userId = userId;
		this.personnel = personnel;
		this.roomPrivate = roomPrivate;
		this.state = state;
		this.map = map;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
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
