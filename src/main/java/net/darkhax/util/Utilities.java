package net.darkhax.util;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

public class Utilities {
    
    /**
     * Creates a ping message for a user based upon their user ID.
     * 
     * @param userID The user ID of the user to generate a ping message for.
     * @return String A short string which will ping the specified user when sent into the
     *         chat.
     */
    public static String getPingMessage (String userID) {
        
        return "<@" + userID + ">";
    }
    
    /**
     * Makes a String message italicized. This only applies to chat.
     * 
     * @param message The message to format.
     * @return String The message with the formatting codes applied.
     */
    public static String makeItalic (String message) {
        
        return "*" + message + "*";
    }
    
    /**
     * Makes a String message bold. This only applies to chat.
     * 
     * @param message The message to format.
     * @return String The message with the bold formatting codes applied.
     */
    public static String makeBold (String message) {
        
        return "**" + message + "**";
    }
    
    /**
     * Makes a String message scratched out. This only applies to chat.
     * 
     * @param message The message to format.
     * @return String The message with the scratched out formatting codes applied.
     */
    public static String makeScratched (String message) {
        
        return "~~" + message + "~~";
    }
    
    /**
     * Makes a String message underlined. This only applies to chat.
     * 
     * @param message The message to format.
     * @return String The message with the underlined formatting codes applied.
     */
    public static String makeUnderlined (String message) {
        
        return "__" + message + "__";
    }
    
    /**
     * Makes a String message appear in a code block. This only applies to chat.
     * 
     * @param message The message to format.
     * @return String The message with the code block format codes applied.
     */
    public static String makeCodeBlock (String message) {
        
        return "`" + message + "`";
    }
    
    /**
     * Makes a String message appear in a multi-lined code block. This only applies to chat.
     * 
     * @param message The message to format.
     * @return String The message with the multi-lined code block format codes applied.
     */
    public static String makeMultiCodeBlock (String message) {
        
        return "```" + message + "```";
    }
    
    /**
     * Attempts to send a private message to a user. If a private message channel does not
     * already exist, it will be created.
     * 
     * @param instance An instance of your IDiscordClient.
     * @param user The user to send the private message to.
     * @param message The message to send to the user.
     */
    public static void sendPrivateMessage (IDiscordClient instance, IUser user, String message) {
        
        try {
            
            sendMessage(instance.getOrCreatePMChannel(user), message);
        }
        
        catch (final Exception e) {
            
            e.printStackTrace();
        }
    }
    
    /**
     * Sends a message into the chat. This version of the method will handle exceptions for
     * you.
     * 
     * @param channel The channel to send to message to.
     * @param message The message to send to the channel.
     */
    public static void sendMessage (IChannel channel, String message) {
        
        try {
            
            channel.sendMessage(message);
            Thread.sleep(1000);
        }
        
        catch (final MissingPermissionsException e) {
            
            e.printStackTrace();
        }
        
        catch (final HTTP429Exception e) {
            
            e.printStackTrace();
        }
        
        catch (final InterruptedException e) {
            
            e.printStackTrace();
        }
        catch (final DiscordException e) {
            
            e.printStackTrace();
        }
    }
    
    public static void getUserFromName () {
    
    }
}