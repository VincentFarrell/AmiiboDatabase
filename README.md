 README
 ### Amiibo Database ###
  
* Author: Vincent Farrell
* Student Number: 20076447
* Course: SSD
* Year: 2nd Year
* Module: Mobile Application Development
  
 ### App Functionality ###
 A Database of Amiibo's (Figures) that have unique Names, identifiers, ID's etc.
 Information on Amiibo's is parsed from a JSON API (Amiiboapi.com/api/amiibos/).
 
 StartScreen Loads on App launch, User clicks SHOW AMIIBOS to load the listView.
 App populates a ListView with all Amiibos.
 Button in top right corner leads to Search Activity, User is prompted for name/id/gameseries input. App will then Parse Amiibos
    from the URL with the added name input ( " amiiboapi.com/api/amiibo/ ?(name)/(id)/(gameseries)/  [userInput] " )
  
 ### Known Bugs ###
 [As of 18/03/2018]
* Images Do not update quickly whilst scrolling through ListView
* If no Radio Button is selected, it will default to search for Amiibo Name. 
* Search field requires the full input for AmiiboSeries/Gameseries etc.
