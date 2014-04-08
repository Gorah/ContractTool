package contract.gui.view.swing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import contract.gui.view.swing.filters.DocumentFilter;

/**
 * This is main view of the application. It's very simple and provides
 * only menu bar along with basic items of the File menu.
 * 
 * A listener is provided to support all the events in menu.
 * 
 * @author	Bartosz Kratochwil (bartosz.krtochwil@hp.com)
 * @version 0.9
 */
public class Main extends SwingView {

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu();
	private JMenuItem newContract = new JMenu();
	private JMenuItem saveData = new JMenuItem();
	private JMenuItem openContract = new JMenuItem();
	private JMenuItem exitProg = new JMenuItem();
	private JMenu edit = new JMenu();
	private JMenuItem loadFile = new JMenuItem();
	private JMenuItem newHire = new JMenuItem();
	private JMenuItem jobChange = new JMenuItem();
	private JFileChooser fc;
	
	/**
	 * This is constructor for Main view class.
	 */
	public Main(){
		
		super(Name.MAIN);
		
		//Create new JFrame container
		mainContainer = new JFrame();
		
		//Setting main menu title and attaching mnemonic
		menu.setText("File");
		menu.setMnemonic(KeyEvent.VK_F);
		//Create instance of menu listener
		MenuListener menuListener = new MenuListener();
		
		//Set menu option captions and attach mnemonic and listeners to them 
		newContract.setText("Create New");
		newContract.setMnemonic(KeyEvent.VK_N);
		newHire.setText("New hire");
		newHire.setMnemonic(KeyEvent.VK_H);
		newHire.addActionListener(menuListener);
		jobChange.setText("Job change");
		jobChange.setMnemonic(KeyEvent.VK_C);
		jobChange.addActionListener(menuListener);
		newContract.add(newHire);
		newContract.add(jobChange);
		openContract.setText("Open Contract");
		openContract.setMnemonic(KeyEvent.VK_O);
		openContract.addActionListener(menuListener);
		saveData.setText("Save");
		saveData.addActionListener(menuListener);
		exitProg.setText("Exit");
		exitProg.setMnemonic(KeyEvent.VK_X);
		exitProg.addActionListener(menuListener);
		
		
		//Attach menu items to main menu.
		menu.add(newContract);
		menu.add(openContract);
		menu.addSeparator();
		menu.add(saveData);
		menu.addSeparator();
		menu.add(exitProg);
		
		//Add main menu to menu bar
		menuBar.add(menu);
		
		//Set title of main frame and attach menu bar to it
		mainContainer.setTitle("MCBC Contract Tool");
		mainContainer.setJMenuBar(menuBar);
		mainContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainContainer.setResizable(true);	
	}
	
	
	
	/**
	 * Renders entire GUI. Content pane gets wiped clean.
	 */
	@Override
	public void render() {
		mainContainer.getContentPane().removeAll();
		mainContainer.setSize(640, 480);
		mainContainer.setLocationRelativeTo(null);
		mainContainer.setVisible(true);
		
	}
	
	/**
	 * Implementation of Action Listener for menu items on the form.
	 * Each action fires respective appController event and performs 
	 * visibility changes on some form elements.
	 * 
	 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
	 * @version 0.8
	 * @see contract.controller.AppController
	 *
	 */
	private class MenuListener implements ActionListener {

		/**
		 * Listens for action event and based on it's source
		 * appropriate events are fired by controller.
		 * 
		 * @author	Bartosz Kratochwil (bartosz.krtochwil@hp.com)
		 * @version	0.8
		 * @param	e	action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == exitProg) {
				//calls exit action
				appController.exit();
			} else if (e.getSource() == newHire){
				//calls rendering a new contract form
				appController.showNewHire();
				//adds additional menu item to menu bar
				edit.setText("Edit");
				loadFile.setText("Load File");
				loadFile.addActionListener(this);
				edit.add(loadFile);				
				menuBar.add(edit);
				mainContainer.setJMenuBar(menuBar);
				mainContainer.setSize(800, 700);
				
			} else if (e.getSource() == loadFile){
				//if no file chooser is setup, a new instance is created
				if(fc == null){
    				fc = new JFileChooser();
    			}
				//Custom document filter for doc, xls and pdf files is applied
				DocumentFilter filter = new DocumentFilter();
    			fc.addChoosableFileFilter(filter);
    			fc.setFileFilter(filter);
                fc.setAcceptAllFileFilterUsed(false);
                
                //if the file was selected, fire reading it
                int returnVal = fc.showOpenDialog(menuBar);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //fire data scanning and populating of the fields
                    appController.readFile(file.getPath());
                } 
                fc.setSelectedFile(null);
			} else if (e.getSource() == openContract){
				//fires rendering of contract search form.
				appController.showOpenContract();
			}
		}
		
	}

}
