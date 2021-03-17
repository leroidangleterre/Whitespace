package whitespace;

import java.util.ArrayList;
import graphs.*;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;

/**
 *
 * @author arthu
 */
public class WhitespaceTree extends Tree {

    private String name;

    // The three branches available in the superclass provide access to the nodes
    // First branch: SPACE, second branch: TAB, third branch: LF
    public WhitespaceTree(int id, String newName) {
        super(0);
        name = newName;
        branches = new ArrayList<>();
        value = id;

        lineSpacing = 20;
    }

    @Override
    public String getStringValue() {
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

    @Override
    public void paint(Graphics g, int x0, int y0, double zoom) {
        super.paint(g, x0, y0, zoom);

        g.setFont(new Font("Monospaced", PLAIN, 10));

        // Apparent coordinates of this node
        int xApp0 = (int) (x0 + this.x * zoom);
        int yApp0 = (int) (y0 + this.y * zoom);

        // Draw the names of the branches
        for (int i = 0; i < branches.size(); i++) {
            Tree branch = branches.get(i);
            // Apparent coordinates of the branch
            int xApp1 = (int) (x0 + branch.getApparentX() * zoom);
            int yApp1 = (int) (y0 + branch.getApparentY() * zoom);

            // Draw the text at the center of the link
            String text = (i == 0 ? "SPACE" : (i == 1 ? "TAB" : "LF"));
            g.setColor(Color.red);
            g.drawString(text, (xApp0 + xApp1) / 2, (yApp0 + yApp1) / 2);
        }
    }
}
