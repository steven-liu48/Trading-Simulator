Features:
1. Show stock current price, price changes, etc.
2. Plot the historical trend 
3. User Registration and login
4. Buy and sell stocks

UC01 Registration
Flow: The user clicks on the "Registration" button. A form will appear and asks the user to input user name and password[1][2][3]. This data will be sent to the cloud database. The user will be notified.
Subflow: 
[1] If the user missed one of the fields, the corresponding field will turn red and the user cannot proceed until he or she enters the field.
[2] If the username has been used, the user will be notified, and he or she cannot proceed until he or she enters a new username.
[3] If the user input a different password in the password confirmation box, the user will be notified and asked to fix it.
Note: 
(1) Store only the hash of the password in database
(2) Do not store the registration information if the registration is unsuccessful(one of the subflows occurs).

UC02 Login
Flow: The user clicks on the "Login" button. A form will appear and asks the user to enter the username and password.[1] If the username and password match the record, the user is directed into the trading page. 
Subflow:
[1] If the username is not in the database, or the username and password do not match, the user will be notified and asked to reinput the information.
Note: only compare the hash of the passwords

UC03 Index Page
Prerequisite: The user has logged in in UC02
Flow: Displays the real-time market data and the users' portfolio. Tapping a stock block opens UC04.

UC04 Detailed Stock Page
Prerequisite: The user is on UC03 or UC06
Flow: If the user taps a stock block, a new page opens up and shows more detailed info of the stock, including current price, price changes, historical data, users' positions, etc. The user can press UP button to go back to UC03. There are buttons to purchase/sell the current stock, which is UC05. If the market is closed, the buttons are grey and users will be notified.

UC05 Buy/Sell Stock
Prerequisite: The user has entered detailed stock page in UC04
Flow: The user clicks on the "Buy/Sell" button on the detailed stock page. The user selects whether he or she want to sell or buy. The user will enter the amount of stock to buy or sell using a seek bar. The user's cash and stock will change accordingly.

UC06 Portfolio
Prerequisite: The user is on UC03
Flow: The user clicks on the second button on the navigation bar, and their portfolio will show up. Tapping any data block brings up UC04.

UC07 Profile Page
Prerequisite: The user is on UC03
Flow: The user clicks on the profile page button on the navigation bar, and their profile information will show up. They can enter UC08 by tapping “Settings” button.

UC08 Settings
Prerequisite: The user is on UC07
Flow: The user can change their settings on this page.
