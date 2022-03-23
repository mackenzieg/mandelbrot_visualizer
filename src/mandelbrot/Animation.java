package mandelbrot;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class Animation {

    private Location startLocation;
    private Location endingLocation;
    private AtomicBoolean startedRending;

    private int index = 0;

    public Animation() {
        this.startedRending = new AtomicBoolean(false);
    }


    public void render(File file) {
        startedRending.set(true);
        Converter converter = new Converter(this.startLocation, this.endingLocation, file, startedRending);
        MandelBrot.pool.execute(converter);
        System.out.println("Render Task Started!");
    }

    public boolean getStartedRending() {
        return startedRending.get();
    }

    public void reset() {
        this.index = 0;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndingLocation() {
        return endingLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public void setEndingLocation(Location endingLocation) {
        this.endingLocation = endingLocation;
    }

}
