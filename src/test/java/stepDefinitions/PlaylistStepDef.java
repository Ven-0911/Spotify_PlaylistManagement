package stepDefinitions;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.PageObject;
import pages.PlaylistApi;
import static org.junit.Assert.*;

import java.io.IOException;

public class PlaylistStepDef {
	
	Response response;
	public static String spotifyUserId;
	public static String playlistId;
	public static String snapshotId;
	PlaylistApi playlist = new PlaylistApi();
	
	@Given("Request authorisation from user and retrieve access token")
	public void request_authorisation_from_user_and_retrieve_access_token() throws Exception {
	    playlist.getAuthorizationCode();
	    playlist.getAccessToken();
	}
	@When("User calls get profile call with GET http method")
	public void user_calls_get_profile_call_with_get_http_method() {
	  response = playlist.getUserSpotifyId();
	}
	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(int statusCode) {
		assertEquals(response.getStatusCode(), statusCode);
	}
	@Then("User gets user {string} from response")
	public void user_get_the_user_id_from_the_response_code(String id) {
	  spotifyUserId = PageObject.readJson(response, id);
	  
	}
	
	@Given("Get user spotify user id")
	public String get_user_spotify_user_id() {
	 return spotifyUserId;
	}
	@When("User creates playlist using user id with POST http method")
	public void user_calls_with_post_http_method() {
	   response = playlist.createPlaylist(get_user_spotify_user_id());
	   
	}
	@Then("User gets playlist {string} from response")
	public void user_get_the_playlist_id_from_the_response_code(String id) {
		playlistId = PageObject.readJson(response, id);
		
	}
	
	@Given("Get spotify playlist id")
	public String get_spotify_playlist_id() {
	    return playlistId;
	}
	@When("User adds items to playlist through POST http method")
	public void user_adds_items_to_playlist_through_post_http_method() {
	    response = playlist.addItemsToPlaylist(get_spotify_playlist_id());
	    
	}
	@Then("User gets {string} from response")
	public void user_gets_snapshot_id_from_the_playlist(String snapshot_Id) {
	    snapshotId = PageObject.readJson(response, snapshot_Id);
	    
	}
	
	@When("User remove item from playlist through DELETE http method")
	public void user_remove_item_from_playlist_through_delete_http_method() {
	    response = playlist.removeItemsFromPlaylist(playlistId, snapshotId);
	    
	}
	
	@When("User change the order and items in playlist through PUT http method")
	public void user_change_the_order_and_items_in_playlist_through_post_http_method() {
	    response = playlist.updatePlaylistItems(playlistId);
	    
	}
	
	@When("User gets the items in the playlist through GET http method")
	public void user_gets_the_items_in_the_playlist_through_get_http_methof() {
	    response = playlist.getPlayListItem(playlistId);
	    
	}
	@Then("User get of number of {string} items present in playlist")
	public void user_get_of_number_of_items_present_in_playlist(String totalItems) {
	    String items = PageObject.readJson(response, totalItems);
	    
	}
	@When("User add the custom image to playlist through PUT http method")
	public void user_add_the_custom_image_to_playlist_through_put_http_method() throws IOException {
	    response = playlist.addCustomPlaylistImage(playlistId);
	    
	}
	@When("User get the custom image of playlist through GET http method")
	public void user_get_the_custom_image_of_playlist_through_get_http_method() {
	    response = playlist.getPlaylistCoverImage(playlistId);
	    
	}
	@Then("User gets the {string} of source image")
	public void user_gets_the_url_of_source_image(String url) {
		String imageUrl = PageObject.readJson(response, url);
		
	}
	
	
	


}
