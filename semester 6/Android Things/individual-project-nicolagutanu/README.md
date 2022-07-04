# Counting of people using a motion detecting sensor


### Overview:
&nbsp;&nbsp;&nbsp;&nbsp; This idea came about with the thought of public transportation in other European countries. I have visited a few of them and so far, most of them have the same rule when using public transport: everyone gets on through the front door of the bus/train/tram/etc. where they either buy a ticket or swipe their transportation card.

&nbsp;&nbsp;&nbsp;&nbsp; In wanting to see how many people use a certain public transport line and what times are the busiest, I thought of creating a project that would count how many people get on the bus. This would then help the company that deals with public transport in better organizing and using their resources by knowing if a line needs more cars or should be completely cut because the traffic is too low and those resources could be better used in other places.

&nbsp;&nbsp;&nbsp;&nbsp; How this would work is: one person gets on the bus and triggers the sensor that is placed above the door. As I have noticed in my travels, people are rather civilized when getting on the bus, going one at a time, not pushing to get in first or blocking the entrance by crowding it unnecessarily. So the sensor should have no problem counting the people coming in one at a time. We are also looking to save the timestamp of each person counted so as to be able to tell at all times when the bus was the busiest.


### Schematics Plan:

![pir_wiring](https://user-images.githubusercontent.com/72856988/161393014-18580b1b-b3d2-417a-b3ce-365ca91f322c.png)


### Pre-requisites:
- Raspberry Pi 4
- Motion sensor 
- Jumper leads: female-female
- Ethernet cable
- Power source
- Laptop
- Python3


### Running:
- Connect the motion sensor to the Raspberry Pi with female-to-female leads as shown in the figure above
- Start up the Raspberry Pi by plugging it to a power source and binding it to the laptop with an Ethernet cable
- Open the command line and enter the command: “ssh pi@ip_address“ followed by the password
- Move to the “motionProject” directory on the Desktop
- Run the motionSensor.py script and the code will register the timestamp of every person that passes in front of the sensor and save it to a file named with the current date (ex: “2022-03-30.txt”)
- Run the countAfterTime.py script and input the start time and end time (ex: start time: 12 and end time: 14) to find the number of people that have passed the sensor in that time

