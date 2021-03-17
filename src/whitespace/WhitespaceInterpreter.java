package whitespace;

import graphs.Tree;
import graphs.GraphPanel;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author arthu
 */
public class WhitespaceInterpreter {

    // Original code, written using the alphabet with three characters: space, tab, linefeed;
    // That code is compiled once during initialization and not used after that.
    private String sourceCode;
    private int sourceAddress;

    // Compiled code, using assembly operators like PUSH, ADD, JUMP
    // Each instruction may be assigned a label, or be the start of a subroutine.
    private ArrayList<Instruction> compiledCode;

    private int codeAddress;
    private static final char SPACE = ' ';
    private static final char TAB = '\t';
    private static final char LF = '\n';

    private WhitespaceTree compilationTree;

    public WhitespaceInterpreter(String newCode) {
        sourceCode = newCode;
        sourceAddress = 0;
        compiledCode = new ArrayList<>();
        codeAddress = 0;
        buildCompilationTree();
        compile();
        print();

        // Draw the compilation tree;
        JFrame frame = new JFrame();
        GraphPanel panel = new GraphPanel(compilationTree);
        panel.setScroll(881, 191);
        panel.setZoomLevel(3.07);
        frame.setContentPane(panel);
        frame.setPreferredSize(new Dimension(1800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Execute the instruction designated by @codeAddress, then update
     *
     * @codeAddress to point to the next instruction.
     */
    public void step() {
//        String instruction = code[codeAddress];
//        // Read the IMP (first part of the instruction
//        if (instruction.charAt(0) == SPACE) {
//            stackManipulation(instruction);
//        }
    }

//    private void stackManipulation(String instruction) {
//        if (instruction.charAt(1) == SPACE) {
//            // Push a number to the stack
//        } else if (!instruction.charAt(2) ==) {
//
//        }
//    }
    /**
     * Print the code to the terminal.
     *
     */
    private void print() {
//        for (int address = 0; address < sourceCode.length; address++) {
//            System.out.print(address + ": ");
//            for (int index = 0; index < sourceCode[address].length(); index++) {
//                char character = sourceCode[address].charAt(index);
//                switch (character) {
//                case ' ':
//                    System.out.print("S");
//                    break;
//                case '\t':
//                    System.out.print("T");
//                    break;
//                default:
//                    System.out.print(".");
//                }
//            }
//            System.out.println("LF");
//        }
    }

    /**
     * Convert the stream of SPACEs, TABs and LINEFEEDs to Instructions.
     *
     */
    private void compile() {
        System.out.println("Whitespace Interpreter compiling...");

//        sourceAddress = 0;
//
//        while (sourceAddress <= sourceCode.length()) {
//            char c = sourceCode.charAt(sourceAddress);
//
//        }
        System.out.println("Compilation: complete. TODO");
    }

    private void compileStackManip() {
//        char c = sourceCode.charAt(sourceAddress + 1);
//        if (c == SPACE) {
//            // PUSH, the next bits are the number that gets pushed.
//        } else {
//            switch ()
//            }
//        }

    }

    /**
     * Create the structure that allows the conversion of a stream
     * of Whitespace sourcecode into a set of Instructions.
     */
    private void buildCompilationTree() {
        System.out.println("Building compilation tree...");
        compilationTree = new WhitespaceTree(0, "Whitespace");
        try {
            String path = "C:\\Users\\arthu\\Documents\\Programmation\\Java\\Whitespace\\src\\whitespace\\";
            FileReader fileReader = new FileReader(path + "compilation_tree.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String text;

            while ((text = reader.readLine()) != null) {
                String values[] = text.split(" ");
                try {
                    // First value is the ID of the node;
                    int nodeID = Integer.valueOf(values[0]);
                    int parentID = 0;
                    String name = "no_name";
                    if (values.length > 1) {
                        // Second value is the parent of that node (no value means that the parent is the root);
                        parentID = Integer.valueOf(values[1]);
                    }
                    if (values.length > 2) {
                        // Third value is the new node's name
                        name = values[2];
                    }
                    WhitespaceTree parent = (WhitespaceTree) compilationTree.findValue(parentID);
                    WhitespaceTree newNode = new WhitespaceTree(nodeID, name);
                    parent.addBranch(newNode);
                } catch (NumberFormatException e) {
                    System.out.println("ignoring line " + text + " while building compilation tree...");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Whitespace compile tree not found.");
            return;
        } catch (IOException e) {
            System.out.println("Error while reading whitespace tree file.");
            return;
        }
        System.out.println("Compilation tree done.");
    }
}
