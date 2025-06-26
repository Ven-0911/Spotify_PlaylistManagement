package pages;

import static io.restassured.RestAssured.*;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import static io.restassured.config.EncoderConfig.encoderConfig;
import java.nio.file.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.support.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.GetAccessToken;
import utils.ConfigReader;
import utils.Payload;

public class PlaylistApi extends PageObject {
	public static String code;
	public static String accessToken;
	public static String id;
	Payload payload = new Payload();

	public void getAuthorizationCode() throws Exception {

		navigateToUrl(
				"https://accounts.spotify.com/authorize?client_id=" + ConfigReader.get("client_id") + "&response_type="
						+ ConfigReader.get("response_type") + "&redirect_uri=" + ConfigReader.get("redirect_uri")
						+ "&state=" + ConfigReader.get("state") + "&scope=" + ConfigReader.get("scope"));
		loginWithUsernameAndPassword(ConfigReader.get("username"), ConfigReader.get("password"));
		Thread.sleep(3000);
		String url = driver.getCurrentUrl();
		String extractCode = url.split("code=")[1];
		code = extractCode.split("&state")[0];
		
	}

	public void getAccessToken() {

		RequestSpecification accessTokenReqSpec = new RequestSpecBuilder()
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization",
						"Basic " + Base64.getEncoder().encodeToString(
								(ConfigReader.get("client_id") + ":" + ConfigReader.get("client_secret")).getBytes()))
				.addFormParam("grant_type", "authorization_code").addFormParam("code", code)
				.addFormParam("redirect_uri", ConfigReader.get("redirect_uri")).build();

		GetAccessToken getTokenAccess = given().spec(accessTokenReqSpec).when()
				.post("https://accounts.spotify.com/api/token").then().extract().as(GetAccessToken.class);

		accessToken = getTokenAccess.getAccess_token();
	}

	public Response getUserSpotifyId() {

		RequestSpecification userIdSpec = new RequestSpecBuilder().addHeader("Authorization", "Bearer " + accessToken)
				.build();

		Response response = given().spec(userIdSpec).when().get("https://api.spotify.com/v1/me");

		return response;
	}

	public Response createPlaylist(String userId) {
		RequestSpecification createPlaylistSpec = new RequestSpecBuilder()
				.addHeader("Authorization", "Bearer " + accessToken).setContentType(ContentType.JSON).build();

		Response response = given().spec(createPlaylistSpec).body(payload.createPlaylistPayLoad()).when()
				.post("https://api.spotify.com/v1/users/" + userId + "/playlists");

		return response;
	}

	public Response addItemsToPlaylist(String playlistId) {
		RequestSpecification addItemsSpec = new RequestSpecBuilder().addHeader("Authorization", "Bearer " + accessToken)
				.setContentType(ContentType.JSON).build();

		Response response = given().spec(addItemsSpec).body(payload.addPlaylistItem()).when()
				.post("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

		return response;
	}

	public Response removeItemsFromPlaylist(String playlistId, String snapID) {
		RequestSpecification removeItemSpec = new RequestSpecBuilder()
				.addHeader("Authorization", "Bearer " + accessToken).setContentType(ContentType.JSON).build();

		Response response = given().spec(removeItemSpec).body(payload.removeItemPayload(snapID)).when()
				.delete("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
		return response;
	}

	public Response updatePlaylistItems(String playlistId) {
		RequestSpecification updatePlaylistSpec = new RequestSpecBuilder()
				.addHeader("Authorization", "Bearer " + accessToken).setContentType(ContentType.JSON).build();

		Response response = given().spec(updatePlaylistSpec).body(payload.updateItemPayLoad()).when()
				.put("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

		return response;
	}

	public Response getPlayListItem(String playlistId) {
		RequestSpecification getPlaylistSpec = new RequestSpecBuilder()
				.addHeader("Authorization", "Bearer " + accessToken).build();

		Response response = given().spec(getPlaylistSpec).when()
				.get("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

		return response;
	}

	public Response addCustomPlaylistImage(String playlistId) throws IOException {
		byte[] imgByte = Files.readAllBytes(Paths.get("images/QueenCover.jpeg"));
		String base64Image = Base64.getEncoder().encodeToString(imgByte);

		RequestSpecification addImageSpec = new RequestSpecBuilder()
				.addHeader("Authorization", "Bearer " + accessToken).build();
		
		Response response = given().spec(addImageSpec).config(config()
				.encoderConfig(encoderConfig().encodeContentTypeAs("image/jpeg", ContentType.TEXT)
			    ))

				.header("Content-Type", "image/jpeg")
				.body(base64Image)
				.when().put("https://api.spotify.com/v1/playlists/" + playlistId + "/images");
		
		return response;
	}

	public Response getPlaylistCoverImage(String playlistId) {
		RequestSpecification getPlaylistCoverSpec = new RequestSpecBuilder()
				.addHeader("Authorization", "Bearer " + accessToken).build();

		Response response = given().spec(getPlaylistCoverSpec).when()
				.get("https://api.spotify.com/v1/playlists/" + playlistId + "/images");

		return response;
	}

}
