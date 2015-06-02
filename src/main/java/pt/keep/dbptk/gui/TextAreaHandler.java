package pt.keep.dbptk.gui;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.LogRecord;

import javax.swing.SwingUtilities;

import javafx.scene.control.TextArea;

public class TextAreaHandler extends java.util.logging.Handler {

    private TextArea textArea = new TextArea();

    

    public TextArea getTextArea() {
        return this.textArea;
    }

	@Override
	public void publish(LogRecord record) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                StringWriter text = new StringWriter();
                PrintWriter out = new PrintWriter(text);
                out.println(textArea.getText());
                out.printf("[%s] [Thread-%d]: %s.%s -> %s", record.getLevel(),
                        record.getThreadID(), record.getSourceClassName(),
                        record.getSourceMethodName(), record.getMessage());
                textArea.setText(text.toString());
            }

        });
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws SecurityException {
		// TODO Auto-generated method stub
		
	}

    //...
}
