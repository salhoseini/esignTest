Esign Recruitment Test - Lottery

Spec: The program is designed and implemented to simulate an actual lottery game
1- There is the possibility of having no winners
2- If first ball is not bought by any player, the prize for first ball is not deducted from the pot even if the second or third balls are assigned to a player (similar scenario for second and third balls)
3- When purchasing a ticket, the number is generated randomly
4- Draw can happen even if no ball is purchased by any player
5- After the winners are announced, the game is re-started. Meaning that the bought tickets have no values anymore

To run the application:
1-You would need to have Java installed and setup in your environment.
2-You would also need to have Maven installed and setup.

The jar file is already created but just if not able to find it:
1-using command line navigate to where the source file is copied, navigate to "target" folder and write "mvn package" this will create the jar file.
2-Then write the following command : java -jar lottery-1.0-SNAPSHOT.jar