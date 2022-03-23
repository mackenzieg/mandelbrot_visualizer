package mandelbrot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class Converter implements Runnable {

    private Location startLocation;
    private Location endingLocation;
    private Location movingLocation;
    private final int colorIterator = 20;
    private File file;
    private AtomicBoolean atomicBoolean;
    private static final int unitMoveSpeed = 2;

    public Converter(Location startLocation, Location endingLocation, File file, AtomicBoolean rendering) {
        this.startLocation = startLocation;
        this.endingLocation = endingLocation;
        this.file = file;
        this.atomicBoolean = rendering;
    }

    @Override
    public void run() {
        /*int deltaX = endingLocation.getxMove() - startLocation.getxMove();
        boolean trueLeft = deltaX > 0;
        int deltaY = endingLocation.getyMove() - startLocation.getyMove();
        double m = deltaY / deltaX;
        double b = this.startLocation.getyMove() - (m * this.startLocation.getxMove());
        int numOfFramesMove = (int) ((Math.sqrt(deltaX ^ 2 + deltaY ^ 2) / unitMoveSpeed) * 60);
        int numOfFramesZoom = (int) (this.startLocation.getZoom() + this.endingLocation.getZoom()) / unitMoveSpeed;
        int frames = numOfFramesMove > numOfFramesZoom ? numOfFramesMove : numOfFramesZoom;
        movingLocation = startLocation.copy();
        int halfFrames = frames / 2;

        String path = this.file.getPath() + "/" + UUID.randomUUID();
        File info = new File(path + "/info.txt");
        if (!info.exists())
            info.mkdirs();
        try {
            PrintWriter out = new PrintWriter(info);
            out.println(frames * 30);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (frames != 0) {
            System.out.println(frames);
            if (frames == halfFrames)
                System.out.println("50% done rending");

            BufferedImage bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < 800; x++) {
                for (int y = 0; y < 600; y++) {
                    ComplexNumber temp = new ComplexNumber(0, 0);

                    // calculate the new location using equation of a line

                    int newX = this.movingLocation.getxMove() + (trueLeft ? -unitMoveSpeed : unitMoveSpeed);
                    int newY = (int) (m * newX + b);
                    double newZoom = movingLocation.getZoom() + unitMoveSpeed;
                    movingLocation.setZoom(newZoom);
                    movingLocation.setxMove(newX);
                    movingLocation.setyMove(newY);

                    final ComplexNumber complex = new ComplexNumber((x - 320 + this.movingLocation.getxMove()) / this.movingLocation.getZoom(),
                            (y - 290 + this.movingLocation.getyMove()) / this.movingLocation.getZoom());
                    int iterator = MandelBrot.numberIterator;
                    while (temp.abs() < 4 && iterator > 0) {
                        temp = temp.multiply(temp);
                        temp = temp.add(complex);
                        iterator--;
                    }
                    bufferedImage.setRGB(x, y, iterator | (iterator << colorIterator));
                    for (int i = 0; i < 30; i++)
                        images.add(bufferedImage);
                }
            }
            frames--;
        }
        System.out.println("Rending task done");

        for (int i = 0; i < images.size(); i++) {
            File file1 = new File(path + "/" + i + ".jpg");
            if (file1.exists())
                file1.mkdirs();
            try {
                ImageIO.write(images.get(i), "jpg", file1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.atomicBoolean.set(false);*/
    }

}
