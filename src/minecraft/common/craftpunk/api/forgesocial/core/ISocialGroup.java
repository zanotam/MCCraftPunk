package common.craftpunk.api.forgesocial.core;

public interface ISocialGroup 
{
	public boolean addMember(String name);

	public boolean removeMember(String name);
	
	public boolean hasMember(String name);
	
	
	
	
	public boolean addLeader(String name);
	
	public boolean removeLeader(String name);
	
	public boolean hasLeader(String name);

}
