/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room prevRoom;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room ticu, mainExit, elevator, cafeteria, kitchen, icuHallway, patientA, patientB, staffRM, lockers,
                pharmacy, security, waitingRM, central, mainCon, lobby, reception, nurses, surgery, medStorage,
                bloodLab, labHallway, quarantine, maintenance, ambulance, morgue, xRay, freezer, loadDock  ;
      
        // create the rooms
        ticu = new Room("inside the Intensive Care Unit, the silence is deafening. Broken heart monitors flicker dimly, and discarded IV lines trail off your hospital bed like dead snakes.");
        patientA = new Room("inside Patient Room A, an empty bed is stained with dried blood. Privacy curtains hang in shreds, fluttering softly in a cold, unnatural draft.");
        patientB = new Room("inside Patient Room B, a dark figure lies completely still beneath a stained sheet. The faint, raspy sound of shallow breathing breaks the quiet.");
        icuHallway = new Room("inside the ICU Hallway, red emergency lights cast an eerie glow over scattered medical files and knocked-over crash carts.");
        nurses = new Room("inside the Nurses' Station, computer monitors display static error messages. A half-eaten meal sits moldy and abandoned on the desk.");
        medStorage = new Room("inside the Medical Storage Room, glass vials lie shattered across the floor. Metal cabinets have been violently pried open, leaving behind only empty boxes.");
        bloodLab = new Room("inside the Blood Lab, overturned microscopes cover the counters. Dark, sticky liquid pools near the drain in the center of the room.");
        labHallway = new Room("inside the Lab Hallway, the air smells heavily of chemical disinfectant mixed with decay. Dark smear marks trail along the tiled walls.");
        morgue = new Room("inside the Morgue, the temperature drops drastically. Stainless steel body drawers sit pulled open, empty except for bloody handprints on the inside handles.");
        xRay = new Room("inside the X-Ray Room, heavy lead doors dampen outside sounds. Radiographs of infected lungs remain illuminated on wall-mounted light boxes.");
        quarantine = new Room("inside the Quarantine Room, thick yellow biohazard curtains partition the space. Warning signs are plastered over cracked observation windows.");
        surgery = new Room("inside Surgery, giant overhead lights hang ominously over an empty operating table covered in dried brown stains.");
        central = new Room("inside the Central Hub, the wide circular hallway opens up in multiple directions, cluttered with abandoned gurneys and fallen directional signs.");
        elevator = new Room("inside the Elevator Lobby, the elevator doors have been pried apart with a crowbar, revealing a pitch-black drop into the empty shaft below.");
        freezer = new Room("inside the Walk-in Freezer, heavy frost coats every surface. Empty meat hooks hang from the ceiling, swinging gently with a metallic squeak.");
        kitchen = new Room("inside the Kitchen, stainless steel pots litter the floor, and a muffled scratching sound echoes from inside an industrial pantry.");
        cafeteria = new Room("inside the Cafeteria, dining tables are flipped sideways into makeshift barricades, surrounded by rotting food trays and swarms of flies.");
        mainCon = new Room("inside the Main Corridor, long drag marks lead toward the lobby. Spent bullet casings rattle underfoot as you move.");
        staffRM = new Room("inside the Staff Room, couch cushions are ripped open and a half-empty coffee pot sits frozen with dark mold on a stained counter.");
        lockers = new Room("inside the Locker Room, tall metal lockers stand open and looted. Stained scrubs are thrown haphazardly across wooden benches.");
        loadDock = new Room("inside the Loading Dock, a heavy roll-up garage door is jammed halfway shut. Smashed wooden crates lie scattered across the concrete floor.");
        maintenance = new Room("inside the Maintenance Room, exposed pipes hiss with steam, and tool racks hang mostly empty above an oil-stained workbench.");
        lobby = new Room("inside the Main Lobby, pale sunlight filters through dirt-streaked skylights onto overturned couches and abandoned luggage.");
        pharmacy = new Room("inside the Pharmacy, heavy security grates have been bent upward. Prescription pill bottles lie crushed into powder across the floor.");
        security = new Room("inside the Security Room, a bank of surveillance monitors flickers with static, showing static feeds of empty, shadowy halls.");
        ambulance = new Room("inside the Ambulance Bay, an abandoned ambulance sits idling with its back doors wide open, revealing a path toward the outside world.");
        reception = new Room("inside Reception, glass partition windows are shattered across the front desk, and discarded check-in clipboards cover the floor.");
        waitingRM = new Room("inside the Waiting Room, rows of plastic chairs are tipped over, and abandoned children's toys lie crushed among dried bloodstains.");
        mainExit = new Room("inside the Main Exit lobby, heavy glass double doors are chained shut and barricaded with heavy wooden furniture, completely blocking escape.");
        // initialise room exits
        // null,null,null,null
        patientB.setExits("south",patientA);

        patientA.setExits("north",patientB);
        patientA.setExits("west",icuHallway);
        patientA.setExits("south",elevator);


        elevator.setExits("up",patientA);
        elevator.setExits("down",staffRM);
        elevator.setExits("door",central);

        staffRM.setExits("east",lockers);
        staffRM.setExits("north",elevator);
        staffRM.setExits("south",pharmacy);
        staffRM.setExits("west",mainCon);

        lockers.setExits("west",staffRM);

        pharmacy.setExits("east",security);
        pharmacy.setExits("south",waitingRM);
        pharmacy.setExits("west",lobby);
        pharmacy.setExits("north",staffRM);

        security.setExits("west",pharmacy);

        waitingRM.setExits("north",pharmacy);
        waitingRM.setExits("west",reception);

        ticu.setExits("south",icuHallway);

        icuHallway.setExits("north",ticu);
        icuHallway.setExits("south",central);
        icuHallway.setExits("west",nurses);
        icuHallway.setExits("east",patientA);

        central.setExits("north",icuHallway);
        central.setExits("west",surgery);
        central.setExits("down",mainCon);
        central.setExits("elevator",elevator);

        mainCon.setExits("up",central);
        mainCon.setExits("east",staffRM);
        mainCon.setExits("south",lobby);
        mainCon.setExits("west",cafeteria);

        lobby.setExits("north",mainCon);
        lobby.setExits("east",pharmacy);
        lobby.setExits("south",reception);

        reception.setExits("north",lobby);
        reception.setExits("east",waitingRM);
        reception.setExits("south",mainExit);

        mainExit.setExits("north",reception);

        medStorage.setExits("door", nurses);

        nurses.setExits("door",medStorage);
        nurses.setExits("east",icuHallway);
        nurses.setExits("west",labHallway);

        surgery.setExits("east",central);
        surgery.setExits("west",quarantine);

        cafeteria.setExits("east",mainCon);
        cafeteria.setExits("west",kitchen);

        bloodLab.setExits("south",labHallway);

        labHallway.setExits("south",quarantine);
        labHallway.setExits("east",nurses);
        labHallway.setExits("west",morgue);
        labHallway.setExits("north",bloodLab);

        quarantine.setExits("north",labHallway);
        quarantine.setExits("south",kitchen);
        quarantine.setExits("east",surgery);

        kitchen.setExits("north",quarantine);
        kitchen.setExits("down",maintenance);
        kitchen.setExits("east",cafeteria);

        maintenance.setExits("up",kitchen);
        maintenance.setExits("ambulance",ambulance);
        maintenance.setExits("west",loadDock);

        morgue.setExits("south", xRay);
        morgue.setExits("east",labHallway);

        xRay.setExits("north",morgue);

        freezer.setExits("door",loadDock);

        loadDock.setExits("door",freezer);
        loadDock.setExits("east",maintenance);




        currentRoom = ticu;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")){
            look();
        }
        else if (commandWord.equals("take")){
            System.out.println("You pick up " + command.getSecondWord());
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You wake up from a coma in a hospital bed. You are lost. You are alone. You wander");
        System.out.println("around you hospital room");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showAllCommand();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom =currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            prevRoom = currentRoom;
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDesciption());
    }
    private void look(){
        System.out.println(currentRoom.getLongDesciption());
    }
}
