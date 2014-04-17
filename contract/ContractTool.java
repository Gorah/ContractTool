package contract;

import contract.repository.ComboOptions;

public class ContractTool {
	private ComboOptions comboOptions;

	public ContractTool(ComboOptions options){
		this.comboOptions = options;
	}

	public ComboOptions getComboOptions() {
		return comboOptions;
	}
	
	
}
