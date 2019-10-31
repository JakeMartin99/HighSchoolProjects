"""
Jake Martin
5-11-2016
9th Grade
"""
 
# Import a library of functions called 'pygame'
import pygame
import random
import math
 
# Initialize the game engine
pygame.init()
 
# Define some colors
BLACK = (0, 0, 0)
DARK_GRAY = (75, 75, 75)
GRAY = (125, 125, 125)
WHITE = (255, 255, 255)
BLUE = (0, 0, 255)
GREEN = (0, 255, 0)
RED = (255, 0, 0)
YELLOW = (225, 225, 0)
SPECIAL= (33, 66, 133)

PI = math.pi

#Counting loopers
counter = 0

#Wheel variables >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
h1 = 50
h2 = 50
h3 = 50

wheel_num = 1

def draw_wheel():
   pygame.draw.circle(screen, wheel_top, (150, 75), 10)
   pygame.draw.circle(screen, wheel_top_right, (200, 100), 10)
   pygame.draw.circle(screen, wheel_right, (225, 150), 10)
   pygame.draw.circle(screen, wheel_bot_right, (200, 200), 10)
   pygame.draw.circle(screen, wheel_bot, (150, 225), 10)
   pygame.draw.circle(screen, wheel_bot_left, (100, 200), 10)
   pygame.draw.circle(screen, wheel_left, (75, 150), 10)
   pygame.draw.circle(screen, wheel_top_left, (100, 100), 10)
#End of wheel variables <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Clock variables >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
clock_on = RED
OFF = BLACK

top_left = clock_on
top_mid = clock_on
top_right = clock_on
mid = clock_on
bottom_left = clock_on
bottom_mid = clock_on
bottom_right = clock_on

number = 0
place_0 = 0
place_1 = 0
place_2 = 0
place_3 = 0
place_4 = 0

def draw_clock(clock_x, clock_y):
   #Top left
   pygame.draw.rect(screen, top_left, [25+clock_x, 25+clock_y, 25, 87.5])
   #Top mid
   pygame.draw.rect(screen, top_mid, [62.5+clock_x, 25+clock_y, 75, 25])
   #Top right
   pygame.draw.rect(screen, top_right, [150+clock_x, 25+clock_y, 25, 87.5])
   #Mid
   pygame.draw.rect(screen, mid, [62.5+clock_x, 112.5+clock_y, 75, 25])
   #Bottom left
   pygame.draw.rect(screen, bottom_left, [25+clock_x, 137.5+clock_y, 25, 87.5])
   #Bottom mid
   pygame.draw.rect(screen, bottom_mid, [62.5+clock_x, 200+clock_y, 75, 25])
   #Bottom right
   pygame.draw.rect(screen, bottom_right, [150+clock_x, 137.5+clock_y, 25, 87.5])
#End of clock variables <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

#Radar variables >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
RADAR_WHITE = (50, 200, 50)
RADAR_GREEN = (0, 200, 0)
DARK_GREEN = (0, 100, 0)

radar_angle = 0

other_x = random.randint(10, 100) * math.sin(radar_angle) + 145
other_y = random.randint(10, 100) * math.cos(radar_angle) + 145

def draw_radar(radar_x, radar_y, angle):
   pygame.draw.ellipse(screen, DARK_GRAY, [10+radar_x, 10+radar_y, 270, 270])
   pygame.draw.ellipse(screen, DARK_GREEN, [20+radar_x, 20+radar_y, 250, 250])
   #Horizontal lines
   pygame.draw.rect(screen, RADAR_WHITE, [35+radar_x, 90+radar_y, 220, 5])
   pygame.draw.rect(screen, RADAR_WHITE, [20+radar_x, 142.5+radar_y, 250, 5])
   pygame.draw.rect(screen, RADAR_WHITE, [35+radar_x, 195+radar_y, 220, 5])
   #Vertical lines
   pygame.draw.rect(screen, RADAR_WHITE, [90+radar_x, 35+radar_y, 5, 220])
   pygame.draw.rect(screen, RADAR_WHITE, [142.5+radar_x, 20+radar_y, 5, 250])
   pygame.draw.rect(screen, RADAR_WHITE, [195+radar_x, 35+radar_y, 5, 220])
   #Circles
   pygame.draw.ellipse(screen, RADAR_GREEN, [20+radar_x, 20+radar_y, 250, 250], 5)
   pygame.draw.ellipse(screen, RADAR_GREEN, [50+radar_x, 50+radar_y, 190, 190], 5)
   pygame.draw.ellipse(screen, RADAR_GREEN, [80+radar_x, 80+radar_y, 130, 130], 5)
   pygame.draw.circle(screen, RADAR_GREEN, [145+radar_x, 145+radar_y], 7)
   
   x = 125 * math.sin(radar_angle) + 145
   y = 125 * math.cos(radar_angle) + 145
   
   pygame.draw.line(screen, RADAR_GREEN, [145+radar_x, 145+radar_y], [x+radar_x, y+radar_y], 5)
#End of radar variables <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

#LED_meter variables >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
LED_number = 0

LED_RED = (175, 0, 0)
LED_GREEN = (100, 175, 0)
LED_YELLOW = (175, 175, 0)

def draw_LED(LED_y, LED_color):
   pygame.draw.circle(screen, DARK_GRAY, [390, 350 + LED_y], 40)
   pygame.draw.circle(screen, LED_color, [390, 350 + LED_y], 30)
   pygame.draw.rect(screen, WHITE, [400, 355 + LED_y, 10, 10])
#End of LED_meter variables <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

#Speedometer variables >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
meter_angle = (random.randint(25, 75) / 100) * PI

def draw_speedometer(meter_x, meter_y, meter_angle):
   speedometer = pygame.image.load("Speedometer.png").convert()
   speedometer.set_colorkey(BLACK)
   screen.blit(speedometer, [meter_x, meter_y])
   
   meter_line_x = 130 * math.sin(meter_angle) + 150
   meter_line_y = 130 * math.cos(meter_angle) + 150
   
   pygame.draw.line(screen, BLACK, [150 + meter_x, 150 + meter_y], [meter_line_x + meter_x, meter_line_y + meter_y], 7)
   pygame.draw.circle(screen, BLACK, [150 + meter_x, 150 + meter_y], 7)
   pygame.draw.circle(screen, GRAY, [150 + meter_x, 150 + meter_y], 3)
#End of speedometer variables <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

#Lever variables >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
LEVER_GRAY = (40, 40, 40)

lever_y = 493

mouse_down = False

def draw_lever(lever_y):
   pygame.draw.rect(screen, LEVER_GRAY, [1250, 350, 124, 325])
   pygame.draw.rect(screen, BLACK, [1275, 375, 74, 275])
   
   pygame.draw.rect(screen, DARK_GRAY, [1275, lever_y + 60, 74, 60])
   pygame.draw.circle(screen, DARK_GRAY, [1312, lever_y + 120], 37)
   pygame.draw.rect(screen, GRAY, [1285, lever_y + 75, 54, 50])
   pygame.draw.circle(screen, GRAY, [1312, lever_y + 120], 27)
   
   
   pygame.draw.rect(screen, DARK_GRAY, [1200, lever_y, 224, 75])
   pygame.draw.rect(screen, GRAY, [1210, lever_y + 10, 204, 55])
#End of lever variables <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

# Set the height and width of the screen
size = (1500, 750)
screen = pygame.display.set_mode(size)
 
pygame.display.set_caption("Spaceship Console")
 
# Loop until the user clicks the close button.
done = False
clock = pygame.time.Clock()
 
# Loop as long as done == False
while not done:
   
   for event in pygame.event.get():  # User did something
      if event.type == pygame.QUIT:  # If user clicked close
         done = True  # Flag that we are done so we exit this loop
      
      elif event.type == pygame.KEYDOWN:
         if event.key == pygame.K_SPACE:
            if clock_on == RED:
               clock_on = GREEN
            elif clock_on == GREEN:
               clock_on = BLUE
            else:
               clock_on = RED
      
      elif event.type == pygame.MOUSEBUTTONDOWN:
         mouse_down = True
         
      elif event.type == pygame.MOUSEBUTTONUP:
         mouse_down = False
      
    # All drawing code happens after the for loop and but
    # inside the main while not done loop.
 
    # Clear the screen and set the screen background
   screen.fill(GRAY)
#------------------------------------------------------------------------------- 
   pygame.draw.rect(screen, DARK_GRAY, [0, 0, 1500, 750], 25)
   pygame.draw.rect(screen, DARK_GRAY, [290, 0, 25, 750])
   pygame.draw.rect(screen, BLACK, [25, 25, 250, 250])
   pygame.draw.rect(screen, BLACK, [325, 20, 1150, 235])
   pygame.draw.rect(screen, DARK_GRAY, [315, 265, 1175, 25])
   pygame.draw.rect(screen, DARK_GRAY, [465, 265, 25, 485])


#Lever >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
   lever_angle = PI * ((lever_y - 313) / 100)
   if lever_angle >= 1.75 * PI:
      lever_angle = 1.75 * PI
   elif lever_angle <= .25 * PI:
      lever_angle = .25 * PI
   
   mouse_pos = pygame.mouse.get_pos()
   mouse_x = mouse_pos[0]
   mouse_y = mouse_pos[1]
   
   if (mouse_down == True) and (1175 <= mouse_x <= 1450) and (300 <= mouse_y <= lever_y + 700):
      lever_y = mouse_y
      if lever_y <= 315:
         lever_y = 315
      elif lever_y >= 493:
         lever_y = 493
   
   draw_lever(lever_y)
#End of lever <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Speedometer >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
   draw_speedometer(600, 350, meter_angle)
   
   if counter % 5 == 0:
      meter_angle = lever_angle + (random.randint(-10, 10) / 100)
   if meter_angle > 2 * PI:
      meter_angle = meter_angle - 2 * PI
#End of speedometer <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#LED_meter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
   LED_y = 0
   LED_color = BLACK
   if counter % 15 == 0:
      if 0 < LED_number < 4:
         LED_number += random.randint(-1, 1)
      elif LED_number <= 0:
         LED_number += 1
      elif LED_number >= 4:
         LED_number -= 1
   
   for i in range(4):
      if LED_number == 4:
         if i == 3 or i == 2:
            LED_color = LED_GREEN
         elif i == 1:
            LED_color = LED_YELLOW
         elif i ==0:
            LED_color = LED_RED
      elif LED_number == 3:
         if i == 3 or i == 2:
            LED_color = LED_GREEN
         elif i == 1:
            LED_color = LED_YELLOW
         elif i == 0:
            LED_color = BLACK
      elif LED_number == 2:
         if i == 3 or i == 2:
            LED_color = LED_GREEN
         elif i == 1 or i == 0:
            LED_color = BLACK
      elif LED_number == 1:
         if i == 3:
            LED_color = LED_GREEN
         elif i == 2 or i == 1 or i == 0:
            LED_color = BLACK
      else:
         LED_color = BLACK
         
      draw_LED(LED_y, LED_color)
      LED_y += 110
#End of LED_meter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Clock>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
   pygame.draw.circle(screen, clock_on, [550, 100], 15)
   pygame.draw.circle(screen, clock_on, [550, 170], 15)
   pygame.draw.circle(screen, clock_on, [1000, 100], 15)
   pygame.draw.circle(screen, clock_on, [1000, 170], 15)


   for i in range(5):
      if i == 0:
         number = place_0
      elif i == 1:
         number = place_1
      elif i == 2:
         number = place_2
      elif i == 3:
         number = place_3
      elif i == 4:
         number = place_4
          
      if number == 0:
         top_left = clock_on
         top_mid = clock_on
         top_right = clock_on
         mid = OFF
         bottom_left = clock_on
         bottom_mid = clock_on
         bottom_right = clock_on
      elif number == 1:
         top_left = OFF
         top_mid = OFF
         top_right = clock_on
         mid = OFF
         bottom_left = OFF
         bottom_mid = OFF
         bottom_right = clock_on 
      elif number == 2:
         top_left = OFF
         top_mid = clock_on
         top_right = clock_on
         mid = clock_on
         bottom_left = clock_on
         bottom_mid = clock_on
         bottom_right = OFF
      elif number == 3:
         top_left = OFF
         top_mid = clock_on
         top_right = clock_on
         mid = clock_on
         bottom_left = OFF
         bottom_mid = clock_on
         bottom_right = clock_on
      elif number == 4:
         top_left = clock_on
         top_mid = OFF
         top_right = clock_on
         mid = clock_on
         bottom_left = OFF
         bottom_mid = OFF
         bottom_right = clock_on
      elif number == 5:
         top_left = clock_on
         top_mid = clock_on
         top_right = OFF
         mid = clock_on
         bottom_left = OFF
         bottom_mid = clock_on
         bottom_right = clock_on
      elif number == 6:
         top_left = clock_on
         top_mid = clock_on
         top_right = OFF
         mid = clock_on
         bottom_left = clock_on
         bottom_mid = clock_on
         bottom_right = clock_on
      elif number == 7:
         top_left = OFF
         top_mid = clock_on
         top_right = clock_on
         mid = OFF
         bottom_left = OFF
         bottom_mid = OFF
         bottom_right = clock_on
      elif number == 8:
         top_left = clock_on
         top_mid = clock_on
         top_right = clock_on
         mid = clock_on
         bottom_left = clock_on
         bottom_mid = clock_on
         bottom_right = clock_on
      elif number == 9:
         top_left = clock_on
         top_mid = clock_on
         top_right = clock_on
         mid = clock_on
         bottom_left = OFF
         bottom_mid = OFF
         bottom_right = clock_on
   
      if i == 0:
         draw_clock(325, 10)
      elif i == 1:
         draw_clock(575, 10)
      elif i == 2:
         draw_clock(775, 10)
      elif i == 3:
         draw_clock(1025, 10)
      elif i == 4:
         draw_clock(1225, 10)
#End of clock <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Loading wheel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
   if counter % 4 == 0:
      if h1 <= 10:
         h1 += random.randint(0, 10)
      elif h1 >= 245:
         h1 -= random.randint(0, 10)
      else:
         h1 += random.randint(-10, 10)
         
      if h2 <= 10:
         h2 += random.randint(0, 10)
      elif h2 >= 245:
         h2 -= random.randint(0, 10)
      else:
         h2 += random.randint(-10, 10)
         
      if h3 <= 10:
         h3 += random.randint(0, 10)
      elif h3 >= 245:
         h3 -= random.randint(0, 10)
      else:
         h3 += random.randint(-10, 10)   
      ON = (h1, h2, h3)
      
      if wheel_num == 1:
         wheel_top = ON
         wheel_top_right = ON
         wheel_right = ON
         wheel_bot_right = OFF
         wheel_bot = OFF
         wheel_bot_left = OFF
         wheel_left = OFF
         wheel_top_left = OFF
      elif wheel_num == 2:
         wheel_top = OFF
         wheel_top_right = ON
         wheel_right = ON
         wheel_bot_right = ON
         wheel_bot = OFF
         wheel_bot_left = OFF
         wheel_left = OFF
         wheel_top_left = OFF
      elif wheel_num == 3:
         wheel_top = OFF
         wheel_top_right = OFF
         wheel_right = ON
         wheel_bot_right = ON
         wheel_bot = ON
         wheel_bot_left = OFF
         wheel_left = OFF
         wheel_top_left = OFF
      elif wheel_num == 4:
         wheel_top = OFF
         wheel_top_right = OFF
         wheel_right = OFF
         wheel_bot_right = ON
         wheel_bot = ON
         wheel_bot_left = ON
         wheel_left = OFF
         wheel_top_left = OFF
      elif wheel_num == 5:
         wheel_top = OFF
         wheel_top_right = OFF
         wheel_right = OFF
         wheel_bot_right = OFF
         wheel_bot = ON
         wheel_bot_left = ON
         wheel_left = ON
         wheel_top_left = OFF
      elif wheel_num == 6:
         wheel_top = OFF
         wheel_top_right = OFF
         wheel_right = OFF
         wheel_bot_right = OFF
         wheel_bot = OFF
         wheel_bot_left = ON
         wheel_left = ON
         wheel_top_left = ON
      elif wheel_num == 7:
         wheel_top = ON
         wheel_top_right = OFF
         wheel_right = OFF
         wheel_bot_right = OFF
         wheel_bot = OFF
         wheel_bot_left = OFF
         wheel_left = ON
         wheel_top_left = ON
      elif wheel_num == 1:
         wheel_top = ON
         wheel_top_right = ON
         wheel_right = OFF
         wheel_bot_right = OFF
         wheel_bot = OFF
         wheel_bot_left = OFF
         wheel_left = OFF
         wheel_top_left = ON
       
      wheel_num += 1
      if wheel_num == 9:
         wheel_num = 1    
   draw_wheel()
#End of loading wheel <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Radar >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
   draw_radar(7, 350, radar_angle)
   
   if counter % 120 == 0:
      other_x = random.randint(10, 100) * math.sin(radar_angle) + 145
      other_y = random.randint(10, 100) * math.cos(radar_angle) + 145
      radar_counter_1 = 255
      radar_counter_2 = 255
      radar_counter_3 = 255
      draw_blip = True
   if draw_blip:
      pygame.draw.circle(screen, (radar_counter_1, radar_counter_2, radar_counter_3), [7 + int(other_x), 350 + int(other_y)], 7)
   
   if radar_counter_1 > 0:
      radar_counter_1 -= 5
   else:
      radar_counter_1 = 0
   if radar_counter_2 > 125:
      radar_counter_2 -= 3
   else:
      draw_blip = False
   if radar_counter_3 > 0:
      radar_counter_3 -= 5
   else:
      radar_counter_3 = 0   
   
   radar_angle = radar_angle - 0.05
   if radar_angle > 2 * PI:
      radar_angle = radar_angle - 2 * PI
#End of Radar <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Counting loops   
   counter += 1
   
   place_4 += 1
   if place_4 == 10:
      place_4 = 0
      place_3 += 1
   if place_3 == 6:
      place_3 = 0
      place_2 += 1
   if place_2 == 10:
      place_2 = 0
      place_1 += 1
   if place_1 == 6:
      place_1 = 0
      place_0 += 1
   if place_0 == 10:
      place_0 = 0  
#-------------------------------------------------------------------------------
   pygame.display.flip()
 
    # This limits the while loop to a max of 60 times per second.
    # Leave this out and we will use all CPU we can.
   clock.tick(60)
 
# Be IDLE friendly
pygame.quit()