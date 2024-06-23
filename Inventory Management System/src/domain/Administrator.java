package domain;

public class Administrator extends User {
	
	private static final String ADMIN_USERNAME = "admin";
	private static final String ADMIN_PASSWORD = "admin123";
	

    public Administrator()
	{
		super(ADMIN_USERNAME, ADMIN_PASSWORD);
	}
  
}
