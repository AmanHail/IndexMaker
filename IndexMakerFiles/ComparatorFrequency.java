import java.util.Comparator;

public class ComparatorFrequency implements Comparator<IndexEntry>{
	
	public int compare(IndexEntry e1, IndexEntry e2) {
		int diff = e1.getFreq()-e2.getFreq();
		if (diff == 0) {
			diff = e1.compareTo(e2);
		}
		return diff;
	}
	
}
