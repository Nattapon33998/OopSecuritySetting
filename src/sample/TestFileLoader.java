package sample;

import java.io.*;
import java.util.ArrayList;

public class TestFileLoader {
    public static void main(String[] args) throws IOException {

//         writeLocationToFile(5.482, 32.226, "Shop 2");
        writeLocationToFile(1505, 1500, "Test Store 4");
        System.out.println(readFileToLocation());
//        writeLocationToFile(17, 902, "Test Store 2");
    }

    static void writeLocationToFile(double x, double y, String name) throws IOException {
//        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("binary.dat"));
//        try{
//            if(dataInputStream.available() > 0) {
//                DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("binary.dat", true));
//                dataOutputStream.writeUTF(name);
//                dataOutputStream.writeDouble(x);
//                dataOutputStream.writeDouble(y);
//
//                dataOutputStream.close();
//            }
//        } catch (Exception e) {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("binary.dat", true));
            dataOutputStream.writeUTF(name);
            dataOutputStream.writeDouble(x);
            dataOutputStream.writeDouble(y);

            dataOutputStream.close();
//        }
    }

    static ArrayList<Location> readFileToLocation() throws IOException {
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
