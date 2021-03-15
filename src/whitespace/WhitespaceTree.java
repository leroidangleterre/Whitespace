package whitespace;

import java.util.ArrayList;
import graphs.*;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author arthu
 */
public class WhitespaceTree extends Tree {

    private String name;

    // x-coordinate of the leftmost element of this tree.
    private double leftmostX;
//    private double rightmostX;

    // Distance between two lines or columns (in space-coordinates, not pixels).
    private double lineSeparation;
    private double columnSeparation;

    // The id is given by super.value;
    // The three branches available in the superclass provide access to the nodes
    // First branch: SPACE, second branch: TAB, third branch: LF
    public WhitespaceTree(int id, String newName) {
        super(0);
        name = newName;
        branches = new ArrayList<>();
        value = id;
        leftmostX = 0;

        lineSeparation = 0;
        columnSeparation = 0;
    }

    @Override
    public String getStringValue() {

//        return "t";
        if (name.equals("no_name")) {
            return value + "";
        } else {
            return value + ":" + name;
        }
    }

    /**
     * Add a new branch or replace an existing one.
     *
     * @param branch the new branch
     * @param position 0 for SPACE, 1 for TAB, 2 for LF
     */
    public void addBranch(WhitespaceTree branch, int position) {
        branches = new ArrayList<>();

        branches.add(branch);
    }

    /**
     * Add a new branch to the first available slot
     *
     * @param branch the newly added branch.
     */
    public void addBranch(WhitespaceTree branch) {
        if (branches == null) {
            branches = new ArrayList<>();
        }
        int index = branches.size();
        branches.add(index, branch);
    }

    public void paint(Graphics g, int x0, int y0, double zoom) {

        int descent = g.getFontMetrics().getDescent();

        // The height of the current node.
        int nodeHeight = g.getFontMetrics().getHeight()
                - descent;

        g.setColor(Color.black);
        String str = getStringValue();

        // Draw a red dot at the origin of the node.
        g.setColor(Color.red);
        g.fillRect((int) (x0 + (this.x * zoom)) - 2,
                (int) (y0 + (this.y * zoom)) - 2,
                5, 5);

        // Draw a rectangle around the number.
        char[] chars = getStringValue().toCharArray();
        int currentNodeWidth = g.getFontMetrics().charsWidth(chars, 0, chars.length); // The width of the current node.
        g.setColor(Color.black);
        g.drawRect((int) (x0 + this.x * zoom),
                (int) (y0 + this.y * zoom + descent),
                (int) (currentNodeWidth),
                (int) (nodeHeight));

        g.drawString(str, (int) (x0 + (this.x * zoom)),
                (int) (y0 + (this.y * zoom) + nodeHeight));

        int i = 0;
        for (Tree branch : this.branches) {
            // Paint the branch
            branch.paint(g, x0, y0, zoom);
            // Paint the link between the branch and the current node
            g.setColor(Color.black);
            int xStart = (int) (x0 + this.x * zoom);
            int yStart = (int) (y0 + (this.y * zoom + descent));

//            int xEnd = (int) (x0 + branch.getNodeWidth(g) * zoom);
//            int yEnd = (int) (y0 + branch.getNodeWidth(g) * zoom //- nodeHeight / 2
//                    + descent);
//            g.drawLine(xStart, yStart, xEnd, yEnd);
//
//            String branchName = (i == 0 ? "S" : (i == 1 ? "T" : "LF"));
//            g.drawString(branchName, (xStart / 5 + 4 * xEnd / 5), (yStart / 5 + 4 * yEnd / 5));
            i++;
        }

        int xMin = this.getMinX();
        g.setColor(Color.red);
        g.drawRect((int) (x0 + xMin * zoom), (int) (y0 + this.y * zoom), 10, 10);
    }

    private int getMinX() {
        if (isLeaf()) {
            return (int) this.x;
        } else {
            int min = Integer.MAX_VALUE;
            for (Tree branch : branches) {
                min = Math.min(min, ((WhitespaceTree) branch).getMinX());
            }
            return min;
        }
    }

    @Override
    public int getWidthInPixels(Graphics g) {
        if (branches.isEmpty()) {
            return getNodeWidth(g);
        } else {
            int width = 0;
            // Sum of the branches width, plus (n-1) separators
            for (Tree branch : branches) {
                width += branch.getWidthInPixels(g);
            }
            width += (branches.size()) * columnSeparation;
            return width;
        }
    }

    @Override
    protected int getNodeHeight(Graphics g) {
        int height = g.getFontMetrics().getHeight();
        return height;
    }
}
