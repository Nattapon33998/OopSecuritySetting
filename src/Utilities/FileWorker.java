package Utilities;

import sample.Location;
import sample.Setting;

import java.io.*;
import java.util.ArrayList;


public class FileWorker {
    public static void writeLocationToFile(double x, double y, String name) throws IOException {
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("binary.dat"));
            if(dataInputStream.available() >= 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("binary.dat", true));
                dataOutputStream.writeUTF(name);
                dataOutputStream.writeDouble(x);
                dataOutputStream.writeDouble(y);

                dataOutputStream.close();
            }
            dataInputStream.close();
        } 
        catch (IOException e) {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("binary.dat"));
            dataOutputStream.writeUTF(name);
            dataOutputStream.writeDouble(x);
            dataOutputStream.writeDouble(y);

            dataOutputStream.close();
        }
    }

    public static ArrayList<Location> readFileToLocations() throws IOException {
        ArrayList<Location> returnArrayList = new ArrayList<>();
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("binary.dat"));
        while(dataInputStream.available() != 0) {
            String name = dataInputStream.readUTF();
            double x = dataInputStream.readDouble();
            double y = dataInputStream.readDouble();

            returnArrayList.add(new Location(x, y, name));
        }
        dataInputStream.close();

        return returnArrayList;
    }
   
    public static void deleteLocationByIndex(int delete_index) throws IOException{
        ArrayList<Location> firstReadLocation = readFileToLocations();
        //System.out.println(firstReadLocation);
        firstReadLocation.remove(delete_index);

        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("binary.dat", false));
        for (int i=0;i<firstReadLocation.size();i++) {
            dataOutputStream.writeUTF(firstReadLocation.get(i).getName());
            dataOutputStream.writeDouble(firstReadLocation.get(i).getX());
            dataOutputStream.writeDouble(firstReadLocation.get(i).getY());
        }
        dataOutputStream.close();
        //ArrayList<Location> lastReadLocation = readFileToLocations();
        //System.out.println("\n\n" + lastReadLocation);
    }

    public static void writeSettings(boolean isLock, String password) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("settings.dat"));
            dataOutputStream.writeBoolean(isLock);
            dataOutputStream.writeUTF(password);
            dataOutputStream.close();
        }
        catch (IOException e) {
        }
    }

    public static Setting readSettings() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("settings.dat"));
        boolean isLock = dataInputStream.readBoolean();
        String password = dataInputStream.readUTF();

        return new Setting(password, isLock);
    }

    public static void writeSettings(Setting setting) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("settings.dat"));
            dataOutputStream.writeBoolean(setting.isLock());
            dataOutputStream.writeUTF(setting.getPassword());
            dataOutputStream.close();
        }
        catch (IOException e) {
        }
    }
}
