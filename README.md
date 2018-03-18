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
 
 App populates a ListView with all Amiibos.
 Button in top right corner leads to Search Activity, User is prompted for name input. App will then Parse Amiibos
    from the URL with the added name input ( amiiboapi.com/api/amiibo/ ?name= [input] )
  
 ### Known Bugs ###
 [As of 16/03/2018]
* All Search Functions Not fully implemented (Currrently By Name)
* Splashscreen/Menu bug
* Difficulty Aquiring images from URL (Currently using placeholder Amiibo Image)
