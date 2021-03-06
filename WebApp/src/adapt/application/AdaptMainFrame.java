package adapt.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.restlet.Component;
import org.restlet.data.Protocol;

import adapt.utils.Settings;

public class AdaptMainFrame extends JFrame {

	private JTextPane statusPane;
	Component component;
	
	public AdaptMainFrame() {
		setTitle(Settings.APP_TITLE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		component = new Component();
		init();
	}

	private void init() {
		statusPane = new JTextPane();
		statusPane.setEditable(false);
		statusPane.setFont(new Font("Monospaced",Font.PLAIN,12));
		statusPane.setForeground(Color.white);
		statusPane.setBackground(Color.black);

		AdaptMainFrame.this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					component.stop();
					System.exit(-1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});
		
		add(statusPane, BorderLayout.CENTER);

		try {
			runApp();
			displayText("Server running...", 0);
		} catch (Exception e1) {
			displayText(e1.getMessage(), 1);
			e1.printStackTrace();
		}
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:8182/"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
 
	/*
	 * TYPE: 0 - INFO, 1 - ERROR
	 */
	public void displayText(String text, int type) {
		StyledDocument document = statusPane.getStyledDocument();
		SimpleAttributeSet set = new SimpleAttributeSet();
		statusPane.setCharacterAttributes(set, true);
		SimpleDateFormat formatter = new SimpleDateFormat("MMM d  H:mm:ss");
	    Date today = new Date();
	    String prefix = "[" + formatter.format(today) + "] ";
	    StyleConstants.setForeground(set, Color.white);
	    if(type == 1) {
	    	StyleConstants.setForeground(set, Color.red);
	    	prefix = "[ERROR] ";
	    }
		try {
			document.insertString(document.getLength(), prefix + text + "\n", set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		statusPane.setCaretPosition(statusPane.getDocument().getLength());
	}

	public void runApp() throws Exception {
		component.getServers().add(Protocol.HTTP, 8182);
		component.getClients().add(Protocol.FILE);
		component.getClients().add(Protocol.HTTP);
		AdaptApplication app = new AdaptApplication();
		app.setMainFrame(AdaptMainFrame.this);
		component.getDefaultHost().attach(app);
		component.start();
		displayText("Starting internal server on port 8182", 0);
	}
	
	

}
