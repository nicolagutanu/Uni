import RPi.GPIO as GPIO
from datetime import datetime
import time
GPIO.setmode(GPIO.BCM)

PIR_PIN=4

GPIO.setup(PIR_PIN,GPIO.IN)

print('PIR module test (CTRL-C to exit)')
time.sleep(2)
print('Ready')
current_day = str(datetime.now().date()) + ".txt"
f = open(current_day, 'w')

while True:
   if GPIO.input(PIR_PIN):
       now = datetime.now()
       current_time = now.strftime("%H:%M")
       print("Current Time =", current_time)
       f.write(current_time + "\n")
       print('Motion detected')
   time.sleep(1)
f.close()
