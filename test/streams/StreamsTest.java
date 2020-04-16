package streams;

import org.junit.Assert;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamsTest {
    long time;

    @Test
    public void GutTest1() throws Exception {
        Streams data = new StreamsImpl();

        float[] values = new float[2];

        double daten = data.saveData(time = System.currentTimeMillis(),new float[]{values[0] = (float) 3.5,values[1] = (float) 8.9});
        Assert.assertArrayEquals(new float[]{(float) 3.5, (float) 8.9},new float[]{values[0], values[1]}, 0);
        Assert.assertEquals(this.time, time);
    }

    @Test(expected = Exception.class)
    public void SchlechtTest1() throws Exception {
        Streams data = new StreamsImpl();

        float[] values = new float[2];

        double daten = data.saveData(time = System.currentTimeMillis(), new float[]{values[0] = (float) 3.5,values[1] = (float) 8.9});
        Assert.assertArrayEquals(new float[]{(float) 3.5, (float) 10.9},new float[]{values[0], values[1]}, 0);
        Assert.assertEquals(this.time-100, time);
    }
}