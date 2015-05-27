package pt.keep.dbptk.gui;

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;

public interface DBMSPane {

	public boolean isInputValid();
	public DatabaseImportModule getImportModule();
	public DatabaseHandler getExportModule();
	
}
