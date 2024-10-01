import javax.swing.*;

public class HelloWorldSwing extends JFrame {

    /**
     * Program entry point
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create the frame and show it
                HelloWorldSwing frame = new HelloWorldSwing(args);
                frame.pack();
                frame.setVisible(true);
            }
        });

    }

    /** Initialize the HelloWorldSwing frame components. */
    public HelloWorldSwing(String[] args) {
        setTitle("HelloWorldSwing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the ubiquitous "Hello World" label.
        
        getContentPane().setLayout(new java.awt.FlowLayout());
        
        for(int i = 0; i < args.length; i++){
            add(new JLabel(args[i]));
        }
    }
}