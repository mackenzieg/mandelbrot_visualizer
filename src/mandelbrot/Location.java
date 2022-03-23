package mandelbrot;

public class Location {

    private double zoom = 130;
    private int xMove = 0;
    private int yMove = 0;

    public Location(double zoom, int xMove, int yMove) {
        this.zoom = zoom;
        this.xMove = xMove;
        this.yMove = yMove;
    }

    public Location() {

    }

    public Location copy() {
        return new Location(zoom, xMove, yMove);
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    public double getZoom() {
        return zoom;
    }

    public int getxMove() {
        return xMove;
    }

    public int getyMove() {
        return yMove;
    }

    public void addZoom(double zoom) {
        this.zoom += zoom;
    }

    public void addyMove(int yMove) {
        this.yMove += yMove;
    }

    public void addxMove(int xMove) {
        this.xMove += xMove;
    }

}
