1. User with twitter handle @user tweets @SC2016_Connect4 with 
   `REGISTER URL:http://10.250.23.12:7040, VERSION:1`
2. Server receives event on twitter stream parses the tweet from @user
   and registers the new bot by inserting it into the lobby
3. On insertion to the lobby the server checks to see if another bot is present
4. Another bot is present, and a new game is created registering each bot as remote players
5. Both bots are removed from the lobby, or are marked busy
6. The server coordinates turns between each remote bot
7. Once the game is one, the authors of the bot are alerted via twitter whether they won or lost the game and who it was against
8. The bots reenter the lobby, or are marked free
