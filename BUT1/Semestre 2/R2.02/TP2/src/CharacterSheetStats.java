import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CharacterSheetStats extends JPanel {
    private JPanel affichage;
    private JPanel affichageCaracteristique;

    private int pointTotal = 27;
    private JLabel textPoint;

    private ArrayList<JSpinner> spinnerList;

    // Les labels
    private JLabel nameLabel;
    private JLabel raceLabel;
    private JLabel classLabel;

    // Les champs de texte
    private JTextField name;

    // Les boutons radios
    private JRadioButton maleButton;
    private JRadioButton femaleButton;

    // Les combobox
    private JComboBox<Races> race;
    private JComboBox<Classes> classe;


    private JSpinner force;
    private JLabel forceLabel;

    private JSpinner dexterite;
    private JLabel dexteritLabel;

    private JSpinner constitution;
    private JLabel constitutionLabel;

    private JSpinner intelligence;
    private JLabel intelligencLabel;

    private JSpinner sagesse;
    private JLabel sagessLabel;

    private JSpinner charisme;
    private JLabel charismeLabel;

    private CharacterSheetListener sheet;

    private int usedPoints = 0;


    public CharacterSheetStats(CharacterSheetListener sheet) {
        this.sheet = sheet;
        initComponents();
    }

    private void initComponents() {
        spinnerList = new ArrayList<JSpinner>();
        setLayout(new GridBagLayout()); // Using GridBagLayout for centering

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some spacing

        this.affichage = new JPanel();
        this.affichage.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout with gaps

        JPanel selectionPersonnage = new JPanel();

        // Ici on ajoute le nom du personnage
        // TIPS : JLabel - JTextField
        this.nameLabel = new JLabel("Nom");
        this.name = new JTextField(10); // Set preferred width

        this.raceLabel = new JLabel("Race");

        this.classLabel = new JLabel("Classe");

        this.add(selectionPersonnage, gbc);

        // Ici on ajoute la sélection de race, on souhaite utilisé l'énumeration Races
        // TIPS : Enum.values() permet de récupérer les valeurs
        Races[] table_race = Races.values();
        this.race = new JComboBox<>(table_race);
        this.race.setPreferredSize(new Dimension(100, 20)); // Set preferred size

        // Ici on ajoute la sélection de classe, on souhaite utilisé l'énumeration Classes
        Classes[] table_classe = Classes.values();
        this.classe = new JComboBox<>(table_classe);
        this.classe.setPreferredSize(new Dimension(100, 20)); // Set preferred size


        // Ici on ajoute les boutons radio H/F (attention, on ne veux pas qu'ils fassent toute la largeur)
        // TIPS : faire un JPanel qui regroupe les deux boutons, ne pas oublier ButtonGroup
        this.maleButton = new JRadioButton("Homme");
        this.femaleButton = new JRadioButton("Femme");

        ButtonGroup bg = new ButtonGroup();

        bg.add(femaleButton);
        bg.add(maleButton);

        JPanel gender = new JPanel();
        gender.add(maleButton);
        gender.add(femaleButton);

        gender.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Reduce spacing between buttons

        this.affichage.add(nameLabel);
        this.affichage.add(name);
        this.affichage.add(raceLabel);
        this.affichage.add(race);
        this.affichage.add(classLabel);
        this.affichage.add(classe);
        this.affichage.add(gender);

        this.affichage.add(new JLabel());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(affichage, gbc);

        
        //Caracteristique

        this.affichageCaracteristique = new JPanel();
        this.affichageCaracteristique.setLayout(new GridLayout(0, 4));

        SpinnerNumberModel forceModel = new SpinnerNumberModel(8, 8, 15, 1.0);  
        SpinnerNumberModel dexteriteLabel = new SpinnerNumberModel(8, 8, 15, 1.0);  
        SpinnerNumberModel consModel = new SpinnerNumberModel(8, 8, 15, 1.0);  
        SpinnerNumberModel intModel = new SpinnerNumberModel(8, 8, 15, 1.0);  
        SpinnerNumberModel wisModel = new SpinnerNumberModel(8, 8, 15, 1.0);  
        SpinnerNumberModel charModel = new SpinnerNumberModel(8, 8, 15, 1.0);  


        this.force = new JSpinner(forceModel);
        this.forceLabel = new JLabel("str");

        this.dexterite = new JSpinner(dexteriteLabel);
        this.dexteritLabel = new JLabel("dex");

        this.constitution = new JSpinner(consModel);
        this.constitutionLabel = new JLabel("con");

        this.intelligence = new JSpinner(intModel);
        this.intelligencLabel = new JLabel(" int");

        this.sagesse = new JSpinner(wisModel);
        this.sagessLabel = new JLabel(" wis");

        this.charisme = new JSpinner(charModel);
        this.charismeLabel = new JLabel(" char");
        
        this.force.addChangeListener(this.sheet);
        this.dexterite.addChangeListener(this.sheet);
        this.constitution.addChangeListener(this.sheet);
        this.intelligence.addChangeListener(this.sheet);
        this.sagesse.addChangeListener(this.sheet);
        this.charisme.addChangeListener(this.sheet);

        this.spinnerList.add(force);
        this.spinnerList.add(dexterite);
        this.spinnerList.add(constitution);
        this.spinnerList.add(intelligence);
        this.spinnerList.add(sagesse);
        this.spinnerList.add(charisme);

        this.affichageCaracteristique.add(forceLabel);
        this.affichageCaracteristique.add(force);

        this.affichageCaracteristique.add(intelligencLabel);
        this.affichageCaracteristique.add(intelligence);

        this.affichageCaracteristique.add(constitutionLabel);
        this.affichageCaracteristique.add(constitution);

        this.affichageCaracteristique.add(sagessLabel);
        this.affichageCaracteristique.add(sagesse);

        this.affichageCaracteristique.add(dexteritLabel);
        this.affichageCaracteristique.add(dexterite);

        this.affichageCaracteristique.add(charismeLabel);
        this.affichageCaracteristique.add(charisme);

        
        this.textPoint = new JLabel("Points Restants : " + this.pointTotal);
        this.affichageCaracteristique.add(textPoint);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(affichageCaracteristique, gbc);
    }

    public ArrayList<JSpinner> getSpinnerList(){
        return(this.spinnerList);
    }

    public String getName(){
        return(this.name.getText());
    }

    public JSpinner getJSpinnerForce(){
        return this.force;
    }

    public JSpinner getJSpinnerDexterite(){
        return(this.dexterite);
    }

    public JSpinner getJSpinnerIntelligence(){
        return(this.intelligence);
    }

    public JSpinner getJSpinnerSagesse(){
        return(this.sagesse);
    }

    public JSpinner getJSpinnerConstitution(){
        return(this.constitution);
    }

    public JSpinner getJSpinnerCharisme(){
        return(this.charisme);
    }

    public int getPointTotal() {
        return this.pointTotal;
    }

    public int getUsedPoint() {
        return this.usedPoints;
    }

    public void setPointTotal(int point){
        this.pointTotal = point;
    }

    public void setUsedPoint(int point){
        this.usedPoints = point;
    }   

    public void updateStatus(int point) {
        this.pointTotal = point;
        this.textPoint.setText("Point restant : " + point);
    }
}