#  HOUSE HUNTING API

### FRONTEND : https://github.com/Delyc/cozygo-fe

### INTRODUCTION
Welcome to our House Hunting App! Our app aims to simplify the process of finding your dream home as an international student in Rwanda. Our platform offers a comprehensive solution to help you discover the perfect property that meets your needs. With the help of multilingual chat support, you can easily communicate with agents and landlords in your preferred language.

### FEATURES
* **Search**:  Easily search for houses based on location, price range, number of bedrooms, and more.
* **Wishlist** : Save your favorite listings to view later or compare with other options and share your wishlist to others.
* **Map View**: Explore properties on a map to see their locations and nearby amenities. The Google Maps panorama feature allows you to virtually walk through the streets to explore neighborhoods.
* **Multilingual Chat**: Communicate with agents, landlords, and other users in your preferred language.

  And so many more, you are welcome to explore :)
  
### INSTALLATION
clone the repository
```
https://github.com/Delyc/cozy-go-api
```

clone frontend here
```
https://github.com/Delyc/cozygo-fe
```



# API ENDPOINT REFERENCE

### AUTH ENDPOINTS

| Method | Endpoint          | Description                           |
|--------|-------------------|---------------------------------------|
| POST   | /auth/signup      | Register a new user                   |
| POST   | /auth/signin      | Authenticate and sign in a user       |
| POST   | /auth/refresh     | Refresh authentication token          |
| GET    | /auth/user/{email}| Get user information by email address |

### HOUSE ENDPOINTS

| Method | Endpoint                                 | Description                             |
|--------|------------------------------------------|-----------------------------------------|
| POST   | /agent/addHouse/{userId}                 | Add a new house by agent     |
| PATCH  | /agent/updateHouse/{id}                  | Update details of a house               |
| GET    | /public/share/{id}                       | Generate  a sharable link to share house              |
| GET    | /public/houses                           | Get a list of all houses                |
| GET    | /public/houses/{id}                      | Get details of a specific house         |
| DELETE | /agent/deleteHouse/{id}                  | Delete a house by an agent|


### WISHLIST ENDPOINTS

| Method | Endpoint                            | Description                                   |
|--------|-------------------------------------|-----------------------------------------------|
| POST   | /user/toggle/{user_id}/add/{house_id} | Toggle adding a house to a user's wishlist |
| GET    | /user/wishlist/{userId}             | Get a user's wishlist                        |
| GET    | /public/wishlist/{shareId}          | Get a shared wishlist by shareId             |
| GET    | /public/wishlist/get/{user_id}      | Get a user's wishlist by user_id             |
| GET    | /public/sharewishlist/{user_id}     | Share a user's wishlist                     |

### SHECUDLING VISITS EDNPOINTS

| Method | Endpoint                                   | Description                                     |
|--------|--------------------------------------------|-------------------------------------------------|
| POST   | /user/book/{userId}/availability/{availabilityId} |Book a visit with a specific agent                        |
| PATCH  | /user/updateBooking/{id}                   | Update booking details for a user               |
| PATCH  | /agent/updateBooking/{id}                  |Update booking status (approve/decline) by agent             |
| GET    | /user/allBookings                          | Get all bookings for a user                     |
| GET    | /adminuser/booking/{id}                    | Get booking details for an admin user           |
| DELETE | /user/deleteBooking/{id}                   | Delete a booking for a user                     |

⚠️ The chat functionality in the application was implemented using Node.js and MongoDB to facilitate the easy use of sockets for real-time communication. 

### CHAT ENDPOINTS

| Method | Endpoint                                      | Description                                    |
|--------|-----------------------------------------------|------------------------------------------------|
| POST   | /api/messages/send/:senderId/:receiverId     | Send a message from one user to another        |
| GET  | /api/messages/:senderId/:userToChatId         | Fetch messages between two users               |
| GET    | /api/users                                    | Fetch all active users                         |

Clone the repository here :   `https://github.com/Delyc/cozygo-chat-be`


### USAGE
Our app is designed to be intuitive and easy to use. Simply use the search filters to narrow down your options, view listings, and save your favorites. You can also use the map view to explore properties in different areas. Contact agents directly through the app for more information or to schedule viewings.


### CONTRIBUTING
Contributions to this app are welcome to make it even better! If you have any suggestions, feedback, or would like to contribute to the development of new features, please fork the repository, make your changes, and then create a pull request with your contribution. Alternatively, you can contact delyceu@gmail.com for more information on how to contribute.

Thank you for considering contributing to this House Hunting App :)

# AUTHOR
Author: [Delyce Twizeyimana](https://github.com/delyc)  
Email: delyceu@gmail.com  
GitHub: [Delyce Twizeyimana](https://github.com/delyc)  
LinkedIn: [Delyce Twizeyimana](https://www.linkedin.com/in/delyce-twizeyimana-475977217/)
Portifolio: [Delyce Twizeyimana](https://delyce.netlify.app/)
