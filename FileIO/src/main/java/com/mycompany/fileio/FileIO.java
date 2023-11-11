/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

/**
 *
 * @author Sven
 */
public class FileIO {
    /*
    This method reads the assigned text file and stores the contents of it in a string
    */
    public static String readTextFile(String filePath)
    {
        JFrame frame = new JFrame("Error box");
        String str = null;
        String line = null;
        Path path = Paths.get("bookings.txt");
        try {
            BufferedReader reader = null;
            reader = Files.newBufferedReader(path);
            while ((line=reader.readLine()) != null) {
                str = Files.readString(path);
            }
            reader.close();
        }
        catch (IOException io) {
            JOptionPane.showMessageDialog(frame, "File cannot be found");
            //System.exit(0);
        }
        
        return str;
    }
    /*
    This methods adds text to the text file
    */
    public static void appendToTextFile(String filePath, String toWrite)
    {
        JFrame frame = new JFrame("Error box");
        toWrite = toWrite.replace("\n", "").replace("\r\n","");
        Path path = Paths.get(filePath);
        
        try {
            BufferedWriter writer = null;
            if (Files.exists(path))
                writer=Files.newBufferedWriter(path, StandardOpenOption.APPEND);//if it exists, append to it
                writer.write(toWrite);
                writer.newLine();
            
            writer.close();
        }
        catch (IOException io)
        {
            JOptionPane.showMessageDialog(frame, "File cannot be found");
        }
    }

    public static void main(String[] args) {
        //readTextFile("bookings.txt");
    }
}
