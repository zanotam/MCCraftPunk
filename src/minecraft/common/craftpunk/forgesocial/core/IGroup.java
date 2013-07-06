package common.craftpunk.forgesocial.core;

import java.util.ArrayList;

public interface IGroup 
{
	public ArrayList<String> getMembers();
	
	public boolean addMember(String name);
	
	public boolean removeMember(String name);
}
