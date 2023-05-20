//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

import biuoop.DrawSurface;

import java.util.LinkedList;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {

    private java.util.List<Sprite> spList;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spList = new LinkedList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        spList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spList.size(); i++) {
            spList.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spList.size(); i++) {
            spList.get(i).drawOn(d);
        }
    }
}
