package contract.repository;

import java.util.List;

import contract.gui.view.swing.table.HireEntry;
import contract.model.HireAbstractModel;

public interface NewHire {

	long add(HireAbstractModel hire) throws EntityAlreadyExistsException;
	
	HireAbstractModel get(long id) throws EntityNotFoundException;
	
	boolean update(HireAbstractModel hire) throws EntityNotFoundException;
	
	void setOpts(ComboOptions opts);

	public List<HireEntry> list();
}
