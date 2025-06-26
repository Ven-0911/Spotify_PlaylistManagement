**Spotify's Playlist Management - End-to-End API Automation using RestAssured**

Spotify is a music streaming platform that allows users to listen to, download and organize songs and episodes into personalized playlists. A playlist on Spotify is essentially a curated list of tracks and episodes with a defined playback order.

Spotify implement the **OAuth2.0 authorization framework** and provide various scopes to ensure secure access when integrating third-party apps.

In this project, I developed an **end-to-end API automation framework** using **RestAssured** to create and manage Spotify playlists based on their Web API documentation. The automation covers full **CRUD operations**- including playlist creation, item additionand removal, playlist updates, and more.

Since the Spotify Web API uses OAuth2.0, the /authorize endpoint requires interactive user authentication in a browser. To automate this step, I integrated **Selenium**, which captures the redirectURI/callback with _code_ parameter. This code is then exchanged for an **access token** (valid for 1 hour), which is used to authorize subsequent API requests. 

The framework supports:
- Creating playlists
- Adding, removing, and reordering tracks
- Uploading and retrieving custom cover images

Technically, I implemented:
- **POJO classes** for request/response modeling
- **Serialization and deserialization** of JSON
- **RequestSpecification** to standardize headers and authorization
- Test automation logic using **Cucumber** and **Selenium**


**Installation Instructions:**
  1. Login into Spotify Developer Dashboard.
  2. Create a new app to generate your Client ID and Client Secret.
  3. Configure the redirect URI and required scopes for token generation

**Usage:**

_**cucumberOptions/TestRunner.java**_

Main runner class to execute all the scenarios present in the feature file. Generates Cucumber HTML and JSON reports.

_**configs/ConfigReader.properties**_

This property has all the information of user login credentials, app's client_id and client_secret to generate the access token and other information related to endpoints body parameters.

_**API Reference**_

https://developer.spotify.com/documentation/web-api

_**Test Evidence**_

<img width="406" alt="image" src="https://github.com/user-attachments/assets/caf46a72-73cc-480d-9dea-7b961955907c" />




