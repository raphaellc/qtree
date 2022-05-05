import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseListener;

public class Main {
    /**
     * Interface
     */
    //threshold of quadtree for points per quadrant
    public static int capacity = 1;

    //size of 2D plane
    public static int width = 400;
    public static int height = 400;

    //num of random points put into quadtree
    public static int num = 500;
    public static Color c1 = StdDraw.LIGHT_GRAY;

    //searchBoundary
    public static double searchX = 50;
    public static double searchY = 200;
    public static double searchWidth = 120;
    public static double searchHeight = 50;
    public static Color c2 = StdDraw.GREEN;


    //------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        /*
         * StdDraw Settings
         */
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        /*
         * QuadTree Settings
         */
        Rectangle boundary = new Rectangle(width / 2, height / 2, width / 2, height / 2);
        QuadTree quadTree = new QuadTree(boundary, capacity);

        /*
         * Point Input
         */
        //random 2d Points to QuadTree
        for (int i = 0; i < num; i++) {
            Point p = new Point(Math.random() * width, Math.random() * height);
            System.out.println(p);
            quadTree.insert(p);
        }

        /*
         * Visualization
         */
        while(true){
        quadTree.show();


            //search rectangle
            StdDraw.setPenColor(c2);
            StdDraw.setPenRadius(0.005);
            Rectangle searchBoundary = new Rectangle(StdDraw.mouseX(), StdDraw.mouseY(), searchWidth, searchHeight);
            StdDraw.rectangle(StdDraw.mouseX(), StdDraw.mouseY(), searchWidth, searchHeight);
            ArrayList<Point> searchResult = quadTree.search(searchBoundary);
            for (Point point : searchResult) {
                point.show(c2);
            }
            StdDraw.pause(500);

            if (StdDraw.hasNextKeyTyped()) break;
        }

    }
}

