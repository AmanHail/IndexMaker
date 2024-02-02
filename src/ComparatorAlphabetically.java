import java.util.Comparator;

public class ComparatorAlphabetically implements Comparator<IndexEntry> {

	private boolean forward; //determines whether list is sorted alphabetically or not 
	
	public ComparatorAlphabetically() {
		forward = true;
	}
	public ComparatorAlphabetically(boolean forward) {
		this.forward = forward;
	}
	
	public int compare(IndexEntry e1, IndexEntry e2) {
		int diff = e1.compareTo(e2);
		if (forward) {
			return diff;
		}
		return -diff;
	}
}
