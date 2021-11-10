Part of Android & Web Development - ISMIN 2021

Course followed by students of Mines St Etienne, ISMIN - M2 Computer Science.

[![Mines St Etienne](./logo.png)](https://www.mines-stetienne.fr/)

# Project 

This project provides information on the apartments offered to students by the Crous in France.

It is designed with an API and a mobile application.


## API

### Launch or join the API

1- You can run the api on your computer.

In the `./api` folder of the project.
Run the commands : 
- `npm install`
- `npm run start`

The API will ben started at the address `http://localhost:8080` of your computer.

2- You can use the CleverCloud site to access the API (if Clever still deploy this API).

The API address is `https://crous-project-bjy-ndn.cleverapps.io/`.

### Send request

You could use your browser or Postman to send request to the API.
According to your choice. 
The url will be : 
- `http://localhost:8080`
- `https://crous-project-bjy-ndn.cleverapps.io/`

### API Requests 

The following requests are available in the API.

- GET /apartments : to show all apartments of the Crous.
- GET /apartments/:id : to have the details of one specific apartment.
- PUT /apartments/:id (body  { favoris:true/false}) : to add an apartment to the favorites.
- GET /apartments/Favorites : to have all the favorites
- DELETE /apartments/:id : to delete an apartment from the favorites

## Android Application

### Installation 

Open the folder `./android` on Android Studio.

Run the application on your Android Smartphone or on an emulator device.

### Use 

Open the application Crous.

It includes 3 pages:
- A list of the apartments in France
For each accommodation, you will find its name, id and zone.
For more details, click on an item
You can scroll the list from top to bottom.
- A map of the apartments
- Some informations about the application

### images
Most apartments don't have a corresponding image , so a default image is used instead for these apartments.



