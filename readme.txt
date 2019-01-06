# PocketMonsters
Program made for Gr12 high school programming course ICS4U - simulates data for 3 trainers that each have max 3 pocket monsters, and are able to conduct various actions

*Do not use code from this or copy any aspects without explicit permission from creator*

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

*Assignment Context*
After falling asleep in ICS4U one day, you wake up in a place that is definitely not C206.
You're approached by a friendly-looking but frazzled man who introduces himself as a Mr. Tajiri.
He explains that you've landed in the magical region of Kanto, and just in time too, Mr. Tajiri needs your help!
Kanto is full of people who are called 'Trainers'.  Trainers collect creatures known as 'Pocket Monsters'.
Mr. Tajiri is in charge of Kanto's 'Department of Collection', which tracks who is registered as a Trainer,
and which Pocket Monsters those trainers have collected.
He had someone create a program which allows the user to examine Trainer data loaded from a file.
The program also allows the user to run a simulation, where they can 'examine' a Trainer's Pocket Monsters,
make Trainers 'release' the Pocket Monsters in their care, or even take a 'walk' in the grass,
where they have a chance to 'catch' a wild Pocket Monster!
Mr. Tajiri needs you to test this new simulation program before he gives it to all the young new Trainers
that will be coming to the Lab tomorrow afternoon to receive their first Pocket Monster
and start their jounrney across the Kanto region.
(He won't take no for an answer)
Pocket Monsters Simulation - Welcome to Kanto!
 
