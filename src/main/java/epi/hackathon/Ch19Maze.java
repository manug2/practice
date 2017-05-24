package epi.hackathon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class Ch19Maze {
    final int size;
    final Pixel[][] V;
    final Map<Pixel, List<Pixel>> E;

    public Ch19Maze(int size) {
        this.size = size;
        this.V = new Pixel[size][];
        for (int i=0; i<V.length; i++)
            V[i] = new Pixel[size];
        this.E = new HashMap<>(size);

        V[0][0] = new Pixel(0, 0);
    }

    private Pixel add(int x, int y) {
        if (V[x][y]==null)
            V[x][y] = new Pixel (x, y);

        return V[x][y];
    }

    public Ch19Maze add(int x1, int y1, int x2, int y2) {
        Pixel p1 = add(x1, y1);
        Pixel p2 = add(x2, y2);

        if (!E.containsKey(p1))
            E.put(p1, new ArrayList<>());

        E.get(p1).add(p2);
        return this;
    }

    public List<Pixel> adjacencies(Pixel u) {
        if (E.containsKey(u))
            return E.get(u);
        else
            return new ArrayList<>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        for (int i=0; i< V.length; i++)
            sb.append('-');

        for (int i=0; i< V.length; i++) {
            sb.append(System.lineSeparator());
            sb.append('|');
            for (int j=0; j<V.length; j++) {
                if (V[i][j] != null)
                    sb.append(' ');
                else
                    sb.append('X');
            }
            sb.append('|');
        }
        sb.append(System.lineSeparator());
        sb.append(' ');
        for (int i=0; i< V.length; i++)
            sb.append('-');

        return sb.toString();
    }
}

class Pixel {
    final int x, y;
    Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return format("(%s,%s)", x, y);
    }
    public int hashCode() {
        return x*1000 + y; //assuming size of max <= 1000
    }
    public boolean equals(Object obj) {
        if (this==obj)
            return true;
        else if (obj==null)
            return false;
        else if (! (obj instanceof Pixel))
            return false;

        Pixel p = (Pixel) obj;
        return x==p.x && y == p.y;
    }
}
