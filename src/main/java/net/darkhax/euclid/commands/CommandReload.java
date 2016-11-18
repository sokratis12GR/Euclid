package net.darkhax.euclid.commands;

import net.darkhax.euclid.Euclid;
import net.darkhax.euclid.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

public class CommandReload implements Command {
    
    @Override
    public void proccessCommand (IMessage message, String[] params) {
        
        Utilities.sendMessage(message.getChannel(), "Reloading handlers and resource!");
        Euclid.initHandlers();
        Utilities.sendMessage(message.getChannel(), "Reload complete. That tickled ;)");
    }
    
    @Override
    public String getDescription () {
        
        return "Reloads all of the handlers and resources for the bot, including commands!";
    }
    
    @Override
    public String getThoroughDescription () {
        
        return this.getDescription();
    }
}
