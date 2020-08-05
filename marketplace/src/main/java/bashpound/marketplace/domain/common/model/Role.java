package bashpound.marketplace.domain.common.model;

public enum Role {
	ADMIN("ROLE_ADMIN"),
	MEMBER("ROLE_MEMBER");
	private String value;
	
	Role(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getKey() {
		return name();
	}
}
