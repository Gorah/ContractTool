package contract.model;

import java.util.Map;

public class HireModel extends HireAbstractModel {

	public HireModel() {
	}
	
	public HireModel(Map<String, String> map){
		super(map);
	}

	@Override
	public void readDataFromFile() {}

}
