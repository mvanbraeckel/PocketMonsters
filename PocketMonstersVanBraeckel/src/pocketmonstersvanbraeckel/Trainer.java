/*
 * Mitchell Van Braeckel
 * 06/10/2016
 * Pocket Monsters Simlulator -- Trainer class
    -> https://docs.google.com/document/d/1sziLb2DVwhJ38OcBChE1ePYQRIoMUjMZjVwP4eY6xBs/edit
    • must have a name and an age to exist
    • has 3 Pocket Monsters (i.e. three slots to hold Pocket Monsters)
    • can "catch" a wild Pocket Monster (accepted as a parameter)
        •-> this action is either successful or it is not (returns boolean) 
            •-> there is a one in four chance of random failure
            •-> there must be an "empty" Pocket Monster slot (variable) for this action to succeed
        •-> if the catch is successful, the caught Pocket Monster can be assigned to any "empty" slot
    • can "release" one of it's Pocket Monsters (identified by name)
        •-> this action is either successful or it is not (returns boolean)
            •-> the action is unsuccessful if the Trainer does not have a Pocket Monster that matches the name given
                ***(so the release() method itself just returns a boolean=true if its successful, but doesn't actually release it)
                   (THEREFORE, boolean=true refers to the success when the name entered by the user matches the name of one of the trainer's pocket monsters)***
        •-> name should be accepted as a parameter
        •-> the slot where the Pocket Monster was residing becomes "empty" after the Pocket Monster has been released
                ***(since the above '***' has been inferred, this action occurs outside of the release() method)***
 */

package pocketmonstersvanbraeckel;

// imports
import java.util.Random;

public class Trainer {
        // declare imports
    Random r = new Random();
    
        // ATTRIBUTES
    private String name;
    private int age;
    private Monster slot[];
        // CONSTRCUTORS
    /**
     * Primary Constructor -- initializes a new trainer w/ at least a name and age
     * @param name - the name of the trainer
     * @param age - the age of the trainer
     */
    public Trainer(String name, int age) {
        this.name = name;
        this.age = age;
        slot = new Monster[3];
        for (int i = 0; i < 3; i++) {       // instantiates a default empty monster for each slot
            slot[i] = new Monster("empty");
        } // end for loop
    }
    /**
     * Secondary Constructor -- (chained) creates a new trainer w/ a name, age, 3 pocket monsters
     * -> if trainer has less than 3 monsters, will show as "empty"
     * @param name - the name of the trainer
     * @param age - the age of the trainer
     * @param inSlot - the list of the trainer's 3 pocket monsters
     */
    public Trainer(String name, int age, Monster[] inSlot) {
        this(name, age);
        System.arraycopy(inSlot, 0, slot, 0, 3);
    }
        // BEHAVIOURS
    /**
     * mutator method - Sets all of the trainer's monsters
     * @param trainerIndex - the index of the trainer that is having its pocket monster's abilities matched
     * @param inSlot - a list of entered monsters to be the trainer's
     */
    public void setAllMonsters(int trainerIndex, Monster[][] inSlot) {
        System.arraycopy(inSlot[trainerIndex], 0, slot, 0, 3);
    }
    /**
     * mutator method -- sets a trainer's monster slot to a new monster
     * @param slotIndex - the array index of the trainer's monster slot to be changed
     * @param inMon - the trainer's new monster
     */
    public void setMonster(int slotIndex, Monster inMon) {
        slot[slotIndex] = inMon;
    }
    /**
     * accessor method - retrieves one of the trainer's specific pocket monsters
     * @param slotIndex - the array index of the trainer's monster slot to be accessed
     * @return the trainer's designated pocket monster
     */
    public Monster getMonster(int slotIndex) {
        return slot[slotIndex];
    }
    /**
     * mutator method -- empties a trainer's monster slot
     * @param slotIndex - the array index of the trainer's monster slot to be changed to be emptied
     */
    public void emptyMonster(int slotIndex) {
        slot[slotIndex] = new Monster("empty");     // overwrites old monster with a blank new one
    }
    /**
     * mutator method -- sets the trainer's name
     * @param name - the name of the trainer
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * accessor method -- retrieves the trainer's name
     * @return the name of the trainer
     */
    public String getName() {
        return name;
    }
    /**
     * mutator method -- sets the trainer's age
     * @param age - the age of the trainer
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * accessor method -- retrieves the trainer's age
     * @return the age of the trainer
     */
    public int getAge() {
        return age;
    }
    /**
     * action method - determines whether a wild pocket monster is caught
     * @return true if the catch attempt is successful
     */
    public boolean catchMonster() {
        boolean emptySlot = false;
        boolean successfulCatch = false;
        // check if there is an open monster slot in the trainer's bag
        for (int i = 0; i < 3; i++) {
            if(slot[i].getSpecies().equalsIgnoreCase("empty")){
                emptySlot = true;
            } // else no empty slots for new monster (stays false) // end if
        } // end for loop
        // if there's an open monster slot, // determine if the catch attempt is successful (75% chance)
        if(emptySlot) {
            double rndNum = r.nextInt(4) * 1;   // gen random # from 1->4
            if(rndNum > 1) {    // successful catch
                successfulCatch = true;
            } // else // =1: failed catch (stays false) // end if
        } // end if
        // return if the catch attempt is successful
        return successfulCatch;
    }
    /**
     * action method -- determines if the release of one of the trainer's pocket monsters is successful
     * @param inName - the name of the pocket monster to be released
     * @return true if the release is successful
     */
    public boolean release(String inName) {
        boolean match = false;
        // check if name entered matches a held monster's name
        for (int i = 0; i < 3; i++) {
            if(inName.equalsIgnoreCase(slot[i].getName())) {
                match = true;
            } // end if
        } // end for loop
        // returns true or false depending on whether a match was found
        return match;
    }
    /**
     * get all the info about a trainer
     * @return a string representation of the trainer
     */
    @Override
    public String toString() {
        return    "Name:      " + name + 
                "\nAge:       " + age + 
                "\nMonster 1: " + slot[0].getName() + 
                "\nMonster 2: " + slot[1].getName() + 
                "\nMonster 3: " + slot[2].getName();
    }
} // end Trainer class
