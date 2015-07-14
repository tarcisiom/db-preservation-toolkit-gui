package pt.keep.dbptk.gui;

public interface Observer {

	public void updateTableObs(String tableName, int tableNumber, int tableRows);
	public void updateRowCountObs(int rowCount,int currentRow);
	public void updateTotalObs(int totalRows, int totalTables);
	public void finish(String finish);
	
}
