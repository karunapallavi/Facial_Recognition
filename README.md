# Facial_Recognition
UnifyID Challenge

Build: Run the gradle with android studio. 
- connect your device


Further Considerations:
-First of all, I would have incorporated encryption for the images while storing in the external device/ storing on the device's memory.
-Storing the images locally would be inefficient. I would suggest to store it in an online repository instead. 
-Shared Preferences would be a better option if the images need to remain locally on the device.

-Storing the images as they were captured was a challenge. I redirected the images to an external storage device. 
-Forcing the front facing camera was another problem. 
cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
using this, I was able to use the front facing camera as default. 

- I used a countdown timer with a tick of 0.5s, where the camera was forced to take a picture.
