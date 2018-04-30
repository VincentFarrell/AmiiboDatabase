 Download APK Here - <a href="https://drive.google.com/open?id=1ziDFZUN6he2n-NA48dRrJ1oxomZNdaye" download="AmiiboDatabase.APK">Amiibo Database.APK</a>
 ### Amiibo Database V2 ###
  ## Version 2 Of AmiiboDatabase App
  
* Author: Vincent Farrell
* Student Number: 20076447
* Course: SSD
* Year: 2nd Year
* Module: Mobile Application Development
  
 ### App Functionality ###
 A Database of Amiibo's (Figures) that have unique Names, identifiers, ID's etc.
 Information on All Amiibo's is parsed from a JSON API (Amiiboapi.com/api/amiibos/).
 Saved Amiibos and New Amiibos that are Created are persisted using FireBase.
 
 StartScreen Loads on App launch, User clicks SHOW AMIIBOS to load the listView containing all amiibos, or clicks MY AMIIBOS to display their list of saved/created amiibos.
 
 Within SHOW AMIIBOS, the Activity populates a ListView with all Amiibos.
 Button in top right corner opens Search Dialog, User is prompted for name/id/gameseries input. App will then Parse Amiibos
    from the URL with the added name input ( " amiiboapi.com/api/amiibo/ ?(name)/(id)/(gameseries)/  [userInput] " )
    
 Within MY AMIIBOS, the Activity populates a ListView with saved/created amiibos.
 Button in top right corner opens Create New Amiibo Dialog, user is promted for all info. App will then push the data to Firebase and the list will populate with the new data.
 
  
 ### Known Bugs ###
Currently No Known Bugs as of 30/04/2018
