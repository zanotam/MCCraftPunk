package common.craftpunk.forgesocial.core;

import java.util.ArrayList;
import java.util.Collection;

public interface ISocialGroup {
	
	/**
	 * 
	 * @return The name of the group.
	 */
	public String getName();
	
	
	
	/**
	 * 
	 * @return An ArrayList with all members in it.
	 */
	public ArrayList<String> getMembers();

	/**
	 * 
	 * @param name The name of the player to add.
	 * @return Whether the player was added.
	 */
	public boolean addMember(String name);
	
	/**
	 * 
	 * @param name The name of the player to remove.
	 * @return Whether the player was removed.
	 */
	public boolean removeMember(String name);
	
	
	
	/**
	 * 
	 * @return an ArrayList with all leaders in it.
	 */
	public ArrayList<String> getLeaders();
	
	/**
	 * 
	 * @param name The name of the player to promote to leader.
	 * @return Whether the player was promoted.
	 */
	public boolean addLeader(String name);
	
	/**
	 * 
	 * @param name The name of the player to demote from leader.
	 * @return Whether the player was demoted.
	 */
	public boolean removeLeader(String name);
	
}
