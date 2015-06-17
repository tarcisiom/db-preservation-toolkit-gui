package pt.keep.dbptk.gui;

public interface Observer {

	public void update(String table);
	public void updateRowCount(String table);
	public void finish(String finish);
	
}
