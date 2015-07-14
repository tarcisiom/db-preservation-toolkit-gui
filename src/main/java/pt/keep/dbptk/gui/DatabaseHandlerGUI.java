package pt.keep.dbptk.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import pt.gov.dgarq.roda.common.convert.db.model.data.Row;
import pt.gov.dgarq.roda.common.convert.db.model.exception.InvalidDataException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.UnknownTypeException;
import pt.gov.dgarq.roda.common.convert.db.model.structure.DatabaseStructure;
import pt.gov.dgarq.roda.common.convert.db.model.structure.TableStructure;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;

public class DatabaseHandlerGUI implements DatabaseHandler, Observable {

	private DatabaseHandler delegate;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private HashMap<String,Integer> tableRows= new HashMap<String,Integer>();

	private int rowCount = 0;
	private int currentRow = 0;
	private int tableNumber = 0;
	private int totalRows = 0;
	private long start;

	public DatabaseHandlerGUI(DatabaseHandler delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public void finishDatabase() throws ModuleException {
		finish("Finished database");
		delegate.finishDatabase();
	}

	@Override
	public void handleDataCloseTable(String arg0) throws ModuleException {
		delegate.handleDataCloseTable(arg0);
		rowCount = 0;
	}

	@Override
	public void handleDataOpenTable(String arg0) throws ModuleException {
		Integer totalTableRows = tableRows.get(arg0);
		rowCount = 0;
		this.tableNumber++;
		updateTable(arg0,tableNumber,totalTableRows);
		delegate.handleDataOpenTable(arg0);
	}

	@Override
	public void handleDataRow(Row arg0) throws InvalidDataException,
			ModuleException {
		rowCount++;
		currentRow++;
		float elapsedTimeSec = (System.currentTimeMillis()-this.start)/1000F;
		if(elapsedTimeSec > 1 || rowCount == 1 || currentRow == totalRows){
			updateRowCount(rowCount,currentRow);
			this.start = System.currentTimeMillis();
		}
		delegate.handleDataRow(arg0);

	}

	@Override
	public void handleStructure(DatabaseStructure arg0) throws ModuleException, UnknownTypeException {
		
		int totalTables = 0;

		for(TableStructure table: arg0.getSchemas().get(0).getTables()){
			tableRows.put(arg0.getSchemas().get(0).getFolder()+"."+table.getName(),table.getRows());
			totalRows+=table.getRows();
			totalTables++;
		}
		updateTotal(totalRows,totalTables);
		delegate.handleStructure(arg0);
	}

	@Override
	public void initDatabase() throws ModuleException {

		this.start = System.currentTimeMillis();
		delegate.initDatabase();
		
	}

	@Override
	public void setIgnoredSchemas(Set<String> arg0) {

		delegate.setIgnoredSchemas(arg0);
		
	}
	
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
    public void removeObserver(Observer observer){
    	observers.remove(observer);
    	
    }
    
    public void updateTotal(int totalRows, int totalTables){
    	for (Observer ob : observers) 
    		ob.updateTotalObs(totalRows,totalTables);
    }

    public void updateTable(String tableName, int tableNumber, int tableRows){
    	for (Observer ob : observers) 
    		ob.updateTableObs(tableName,tableNumber,tableRows);
    }
    
    public void updateRowCount(int rowCount,int currentRow){
    	for (Observer ob : observers) {
            ob.updateRowCountObs(rowCount,currentRow);
    	}
    }
    public void finish(String arg0){
    	for (Observer ob : observers) {
            ob.finish(arg0);
    	}
    }
	
}

