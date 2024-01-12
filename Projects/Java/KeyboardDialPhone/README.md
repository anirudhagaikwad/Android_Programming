# DialPhone Android App

Welcome to the "DialPhone" Android app – a straightforward and practical application that allows users to dial a phone number directly from the app. This app utilizes runtime permissions to request the CALL_PHONE permission and ensures a smooth user experience.


### Learning Objectives:

1. **Runtime Permissions:** Students will gain hands-on experience in implementing and handling runtime permissions, a crucial aspect of Android development introduced in Android 6.0.

2. **Intent Usage:** The app showcases the usage of Intent to initiate a phone call using the ACTION_CALL action.

3. **Toast Messages:** Toast messages are used to provide feedback to the user, such as when there's no app available to make a call or when permission is denied.

***
### App Functionality:

1. **Phone Number Input:**
   - Users can input a phone number into the EditText field provided in the app.

2. **Making a Call:**
   - Upon tapping the "Make Call" button, the app checks for the CALL_PHONE permission.
   - If the permission is granted, the app initiates a call to the specified phone number.
   - If the permission is not granted, the app requests permission from the user.

3. **Runtime Permission Handling:**
   - The app dynamically handles runtime permissions, ensuring compatibility with Android 6.0 (Marshmallow) and above.

4. **Log Information:**
   - The app logs the dialed phone number for reference.

***
### Instructions for Users:

1. Open the "DialPhone" app on your Android device.
2. Enter the desired phone number into the provided EditText field.
3. Tap the "Make Call" button.
4. If prompted, grant the app the necessary CALL_PHONE permission.
5. Observe the call being initiated to the specified phone number.

##### explore the implementation of runtime permissions, intent usage, and effective handling of phone calls in Android applications. This project is tailored to provide students with practical experience in creating user-friendly and permission-aware Android applications.