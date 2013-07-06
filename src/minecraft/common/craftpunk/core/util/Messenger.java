package common.craftpunk.core.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

public class Messenger {
	
	public static void sendMessage(EntityPlayer player, String message)
	{
		player.sendChatToPlayer(new ChatMessageComponent().func_111079_a(message));
	}

}
