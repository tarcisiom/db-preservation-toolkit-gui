package pt.keep.dbptk.gui;

import java.util.ArrayList;
import java.util.Set;

import pt.gov.dgarq.roda.common.convert.db.model.data.Row;
import pt.gov.dgarq.roda.common.convert.db.model.exception.InvalidDataException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.UnknownTypeException;
import pt.gov.dgarq.roda.common.convert.db.model.structure.DatabaseStructure;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;

public class DatabaseHandlerGUI implements DatabaseHandler, Observable {

	private DatabaseHandler delegate;
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	private int rowCount = 0;

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
		rowCount = 0;
		
		notifyObservers("Processed this table "+arg0);
		// TODO update interface with table opened.
		delegate.handleDataOpenTable(arg0);
	}

	@Override
	public void handleDataRow(Row arg0) throws InvalidDataException,
			ModuleException {
		rowCount++;
		if (rowCount % 100 == 0) {
			// TODO update interface with row count
			updateRowcount(String.valueOf(rowCount)+" row(s) processed ");
		}

		delegate.handleDataRow(arg0);

	}

	@Override
	public void handleStructure(DatabaseStructure arg0)
			throws ModuleException, UnknownTypeException {
		// TODO Auto-generated method stub
		arg0.getSchemas().get(0).getTables().get(0).getRows();
		delegate.handleStructure(arg0);

	}

	@Override
	public void initDatabase() throws ModuleException {
		// TODO Auto-generated method stub
		delegate.initDatabase();
		
	}

	@Override
	public void setIgnoredSchemas(Set<String> arg0) {
		// TODO Auto-generated method stub
		delegate.setIgnoredSchemas(arg0);
		
	}
	
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
    public void removeObserver(Observer observer){
    	observers.remove(observer);
    	
    }
    public void notifyObservers(String arg0){
    	for (Observer ob : observers) {
            ob.update(arg0);
    	}
    }
    public void updateRowcount(String arg0){
    	for (Observer ob : observers) {
            ob.updateRowCount(arg0);
    	}
    }
    public void finish(String arg0){
    	for (Observer ob : observers) {
            ob.finish(arg0);
    	}
    }
	
	
	

}

