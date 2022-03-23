package mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MandelBrot extends JFrame {

    private BufferedImage bufferedImage;

    public static int numberIterator = 50;
    File file = new File(System.getProperty("user.dir"));
    private final double zoomIncrease = 100;
    private final int moveSpeed = 25;
    private final int colorIterator = 20;
    private Location location = new Location();
    private Animation animation = new Animation();
    public boolean exit = false;
    public static ExecutorService pool = Executors.newCachedThreadPool();


    public MandelBrot(int iteration) {
        numberIterator = iteration;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setFocusable(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void recalculate() {
        this.bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                ComplexNumber temp = new ComplexNumber(0, 0);
                final ComplexNumber complex = new ComplexNumber((x - 320 + this.location.getxMove()) / this.location.getZoom(),
                        (y - 290 + this.location.getyMove()) / this.location.getZoom());
                int iterator = numberIterator;
                while (temp.abs() < 4 && iterator > 0) {
                    temp = temp.multiply(temp);
                    temp = temp.add(complex);
                    iterator--;
                }
                this.bufferedImage.setRGB(x, y, iterator | (iterator << colorIterator));
            }
        }
    }


    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(this.bufferedImage, 0, 0, null);
    }

    @Override
    public void processKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 49: // 1 to set start location
                animation.setStartLocation(this.location.copy());
                break;
            case 50: // 2 to set ending location
                animation.setEndingLocation(this.location.copy());
                break;
            case 51: // 3 to render animation
                if (!animation.getStartedRending())
                    animation.render(file);
                break;
            case 52: // 4 to reset animation
                animation = new Animation();
                break;
            case 27:
                this.exit = true;
                break;
            case 37:
                this.location.addxMove(-moveSpeed);
                this.recalculate();
                break;
            case 38:
                this.location.addyMove(-moveSpeed);
                this.recalculate();
                break;
            case 39:
                this.location.addxMove(moveSpeed);
                this.recalculate();
                break;
            case 40:
                this.location.addyMove(moveSpeed);
                this.recalculate();
                break;
            case 107:
                this.location.addZoom(this.zoomIncrease);
                this.recalculate();
                break;
            case 109:
                if (!(this.location.getZoom() - this.zoomIncrease <= 0)) {
                    this.location.addZoom(-this.zoomIncrease);
                    this.recalculate();
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void setVisible(final boolean visible) {
        if (!visible || !isVisible()) {
            super.setVisible(visible);
        }

        if (visible) {
            int state = super.getExtendedState();
            state &= ~JFrame.ICONIFIED;
            super.setExtendedState(state);
            super.setAlwaysOnTop(true);
            super.toFront();
            super.requestFocus();
            super.setAlwaysOnTop(false);
        }
    }

    @Override
    public void toFront() {
        super.setVisible(true);
        int state = super.getExtendedState();
        state &= ~JFrame.ICONIFIED;
        super.setExtendedState(state);
        super.setAlwaysOnTop(true);
        super.toFront();
        super.requestFocus();
        super.setAlwaysOnTop(false);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input iteration number.");
        final MandelBrot mandelbrot = new MandelBrot(scanner.nextInt());
        mandelbrot.recalculate();
        while (!mandelbrot.exit) {
            mandelbrot.repaint();
        }
        mandelbrot.dispatchEvent(new WindowEvent(mandelbrot, WindowEvent.WINDOW_CLOSING));
    }

}