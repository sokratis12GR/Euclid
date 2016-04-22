package net.darkhax.euclid.commands;

import net.darkhax.euclid.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

public class CommandAbout implements Command {
    
    @Override
    public void proccessCommand (IMessage message, String[] args) {
        
        Utilities.sendPrivateMessage(message.getAuthor(), "Hello! I am Euclid. I am a discord chat bot written by Darkhax. My primary directive is to provide an interface to some of the random projects that Darkhax is working on." + Utilities.makeMultiCodeBlock("Java Version: " + System.getProperty("java.version") + SEPERATOR + "OS Name: " + System.getProperty("os.name") + SEPERATOR + "Country: " + System.getProperty("user.country") + SEPERATOR + "Author: Darkhax" + SEPERATOR + "Birth Date: 2016-01-26"));
    }
    
    @Override
    public String getDescription () {
        
        return "Shares a list of information about the bot, including system specs and authorship info.";
    }
    
    @Override
    public String getThoroughDescription () {
        
        return "Provides information about the bot, and the hardware it is running on. There are no additional parameters.";
    }
}