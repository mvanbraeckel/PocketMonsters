/*
 * Mitchell Van Braeckel
 * 05/10/2016
 * Pocket Monsters Simlulator -- Pocket Monster class
    -> https://docs.google.com/document/d/1sziLb2DVwhJ38OcBChE1ePYQRIoMUjMZjVwP4eY6xBs/edit
    • must have a species to exist
    • has a name*, type, level (lv), experience value (exp), hit points (hp), weight (kg), height (m), and an array of 4 abilities (Strings)
    • can activate an ability (specified by an integer 1 through 4)
        •-> what that ability actually does is up to you (use it during training)
 */

package pocketmonstersvanbraeckel;

// imports
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import java.util.Random;

public class Monster {
        // declare imports
    DecimalFormat wf = new DecimalFormat("#,###,##0");
    DecimalFormat df = new DecimalFormat("#,###,##0.0#");
    Random r = new Random();
    
        // ATTRIBUTES
    private String species, name, type;
    private int lv;
    private double exp, hp, weight, height;
    private String ability[][];     
    
        // CONSTRUCTORS
    /**
     * Primary Constructor -- initializes a new pocket monster w/ at least a species
     * @param species - the species of pocket monster
     */
    public Monster(String species) {
        this.species = species;
        name = species;         //same as species unless otherwise nicknamed
        type = "not assigned";
        lv = 0;
        exp = 0;
        hp = 0;
        weight = 0;
        height = 0;
        ability = new String[5][2];
        for (int i = 0; i < 5; i++) {   // instantiates the ability array
            // checks if first row of array
            if(i == 0) {    // instantiates type
                ability[i][0] = "not assigned";
                ability[i][1] = ability[i][0];
            } else {    // instantiates 4 default "unknown" abilites
                ability[i][0] = "unknown move";
                ability[i][1] = "no description";
            } // end if
        } // end for loop
    }
    /**
     * Secondary Constructor -- (chained) creates a pocket monster w/ full attributes (except name)(except abilities)
     * @param species - the species of pocket monster
     * @param type - the type (elemental attribute) of the pocket monster
     * @param height - the height, in meters, of the monster
     * @param weight - the weight, in kilograms, of the monster
     * @param lv - the level of the pocket monster
     * @param exp - the total amount of experience points that the monster has
     * @param hp - the amount of health points the monster has
     */
    public Monster(String species, String type, double height, double weight, int lv, double exp, double hp) {
        this(species);
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.lv = lv;
        this.exp = exp;
        this.hp = hp;
    }
    /**
     * Tertiary Constructor -- (chained) creates a pocket monster w/ full attributes (except abilities)
     * @param species - the species of pocket monster
     * @param type - the type (elemental attribute) of the pocket monster
     * @param height - the height, in meters, of the monster
     * @param weight - the weight, in kilograms, of the monster
     * @param lv - the level of the pocket monster
     * @param exp - the total amount of experience points that the monster has
     * @param hp - the amount of health points the monster has
     * @param name - the nickname of the monster
     */
    public Monster(String species, String type, double height, double weight, int lv, double exp, double hp, String name) {
        this(species, type, height, weight, lv, exp, hp);
        this.name = name;
    }
    
        // BEHAVIOURS
    /**
     * accessor method -- retrieves the species of a pocket monster
     * @return the species of a pocket monster
     */
    public String getSpecies() {
        return species;
    }
    /**
     * mutator method -- sets the name of a pocket monster
     * @param name - the new name of the pocket monster
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * accessor method -- retrieves the name of a pocket monster
     * @return the name of a pocket monster
     */
    public String getName() {
        return name;
    }
    /**
     * mutator method -- sets the type of a pocket monster
     * @param type - the new type of the pocket monster
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * accessor method -- retrieves the type of a pocket monster
     * @return the type of the pocket monster
     */
    public String getType() {
        return type;
    }
    // =============================== set stats ===============================
    /**
     * mutator method -- sets the level of a pocket monster
     * @param lv - the new level of the pocket monster
     */
    public void setLV(int lv) {
        this.lv = lv;
    }
    /**
     * mutator method -- sets the amount of experience that a pocket monster has
     * @param exp - the new amount of experience of the pocket monster
     */
    public void setEXP(double exp) {
        this.exp = exp;
    }
    /**
     * mutator method -- sets the amount of hit points that a pocket monster has
     * @param hp - the new amount of hit points of the pocket monster
     */
    public void setHP(double hp) {
        this.hp = hp;
    }
    /**
     * mutator method -- sets the height of a pocket monster
     * @param h - the new height of the pocket monster
     */
    public void setHeight(double h) {
        this.height = h;
    }
    /**
     * mutator method -- sets the weight of a pocket monster
     * @param wt - the new weight of the pocket monster
     */
    public void setWeight(double wt) {
        this.weight = wt;
    }
    // =============================== get stats ===============================
    /**
     * accessor method -- retrieves the level of a pocket monster
     * @return the level of the pocket monster
     */
    public int getLV() {
        return lv;
    }
    /**
     * accessor method -- retrieves the amount of experience that a pocket monster has
     * @return the amount of experience that the pocket monster has
     */
    public double getEXP() {
        return exp;
    }
    /**
     * accessor method -- retrieves the amount of hit points that a pocket monster has
     * @return the amount of hit points that the pocket monster has
     */
    public double getHP() {
        return hp;
    }
    /**
     * accessor method -- retrieves the height of a pocket monster
     * @return the height of the pocket monster
     */
    public double getHeight() {
        return height;
    }
    /**
     * accessor method -- retrieves the weight of a pocket monster
     * @return the weight of the pocket monster
     */
    public double getWeight() {
        return weight;
    }
    // ============================ abilities ==================================
    /**
     * mutator method -- sets all of the pocket monster's abilities based on type using the full list of abilities
     * @param typeIndex - the index of the element in the Monster object array that contains the pocket monster's type
     * @param ability - reference to the 3D array of a list of pocket monster abilities and their descriptions based on type
     */
    public void setAbilities(int typeIndex, String[][][] ability) {
        // loop through and copy the abilities and descriptions from the list of abilities based on type
        for (int i = 0; i < 5; i++) {
            System.arraycopy(ability[typeIndex][i], 0, this.ability[i], 0, 2); // end for loop-2nd level j
        } // end for loop-1st level i
    }
    /**
     * accessor method -- retrieves the name of one of the pocket monster's abilities
     * @param abilityNum - specifies which of the pocket monster's 4 abilities is being retrieved
     * @return the name of the ability
     */
    public String getAbilityName(int abilityNum) {
        return ability[abilityNum][0];
    }
    /**
     * accessor method -- retrieves the description of one of the pocket monster's abilities
     * @param abilityNum - specifies which of the pocket monster's 4 abilities is being retrieved
     * @return the description of the ability
     */
    public String getAbilityDescription(int abilityNum) {
        return ability[abilityNum][1];
    }
    /**
     * action method -- uses a pocket monster's ability and displays info about what happened to the target
     * @param abilityNum - refers to which of the monster's 4 abilities is being used
     * @param targetWeight - the weight of the target (either 'light' or 'heavy')
     */
    public void useAbility(int abilityNum, String targetWeight) {
        // declare needed variables
        int rndNum = r.nextInt(16) + 1;
        boolean poisoned = false;
        // display use move and move description
        JOptionPane.showMessageDialog(null, name + " used " + ability[abilityNum][0] + "!", 
                "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, name + "" + ability[abilityNum][1], 
                "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
        // calc chance of status condition occuring
        if(type.equals("Water") && abilityNum == 2) {   // 30% burn chance
            rndNum = r.nextInt(10) + 1;
            // Water: Scald
            if(rndNum <= 3) {   //display burn msg
                JOptionPane.showMessageDialog(null, "The target was burned!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if(type.equals("Fire") && (abilityNum == 2 || abilityNum == 3)) {    // 10% burn chance
            rndNum = r.nextInt(10) + 1;
            // Fire: Flamethrower, Flare Blitz
            if(rndNum == 1) {   //display burn msg
                JOptionPane.showMessageDialog(null, "The target was burned!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if((type.equals("Electric") && abilityNum == 2) || (type.equals("Normal") && abilityNum == 1)) { // 30% stun chance
            rndNum = r.nextInt(10) + 1;
            // Electric: Thunder; Normal: Body Slam
            if(rndNum <= 3) {   //display stun msg
                JOptionPane.showMessageDialog(null, "The target was paralyzed!\nIt can't move!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if(type.equals("Electric") && abilityNum > 2) { // 10% stun chance
            rndNum = r.nextInt(10) + 1;
            // Electric: Thunder Punch, Thunderbolt
            if(rndNum == 1) {   //display stun msg
                JOptionPane.showMessageDialog(null, "The target was paralyzed!\nIt can't move!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if(type.equals("Poison") && abilityNum == 4) { // x2 damage if already poisoned
            // checks if target is already poisoned
            if(poisoned) {  // inflict double damage
                JOptionPane.showMessageDialog(null, "Venoshock inflicts double damage because the target was already poisoned!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if(type.equals("Poison") && abilityNum == 2) { // 30% poison chance
            rndNum = r.nextInt(10) + 1;
            // Poison: Sludge Bomb
            if(rndNum <= 3) {   //display poison msg
                JOptionPane.showMessageDialog(null, "The target was poisoned!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if(type.equals("Ice") && (abilityNum == 2 || abilityNum == 3)) { // 10% freeze chance
            rndNum = r.nextInt(10) + 1;
            // Ice: Blizzard, Ice Beam
            if(rndNum == 1) {   //display freeze msg
                JOptionPane.showMessageDialog(null, "The target was frozen solid!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if(type.equals("Ice") && abilityNum == 4) { // 30% flinch chance
            rndNum = r.nextInt(10) + 1;
            // Ice: Icicle Crash
            if(rndNum <= 3) {   //display flinch msg
                JOptionPane.showMessageDialog(null, "The target flinched!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if((type.equals("Psychic") && abilityNum == 4) || (type.equals("Dragon") && abilityNum == 3)) { // 20% flinch chance
            rndNum = r.nextInt(10) + 1;
            // Psychic: Zen Headbutt; Dragon: Dragon Rush
            if(rndNum <= 2) {   //display flinch msg
                JOptionPane.showMessageDialog(null, "The target flinched!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if((type.equals("Earth") && abilityNum == 3) || (type.equals("Psychic") && abilityNum == 2)) { // 10% chance sp.def lowered
            rndNum = r.nextInt(10) + 1;
            // Earth: Earth Power; Psychic: Psychic(ability)
            if(rndNum == 1) {   //display lowered sp.def msg
                JOptionPane.showMessageDialog(null, "The target's Sp. Defense fell!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if(type.equals("Fairy") && abilityNum == 3) { // 30% chance sp.def lowered
            rndNum = r.nextInt(10) + 1;
            // Fairy: Moonblast
            if(rndNum >= 3) {   //display lowered sp.atk msg
                JOptionPane.showMessageDialog(null, "The target's Sp. Attack fell!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else if(type.equals("Fighting") && abilityNum == 4) { // inflict x2 damage if heavy
            if(targetWeight.equals("heavy")) {   //display lowered sp.atk msg
                JOptionPane.showMessageDialog(null, "Low Kick inflicts double damage because the target is heavy!", 
                        "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } // no else // end if
        
        // calc if attack is a critical hit
        if(type.equals("Fighting") && abilityNum == 2) {    // x2 crit chance
            if(rndNum >= 2) {   // inflict critical damage
                JOptionPane.showMessageDialog(null, "It's a critical hit!", 
                            "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } else {
            if(rndNum == 1) {   // inflict critical damage
                JOptionPane.showMessageDialog(null, "It's a critical hit!", 
                            "Pocket Monsters Simulation - Training Area", JOptionPane.INFORMATION_MESSAGE);
            } // else nothing // end if
        } // end if
    }
    // =========================================================================
    /**
     * get all the info about a pocket monster
     * @return a string representation of the pocket monster
     */
    @Override
    public String toString() {
        return    "Species:    " + species + 
                "\nName:       " + name + 
                "\nType:       " + type + 
                "\nHeight:     " + df.format(height) + "     Weight: " + df.format(weight) +
                "\nLevel:      " + lv + 
                "\nHit Points: " + wf.format(hp) + 
                "\nExperience: " + wf.format(exp) +
                "\nAbilites:   " + ability[1][0] + ", " + ability[2][0] + ", " + ability[3][0] + ", " + ability[4][0];
    }
} // end class Monster
