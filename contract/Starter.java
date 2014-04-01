package contract;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import contract.controller.AppController;
import contract.gui.view.swing.Main;
import contract.gui.view.swing.NewHire;
import contract.gui.view.swing.OpenContract;

public class Starter {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			Logger.getLogger(Starter.class.getName())
				.log(Level.WARNING, "Invalid look and feel class");
		}
		
		ContractTool tool = new ContractTool();
		AppController app = new AppController(tool);
		app.addViews(new Main(),
					new OpenContract(),
					new NewHire());
		app.init();
	}

}
