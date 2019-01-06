/*
 * Mitchell Van Braeckel
 * 05/10/2016
 * Pocket Monsters Simlulator
    -> https://docs.google.com/document/d/1sziLb2DVwhJ38OcBChE1ePYQRIoMUjMZjVwP4eY6xBs/edit
    -> 1. load data from all text files
        a) read in data about pocket monster abilites from a text file
        b) read in data about Kanto trainers and the 3 pocket monsters they carry
        c) read in data about pocket monsters that can be found in the wild
    -> 2. User selects a trainer from the trainer selection menu
        -> Ash, Professor Oak, Giovanni, Done (to Quit)
    -> 3. User selects what they would like to do as that trainer from the action selection menu
        -> Walk (in the tall grass), Release (a pocket monster), Examine (a pocket monster, Nickname* (a pocket monster), Train* (a pocket monster), Back
        
================================================================================================================================================
    ----- abilities.txt file ----- ()[]() a list of types w/ 4 moves and their respective move descriptions ()[]()
        • first item in file is the #of "type" records it contains
        • record looks like:
            •-> Type
            •-> Type
            •-> Ability 1
            •-> Ability 1 description
            •-> Ability 2
            •-> Ability 2 description
            •-> Ability 3
            •-> Ability 3 description
            •-> Ability 4
            •-> Ability 4 description
            (NOTE: Ability Description Output Format: "Pocket Monster Name(-from main class--from Trainer class)" + " verb(-present tense)(-from file)........"
    ----- kanto.txt file ----- ()[]() a list of trainers in kanto and the 3 pocket monsters they carry ()[]()
        • first item in file is the #of "trainer" records it contains
        • record looks like:
            •-> Trainer Name
            •-> Trainer Age
            •-> Pocket Monster 1 Species
            •-> Pocket Monster 1 Name
            •-> Pocket Monster 1 Type
            •-> Pocket Monster 1 Height
            •-> Pocket Monster 1 Weight
            •-> Pocket Monster 1 Hit Points
            •-> Pocket Monster 1 Experience
            •-> Pocket Monster 1 Level
            •-> Pocket Monster 2 (follows same pattern as Pocket Monster 1)
            •-> Pocket Monster 3 (follows same pattern as Pocket Monster 1)
    ----- wild.txt file ----- ()[]() a list of wild pocket monsters ()[]()
        • first item in file is the #of "wild pocket monster" records it contains
        • record looks like:
            •-> Wild Monster Species
            •-> Wild Monster Type
            •-> Wild Monster Height
            •-> Wild Monster Weight
            •-> Wild Monster Lowest Level
            •-> Wild Monster Highest Level
 */

package pocketmonstersvanbraeckel;

// imports
import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class PocketMonstersVanBraeckel {
    // declare needed variables
    static Random r = new Random();
    /**
     * Runs the main method of the simulator
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // Reads the #of records in the abilities.txt data file
        // declare needed variables
        boolean eof;
        String line;
        int counter;
        int abilityRecords = 0;
        
        try {   // read in #of records for pocket monster abilites text file
            FileReader fr = new FileReader("src\\pocketmonstersvanbraeckel\\abilities.txt");
            BufferedReader br = new BufferedReader(fr);
            eof = false;
            counter = 0;
            while(!eof) {
                line = br.readLine();
                // check if anything is there
                if(line == null) {
                    // stop reading
                    eof = true;
                } else {    // cont reading
                    // check if it's the first line in the file
                    if(counter == 0) {
                        abilityRecords = Integer.parseInt(line);
                        br.close(); // closes stream
                        break;  //once the #of records is recorded, stop reading
                    } // end if
                    counter++;  //accumulate counter
                } // end 
            } // end while loop
            br.close(); //closes stream
        } catch(IOException e) {
            System.out.println("Error: " + e.toString());
        } // end try-catch abilities
        
        // create an array for the text file of abilities to be stored //trainers[a][b][c]
            // a = #of types; b = type + 4 moves; c = move name + description
        String ability[][][] = new String[abilityRecords][5][2];
        
        // Loads all the records of the abilities.txt file into an array of pocket monster abilities
        readAbilities(ability);
        
            // Reads the #of records in the kanto.txt data file
        // declare needed variables
        int trainerRecords = 0;
        
        try {   // read in #of records for Kanto trainers text file
            FileReader fr = new FileReader("src\\pocketmonstersvanbraeckel\\kanto.txt");
            BufferedReader br = new BufferedReader(fr);
            eof = false;
            counter = 0;
            while(!eof) {
                line = br.readLine();
                // check if anything is there
                if(line == null) {
                    // stop reading
                    eof = true;
                } else {    // cont reading
                    // check if it's the first line in the file
                    if(counter == 0) {
                        trainerRecords = Integer.parseInt(line);
                        br.close(); // closes stream
                        break;  //once the #of records is recorded, stop reading
                    } // end if
                    counter++;  //accumulate counter
                } // end 
            } // end while loop
            br.close(); //closes stream
        } catch(IOException e) {
            System.out.println("Error: " + e.toString());
        } // end try-catch kanto
        
        // create an array for the text file of Kanto Trainers to be stored
        Trainer trainers[] = new Trainer[trainerRecords];
        // create an array for the trainer's pocket monsters to be stored
        Monster trainerMonsters[][] = new Monster[trainerRecords][3];
        
        // Loads all the records of the kanto.txt file into an array of Kanto Trainers
        readTrainers(trainers, trainerMonsters);
        
        // Gives each of the trainer's 3 pocket monsters abilities based on type
        for (int i = 0; i < trainerRecords; i++) {
            for (int j = 0; j < 3; j++) {
                matchTrainerMonsterAbilities(trainerMonsters[i][j].getType(), abilityRecords, ability, i, j, trainerMonsters);
            } // end for loop-2nd level j
        } // end for loop-1st level i
        
        // copy the array of all the trainer's 3 pocket monsters into an the array of trainers
            // done by copying each trainer's 3 monsters at a time
        for (int i = 0; i < trainerRecords; i++) {
            trainers[i].setAllMonsters(i, trainerMonsters);
        } // end for loop
        
            // Reads the #of records in the wild.txt data file
        // declare needed variables
        int wildRecords = 0;
        
        try {   // read in #of records for Kanto trainers text file
            FileReader fr = new FileReader("src\\pocketmonstersvanbraeckel\\wild.txt");
            BufferedReader br = new BufferedReader(fr);
            eof = false;
            counter = 0;
            while(!eof) {
                line = br.readLine();
                // check if anything is there
                if(line == null) {
                    // stop reading
                    eof = true;
                } else {    // cont reading
                    // check if it's the first line in the file
                    if(counter == 0) {
                        wildRecords = Integer.parseInt(line);
                        br.close(); // closes stream
                        break;  //once the #of records is recorded, stop reading
                    } // end if
                    counter++;  //accumulate counter
                } // end 
            } // end while loop
            br.close(); //closes stream
        } catch(IOException e) {
            System.out.println("Error: " + e.toString());
        } // end try-catch kanto
        
        // create an array for the text file of wild pocket monsters to be stored
        Monster wildMonsters[] = new Monster[wildRecords];
        
        // Loads all the records of the wild.txt file into an array of wild pocket monsters
        readWild(wildMonsters, ability);
        
        // Gives each of the wild pocket monsters abilities based on type
        for (int i = 0; i < wildRecords; i++) {
            matchWildMonsterAbilities(wildMonsters[i].getType(), abilityRecords, ability, i, wildMonsters);
        } // end for loop
        
        // Display welcoming msgs and origin story
        JOptionPane.showMessageDialog(null, "After falling asleep in ICS4U one day, you wake up in a place that is definitely not C206.  " +
                "\nYou're approached by a friendly-looking but frazzled man who introduces himself as a Mr. Tajiri.  " + 
                "\nHe explains that you've landed in the magical region of Kanto, and just in time too, Mr. Tajiri needs your help!\n\n" + 
                "Kanto is full of people who are called 'Trainers'.  Trainers collect creatures known as 'Pocket Monsters'.  " + 
                "\nMr. Tajiri is in charge of Kanto's 'Department of Collection', which tracks who is registered as a Trainer, " + 
                "\nand which Pocket Monsters those trainers have collected.\n\n" +
                "He had someone create a program which allows the user to examine Trainer data loaded from a file.  " +
                "\nThe program also allows the user to run a simulation, where they can 'examine' a Trainer's Pocket Monsters, " +
                "\nmake Trainers 'release' the Pocket Monsters in their care, or even take a 'walk' in the grass, " + 
                "\nwhere they have a chance to 'catch' a wild Pocket Monster!\n\n" + 
                "Mr. Tajiri needs you to test this new simulation program before he gives it to all the young new Trainers " +
                "\nthat will be coming to the Lab tomorrow afternoon to receive their first Pocket Monster " + 
                "\nand start their jounrney across the Kanto region.\n\n(He won't take no for an answer)", 
                "Pocket Monsters Simulation - Welcome to Kanto!", JOptionPane.INFORMATION_MESSAGE);
        
        // put all Kanto trainers in a list for ouput
        String trainerList = "";
        for (int i = 0; i < trainerRecords; i++) {
            trainerList += "\n• " + trainers[i].getName();
        } // end for loop
        
        // declare needed variables
        String trainerMenuChoice = "";
        boolean tmcBadInput = false;
        boolean foundTrainerMatch = false;
        int useTrainer = 0;
        boolean actionMenu = false;
        String actionMenuChoice = "";
        int rndNum = 0;
        
        while(true) {   // infinite loop menu -- unless user selects to exit, which stops the program
            // Display 1st menu where user can select which Trainer they would like to use [?? MAKE a "CREATE NEW TRAINER" OPTION??]
            trainerMenuChoice = (String)JOptionPane.showInputDialog(null, 
                    "Which Trainer's data would you like to load?" + trainerList + "\n• Done (to quit)", 
                    "Pocket Monsters Simulation - Select Trainer Menu", JOptionPane.QUESTION_MESSAGE, null, null, "exit");
            // check if trainer entered exists
            for (int i = 0; i < trainerRecords; i++) {
                if(trainerMenuChoice.equalsIgnoreCase(trainers[i].getName())) {
                    foundTrainerMatch = true;
                    useTrainer = i;     // set to index of the trainer in use
                    actionMenu = true;
                    // Display 2nd menu where user can choose what they would like to do
                    while(actionMenu) {
                        actionMenuChoice = (String)JOptionPane.showInputDialog(null, trainers[useTrainer].getName() + 
                                " holds the following:\n1) " + trainers[useTrainer].getMonster(0).getName() + 
                                "\n2) " + trainers[useTrainer].getMonster(1).getName() + "\n3) " + trainers[useTrainer].getMonster(2).getName() + 
                                "\n\nWhat would you like to do?\n1. Walk (in the grass - you might find a wild pocket monster!)" + 
                                "\n2. Release (one of the pocket monsters in " + trainers[useTrainer].getName() + "'s care)" +
                                "\n3. Examine (one of the pocket monsters in " + trainers[useTrainer].getName() + "'s care)" + 
                                "\n4. Nickname (one of the pocket monsters in " + trainers[useTrainer].getName() + "'s care)" + 
                                "\n5. Train (at the special area to practice using abilities on a target)" +
                                "\n6. Back (to previous menu)", 
                                "Pocket Monsters Simulation - Select Action Menu", JOptionPane.QUESTION_MESSAGE, null, null, "back");
                        // check which action is selected
                        if(actionMenuChoice.equals("1") || actionMenuChoice.equalsIgnoreCase("walk")) {
                            walk(abilityRecords, ability, wildRecords, wildMonsters, useTrainer, trainers);     // run walking in the grass event sequence
                        } else if(actionMenuChoice.equals("2") || actionMenuChoice.equalsIgnoreCase("release")) {
                            releaseMonster(useTrainer, trainers);
                        } else if(actionMenuChoice.equals("3") || actionMenuChoice.equalsIgnoreCase("examine")) {
                            examine(useTrainer, trainers);
                        } else if(actionMenuChoice.equals("4") || actionMenuChoice.equalsIgnoreCase("nickname")) {
                            nickname(useTrainer, trainers);
                        } else if(actionMenuChoice.equals("5") || actionMenuChoice.equalsIgnoreCase("train")) {
                            train(useTrainer, trainers);
                        } else if(actionMenuChoice.equals("6") || actionMenuChoice.equalsIgnoreCase("back")) {
                            // return to the previous trainer selection menu
                            actionMenu = false;
                        } else {
                            // display error msg
                            JOptionPane.showMessageDialog(null, "INVALID INPUT\nThat is not an option, plese try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } // end if
                    } // end while loop-action menu
                } else if(trainerMenuChoice.equalsIgnoreCase("exit") || trainerMenuChoice.equalsIgnoreCase("done") || trainerMenuChoice.equalsIgnoreCase("quit")) {
                    System.exit(0);     //exits the program
                } else {
                    tmcBadInput = true; //bad input was given
                } // end if
            } // end for loop
            
            // check if user's input was bad
            if(tmcBadInput && !foundTrainerMatch) {
                tmcBadInput = false;    //reset
                // display error msg
                JOptionPane.showMessageDialog(null, "The trainer entered does not exist.\nPlese try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } // else it was good // end if
            foundTrainerMatch = false;  //reset
        } // end while loop-trainer menu
    } // end main
    /**
     * Loads all the records of the abilities.txt file into an array of pocket monster abilities
     * @param ability - reference to the 3D array of a list of pocket monster abilities and their descriptions based on type
     */
    static private void readAbilities(String ability[][][]) {
        // declare needed variables
        int abilityRecords = 0;
        
        try {   // read in data about pocket monster abilites from a text file
            FileReader fr = new FileReader("src\\pocketmonstersvanbraeckel\\abilities.txt");
            BufferedReader br = new BufferedReader(fr);
            abilityRecords = Integer.parseInt(br.readLine());    // set #of records
            // loop through the data file and put each record in the array
            for (int i = 0; i < abilityRecords; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 2; k++) {
                        ability[i][j][k] = br.readLine();
                    } // end for loop-3rd level k
                } // end for loop-2nd level j
            } // end for loop-1st level i
            br.close(); // closes stream
        } catch(IOException e) {
            System.out.println("Error: " + e.toString());
        } // end try-catch abilities
    } // end readAbilities()
    /**
     * Loads all the records of the kanto.txt file into an array of Kanto Trainers
     * @param trainers - reference to the array of Kanto trainers
     * @param trainerMonsters - reference to the array of the trainer's 3 pocket monsters
     */
    static private void readTrainers(Trainer trainers[], Monster trainerMonsters[][]) {
        // declare needed variables
        int trainerRecords = 0;
        
        // declare needed variables
        String trainerName = "";
        int age = -1;
        String species = "";
        
        try {   // read in data about pocket monster abilites from a text file
            FileReader fr = new FileReader("src\\pocketmonstersvanbraeckel\\kanto.txt");
            BufferedReader br = new BufferedReader(fr);
            trainerRecords = Integer.parseInt(br.readLine());    // set #of records
            // loop through the data file and put each record in the array
            for (int i = 0; i < trainerRecords; i++) {
                // add trainer's name and age
                trainerName = br.readLine();
                age = Integer.parseInt(br.readLine());
                trainers[i] = new Trainer(trainerName, age);    // instantiates the Trainer object w/ a name and age
                // loop through and add the 3 pocket monsters that the trainer carries
                for (int j = 0; j < 3; j++) {
                    species = br.readLine();
                    trainerMonsters[i][j] = new Monster(species);   // instantiates the Monster object w/ a species
                    trainerMonsters[i][j].setName(br.readLine());
                    trainerMonsters[i][j].setType(br.readLine());
                    trainerMonsters[i][j].setHeight(Double.parseDouble(br.readLine()));
                    trainerMonsters[i][j].setWeight(Double.parseDouble(br.readLine()));
                    trainerMonsters[i][j].setHP(Double.parseDouble(br.readLine()));
                    trainerMonsters[i][j].setEXP(Double.parseDouble(br.readLine()));
                    trainerMonsters[i][j].setLV(Integer.parseInt(br.readLine()));
                } // end for loop-2nd level k
            } // end for loop-1st level i
            
            br.close(); // closes stream
        } catch(IOException e) {
            System.out.println("Error: " + e.toString());
        } // end try-catch trainers
    } // end readTrainers()
    /**
     * Loads all the records of the wild.txt file into an array of wild pocket monsters
     * @param wildMonsters - reference to the array of wild pocket monsters
     */
    static private void readWild(Monster wildMonsters[], String[][][] ability) {
        // declare needed variables
        int wildRecords = 0;
        
        // declare needed variables
        int lowLV = 0;
        int highLV = 0;
        int level = 0;
        int hp = 15;
        int rndNum = 0;
        
        try {   // read in data about pocket monster abilites from a text file
            FileReader fr = new FileReader("src\\pocketmonstersvanbraeckel\\wild.txt");
            BufferedReader br = new BufferedReader(fr);
            wildRecords = Integer.parseInt(br.readLine());    // set #of records
            // loop through the data file and put each record in the array
            for (int i = 0; i < wildRecords; i++) {
                // add species, type, height, weight, lowest level, and highest level
                wildMonsters[i] = new Monster(br.readLine());       // instantiates the Monster object w/ a species
                wildMonsters[i].setType(br.readLine());
                wildMonsters[i].setHeight(Double.parseDouble(br.readLine()));
                wildMonsters[i].setWeight(Double.parseDouble(br.readLine()));
                lowLV = Integer.parseInt(br.readLine());
                highLV = Integer.parseInt(br.readLine());

                // calc level, exp, hp
                level = r.nextInt(highLV-lowLV+1) + lowLV;  // rnd level within the range its found at
                wildMonsters[i].setLV(level);               //add level
                wildMonsters[i].setEXP(Math.pow(level, 3)); //add exp
                hp = 15;
                for (int j = 1; j < level; j++) {   // for each level up to current level, monster gains 1-3 HP
                    rndNum = r.nextInt(100) + 1;
                    if(rndNum <= 50) {          //50% chance +1
                        hp += 1;
                    } else if(rndNum <= 85) {   //35% chance +2
                        hp += 2;
                    } else {                    //15% chance +3
                        hp += 3;
                    } // end if
                } // end for loop
                wildMonsters[i].setHP(hp);      //add hp
            } // end for loop
            
            br.close(); // closes stream
        } catch(IOException e) {
            System.out.println("Error: " + e.toString());
        } // end try-catch wild
    } // end readWild()
    /**
     * Matches the trainer's 3 pocket monsters w/ their respective abilities based on type
     * @param type - the elemental type of the pocket monster to be matched with corresponding abilities
     * @param abilityRecords - the #of records in the array of pocket monster abilities
     * @param ability - reference to the 3D array of a list of pocket monster abilities and their descriptions based on type
     * @param trainerIndex - the index of the trainer that is having its pocket monster's abilities matched
     * @param trainerMonster - which of the trainer's 3 monsters will have its abilities set
     * @param trainerMonsters - reference to the array of the trainer's 3 pocket monsters
     */
    static private void matchTrainerMonsterAbilities(String type, int abilityRecords, String ability[][][], int trainerIndex, int trainerMonster, Monster[][] trainerMonsters) {
        // loop through the array of abilities to find a type match, then copy the move info to the trainer's pocket monster
        for (int i = 0; i < abilityRecords; i++) {
            // check if the type matches
            if(ability[i][0][0].equals(type)) {
                // copy the abilites for that type as the monster's abilities
                trainerMonsters[trainerIndex][trainerMonster].setAbilities(i, ability);
            } // else nothing // cont looping // end if
        } // end for loop-1st level i
    } // end matchTrainerMonsterAbilities()
    /**
     * Matches the trainer's 3 pocket monsters w/ their respective abilities based on type
     * @param type - the elemental type of the pocket monster to be matched with corresponding abilities
     * @param abilityRecords - the #of records in the array of pocket monster abilities
     * @param ability - reference to the 3D array of a list of pocket monster abilities and their descriptions based on type
     * @param wildIndex - the index of the wild pocket monster that is having its abilities matched
     * @param wildMonsters - reference to the array of wild pocket monsters
     */
    static private void matchWildMonsterAbilities(String type, int abilityRecords, String ability[][][], int wildIndex, Monster[] wildMonsters) {
        // loop through the array of abilities to find a type match, then copy the move info to the trainer's pocket monster
        for (int i = 0; i < abilityRecords; i++) {
            // check if the type matches
            if(ability[i][0][0].equals(type)) {
                // copy the abilites for that type as the monster's abilities
                wildMonsters[wildIndex].setAbilities(i, ability);
            } // else nothing // cont looping // end if
        } // end for loop-1st level i
    } // end matchWildMonsterAbilities()
    /**
     * Matches the trainer's 3 pocket monsters w/ their respective abilities based on type
     * @param type - the elemental type of the pocket monster to be matched with corresponding abilities
     * @param abilityRecords - the #of records in the array of pocket monster abilities
     * @param ability - reference to the 3D array of a list of pocket monster abilities and their descriptions based on type
     * @param wildMonster - the monster to have its abilities set
     */
    static private void matchSingleMonsterAbilities(String type, int abilityRecords, String ability[][][], Monster wildMonster) {
        // loop through the array of abilities to find a type match, then copy the move info to the trainer's pocket monster
        for (int i = 0; i < abilityRecords; i++) {
            // check if the type matches
            if(ability[i][0][0].equals(type)) {
                // copy the abilites for that type as the monster's abilities
                wildMonster.setAbilities(i, ability);
            } // else nothing // cont looping // end if
        } // end for loop-1st level i
    } // end matchSingleMonsterAbilities()
    /**
     * Runs the sequence of events that occur when walking in the grass
     * @param abilityRecords - the #of records in the array of pocket monster abilities
     * @param ability - reference to the 3D array of a list of pocket monster abilities and their descriptions based on type
     * @param wildRecords - the #of records in the array of wild pocket monsters
     * @param wildMonsters - reference to the array of wild pocket monsters
     * @param useTrainer - the index of the Kanto trainer in use
     * @param trainers - reference to the array of Kanto trainers
     */
    static private void walk(int abilityRecords, String ability[][][], int wildRecords, Monster wildMonsters[], int useTrainer, Trainer trainers[]) {
        // declare needed variables
        String ignore = "";
        boolean badInput = true;
        boolean emptySlot[] = new boolean[3];
        for (int i = 0; i < 3; i++) {
            emptySlot[i] = false;   //instantiate as false
        } // end for loop
        int emptySlots = 0;
        String sEmptySlots = "";
        String sChooseSlot = "";
        boolean chooseSlot = true;
        // display walking msg
        JOptionPane.showMessageDialog(null, trainers[useTrainer].getName() + " wanders around in the grass for a bit...",
                "Pocket Monsters Simulation - Walk", JOptionPane.INFORMATION_MESSAGE);
        // calc chance of wild monster encounter (33% chance)
        int chanceEncounter = r.nextInt(3) + 1;
        if(chanceEncounter == 1) {
            // generate a random wild pocket monster
            int rndNum = r.nextInt(wildRecords);    //gen rnd# 0->#of records
            //set the new wild monster to an instance of a randomly generated monster
            Monster wildMonster = new Monster(wildMonsters[rndNum].getSpecies(), wildMonsters[rndNum].getType(), wildMonsters[rndNum].getHeight(), 
                    wildMonsters[rndNum].getWeight(), wildMonsters[rndNum].getLV(), wildMonsters[rndNum].getEXP(), wildMonsters[rndNum].getHP());
            //add abilities
            matchSingleMonsterAbilities(wildMonster.getType(), abilityRecords, ability, wildMonster);
            // loop menu for choice of action w/ wild monster until valid input is given
            while(badInput) {   // display menu - try to 'catch' the wild pocket monster, or 'ignore' it
                ignore = (String)JOptionPane.showInputDialog(null, trainers[useTrainer].getName() + " encounters a wild " + 
                        wildMonster.getSpecies() + ", a " + wildMonster.getType() + " type pocket monster." + 
                        "\n(A wild " + wildMonster.getSpecies().toUpperCase() + " appears!)" + "\nWhat would you like to do?" + 
                        "\n1. 'Catch' the wild " + wildMonster.getSpecies() + " (chance of faliure)" + 
                        "\n2. 'Ignore' it", 
                        "Pocket Monsters Simulation - Wild Encounter", JOptionPane.QUESTION_MESSAGE, null, null, "catch");
                // check which option is selected
                if(ignore.equals("1") || ignore.equalsIgnoreCase("catch")) {
                    // calc if attempt to catch wild monster is successful
                    if(trainers[useTrainer].catchMonster()) {   // success
                        // loop through and check which slots are 'empty'
                        for (int i = 0; i < 3; i++) {
                            if(trainers[useTrainer].getMonster(i).getSpecies().equals("empty")) {
                                emptySlot[i] = true;
                                emptySlots++;   //accumulate
                                sEmptySlots += "\n" + (i+1);
                            } // end if
                        } // end for loop
                        
                        // set the caught monster in an 'empty' slot
                        if(emptySlots == 1) {
                            // only 1 'empty' slot, so loop through to find and set it
                            for (int i = 0; i < 3; i++) {
                                if(emptySlot[i]) {
                                    // set the caught monster in that slot
                                    trainers[useTrainer].setMonster(i, wildMonster);
                                } // end if
                            } // end for loop
                            badInput = false;   // stop looping
                        } else {
                            chooseSlot = true;
                            while(chooseSlot) { // cont looping until good input is given
                                sChooseSlot = (String)JOptionPane.showInputDialog(null, trainers[useTrainer].getName() + 
                                        " holds the following:\n1) " + trainers[useTrainer].getMonster(0).getName() + 
                                        "\n2) " + trainers[useTrainer].getMonster(1).getName() + "\n3) " + trainers[useTrainer].getMonster(2).getName() + 
                                        "\n\nAs you can see, the following slots are open:" + sEmptySlots + 
                                        "\n\nSelect which open slot you would like to place your new " + 
                                        wildMonster.getSpecies() + ", AKA " + wildMonster.getName() + ", in.", 
                                        "Pocket Monsters Simulation - Wild Encounter", JOptionPane.QUESTION_MESSAGE, null, null, "0");
                                // check user's answer
                                if(trainers[useTrainer].getMonster(0).getSpecies().equals("empty") && sChooseSlot.equals("1")) {
                                    // set the caught monster in slot[0]
                                    trainers[useTrainer].setMonster(0, wildMonster);
                                    // stop walking
                                    chooseSlot = false;
                                    badInput = false;
                                } else if(trainers[useTrainer].getMonster(1).getSpecies().equals("empty") && sChooseSlot.equals("2")) {
                                    // set the caught monster in slot[1]
                                    trainers[useTrainer].setMonster(1, wildMonster);
                                    // stop walking
                                    chooseSlot = false;
                                    badInput = false;
                                } else if(trainers[useTrainer].getMonster(2).getSpecies().equals("empty") && sChooseSlot.equals("3")) {
                                    // set the caught monster in slot[2]
                                    trainers[useTrainer].setMonster(2, wildMonster);
                                    // stop walking
                                    chooseSlot = false;
                                    badInput = false;
                                } else {
                                    // display error msg
                                    JOptionPane.showMessageDialog(null, "INVALID INPUT\nThat is not an option, please select from the following:" + sEmptySlots, 
                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                } // end if
                            } // end while loop
                        } // end if
                    } else {    // fail
                        JOptionPane.showMessageDialog(null, wildMonster.getSpecies() + " broke free!", 
                                "Pocket Monsters Simulation - Wild Encounter", JOptionPane.INFORMATION_MESSAGE);
                        // calc if wild monster runs away (75% chance)
                        if(r.nextInt(4) + 1 < 4) {  // runs away
                            JOptionPane.showMessageDialog(null, "Oh no!  " + wildMonster.getSpecies() + " ran away!", 
                                    "Pocket Monsters Simulation - Wild Encounter", JOptionPane.INFORMATION_MESSAGE);
                            badInput = false;   // stop walking
                        } // else nothing (does NOT run away) // end if
                    } // end if
                } else if(ignore.equals("2") || ignore.equalsIgnoreCase("ignore")) {
                    JOptionPane.showMessageDialog(null, trainers[useTrainer].getName() + " got away safely!", 
                                    "Pocket Monsters Simulation - Wild Encounter", JOptionPane.INFORMATION_MESSAGE);
                    badInput = false;   // stop walking
                } else {
                    // display error msg
                    JOptionPane.showMessageDialog(null, "INVALID INPUT\nThat is not an option, please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                } // end if
            } // end while loop
        } else {    // display uneventful walk msg
            JOptionPane.showMessageDialog(null, "...but doesn't find anything",
                "Pocket Monsters Simulation - Walk", JOptionPane.INFORMATION_MESSAGE);
        } // end if
    } // end walk()
    /**
     * Releases a designated pocket monster in a trainer's possession
     * @param useTrainer - the index of the Kanto trainer in use
     * @param trainers - reference to the array of Kanto trainers
     */
    static private void releaseMonster(int useTrainer, Trainer trainers[]) {
        // declare needed variables
        boolean releaseMenu = true;
        String releaseChoice = "";
        while(releaseMenu) {
            // user selects which pokemon to release
            releaseChoice = (String)JOptionPane.showInputDialog(null, "Which of " + trainers[useTrainer].getName() + 
                    " pocket monsters would you like to release?\n(Identify it by name)\n1) " + trainers[useTrainer].getMonster(0).getName() + 
                    "\n2) " + trainers[useTrainer].getMonster(1).getName() + "\n3) " + trainers[useTrainer].getMonster(2).getName() + 
                    "\n4. Back (to previous menu)", 
                    "Pocket Monsters Simulation - Release", JOptionPane.QUESTION_MESSAGE, null, null, "back");
            // check user's input
            if(trainers[useTrainer].release(releaseChoice)) {   // found match
                // loop through trainer's monsters and find index of monster to be released
                for (int i = 0; i < 3; i++) {
                    if(trainers[useTrainer].getMonster(i).getName().equalsIgnoreCase(releaseChoice) && !trainers[useTrainer].getMonster(i).getName().equals("empty")) {
                        // set monster as 'empty' to release it
                        trainers[useTrainer].emptyMonster(i);
                    } // end if
                } // end for loop
            } else if(releaseChoice.equals("4") || releaseChoice.equalsIgnoreCase("back")) {
                releaseMenu = false;    // stop looping
            } else {
                // display error msg
                JOptionPane.showMessageDialog(null, "The name entered is not one of " + trainers[useTrainer].getName() + "'s pocket monsters." + 
                        "\nPlese try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } // end if
        } // end while loop
    } // end releaseMonster()
    /**
     * Examines a designated pocket monster in a trainer's possession
     * @param useTrainer - the index of the Kanto trainer in use
     * @param trainers - reference to the array of Kanto trainers
     */
    static private void examine(int useTrainer, Trainer trainers[]) {
        // declare needed variables
        boolean examineMenu = true;
        String examineChoice = "";
        while(examineMenu) {
            // user selects which pokemon to examine
            examineChoice = (String)JOptionPane.showInputDialog(null, "Which of " + trainers[useTrainer].getName() + 
                    " pocket monsters would you like to examine?\n(Identify it by name)\n1) " + trainers[useTrainer].getMonster(0).getName() + 
                    "\n2) " + trainers[useTrainer].getMonster(1).getName() + "\n3) " + trainers[useTrainer].getMonster(2).getName() + 
                    "\n4. Back (to previous menu)", 
                    "Pocket Monsters Simulation - Examine", JOptionPane.QUESTION_MESSAGE, null, null, "back");
            // check user's input
            if(trainers[useTrainer].release(examineChoice)) {   // found match
                // loop through trainer's monsters and find index of monster to be examined
                for (int i = 0; i < 3; i++) {
                    if(trainers[useTrainer].getMonster(i).getName().equalsIgnoreCase(examineChoice) && !trainers[useTrainer].getMonster(i).getName().equals("empty")) {
                        // display stats of monster to be examined
                        JOptionPane.showMessageDialog(null, trainers[useTrainer].getMonster(i).toString(), 
                                "Pocket Monsters Simulation - Examine", JOptionPane.INFORMATION_MESSAGE);
                    } // end if
                } // end for loop
            } else if(examineChoice.equals("4") || examineChoice.equalsIgnoreCase("back")) {
                examineMenu = false;    // stop looping
            } else {
                // display error msg
                JOptionPane.showMessageDialog(null, "The name entered is not one of " + trainers[useTrainer].getName() + "'s pocket monsters." + 
                        "\nPlese try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } // end if
        } // end while loop
    } // end examine()
    /**
     * Nicknames a designated pocket monster in a trainer's possession
     * @param useTrainer - the index of the Kanto trainer in use
     * @param trainers - reference to the array of Kanto trainers
     */
    static private void nickname(int useTrainer, Trainer trainers[]) {
        // declare needed variables
        boolean nicknameMenu = true;
        String nicknameChoice = "";
        boolean giveName = true;
        String nickname = "";
        String sure = "";
        while(nicknameMenu) {
            // user selects which pokemon to nickname
            nicknameChoice = (String)JOptionPane.showInputDialog(null, "Which of " + trainers[useTrainer].getName() + 
                    " pocket monsters would you like to nickname?\n(Identify it by name)\n1) " + trainers[useTrainer].getMonster(0).getName() + 
                    "\n2) " + trainers[useTrainer].getMonster(1).getName() + "\n3) " + trainers[useTrainer].getMonster(2).getName() + 
                    "\n4. Back (to previous menu)", 
                    "Pocket Monsters Simulation - Nickname", JOptionPane.QUESTION_MESSAGE, null, null, "back");
            // check user's input
            if(trainers[useTrainer].release(nicknameChoice)) {   // found match
                // loop through trainer's monsters and find index of monster to be examined
                for (int i = 0; i < 3; i++) {
                    if(trainers[useTrainer].getMonster(i).getName().equalsIgnoreCase(nicknameChoice)  && !trainers[useTrainer].getMonster(i).getName().equals("empty")) {
                        giveName = true;
                        // ask for what they would like to nickname the monster
                        while(giveName) {
                            nickname = (String)JOptionPane.showInputDialog(null, "What would you like " + trainers[useTrainer].getName() + 
                                    "'s " + trainers[useTrainer].getMonster(i).getSpecies() + " (AKA " + trainers[useTrainer].getMonster(i).getName() + 
                                    ") new nickname to be?\n(Enter 'Back' to go to the previous menu)", 
                                    "Pocket Monsters Simulation - Nickname", JOptionPane.QUESTION_MESSAGE, null, null, "back");
                            // check user's input
                            if(nickname.equalsIgnoreCase("back")) {
                                giveName = false;   // stop looping // go back to prev menu-select monster to nickname
                            } else {
                                // check if user is sure about the new nickname
                                sure = (String)JOptionPane.showInputDialog(null, "Are you sure?\n('YES' or 'NO')", 
                                        "Pocket Monsters Simulation - Nickname", JOptionPane.QUESTION_MESSAGE, null, null, "NO");
                                // check user's input
                                if(sure.equalsIgnoreCase("yes") || sure.equalsIgnoreCase("y")) {
                                    // set the new nickname on the monster
                                    trainers[useTrainer].getMonster(i).setName(nickname);
                                    // stop looping
                                    giveName = false;
                                } // else nothing // auto no, if not yes // do nothing and let loop back and ask for nickname again
                            } // end if
                        } // end while loop
                    } // end if
                } // end for loop
            } else if(nicknameChoice.equals("4") || nicknameChoice.equalsIgnoreCase("back")) {
                nicknameMenu = false;    // stop looping
            } else {
                // display error msg
                JOptionPane.showMessageDialog(null, "The name entered is not one of " + trainers[useTrainer].getName() + "'s pocket monsters." + 
                        "\nPlese try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } // end if
        } // end while loop
    } // end nickname()
    /**
     * Examines a designated pocket monster in a trainer's possession
     * @param useTrainer - the index of the Kanto trainer in use
     * @param trainers - reference to the array of Kanto trainers
     */
    static private void train(int useTrainer, Trainer trainers[]) {
        // declare needed variables
        boolean trainMenu = true;
        String trainChoice = "";
        boolean abilityMenu = true;
        String abilityChoice = "";
        String targetWeight = "light";
        int rndNum = r.nextInt(2) + 1;  //gen rnd# 1->2
        // determine if this is a 'light' or 'heavy' target
        if(rndNum == 1) {
            targetWeight = "heavy";
        } // else (stays 'light') // end if
        while(trainMenu) {
            // user selects which pokemon to train
            trainChoice = (String)JOptionPane.showInputDialog(null, "In the training area, there is a " + targetWeight + " target set up for practice.\n" + 
                    trainers[useTrainer].getName() + " notices a sign at the entrance and reads it." + 
                    "\n\n'Dear Trainers," + 
                    "\n I have built this special training area so you and you pokemon " + 
                    "\n have a sufficient facility to improve your abilities as a team." +
                    "\n Please feel free to use this Training Area to your hearts' content." + 
                    "\n Now, go and beat up those target dummies!'" + 
                    "\n\nWhich of " + trainers[useTrainer].getName() + 
                    " pocket monsters would you like to train?\n(Identify it by name)\n1) " + trainers[useTrainer].getMonster(0).getName() + 
                    "\n2) " + trainers[useTrainer].getMonster(1).getName() + "\n3) " + trainers[useTrainer].getMonster(2).getName() + 
                    "\n4. Back (to previous menu)", 
                    "Pocket Monsters Simulation - Training Area", JOptionPane.QUESTION_MESSAGE, null, null, "back");
            // check user's input
            if(trainers[useTrainer].release(trainChoice)) {   // found match
                // loop through trainer's monsters and find index of monster to be trained
                for (int i = 0; i < 3; i++) {
                    if(trainers[useTrainer].getMonster(i).getName().equalsIgnoreCase(trainChoice) && !trainers[useTrainer].getMonster(i).getName().equals("empty")) {
                        // display menu of pocket monster's abilities to use
                        abilityMenu = true; //reset
                        while(abilityMenu) {
                            abilityChoice = (String)JOptionPane.showInputDialog(null, "Which ability will " + trainers[useTrainer].getMonster(i).getName() + 
                                    ", " + trainers[useTrainer].getName() + "'s " + trainers[useTrainer].getMonster(i).getType() + 
                                    "-type " + trainers[useTrainer].getMonster(i).getSpecies() + ", use on the target?" + 
                                    "\n1) " + trainers[useTrainer].getMonster(i).getAbilityName(1) + 
                                    "\n2) " + trainers[useTrainer].getMonster(i).getAbilityName(2) + 
                                    "\n3) " + trainers[useTrainer].getMonster(i).getAbilityName(3) + 
                                    "\n4) " + trainers[useTrainer].getMonster(i).getAbilityName(4) + 
                                    "\n5. Back (to previous menu)", 
                                    "Pocket Monsters Simulation - Training Area", JOptionPane.QUESTION_MESSAGE, null, null, "back");
                            // check user's input
                            if(abilityChoice.equals("1") || abilityChoice.equalsIgnoreCase(trainers[useTrainer].getMonster(i).getAbilityName(1))) {
                                trainers[useTrainer].getMonster(i).useAbility(1, targetWeight);
                            } else if(abilityChoice.equals("2") || abilityChoice.equalsIgnoreCase(trainers[useTrainer].getMonster(i).getAbilityName(2))) {
                                trainers[useTrainer].getMonster(i).useAbility(2, targetWeight);
                            } else if(abilityChoice.equals("3") || abilityChoice.equalsIgnoreCase(trainers[useTrainer].getMonster(i).getAbilityName(3))) {
                                trainers[useTrainer].getMonster(i).useAbility(3, targetWeight);
                            } else if(abilityChoice.equals("4") || abilityChoice.equalsIgnoreCase(trainers[useTrainer].getMonster(i).getAbilityName(4))) {
                                trainers[useTrainer].getMonster(i).useAbility(4, targetWeight);
                            } else if(abilityChoice.equals("5") || abilityChoice.equalsIgnoreCase("back")) {
                                abilityMenu = false;    //stop looping
                            } else {
                                // display error msg
                                JOptionPane.showMessageDialog(null, "INVALID INPUT\nThat is not an option, plese try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            } // end if
                        } // end while loop
                    } // end if
                } // end for loop
            } else if(trainChoice.equals("4") || trainChoice.equalsIgnoreCase("back")) {
                trainMenu = false;    // stop looping
            } else {
                // display error msg
                JOptionPane.showMessageDialog(null, "The name entered is not one of " + trainers[useTrainer].getName() + "'s pocket monsters." + 
                        "\nPlese try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } // end if
        } // end while loop
    } // end train()
    /**
     * Checks if a value entered is a double data type
     * @param str - the data entered by the user as a String data type
     * @return true if the data entered is a double data type
     */
    /*private static boolean isNumericDouble(String str) { 
        try { 
            double d = Double.parseDouble(str);
        } catch(NumberFormatException e) { 
            return false;
        } // end try-catch
        return true;  
    } // end isNumericDouble(String)*/
    /**
     * Checks if a value entered is an integer data type
     * @param str - the data entered by the user as a String data type
     * @return true if the data entered is an integer data type
     */
    /*private static boolean isNumericInt(String str) { 
        try { 
            int num = Integer.parseInt(str);
        } catch(NumberFormatException e) { 
            return false;
        } // end try-catch
        return true;  
    } // end isNumericInt(String)*/
} // end PocketMonstersVanBraeckel class
