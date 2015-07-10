package restapi;

public class Relationship {
	
	public int p_id1;
	public int p_id2;
	
	public Relationship(int p_id1, int p_id2) {
		this.p_id1 = p_id1;
		this.p_id2 = p_id2;
	}
	
	public int getId1(){
		return p_id1;
	}
	
	public int getId2() {
		return p_id2;
	}
}