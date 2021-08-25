public class NBody {

    /**
     * for this task, we only need to read up to the second value. Also, we can write "in.readInt()" instead of assigning this value to a variable name.
     */
    public static double readRadius(String name) {
        /* casted string "name" into class In." */
        In in = new In(name);

        int firstItemInFile = in.readInt();
        /* above can be written as "in.readInt();" */

        double secondItemInFile = in.readDouble();

/*          cannot run properly if we include the below.

            while(!in.isEmpty()) {
                double thirdItemInFile = in.readDouble();
                double fourthItemInFile = in.readDouble();
                double fifthItemInFile = in.readDouble();
                double sixthItemInFile = in.readDouble();
                double seventhItemInFile = in.readDouble();
                String eighthItemInFile = in.readString();
            }*/
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String name) {

        In in = new In(name);
        int numPlanets = in.readInt();

        /* arrays are of a fixed size -- the only way we can have a dynamic array is using ArrayList. */
        Planet[] collection = new Planet[numPlanets];

        in.readDouble();

        for (int i = 0; i < numPlanets; i += 1) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String gif = in.readString();
            collection[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, gif);
        }
        return collection;
    }

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);

        double dt = Double.valueOf(args[1]);

        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] collection = readPlanets(filename);

        /* Note: we cannot declare a public or method in main. */
        String imageToDraw = "images/starfield.jpg";

                 /*sets up the scale x, y scale so it goes from 0 to radius on the x-scale and 0 to radius on the y-scale. If we did (-100, 100) it would go from -100 to 100 on the x-scale, and -100 to 100 on the y-scale.
                StdDraw.setScale(-2*radius, 2*radius); */

        /*                 if we dont want to form a uniform scale with "setScale" we can set the x, y scales separately.*/
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);


        /* StdDraw.picture(x-value, y-value, image to pull from) and sets it at the x-value and y-value provided. Let's set it at (0,0) to draw at. */
        StdDraw.picture(0, 0, imageToDraw);

        for (int i = 0; i < collection.length; i++) {

            /* if a method takes in no variables, we need to write in the "()" too. Instead of ".draw" we need to write ".draw()" */
            collection[i].draw();
        }

                /* DoubleBuffering is a graphics technique used to prevent flickering in the animation. When this is called, all drawing takes place on an offscreen canvas, then when we call "show()" our drawing gets copied
                    from the offscreen canvas to the onscreen canvas. DoubleBuffering copies all of the lines, points, shapes, and text that you tell it to draw, then drawing them all simultaneously, upon request. */
        StdDraw.enableDoubleBuffering();

        /* we can declare and initialize multiple variables within a for loop's initialization phase. The second variable, and so on, will share the same declaration as the first. */
        /* When we instantiating a new instance over the same variable name, we're not rewriting anything, but adding to it. */


        for (double time = 0; time <= T; time += dt) {
            double[] xForces = new double[collection.length];
            double[] yForces = new double[collection.length];

            for (int i = 0; i < collection.length; i++) {
                xForces[i] = collection[i].calcNetForceExertedByX(collection);
                yForces[i] = collection[i].calcNetForceExertedByY(collection);
            }

            for (int j = 0; j < collection.length; j++) {
                collection[j].update(time, xForces[j], yForces[j]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);

            for (int k = 0; k < collection.length; k++) {
                collection[k].draw();
            }

            /* Shows the drawing to the screen, and waits 10 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
        }

        /* Prints out the final state of the universe. */
        StdOut.printf("%d\n", collection.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < collection.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    collection[i].xxPos, collection[i].yyPos, collection[i].xxVel,
                    collection[i].yyVel, collection[i].mass, collection[i].imgFileName);
        }
    }
}








