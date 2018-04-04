# mobileLab3 - Moving a ball using Android sensors
lab 3 for mobile at NTNU

With this app the user can tilt their mobile-phone in any direction and a (on screen) ball will move that way. If the ball hits the edge of the black box it "bounces" of it and the phone vibrates and plays a noice.

The app moves the ball using the gravity sensor.

## Project description

### Lab 3: Sensors

#### The idea

Create an app consisting of a single activity, that only works in a single mode: landscape. The activity will have a black rectangle drawn on a white background with a small margin on the outside of the square. In other words, there will be a small gap between the inner rectangle drawn on the screen and the phone outside edge. 

In the middle, in the initial position, draw a circle, or render an image, that is roughly 5% of the width of the screen. The circle (or image) will move around and bounce off the black boundery (you can use animations or move the view yourself, pixel by pixel). No external libraries allowed, use only Java and Android drawing APIs. 

The movement will be controlled by the phone sensors (gyroscope, accelerometer, gravity vector). The task is to detect the tilt of the phone, and move the "ball" towards the lowest point of the phone tilt. 

The app will generate additional events upon ball hitting the boundary rectangle. The app will make the phone emit a "ping" sound, and you the phone will vibrate just enough to signal the ball bouncing off the boundary. This will provide additional audio and haptic feedback to the user. Those additional events are easy to program - few additional lines of code. 


#### Technologies

* Sensors
* Permissions
* "Free" drawing on canvas, textures, animations
* Media play (audio)
* Vibrations (haptics)



#### Checklist

- [x] The repo URL is in correct format.

- [x] The code is well written, structured, and Linter warnings are non existed, or well documented and properly justified. 

- [x] The app starts with the "ball" in the middle of the screen, and rectangle around the screen with the gap to the outside edge of the phone.

- [x] When the phone is placed flat on the table, and the app is started, the "ball" does not move much from the center (understandably, the "move much" is a subjective test, discuss the result with the author of the app).

When the phone is held in a hand, the "ball" can be controlled by tilting the phone, and the ball moves as if pulled by the Earth gravity. Discuss the "control feel" of the ball movement with the author.

- [x] No lag, immediate feedback.

- [ ] Some lag, acceptable.

- [ ] Large lag, non-acceptable. 



When the ball hits the drawn edge, there is a "ping" sound. The delay between the visual event, i.e. the ball hitting the wall, and the sound, is "acceptable". If the delay is laggy, discuss with the author of the app.


- [ ] No lag, immediate feedback.

- [x] (sometimes) Some lag, acceptable.

- [ ] Large lag, non-acceptable. 



 When the ball hits the edge, there is a short vibration event that provides haptic feedback to the user. 


- [x] No lag, immediate feedback.

- [ ] Some lag, acceptable.

- [ ] Large lag, non-acceptable. 



- [ ] Bonus: the magnitude of the tilt speeds up or slows down the ball movement.
