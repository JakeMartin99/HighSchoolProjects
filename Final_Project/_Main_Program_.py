"""
Jake Martin
Block 4
9th Grade
 
"""
 
#Import libraries of functions
import pygame
import random
import math

#Initialize the game engine
pygame.init()
 
#Define some colors
BLACK = (0, 0, 0)
GRAY = (125, 125, 125)
LIGHT_GRAY = (200, 200, 200)
VERY_LIGHT_GRAY = (225, 225, 225)
WHITE = (255, 255, 255)
BLUE = (0, 0, 255)
GREEN = (0, 255, 0)
RED = (255, 0, 0)
YELLOW = (225, 225, 0)
SPECIAL= (33, 66, 133)
 
PI = 3.141592653

#Modes
title_screen = True
playing_mode = False
battle_scene = False
game_over = False
you_won = False

#Counter
counter = 0
other_counter = 0

#Mouse
mouse_down = False
 
#Set the height and width of the screen
size = (1000, 750)
screen = pygame.display.set_mode(size)
 
pygame.display.set_caption("Total Conquest")
 
#Loop until the user clicks the close button.
done = False
clock = pygame.time.Clock()


#Background >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
TITLE_COLOR = (225, 25, 25)

background_x = -500
background_y = -5
background_x_change = 1
background_y_change = 1
background = pygame.image.load("Background.png").convert()

background_font = pygame.font.SysFont('Timesroman', 200, True, False)
background_text_1 = background_font.render("Total", True, TITLE_COLOR)
background_text_2 = background_font.render("Conquest", True, TITLE_COLOR)
#End of background <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Game over >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
game_over_x = 0
game_over_y = 0
game_over_x_change = -2
game_over_y_change = -1
game_over_background = pygame.image.load("Game_Over.png").convert()

game_over_font = pygame.font.SysFont('Timesroman', 200, True, False)
game_over_text_1 = game_over_font.render("GAME", True, BLACK)
game_over_text_2 = game_over_font.render("OVER", True, BLACK)
#End of game over <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#You won >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
you_won_x = -400
you_won_y = 0
you_won_background = pygame.image.load("You_Won.png")

you_won_font = pygame.font.SysFont('Timesroman', 200, True, False)
you_won_text = you_won_font.render("YOU WON", True, WHITE)
#End of you won <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Battle scene >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
battle_x = 0
battle_y = 0
battle_x_change = -6
battle_y_change = -1
#End of battle scene <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Map >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
map_background = pygame.image.load("Map_Background.png").convert()
game_map = pygame.image.load("Game_Map.png").convert()
#End of map <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Buttons >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
class Button():
    def __init__(self):
        self.pos_x = 0
        self.pos_y = 0
        self.x_size = 0
        self.y_size = 0
        self.back_inset = 0
        self.back_color = (0, 0, 0)
        self.border_color = (0, 0, 0)
        self.text_color = (0, 0, 0)
        self.text = ""
        self.text_size = 0
        self.text_pos = 0
        
    def draw_button(self):
        #Border
        pygame.draw.rect(screen, self.border_color, [self.pos_x, self.pos_y, self.x_size, self.y_size])
        #Back
        pygame.draw.rect(screen, self.back_color, [(self.pos_x + self.back_inset), (self.pos_y + self.back_inset), (self.x_size - (2 * self.back_inset)), (self.y_size - (2 * self.back_inset))])
        #Text
        button_font = pygame.font.SysFont('Calibri', self.text_size, True, False)
        button_text = button_font.render(self.text, True, self.text_color)
        screen.blit(button_text, [self.text_pos, self.pos_y + self.back_inset])
               
play_button = Button()
play_button.pos_x = 350
play_button.pos_y = 450
play_button.x_size = 300
play_button.y_size = 100
play_button.back_inset = 10
play_button.back_color = GREEN
play_button.border_color = BLACK
play_button.text_color = BLACK
play_button.text = "PLAY"
play_button.text_size = 100
play_button_x2 = play_button.pos_x + play_button.x_size
play_button_y2 = play_button.pos_y + play_button.y_size
play_button.text_pos = 400

quit_button = Button()
quit_button.pos_x = 400
quit_button.pos_y = 575
quit_button.x_size = 200
quit_button.y_size = 70
quit_button.back_inset = 10
quit_button.back_color = BLUE
quit_button.border_color = BLACK
quit_button.text_color = BLACK
quit_button.text = "QUIT"
quit_button.text_size = 50
quit_button_x2 = quit_button.pos_x + quit_button.x_size
quit_button_y2 = quit_button.pos_y + quit_button.y_size
quit_button.text_pos = 445

attack_button = Button()
attack_button.pos_x = 400
attack_button.pos_y = 575
attack_button.x_size = 200
attack_button.y_size = 60
attack_button.back_inset = 7
attack_button.back_color = RED
attack_button.border_color = BLACK
attack_button.text_color = GRAY
attack_button.text = "ATTACK"
attack_button.text_size = 50
attack_button_x2 = attack_button.pos_x + attack_button.x_size
attack_button_y2 = attack_button.pos_y + attack_button.y_size
attack_button.text_pos = 420
#End of buttons <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Capitals >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
city_clicked = "None"

city_armies = {}
city_armies["None"] = 0
city_armies["Washington"] = 1000
city_armies["Rio"] = 600
city_armies["Paris"] = 800
city_armies["Moscow"] = 900
city_armies["Baghdad"] = 700
city_armies["Tripoli"] = 600
city_armies["Capetown"] = 500
city_armies["Beijing"] = 900
city_armies["Delhi"] = 700
city_armies["Canberra"] = 800

def armies_display(your_armies, enemy_armies):
    pygame.draw.rect(screen, BLACK, [25, 491, 318, 40])
    pygame.draw.rect(screen, VERY_LIGHT_GRAY, [30, 491, 308, 35])
    pygame.draw.rect(screen, BLACK, [615, 491, 360, 40])
    pygame.draw.rect(screen, VERY_LIGHT_GRAY, [620, 491, 350, 35])    
    
    armies_font = pygame.font.SysFont('Calibri', 25, True, False)
    armies_text_1 = armies_font.render(("Your remaining armies: " + str(your_armies)), True, BLACK)
    armies_text_2 = armies_font.render(("Enemy's remaining armies: " + str(enemy_armies)), True, BLACK)
    screen.blit(armies_text_1, [35, 500])
    screen.blit(armies_text_2, [625, 500]) 

city_ownership = {}
city_ownership["None"] = "None"
city_ownership["Washington"] = "Washington"
city_ownership["Rio"] = "Rio"
city_ownership["Paris"] = "Paris"
city_ownership["Moscow"] = "Moscow"
city_ownership["Baghdad"] = "Baghdad"
city_ownership["Tripoli"] = "Tripoli"
city_ownership["Capetown"] = "Capetown"
city_ownership["Beijing"] = "Beijing"
city_ownership["Delhi"] = "Delhi"
city_ownership["Canberra"] = "Canberra"

class Capital():
    def __init__(self):
        self.pos_x = 0
        self.pos_y = 0
        self.color = (0, 0, 0)
        self.crosshair_color = (255, 0, 0)
        
    def draw_capital(self):
        pygame.draw.circle(screen, BLACK, [self.pos_x, self.pos_y], 6)
        pygame.draw.circle(screen, self.color, [self.pos_x, self.pos_y], 4)
        
    def draw_crosshair(self):
        pygame.draw.line(screen, BLACK, [self.pos_x - 24, self.pos_y], [self.pos_x - 12, self.pos_y], 3)
        pygame.draw.line(screen, self.crosshair_color, [self.pos_x - 23, self.pos_y], [self.pos_x - 13, self.pos_y], 1)
        
        pygame.draw.line(screen, BLACK, [self.pos_x, self.pos_y - 24], [self.pos_x, self.pos_y - 12], 3)
        pygame.draw.line(screen, self.crosshair_color, [self.pos_x, self.pos_y - 13], [self.pos_x, self.pos_y - 23], 1)
        
        pygame.draw.line(screen, BLACK, [self.pos_x + 24, self.pos_y], [self.pos_x + 12, self.pos_y], 3)
        pygame.draw.line(screen, self.crosshair_color, [self.pos_x + 23, self.pos_y], [self.pos_x + 13, self.pos_y], 1)
        
        pygame.draw.line(screen, BLACK, [self.pos_x, self.pos_y + 13], [self.pos_x, self.pos_y + 23], 3)
        pygame.draw.line(screen, self.crosshair_color, [self.pos_x, self.pos_y + 12], [self.pos_x, self.pos_y + 24], 1)
        
        pygame.draw.circle(screen, BLACK, [self.pos_x, self.pos_y], 19, 1)
        pygame.draw.circle(screen, BLACK, [self.pos_x, self.pos_y], 17, 1)
        pygame.draw.circle(screen, self.crosshair_color, [self.pos_x, self.pos_y], 18, 1)
        
washington = Capital()
washington.pos_x = 210
washington.pos_y = 180
washington.color = GREEN
washington.crosshair_color = GREEN

rio = Capital()
rio.pos_x = 300
rio.pos_y = 350
rio.color = RED

paris = Capital()
paris.pos_x = 450
paris.pos_y = 150
paris.color = RED

moscow = Capital()
moscow.pos_x = 525
moscow.pos_y = 125
moscow.color = RED

baghdad = Capital()
baghdad.pos_x = 540
baghdad.pos_y = 190
baghdad.color = RED

tripoli = Capital()
tripoli.pos_x = 475
tripoli.pos_y = 210
tripoli.color = RED

capetown = Capital()
capetown.pos_x = 480
capetown.pos_y = 380
capetown.color = RED

beijing = Capital()
beijing.pos_x = 720
beijing.pos_y = 160
beijing.color = RED

delhi = Capital()
delhi.pos_x = 635
delhi.pos_y = 225
delhi.color = RED

canberra = Capital()
canberra.pos_x = 800
canberra.pos_y = 400
canberra.color = RED
#End of capitals <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


#Loop as long as done == False
while not done:
 
    for event in pygame.event.get():  #User did something
        if event.type == pygame.QUIT:  #If user clicked close
            done = True  #Flag that we are done so we exit this loop
            
        elif event.type == pygame.MOUSEBUTTONDOWN:
            mouse_down = True
        elif event.type == pygame.MOUSEBUTTONUP:
            mouse_down = False
            
    #All drawing code happens after the for loop and but
    #inside the main while not done loop.s
 
    #Clear the screen and set the screen background
    screen.fill(WHITE)
#-------------------------------------------------------------------------------
    #Mouse >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    mouse_pos = pygame.mouse.get_pos()
    mouse_x = mouse_pos[0]
    mouse_y = mouse_pos[1]
    #Mouse end <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    
    if title_screen:
        #Background >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        screen.blit(background, [background_x, background_y])
        
        if counter % 3 == 0:
            background_x += background_x_change
            background_y += background_y_change
            if background_x <= -500 or background_x >= 0:
                background_x_change *= -1
            if background_y <= -50 or background_y >= 0:
                background_y_change *= -1
                
        screen.blit(background_text_1, [25, 25])
        screen.blit(background_text_2, [175, 175])
        #Background end <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        
        #Buttons >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if (play_button.pos_x <= mouse_x <= play_button_x2) and (play_button.pos_y <= mouse_y <= play_button_y2):
            play_button.text_color = WHITE
            if mouse_down:
                title_screen = False
                playing_mode = True
        elif (quit_button.pos_x <= mouse_x <= quit_button_x2) and (quit_button.pos_y <= mouse_y <= quit_button_y2):
            quit_button.text_color = WHITE
            if mouse_down:
                done = True
        else:
            play_button.text_color = BLACK
            quit_button.text_color = BLACK
            
        play_button.draw_button()
        quit_button.draw_button()
        #Buttons end <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    
    elif playing_mode:
        #Background >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        screen.blit(map_background, [0, 0])
        pygame.draw.rect(screen, BLACK, [25, 25, 950, 466])
        screen.blit(game_map, [50, 50])
        
        armies_display(city_armies["Washington"], city_armies[city_ownership[city_clicked]])
        #End of background <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        
        #Buttons >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if city_clicked != "None" and city_ownership[city_clicked] != "Washington":
            if (attack_button.pos_x <= mouse_x <= attack_button_x2) and (attack_button.pos_y <= mouse_y <= attack_button_y2):
                attack_button.text_color = WHITE
                if mouse_down:
                    playing_mode = False
                    battle_scene = True
                    randomizer = random.randint(1, 12)
                    battle = pygame.image.load("Battle_" + str(randomizer) + ".png").convert()
                    
                    city_armies["Washington"] -= random.randint(int((city_armies["Washington"] + city_armies[city_ownership[city_clicked]]) * .05), int((city_armies["Washington"] + city_armies[city_ownership[city_clicked]]) * .15))
                    
                    city_armies[city_ownership[city_clicked]] -= random.randint(int((city_armies[city_ownership[city_clicked]] + city_armies["Washington"]) * .05), int((city_armies[city_ownership[city_clicked]] + city_armies["Washington"]) * .15))
                    
                    if city_armies[city_ownership[city_clicked]] <= 0 and city_ownership[city_clicked] == city_clicked:
                        for i in ["Rio", "Paris", "Moscow", "Baghdad", "Tripoli", "Capetown", "Beijing", "Delhi", "Canberra"]:
                            if city_ownership[i] == city_clicked:
                                city_armies[i] += 250
                        city_ownership[city_clicked] = "Washington"
                        city_armies["Washington"] += random.randint(10, 50) * 10
                        
                    elif city_armies[city_ownership[city_clicked]] <= 100 and city_ownership[city_clicked] != city_clicked:
                        for i in ["Rio", "Paris", "Moscow", "Baghdad", "Tripoli", "Capetown", "Beijing", "Delhi", "Canberra"]:
                            if city_ownership[city_clicked] == i:
                                city_armies[i] += 500
                        city_ownership[city_clicked] = "Washington"
                        city_armies["Washington"] += random.randint(10, 25) * 10
                    
                    win_score = 0
                    for i in ["Washington", "Rio", "Paris", "Moscow", "Baghdad", "Tripoli", "Capetown", "Beijing", "Delhi", "Canberra"]:
                        if city_ownership[i] == i:
                            if city_armies[i] > 0:
                                city_armies[i] += int(city_armies[i] / (random.randint(2, 10)))
                        if city_ownership[i] == "Washington":
                            win_score += 1
                    
                    
            else:
                attack_button.back_color = RED
                attack_button.border_color = BLACK
                attack_button.text_color = BLACK

        else:
            attack_button.back_color = GRAY
            attack_button.border_color = LIGHT_GRAY
            attack_button.text_color = LIGHT_GRAY
            
        attack_button.draw_button()
        #End of buttons <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        
        #Capitals >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if city_clicked == "Washington":
            washington.draw_crosshair()
        elif city_clicked == "Rio":
            rio.draw_crosshair()
        elif city_clicked == "Paris":
            paris.draw_crosshair()
        elif city_clicked == "Moscow":
            moscow.draw_crosshair()
        elif city_clicked == "Baghdad":
            baghdad.draw_crosshair()
        elif city_clicked == "Tripoli":
            tripoli.draw_crosshair()
        elif city_clicked == "Capetown":
            capetown.draw_crosshair()
        elif city_clicked == "Beijing":
            beijing.draw_crosshair()
        elif city_clicked == "Delhi":
            delhi.draw_crosshair()
        elif city_clicked == "Canberra":
            canberra.draw_crosshair()
        
        if (washington.pos_x - 5) <= mouse_x <= (washington.pos_x + 5) and (washington.pos_y - 5) <= mouse_y <= (washington.pos_y + 5):
            washington.color = WHITE
            if mouse_down:
                city_clicked = "Washington"
        elif (rio.pos_x - 5) <= mouse_x <= (rio.pos_x + 5) and (rio.pos_y - 5) <= mouse_y <= (rio.pos_y + 5):
            rio.color = WHITE
            if mouse_down:
                city_clicked = "Rio"            
        elif (paris.pos_x - 5) <= mouse_x <= (paris.pos_x + 5) and (paris.pos_y - 5) <= mouse_y <= (paris.pos_y + 5):
            paris.color = WHITE
            if mouse_down:
                city_clicked = "Paris"            
        elif (moscow.pos_x - 5) <= mouse_x <= (moscow.pos_x + 5) and (moscow.pos_y - 5) <= mouse_y <= (moscow.pos_y + 5):
            moscow.color = WHITE
            if mouse_down:
                city_clicked = "Moscow"            
        elif (baghdad.pos_x - 5) <= mouse_x <= (baghdad.pos_x + 5) and (baghdad.pos_y - 5) <= mouse_y <= (baghdad.pos_y + 5):
            baghdad.color = WHITE
            if mouse_down:
                city_clicked = "Baghdad"
        elif (tripoli.pos_x - 5) <= mouse_x <= (tripoli.pos_x + 5) and (tripoli.pos_y - 5) <= mouse_y <= (tripoli.pos_y + 5):
            tripoli.color = WHITE
            if mouse_down:
                city_clicked = "Tripoli"            
        elif (capetown.pos_x - 5) <= mouse_x <= (capetown.pos_x + 5) and (capetown.pos_y - 5) <= mouse_y <= (capetown.pos_y + 5):
            capetown.color = WHITE
            if mouse_down:
                city_clicked = "Capetown"
        elif (beijing.pos_x - 5) <= mouse_x <= (beijing.pos_x + 5) and (beijing.pos_y - 5) <= mouse_y <= (beijing.pos_y + 5):
            beijing.color = WHITE
            if mouse_down:
                city_clicked = "Beijing"            
        elif (delhi.pos_x - 5) <= mouse_x <= (delhi.pos_x + 5) and (delhi.pos_y - 5) <= mouse_y <= (delhi.pos_y + 5):
            delhi.color = WHITE
            if mouse_down:
                city_clicked = "Delhi"            
        elif (canberra.pos_x - 5) <= mouse_x <= (canberra.pos_x + 5) and (canberra.pos_y - 5) <= mouse_y <= (canberra.pos_y + 5):
            canberra.color = WHITE
            if mouse_down:
                city_clicked = "Canberra"
                
        elif mouse_down and not (attack_button.pos_x <= mouse_x <= attack_button_x2 and attack_button.pos_y <= mouse_y <= attack_button_y2):
            city_clicked = "None"

        else:
            if city_clicked != "Washington":
                washington.color = GREEN
            if city_clicked != "Rio":
                if city_ownership["Rio"] != "Washington":
                    rio.color = RED
                    rio.crosshair_color = RED
                else:
                    rio.color = GREEN
                    rio.crosshair_color = GREEN
            if city_clicked != "Paris":
                if city_ownership["Paris"] != "Washington":
                    paris.color = RED
                    paris.crosshair_color = RED
                else:
                    paris.color = GREEN
                    paris.crosshair_color = GREEN
            if city_clicked != "Moscow":
                if city_ownership["Moscow"] != "Washington":
                    moscow.color = RED
                    moscow.crosshair_color = RED
                else:
                    moscow.color = GREEN
                    moscow.crosshair_color = GREEN
            if city_clicked != "Baghdad":
                if city_ownership["Baghdad"] != "Washington":
                    baghdad.color = RED
                    baghdad.crosshair_color = RED
                else:
                    baghdad.color = GREEN
                    baghdad.crosshair_color = GREEN
            if city_clicked != "Tripoli":
                if city_ownership["Tripoli"] != "Washington":
                    tripoli.color = RED
                    tripoli.crosshair_color = RED
                else:
                    tripoli.color = GREEN
                    tripoli.crosshair_color = GREEN
            if city_clicked != "Capetown":
                if city_ownership["Capetown"] != "Washington":
                    capetown.color = RED
                    capetown.crosshair_color = RED
                else:
                    capetown.color = GREEN
                    capetown.crosshair_color = GREEN
            if city_clicked != "Beijing":
                if city_ownership["Beijing"] != "Washington":
                    beijing.color = RED
                    beijing.crosshair_color = RED
                else:
                    beijing.color = GREEN
                    beijing.crosshair_color = GREEN
            if city_clicked != "Delhi":
                if city_ownership["Delhi"] != "Washington":
                    delhi.color = RED
                    delhi.crosshair_color = RED
                else:
                    delhi.color = GREEN
                    delhi.crosshair_color = GREEN
            if city_clicked != "Canberra":
                if city_ownership["Canberra"] != "Washington":
                    canberra.color = RED
                    canberra.crosshair_color = RED
                else:
                    canberra.color = GREEN
                    canberra.crosshair_color = GREEN
        
        washington.draw_capital()
        rio.draw_capital()
        paris.draw_capital()
        moscow.draw_capital()
        baghdad.draw_capital()
        tripoli.draw_capital()
        capetown.draw_capital()
        beijing.draw_capital()
        delhi.draw_capital()
        canberra.draw_capital()
        #End of capitals <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        
    
    elif battle_scene:
        screen.blit(battle, [battle_x, battle_y])
        if counter % 2 == 0:
            battle_x += battle_x_change
            battle_y += battle_y_change
        if battle_x <= -450:
            battle_scene = False
            playing_mode = True
            
            if win_score == 10:
                playing_mode = False
                you_won = True                    
            elif city_armies["Washington"] <= 0:
                playing_mode = False
                game_over = True
                
            battle_x = 0
            battle_y = 0
            
            if you_won or game_over:
                for i in ["Washington", "Rio", "Paris", "Moscow", "Baghdad", "Tripoli", "Capetown", "Beijing", "Delhi", "Canberra"]:
                    city_ownership[i] = i
                city_clicked = "None"
                city_armies["None"] = 0
                city_armies["Washington"] = 1000
                city_armies["Rio"] = 600
                city_armies["Paris"] = 800
                city_armies["Moscow"] = 900
                city_armies["Baghdad"] = 700
                city_armies["Tripoli"] = 600
                city_armies["Capetown"] = 500
                city_armies["Beijing"] = 900
                city_armies["Delhi"] = 700
                city_armies["Canberra"] = 800                                
        
        
    elif game_over:
        screen.blit(game_over_background, [game_over_x, game_over_y])
        
        if counter % 2 == 0:
            game_over_x += game_over_x_change
            game_over_y += game_over_y_change
        if game_over_x <= -600:
            game_over = False
            title_screen = True
            game_over_x = 0
            game_over_y = 0
               
        screen.blit(game_over_text_1, [125, 125])
        screen.blit(game_over_text_2, [275, 275])        
        
    
    elif you_won:
        screen.blit(you_won_background, [you_won_x, you_won_y])
        
        other_counter += 1
        if mouse_down and other_counter > 240:
            you_won = False
            title_screen = True
            other_counter = 0
                
        screen.blit(you_won_text, [5, -10])
        
        
    #Counter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    counter += 1
    #Counter end <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
#-------------------------------------------------------------------------------
    pygame.display.flip()
 
    #This limits the while loop to a max of 60 times per second.
    #Leave this out and we will use all CPU we can.
    clock.tick(60)
 
#Be IDLE friendly
pygame.quit()