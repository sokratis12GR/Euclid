package net.darkhax.euclid.commands;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

public class CommandBarCode implements Command {
    
    @Override
    public void proccessCommand (IMessage message, String[] params) {
        
        String data = "";
        Color background = Color.white;
        Color foreground = Color.black;
        
        for (String arg : Arrays.copyOfRange(params, 2, params.length)) {
            
            if (arg.startsWith("background=")) {
                
                final String text = arg.substring(11);
                
                if (StringUtils.isNumeric(text))
                    background = new Color(Integer.parseInt(text));
            }
            
            else if (arg.startsWith("foreground=")) {
                
                final String text = arg.substring(11);
                
                if (StringUtils.isNumeric(text))
                    foreground = new Color(Integer.parseInt(text));
            }
            
            else
                data += (" " + arg);
        }
        
        if (params[1].equalsIgnoreCase("QR"))
            generate(message.getChannel(), data, 250, "qr.png", "png", background, foreground);
    }
    
    @Override
    public String getDescription () {
        
        return "Takes a message and writes it into a bar code. Currently supports only QR codes.";
    }
    
    private void generate (IChannel channel, String message, int size, String fileName, String fileType, Color background, Color foreground) {
        
        try {
            
            final File file = new File(fileName);
            
            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hintMap.put(EncodeHintType.MARGIN, 1);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            
            QRCodeWriter qrWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrWriter.encode(message, BarcodeFormat.QR_CODE, size, size, hintMap);
            int matrixWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(background);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            graphics.setColor(foreground);
            
            for (int pixelX = 0; pixelX < matrixWidth; pixelX++)
                for (int pixelY = 0; pixelY < matrixWidth; pixelY++)
                    if (byteMatrix.get(pixelX, pixelY))
                        graphics.fillRect(pixelX, pixelY, 1, 1);
                        
            ImageIO.write(image, fileType, file);
            
            if (file.exists())
                channel.sendFile(file);
        }
        
        catch (WriterException | IOException | MissingPermissionsException | HTTP429Exception | DiscordException exception) {
            
            exception.printStackTrace();
        }
    }
    
    @Override
    public String getThoroughDescription () {
        
        return "Generates a barcode which contains a message. There are a few special parameters which can be used to change the color of the generated code. forgeground=colorcode will set the code color, while background=colorcode will set the background color code. Example: !barcode qr background=0 background=65280 This QR Code is Black and Green";
    }
}
