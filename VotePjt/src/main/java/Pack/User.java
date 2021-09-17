package Pack;

public class User {
	private String UID;
	private String UNAME;
	private String PW;
	private String CID;
	
	public User(String UID, String UNAME, String PW, String CID) {
		System.out.println("생성자콜");
		this.UID = UID;
		this.UNAME = UNAME;
		this.PW = PW;
		this.CID = CID;
	}
	
	public String getUID() {
		return UID;
	}
	
	public void setUID(String uID) {
		this.UID = uID;
	}
	
	public String getUNAME() {
		return UNAME;
	}
	
	public void setUNAME(String uNAME) {
		this.UNAME = uNAME;
	}
	
	public String getPW() {
		return PW;
	}
	
	public void setPW(String pW) {
		this.PW = pW;
	}
	
	public String getCID() {
		return CID;
	}
	
	public void setCID(String cID) {
		this.CID = cID;
	}
}
