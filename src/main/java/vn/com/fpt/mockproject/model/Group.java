package vn.com.fpt.mockproject.model;

public class Group {
	String id;
	String groupName;	
	public Group() {
		super();
	}
	public Group(String id, String groupName) {
		super();
		this.id = id;
		this.groupName = groupName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}