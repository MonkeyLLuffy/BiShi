
public class Contract {

	String ID;
	String contractName;
	String partyA;
	String partyB;
	String contractType;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getPartyA() {
		return partyA;
	}
	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}
	public String getPartyB() {
		return partyB;
	}
	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	@Override
	public String toString() {
		return "Contract [ID=" + ID + ", contractName=" + contractName
				+ ", partyA=" + partyA + ", partyB=" + partyB
				+ ", contractType=" + contractType + "]";
	}
	
}
