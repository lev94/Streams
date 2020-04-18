package streams;

import org.junit.Assert;
import org.junit.Test;

import java.beans.PersistenceDelegate;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamsTest {

    @Test
    public void gutTest1() throws Exception {
        Streams data = new StreamsImpl();

        long time = System.currentTimeMillis();
        float[] v = new float[2];
        v[0] = (float) 3.5;
        v[1] = (float) 8.9;

        double daten = data.saveData(time,v);
        Assert.assertArrayEquals(v, v, 0);
        Assert.assertEquals(time, time);
    }

    @Test
    public void schelchtTest1() throws Exception {
        Streams data = new StreamsImpl();
        try {
            long time = System.currentTimeMillis();
            float[] v = new float[Integer.MAX_VALUE+1];
            for(long i = 0; i <= v.length; i++) {
                v[(int) i] = (float) 3.5;
            }

            int daten = (int) data.saveData(time,v);
            Assert.fail();
        } catch (Exception e) {

        }
    }
}
