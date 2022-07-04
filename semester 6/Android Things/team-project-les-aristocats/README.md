# Motion activated night light

### Overview:
&nbsp;&nbsp;&nbsp;&nbsp; This project is looking to create a light bulb that is activated by motion and only turns on during the night. We will use a motion sensor 
connected to a Raspberry Pi 4 in order to detect when a person is moving in front of the light. At the same time, a light sensor will record the current amount of 
light in the environment and send the value to the script. When the motion sensor detects movement and the light is below a certain threshold, that’s when the light 
bulb will turn on.

&nbsp;&nbsp;&nbsp;&nbsp; This project can then be used in multiple ways depending on the settings: it can just be a normal night light placed above a house’s front 
door with a timer, it can be used to light up a path in a show spectacular way where you pass the light, it turns on while you are moving in front of it and then turns
off and you move on to the next light in the path.

&nbsp;&nbsp;&nbsp;&nbsp; Using both a light sensor and a motion sensor ensures that the light doesn't turn on when there’s light outside, making this project 
economical and ecological. The user can also set the amount of seconds the light stays on depending on its preferred use to further its economical and ecological 
factors.


### Schematics Plan:
![Schematics here](https://github.com/at-cs-ubbcluj-ro/team-project-les-aristocats/blob/master/schematics.png)


### Pre-requisites:
- Raspberry Pi 4
-Motion sensor
- Light sensor CJMCU-9930 
- Led
- Resistor
- Jumper leads: female-male
- Ethernet cable
- Power source
- Laptop
- Python3 


### Setup and Build:
The steps towards building our project were:
- Connect the LED to the breadboard. The longer wire of the LED (anode) is connected to the breadboard on the left side, while the cathode is connected on the right 
side of the breadboard.
- Add a resistor. The resistor is connected to the breadboard with one of its legs in the same row as the cathode. The other leg is connected to any spot on the right.
- Using a female-to-male wire, the male end is inserted into the same row as the LED, while the female end is inserted into a 3V3 pin on the Raspberry.
- A second female-to-male wire is inserted with the female end into the GND pin of the raspberry, and the male end is inserted into the same row as the resistor.
- A motion sensor is connected to the raspberry, using the following configuration: VCC to 5V, Out to GPIO3, GND to GND.
- A light sensor is connected to the raspberry, using the following configuration: Vin to 3v3 pin, Gnd to the ground pin,  SCL to GPIO17, SDA to GPIO27.
- The raspberry pi is plugged into a router using an ethernet cable.
- The raspberry pi is plugged to a power source.


### Running:
- Using an Advanced IP Scanner on a laptop, we find the IP address of the raspberry pi. We must make sure that we are connected to the same router, either via WI-FI 
or via ethernet cable.
- Connect to the raspberry by entering into CMD and typing: ssh pi@IPADDRESS and inserting password when prompted.
- Run the script using command “python3 nightlight.py”



