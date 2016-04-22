package net.darkhax.euclid.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.asprise.ocr.Ocr;

import net.darkhax.euclid.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

public class CommandReadImage implements Command {
    
    protected static final Logger LOGGER = Logger.getLogger("Euclid");
    
    @Override
    public void proccessCommand (IMessage message, String[] args) {
        
        for (String arg : args) {
            
            if (arg.endsWith(".png") || arg.endsWith(".jpg")) {
                
                try {
                    
                    BufferedImage image = ImageIO.read(new URL(arg));
                    makeGray(image);
                    ImageIO.write(image, "png", new File("OCR.png"));
                    
                    Ocr.setUp();
                    Ocr ocr = new Ocr();
                    ocr.startEngine("eng", Ocr.SPEED_SLOW, "START_PROP_DICT_CUSTOM_DICT_FILE=diction.txt");
                    String s = ocr.recognize(new File[] { new File("OCR.png") }, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
                    ocr.stopEngine();
                    
                    Utilities.sendMessage(message.getChannel(), "I am not sure, but I think it says this: " + System.lineSeparator() + Utilities.makeMultiCodeBlock(s));
                    Utilities.sendMessage(message.getChannel(), "And this is how I see the image.");
                    message.getChannel().sendFile(new File("OCR.png"));
                }
                
                catch (IOException | MissingPermissionsException | HTTP429Exception | DiscordException e) {
                    
                    if (e instanceof IOException && e.toString().contains("Can't get input stream from URL!"))
                        Utilities.sendMessage(message.getChannel(), "It looks like somebody doesn't want me to see that image.");
                        
                    else
                        LOGGER.log(Level.SEVERE, "Exception while writing DataCompound to file.", e);
                }
            }
        }
    }
    
    @Override
    public String getDescription () {
        
        return "Attempts to read text from a linked image. Only accepts .png and .jpg images.";
    }
    
    public static void makeGray (BufferedImage img) {
        
        for (int x = 0; x < img.getWidth(); ++x)
            for (int y = 0; y < img.getHeight(); ++y) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);
                
                int grayLevel = (r + g + b) / 3;
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                img.setRGB(x, y, gray);
            }
    }
    
    @Override
    public String getThoroughDescription () {
        
        return "Attempts to download a linked image and read it for text. Any amount of images can be linked in a single message. Example: !ocr https://www.w3.org/TR/SVGTiny12/examples/textArea01.png";
    }
}