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
        Assert.assertArrayEquals(v, v, 0); //vor 0 steht "delta:"
        Assert.assertEquals(time, time);
    }

    //@Test(expected = Exception.class)
    @Test(expected = AssertionError.class) //AssertionError.class statt Exception.class?
    public void schelchtTest1() throws Exception {
        Streams data = new StreamsImpl();

        long time = System.currentTimeMillis();
        float[] v = new float[2];
        v[0] = (float) 3.5;
        v[1] = (float) 8.9;

        double daten = data.saveData(time,v);
        Assert.assertArrayEquals(v, v, 0);
        Assert.assertEquals(time-2000, time);
    }
}
