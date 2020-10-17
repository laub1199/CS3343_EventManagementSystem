package EventManagementSystem;

public enum Major {
	cs("cs", "Computer Science"),
	ds("ds", "Data Science"),
	ee("ee", "Electrical Engineering"),
	sci("sci", "Science"),
	is("is", "Information System"),
	acc("acc", "Accounting"),
	mkt("mkt", "Marketing"),
	cm("cm", "Creative Media"),
	law("law", "Law"),
	eng("eng", "English"),
	vm("vm", "Veterinary Medicine");

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
	
	public static Major getMajor(String major) throws ExMajorNotFound {
		for (Major m : values()) {
			if(m.getName().equals(major)) {
				return m;
			}
		}
		throw new ExMajorNotFound();
	}
}
