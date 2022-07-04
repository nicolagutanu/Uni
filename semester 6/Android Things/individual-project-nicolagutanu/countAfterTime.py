import time
from datetime import datetime

start = str(input('Input start time: ')) + ":00"
end = str(input('Input end time: ')) + ":00"

current_day = str(datetime.now().date()) + ".txt"

txt_file = open(current_day, "r")
file_content = txt_file.read()

content_list = file_content.split("\n")
txt_file.close()

count = 0

for t in content_list:
   if t>=start and t<end:
      count+=1

print("Total number of people: " + str(count))