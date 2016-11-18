package net.darkhax.euclid.commands;

import net.darkhax.euclid.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

public class CommandAbout implements Command {
    
    @Override
    public void proccessCommand (IMessage message, String[] args) {
        
        final String about = Utilities.makeMultiCodeBlock(Utilities.makeMultilineMessage(String.format("Java Version: %s", System.getProperty("java.version")), String.format("OS: %s", System.getProperty("os.name")), String.format("Country: %s", System.getProperty("user.country")), "Owner: Darkhax", "Birth Date: 2016-01-26", "Source: https://github.com/darkhax/Euclid"));
        Utilities.sendPrivateMessage(message.getAuthor(), "Hello! I am Euclid. My primary derective is to serve as an interface for some of the projects my author is working on. The following is a list of my specifications." + about);
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