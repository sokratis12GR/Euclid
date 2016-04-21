package net.darkhax.bot.commands;

import sx.blah.discord.handle.obj.IMessage;

public class Command {
    
    /**
     * Checks if a command is valid. If not, it will not be executed.
     * 
     * @param message The message which contains all the command information.
     * @return boolean Whether or not the command should execute.
     */
    public boolean isValidUsage (IMessage message) {
        
        return true;
    }
    
    /**
     * Processes a command once it has been confirmed to be valid. This is where a command is
     * executed.
     * 
     * @param message The message which contains all of the command information.
     */
    public void proccessCommand (IMessage message, String[] params) {
    
    }
    
    /**
     * Provides a description of the command and how to use it.
     * 
     * @return String A description of the command being registered.
     */
    public String getCommandDescription () {
        
        return "If you are reading this, someone didn't code their command right!";
    }
}
