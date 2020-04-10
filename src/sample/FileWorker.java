package sample;

import java.io.*;
import java.util.ArrayList;

public class FileWorker {
    public static void writeLocationToFile(double x, double y, String name) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("binary.dat"));
        if(dataInputStream.available() == 0) {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("binary.dat"));
            dataOutputStream.writeUTF(name);
            dataOutputStream.writeDouble(x);
            dataOutputStream.writeDouble(y);

            dataOutputStream.close();
        }else {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("binary.dat", true));
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

        return returnArrayList;
    }
}
