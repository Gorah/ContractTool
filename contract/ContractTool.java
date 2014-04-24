package contract;

import contract.repository.ComboOptions;
import contract.repository.NewHire;

public class ContractTool {
	private ComboOptions comboOptions;
	private NewHire newHireRepository;

	public ContractTool(ComboOptions options, NewHire nhRepo){
		this.comboOptions = options;
		this.newHireRepository = nhRepo;
		nhRepo.setOpts(options);
	}

	public ComboOptions getComboOptions() {
		return comboOptions;
	}

	public NewHire getNewHireRepository() {
		return newHireRepository;
	}
	
	
}
