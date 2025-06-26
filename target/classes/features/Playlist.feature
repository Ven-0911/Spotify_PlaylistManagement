Feature: Create a Spotify playlist

  @GetUserCurrentProfile
  Scenario: Get user information
    Given Request authorisation from user and retrieve access token
    When User calls get profile call with GET http method
    Then API call got success with status code 200
    And User gets user "id" from response

  @CreatePlaylist
  Scenario: Create a spotify playlist
    Given Get user spotify user id
    When User creates playlist using user id with POST http method
    Then API call got success with status code 201
    And User gets playlist "id" from response

  @AddItemsToPlaylist
  Scenario: Add items to spotify playlist
    Given Get spotify playlist id
    When User adds items to playlist through POST http method
    Then API call got success with status code 201
    And User gets "snapshot_id" from response

  @RemoveItemFromPlaylist
  Scenario: Remove items from spotify playlist
    Given Get spotify playlist id
    When User remove item from playlist through DELETE http method
    Then User gets "snapshot_id" from response

  @UpdateItemsInPlaylist
  Scenario: Update items in the spotify playlist
    Given Get spotify playlist id
    When User change the order and items in playlist through PUT http method
    Then API call got success with status code 200
    And User gets "snapshot_id" from response

  @GetPlaylistItems
  Scenario: Get items in the spotify playlist
    Given Get spotify playlist id
    When User gets the items in the playlist through GET http method
    Then API call got success with status code 200
    And User get of number of "total" items present in playlist

  @AddCustomPlaylistImage
  Scenario: Add custom image to the spotify playlist
    Given Get spotify playlist id
    When User add the custom image to playlist through PUT http method
    Then API call got success with status code 202

  @GetCustomPlaylistImage
  Scenario: Get custom image of spotify playlist
    Given Get spotify playlist id
    When User get the custom image of playlist through GET http method
    Then API call got success with status code 200
    And User gets the "url" of source image
