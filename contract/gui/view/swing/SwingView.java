package contract.gui.view.swing;
import javax.swing.JFrame;

import contract.gui.view.AbstractView;

/**
 * SwingView class implements AbstractView class 
 * for the purposes of creating Swing based GUI view template.
 * 
 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
 * @version 1.0
 * @see contract.gui.view.AbstractView
 */
public abstract class SwingView extends AbstractView {

	protected static JFrame mainContainer;
	
	/**
	 * Only constructor for the class. It's registering enum view name.
	 * 
	 * @param name enum name parameter for the view.
	 */
	public SwingView(Name name) {
		super(name);
	}

}
