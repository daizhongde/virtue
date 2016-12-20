package person.daizhongde.virtue.dao;

public interface GlobalEnvDAO {
	public abstract String getSystemVersion();
	public abstract String getSystemDBDate();
	public abstract String getSystemDBDate( String fmt );
	public abstract String getSystemDBTime();

	public abstract String getSystemDBDateW();
	
}