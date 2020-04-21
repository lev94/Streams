package streams;

import java.io.*;

public class StreamsImpl implements Streams {
    @Override
    public double saveData(long time, float[] values) throws Exception {
        final String filename = "testFile.txt";
        //Outputstream eröffnen
        OutputStream os;
        DataOutputStream dos = null;
        try {
            os = new FileOutputStream(filename);
            dos = new DataOutputStream(os);
        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
            System.exit(0); // brutal exception handling
        }
        //alle Messwerte eintragen
            try {
                if(values.length > Short.MAX_VALUE){
                    throw new Exception();
                }
                dos.writeLong(time);
                for (int i = 0; i < values.length; i++) {
                    dos.writeFloat(values[i]);
                }

            } catch (IOException ex) {
                System.err.println("couldn’t write data (fatal)");
                System.exit(0);
            }

        // read data from file and print to System.out
        InputStream is;
        DataInputStream dis = null;
        try {
            is = new FileInputStream(filename);
            dis = new DataInputStream(is);
        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
            System.exit(0);
        }

            try {
                long c = dis.readLong();
                System.out.print(c + " ");
                for (int j = 0; j < values.length; j++) {
                    float e = dis.readFloat();
                    System.out.print(e +" ");
                }
            } catch (IOException ex) {
                System.err.println("couldn’t write data (fatal)");
                System.exit(0);
            }

        return 0;
    }
}
