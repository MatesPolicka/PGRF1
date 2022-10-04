package rasterops;

import rasterdata.RasterImage;

public class TrivialLiner<P> implements Liner<P>{


    @Override
    public void drawLine( RasterImage<P> img, int c1,  int r1,
                          int c2, int r2, P pixelValue) {
        final double k = (r2 - r1) / (double) (c2 - c1);
        final double q = r1 - k * c1;
        for (int c = c1; c <= c2; c++){
            int r = (int) Math.round(k * c + q);
            img.setPixel(c, r, pixelValue);
        }

        if (Math.abs(r2 - r1) < Math.abs(c2 - c1)) { //iterate over c
            if (c1 > c2) { // swap start and end points
                int temp = c1;
                c1 = c2;
                c2 = temp;

            }
            for (int x = c1; x <= c2; x++) {
                int y = (int) Math.round(k * x + q);
                img.setPixel(x, y, pixelValue);
            }
            // iterating over c and calculating r
        } else { // iterate over r
            if (r1 > r2) { // swap start and end points
                int temp = r1;
                r1 = r2;
                r2 = temp;
            }
        }
        // iterating over r and calculating c
        for (int y = r1; y <= r2; y++)
        {
            int x = (int) Math.round((y - q) / k);
            img.setPixel(x, y, pixelValue);
        }
    }
}
