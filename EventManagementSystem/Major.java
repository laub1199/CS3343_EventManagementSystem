package EventManagementSystem;

public enum Major {
	cs("cs", "Computer Science"),
	ee("ee", "Electrical Engineering"),
	sci("sci", "Science");
	
	private final String major;  
	private final String majorFullTitle;
	
	Major(String maj, String majorFullTitle) {
		major = maj;
		this.majorFullTitle = majorFullTitle;
    }
	
	String getMajorFullTitle() {
		return majorFullTitle;
	}
	
	String getName() {
		return major;
	}
	
	public static Major getMajor(String major) {
		for (Major _enum : values()) {
			if(_enum.getName().equals(major)) {
				return _enum;
			}
		}
		return null;
	}
}
