package pt.keep.dbptk.gui;

public interface Observer {

	public void update(String table);
	public void updateRowcount(String table, String arg1, String arg2,String arg3);
	public void finish(String finish);
	
}
