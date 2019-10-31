"""
Jake Martin
Block 4
9th Grade
"""
 
#Imports the libraries of the functions 'pygame' and others
import pygame
import random
import math
 
#Initializes the game engine
pygame.init()

#Defines some colors and initial RGB values
BLACK = (0, 0, 0)
GRAY = (150, 150, 150)
WHITE = (255, 255, 255)
BLUE = (0, 0, 255)
GREEN = (0, 255, 0)
RED = (255, 0, 0)
YELLOW = (255, 255, 0)
ORANGE = (255, 127, 0)
rand_RED_h1 = 255
spark_color_h2 = 127

#Creates the spark array and loop
spark_list = []
for i in range(25):
    spark_x = random.randrange(0, 1000)
    spark_y = random.randrange(0, 500)
    spark_list.append([spark_x, spark_y])

#Initializes the 1/60th of a second counter, and the hour counter
counter = 0
hour = 0

#Initializes the random movement variables
start_pupil_dilate = False
start_pupil_contract = False
start_pupil_reset = True

start_blink = False
blink_close = False
blink_open = False
mid_blink = False
blink_looper = 1

start_look_right = False
start_look_left = False
start_look_center = True

#Initializes eye variables
eyelid_x = 100
eyelid_y = 50
outer_eye_x = 150
outer_eye_y = 75
outer_iris_x = 500
outer_iris_y = 188
inner_iris_x = 500
inner_iris_y = 188
pupil_x = 500
pupil_y = 188
pupil_size = 25

#The initial speed the eye looks at
rand_look_speed = 1

#Sets PI 
PI = math.pi
 
# Sets the width and height of the screen to 1000 by 500
size = (1000, 500)
screen = pygame.display.set_mode(size)

#Captions it as "The Evil Eye"
pygame.display.set_caption("The Evil Eye")
 
#Loop until the user clicks the close button.
done = False
clock = pygame.time.Clock()
 
#Loop as long as done == False
while not done:
 
    for event in pygame.event.get():  #User did something
        if event.type == pygame.QUIT:  #If user clicked close
            done = True  #Flag that we are done so we exit this loop
 
    #Clear the screen and set the screen background(The eye "stand")
    screen.fill(BLACK)
    
    
#===============================================================================


    '''Randomizes the sky's color every 1/6th of a second'''
    if(counter % 10 == 0):
        #Keeps the color from being to dark
        if(rand_RED_h1 <= 100):
            rand_RED_h1_change = random.randint(0, 10)
        #Keeps the color from getting a value higher than 255
        elif(rand_RED_h1 >= 245):
            rand_RED_h1_change = random.randint(-10, 0)
        else:
            rand_RED_h1_change = random.randint(-10, 10)
        rand_RED_h1 += rand_RED_h1_change
    rand_red = (rand_RED_h1, 0, 0)
    #Draws the sky
    pygame.draw.ellipse(screen, rand_red, [0,-550, 1000, 1000])
    
    
    '''Decides if a random movement should be done once per second'''
    if(counter % 60 == 0):
        rand_movement = random.randint(1, 18)
    #Allows the pupil to dilate
    if(rand_movement == 1):
        start_pupil_dilate = True
        start_pupil_contract = False
        start_pupil_reset = False
    #Allows the pupil to contract
    if(rand_movement == 2):
        start_pupil_contract = True
        start_pupil_dilate = False
        start_pupil_reset = False
    #Allows the pupil to return to its original size
    if(rand_movement == 3):
        start_pupil_reset = True
        start_pupil_dilate = False
        start_pupil_contract = False
    #Allows the eye to blink
    if(rand_movement == 4 or rand_movement == 5):
        start_blink = True
    #Allows the eye to look right
    if(rand_movement == 6 or rand_movement == 7):
        start_look_right = True
        start_look_left = False
        start_look_center = False
        rand_look_speed = random.randint(1, 5)
    #Allows the eye to look left
    if(rand_movement == 8 or rand_movement == 9):
        start_look_left = True
        start_look_right = False
        start_look_center = False
        rand_look_speed = random.randint(1, 5)
    #Allows the eye to return to center
    if(rand_movement == 10 or rand_movement == 11):
        start_look_center = True
        start_look_right = False
        start_look_left = False
        rand_look_speed = random.randint(1, 5)
    
    
    '''Modifies the eye variables'''
    #Makes the pupil dilate
    if(start_pupil_dilate == True and start_pupil_contract == False and start_pupil_reset == False):
        if(pupil_size < 75 and counter % 2 == 0):
            pupil_size = pupil_size + 1
        elif(pupil_size == 75):
            start_pupil_dilate = False
    #Makes the pupil contract
    if(start_pupil_contract == True and start_pupil_dilate == False and start_pupil_reset == False):
        if(pupil_size >15 and counter % 2 == 0):
            pupil_size = pupil_size - 1
        elif(pupil_size == 15):
            start_pupil_contract = False
    #Makes the pupil return to normal size
    if(start_pupil_reset == True and start_pupil_dilate == False and start_pupil_contract == False):
        #If the pupil is contracted
        if(pupil_size < 25 and counter % 2 == 0):
            pupil_size = pupil_size + 1
        #If the pupil is dilated
        elif(pupil_size > 25 and counter % 2 == 0):
            pupil_size = pupil_size - 1
        #Once the pupil's size is reset
        elif(pupil_size == 25):
            start_pupil_reset = False
            
    #Makes the eye blink
    if(start_blink == True):
        #Tells the eye to close
        if(blink_looper < 125):
            blink_close = True
            blink_open = False
            mid_blink = False
        #Tells the eye to stay closed
        elif(blink_looper == 125):
            blink_close = False
            blink_open = False
            mid_blink = True
        #Tells the eye to open
        elif(blink_looper > 125 and blink_looper <= 250):
            blink_open = True
            blink_close = False
            mid_blink = False
        #Tells the eye to stay open
        elif(blink_looper >= 250):
            start_blink = False
            blink_close = False
            blink_open = False
            mid_blink = False
            blink_looper = 1
            
    #Makes the eye move right
    if(start_look_right == True and start_look_left == False and start_look_center == False):
        #If the eye is to the left
        if(outer_iris_x < 600):
            outer_iris_x = outer_iris_x + rand_look_speed
            inner_iris_x = inner_iris_x + rand_look_speed
            pupil_x = pupil_x + rand_look_speed
        #Once the eye is to the right
        elif(outer_iris_x == 600):
            start_look_right = False
    #Makes the eye move left
    if(start_look_left == True and start_look_right == False and start_look_center == False):
        #If the eye is to the right
        if(outer_iris_x > 400):
            outer_iris_x = outer_iris_x - rand_look_speed
            inner_iris_x = inner_iris_x - rand_look_speed
            pupil_x = pupil_x - rand_look_speed
        #Once the eye is to the left
        elif(outer_iris_x == 400):
            start_look_left = False
    #Makes the eye return to center
    if(start_look_center == True and start_look_right == False and start_look_left == False):
        #If the eye is to the left
        if(outer_iris_x < 500):
            outer_iris_x = outer_iris_x + rand_look_speed
            inner_iris_x = inner_iris_x + rand_look_speed
            pupil_x = pupil_x + rand_look_speed
        #If the eye is to the right
        elif(outer_iris_x > 500):
            outer_iris_x = outer_iris_x - rand_look_speed
            inner_iris_x = inner_iris_x - rand_look_speed
            pupil_x = pupil_x - rand_look_speed
        #Once the eye is centered
        elif(outer_iris_x == 500):
            start_look_center = False    
    
    
    '''Draws the eye parts'''
    #Draws the eyelid
    pygame.draw.ellipse(screen, ORANGE, [eyelid_x, eyelid_y, 800, 275])
    #Draws the outer eye
    pygame.draw.ellipse(screen, YELLOW, [outer_eye_x, outer_eye_y, 700, 225])
    #Draws the outer iris
    pygame.draw.circle(screen, ORANGE, [outer_iris_x, outer_iris_y], 105)
    #Draws the inner iris
    pygame.draw.circle(screen, RED, [inner_iris_x, inner_iris_y], 95)
    #Draws the pupil
    pygame.draw.circle(screen, BLACK, [pupil_x, pupil_y], pupil_size)


    '''Draws the blink'''
    #Draws the eye closing
    if(blink_close == True):
        for i in range(blink_looper):
            pygame.draw.ellipse(screen, ORANGE, [eyelid_x, (eyelid_y + i), 800, (275 - (2*i) )], 10)
        blink_looper = blink_looper + 1
    #Draws the closed eye
    if(mid_blink == True):
        if(counter % 60 != 0):
            pygame.draw.ellipse(screen, ORANGE, [eyelid_x, eyelid_y, 800, 275])
        else:
            blink_looper = blink_looper + 1
            pygame.draw.ellipse(screen, ORANGE, [eyelid_x, eyelid_y, 800, 275])
    #Draws the eye opening
    if(blink_open == True):
        for i in range( 125 - (blink_looper - 126) ):
            pygame.draw.ellipse(screen, ORANGE, [eyelid_x, (eyelid_y + i), 800, (275 - (2*i) )], 10)
        blink_looper = blink_looper + 1
    
            
    '''Draws the random sparks'''
    #Creates the sparks' color
    if(counter % 3 == 0):
        if(spark_color_h2 <= 15):
            spark_color_h2_change = random.randint(0, 15)
        elif(spark_color_h2 >= 240):
            spark_color_h2_change = random.randint(-15, 0)
        else:
            spark_color_h2_change = random.randint(-15, 15)
        spark_color_h2 += spark_color_h2_change
    rand_spark_color =(255, spark_color_h2, 0)
    
    #Draws all of the sparks
    for i in range(len(spark_list)):
        pygame.draw.circle(screen, GRAY, spark_list[i], 6)
        pygame.draw.circle(screen, rand_spark_color, spark_list[i], 5)
        spark_list[i][1] += 1
        
        if(spark_list[i][1] > 500):
            spark_y = random.randrange(-50, -10)
            spark_list[i][1] = spark_y
            
            spark_x = random.randrange(0, 1000)
            spark_list[i][0] = spark_x


    '''Manages the counter'''
    #Counts the number of 1/60th seconds that have passed
    counter = counter + 1
    #Resets the counter after each hour to make sure you aren't storing excessive numbers unnecessarily
    if(counter == 216000):
        counter = 0
        hour = hour + 1
    
    #Prints text to the screen if you've been running the program for > 1 hour    
    if(hour > 0):
        font = pygame.font.SysFont('Calibri', 25, True, True)
        if(hour != 1):
            text = font.render("WOW! You've been running this program for "+ str(hour) + " hours!", True, WHITE)
        else:
            text = font.render("WOW! You've been running this program for an hour!", True, WHITE)
        screen.blit(text, [250, 450])
        
        
#===============================================================================


    #Go ahead and update the screen with what we've drawn.
    #This MUST happen after all the other drawing commands.
    pygame.display.flip()
 
    #This limits the while loop to a max of 60 times per second.
    #Leave this out and we will use all CPU we can.
    clock.tick(60)
 
#Be IDLE friendly
pygame.quit()