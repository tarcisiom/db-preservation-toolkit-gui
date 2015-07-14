package pt.keep.dbptk.gui;

public interface Observable {
	
	public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);

}
