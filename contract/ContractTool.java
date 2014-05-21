package contract;

import contract.repository.ComboOptions;
import contract.repository.Login;
import contract.repository.NewHire;

public class ContractTool {
	private ComboOptions comboOptions;
	private NewHire newHireRepository;
	private Login loginRepo;

	public ContractTool(ComboOptions options, NewHire nhRepo, Login loginRepo){
		this.comboOptions = options;
		this.newHireRepository = nhRepo;
		nhRepo.setOpts(options);
		this.loginRepo = loginRepo;
	}

	public ComboOptions getComboOptions() {
		return comboOptions;
	}

	public NewHire getNewHireRepository() {
		return newHireRepository;
	}

	public Login getLoginRepo() {
		return loginRepo;
	}
	
	
}
