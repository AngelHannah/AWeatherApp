
# AWeatherApp - The ReadMe
 This was a personal practice project where I followed a tutorial from YouTube and created a weather application UI and now I am adding a SQLite database with CRUD functionality to be able to sign up and log in to the application. I followed the Login and Signup tutorial so that I could get a better understanding of how to build the UIs because those can be challenging to plan out.

 
### Links to the tutorials I've referenced while making this project

- [Weather App: Android Studio Project By UiLover Android](https://www.youtube.com/watch?v=GFhKfMY0L2E&list=PLGEb2so2eBMrXdIrruYcvTeruX_QcHszF&index=6)
- [Get Current Time And Date In Android](https://www.geeksforgeeks.org/how-to-get-current-time-and-date-in-android/)
- [Login And SignUp In Android With SQLite](https://www.youtube.com/watch?v=-PMxYVNGl1c&list=PLGEb2so2eBMrXdIrruYcvTeruX_QcHszF&index=9)

### Project Testing Instructions
1. When the app launches it will bring you to the Login Activity because that's how I've got the intents set up. At the bottom of the login screen, just below the buttons will be a link to sign up which looks like the picture with the link underlined.

  ![login-signuplink](https://github.com/AngelHannah/AWeatherApp/assets/87335534/36333f9a-bd5c-4cb6-8037-2cd82d092eda)

2. Click the sign-up link and it'll bring you to the sign-up screen to create a new user. This takes the entered information and uses it to make a new user. This is the Create portion of CRUD functionality.
   
  ![filledoutsignup](https://github.com/AngelHannah/AWeatherApp/assets/87335534/7cfb2178-d50d-4cf5-b0e9-0f0dfaf7c322)
  
  ![successfulsignup](https://github.com/AngelHannah/AWeatherApp/assets/87335534/df375f21-df05-43da-a6ae-25419c6ea1d8)

3. To navigate to the Admin activity you can enter "Admin" and "Admin" as both the email and password and it will divert your login to the admin screen.

  ![adminpageview](https://github.com/AngelHannah/AWeatherApp/assets/87335534/3d1fae3f-f3f5-4a01-bd47-e8eed728439e)

4. To Update: Updating a user requires that the admin enters the original email, the new or existing email and the new or existing password then press update

5. To Delete: Delete is similar to update but only requires the admin to enter the email address into the 'original email' edit text




