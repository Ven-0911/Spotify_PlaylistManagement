package pojo;

import java.util.List;

public class AddItemsToPlaylist {

	private List<String> uris;
	private int position;
	
	public List<String> getUris() {
		return uris;
	}
	public void setUris(List<String> uris) {
		this.uris = uris;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
