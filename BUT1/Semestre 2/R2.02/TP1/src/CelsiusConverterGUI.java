import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CelsiusConverterGUI extends JFrame {

    private JTextField tempCelsTextField;
    private JLabel celsiusLabel;
    
    private JLabel fahrenheitLabel;
    private JTextField tempFahrenheitTextField;

    private JButton celsiusVersFahrenheit;
    private JButton fahrenheitVersCelsius;

    private JButton remplissageVide;
    private JButton quitter;
    

    /**
     * Program entry point
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CelsiusConverterGUI frame = new CelsiusConverterGUI();
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    /** Initialize the CelsiusConverterGUI frame components. */
    public CelsiusConverterGUI() {

        setTitle("Celsius Converter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Creation des composants

        tempCelsTextField = new JTextField();
        tempFahrenheitTextField = new JTextField();
        celsiusVersFahrenheit = new JButton();
        fahrenheitVersCelsius = new JButton();
        fahrenheitLabel = new JLabel();
        celsiusLabel = new JLabel();
        remplissageVide = new JButton();
        quitter = new JButton();


        fahrenheitLabel.setText("Fahrenheit");
        celsiusLabel.setText("Celsius");

        //ActionListener

        celsiusVersFahrenheit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                celsVersFahr(evt);
            }
        });

        fahrenheitVersCelsius.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FahrVersCels(evt);
            }
        });

        remplissageVide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
                suppression(evt);
            }
        });

        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
                quitterApp(evt);
            }
        });

        //Set Text

        fahrenheitVersCelsius.setText("F => C");
        celsiusVersFahrenheit.setText("C => F");
        remplissageVide.setText("DELETE");
        quitter.setText("Exit");

        //Layout

        getContentPane().setLayout(new GridLayout(4, 2));
        add(tempCelsTextField);
        add(celsiusLabel);

        add(tempFahrenheitTextField);
        add(fahrenheitLabel);

        add(celsiusVersFahrenheit);
        add(fahrenheitVersCelsius);

        add(remplissageVide);
        add(quitter);
        
    }

    /** Parse degrees Celsius as a double and convert to Fahrenheit */
    private void celsVersFahr(ActionEvent evt) {
        if(tempCelsTextField.getText() != null){
            int tempFahr = (int) ((Double.parseDouble(tempCelsTextField.getText())) * 1.8 + 32);
            tempFahrenheitTextField.setText(tempFahr + "");
        } else {
            System.out.println("Erreur");
        }
    }

    private void FahrVersCels(ActionEvent evt) {
        if(tempFahrenheitTextField.getText() != null){
            int tempFahr = (int) ((Double.parseDouble(tempFahrenheitTextField.getText())) / 1.8 - 32);
            tempCelsTextField.setText(tempFahr + "");
        } else {
            System.out.println("Erreur");
        }
    }

    private void suppression(ActionEvent evt){
        tempCelsTextField.setText("");
        tempFahrenheitTextField.setText("");
    }

    private void quitterApp(ActionEvent evt){
        System.exit(0);
    }
}