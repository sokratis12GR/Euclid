package net.darkhax.euclid;

import net.darkhax.euclid.util.NamedRegistry;

public class ResourceHandler {
    
    public static NamedRegistry<String> RESOURCES = new NamedRegistry<>();
    
    public static void initTextures () {
        
        RESOURCES.clear();
        RESOURCES.registerValue("Command_Syntax", "http://i.imgur.com/lqocx7O.png");
    }
}
