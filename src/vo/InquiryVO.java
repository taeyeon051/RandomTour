package vo;

public class InquiryVO {
	private int id;
	private String userId;
	private String title;
	private String select;
	private String content;

	public InquiryVO() {
		super();
	}

	public InquiryVO(int id, String userId, String title, String select, String content) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.select = select;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
