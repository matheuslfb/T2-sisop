
public class Bloco {

	private int start;
	private int end;
	private int size;
	
	private int ID;

	public Bloco(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	public int getSize() {
		return this.size;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public void setID(int id) {
		this.ID = id;
	}
	
	public int getID(){return this.ID;}
	
	public String toString() {
		return this.start+"-"+this.end+" | Bloco - "+ID;
	}

}
