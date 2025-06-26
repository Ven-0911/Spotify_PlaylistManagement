package utils;

import java.util.*;
import java.util.Arrays;

import pojo.AddItemsToPlaylist;
import pojo.CreatePlaylist;
import pojo.RemoveItemsFromPlaylist;
import pojo.Tracks;
import pojo.UpdateItemsInPlaylist;

public class Payload {
	
	public CreatePlaylist createPlaylistPayLoad() {
		
		CreatePlaylist cp = new CreatePlaylist();
		cp.setName(ConfigReader.get("name"));
		cp.setDescription(ConfigReader.get("description"));
		cp.setPublicValue(ConfigReader.get("public"));
		
		return cp;
	}
	
	public AddItemsToPlaylist addPlaylistItem() {
		
		AddItemsToPlaylist add = new AddItemsToPlaylist();
		add.setUris(Arrays.asList(ConfigReader.get("uris").split(",")));
		add.setPosition(Integer.valueOf(ConfigReader.get("position")));
		
		return add;
	}
	
	public RemoveItemsFromPlaylist removeItemPayload(String snapId) {
		
		Tracks track = new Tracks();
		track.setUri(ConfigReader.get("removeUri1"));
		
		RemoveItemsFromPlaylist rem = new RemoveItemsFromPlaylist();
		List<Tracks> t = new ArrayList<Tracks>();
		t.add(track);
		rem.setSnapshotId(snapId);
		rem.setTracks(t);
		return rem;
		
	}
	
	public UpdateItemsInPlaylist updateItemPayLoad() {
		
		UpdateItemsInPlaylist update = new UpdateItemsInPlaylist();
		update.setUris(Arrays.asList(ConfigReader.get("updateUris").split(",")));
		update.setInsert_before(Integer.valueOf(ConfigReader.get("range_length")));
		update.setRange_length(Integer.valueOf(ConfigReader.get("insert_before")));
		update.setRange_start(Integer.valueOf(ConfigReader.get("range_start")));
		return update;
	}

}
