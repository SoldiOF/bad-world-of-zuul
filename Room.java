import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{

    private String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {

        this.description = description;
        exits = new HashMap<String, Room>();
    }
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there)..
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction,neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction){
        return exits.get(direction);
    }
    public String getExitString(){
        ArrayList<String> exitsString = new ArrayList<>();
        if( exits.containsKey("north")){
            exitsString.add("North");
        }
        if(exits.containsKey("east")){
            exitsString.add("East");
        }
        if(exits.containsKey("south")){
            exitsString.add("South");
        }
        if(exits.containsKey("west")){
            exitsString.add("West");
        }
        return exitsString.toString().replace("[","").replace("]","");
    }
    public Room getPrevRoom(){
        return null;
    }
    public String getLongDesciption(){
        return "You are " + description + ".\n" + "Exits: " + getExitString() + "\n";
    }

}
