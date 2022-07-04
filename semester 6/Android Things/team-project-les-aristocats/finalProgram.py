#LIGHT
import quick2wire.i2c as i2c
import math
from math import *
import os
import time
import string
import binascii
import array
from array import *
from quick2wire.i2c import I2CMaster, writing_bytes, reading
import sys
import RPi.GPIO as GPIO
import RPi.GPIO as GPIO_LED
from datetime import datetime
import time

int_register = 0x00
lux_high_register = 0x03
lux_low_register = 0x04
i = 0
c = '0'

GPIO.setmode(GPIO.BCM)

PIR_PIN=17

GPIO.setup(PIR_PIN,GPIO.IN)


address = 0x4a #Address will be either 0x4a or 0x04b
LIGHT = 0



def getLight():
        global LIGHT
        with i2c.I2CMaster() as bus:

        #Read register values from sensor
            high_state = bus.transaction(
                i2c.writing_bytes(address, lux_high_register),
                i2c.reading(address, 2))[0][0]

            low_state = bus.transaction(
                i2c.writing_bytes(address, lux_low_register),
                i2c.reading(address, 2))[0][0]

            int_state = bus.transaction(
                i2c.writing_bytes(address, int_register),
                i2c.reading(address, 2))[0][0]
            value_high = bin(high_state)[2:]
            value_low = bin(low_state)[2:]

            while len(value_high) < 8:
                value_high = c + value_high


            while len(value_low) < 4:
                value_low = c + value_low


            A = [value_high[4*i:4*(i+1)]for i in range(len(value_high)//4)]
            B = [value_low[4*i:4*(i+1)]for i in range(len(value_low)//4)]
            D = A[1] + B[0]
            A.remove(A[1])
            A.append(D)
            ints = [int(x,2) for x in A]


            #Calculate LUX
            exponent = ints[0]
            mantissa = ints[1]
            lux = 2**(exponent)*(mantissa)*(0.045)

            #Average readings together
            os.system("clear")
            print("==================")
            print("Lux -->>> ", lux)
            LIGHT = lux
            print("==================")
            print("")


#MOTION


while True:
    #global LIGHT
    if GPIO.input(PIR_PIN):
       print("Motion detected")
       getLight()
       if LIGHT <50:
            GPIO_LED.setmode(GPIO_LED.BCM)
            GPIO_LED.setwarnings(False)
            GPIO_LED.setup(27,GPIO_LED.OUT)
            print ("LED on")
            GPIO_LED.output(27,GPIO_LED.HIGH)
            time.sleep(30)
            print ("LED off")
            GPIO_LED.output(27,GPIO_LED.LOW)