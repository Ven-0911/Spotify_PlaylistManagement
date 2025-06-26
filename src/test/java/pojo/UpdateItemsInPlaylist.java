package pojo;

import java.util.List;

public class UpdateItemsInPlaylist {
	
	private List<String> uris;
	private int range_start;
	private int insert_before;
	private int range_length;
	
	public List<String> getUris() {
		return uris;
	}
	public void setUris(List<String> uris) {
		this.uris = uris;
	}
	public int getRange_start() {
		return range_start;
	}
	public void setRange_start(int range_start) {
		this.range_start = range_start;
	}
	public int getInsert_before() {
		return insert_before;
	}
	public void setInsert_before(int insert_before) {
		this.insert_before = insert_before;
	}
	public int getRange_length() {
		return range_length;
	}
	public void setRange_length(int range_length) {
		this.range_length = range_length;
	}
	
	

}
