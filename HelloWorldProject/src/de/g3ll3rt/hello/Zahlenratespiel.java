package de.g3ll3rt.hello;

import java.awt.*;
import java.awt.event.*;

/**
 * Zahlenratespiel (AWT-GUI)
 *
 * Der Spieler muss eine Zahl zwischen 1 und 100 erraten.
 * Es stehen maximal 10 Versuche zur Verfuegung.
 *
 * Kompilieren:  javac Zahlenratespiel.java
 * Starten:      java Zahlenratespiel
 */
public class Zahlenratespiel extends Frame implements ActionListener {

    private static final int MIN = 1;
    private static final int MAX = 1000;
    private static final int MAX_VERSUCHE = 10;

    private int gesuchteZahl;
    private int versuche;
    private boolean spielBeendet;

    private final TextField eingabeFeld;
    private final Button ratenButton;
    private final Button resetButton;
    private final TextArea ausgabeKonsole;
    private final Label statusLabel;

    public Zahlenratespiel() {
        super("Zahlenratespiel");

        setLayout(new BorderLayout(5, 5));

        // Oberer Bereich: Eingabefeld + Buttons
        Panel eingabePanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        eingabePanel.add(new Label("Deine Zahl (1-100):"));

        eingabeFeld = new TextField(10);
        eingabePanel.add(eingabeFeld);

        ratenButton = new Button("raten");
        eingabePanel.add(ratenButton);

        resetButton = new Button("Reset");
        eingabePanel.add(resetButton);

        add(eingabePanel, BorderLayout.NORTH);

        // Mittlerer Bereich: Ausgabekonsole
        ausgabeKonsole = new TextArea("", 10, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ausgabeKonsole.setEditable(false);
        add(ausgabeKonsole, BorderLayout.CENTER);

        // Unterer Bereich: Statuszeile
        statusLabel = new Label();
        add(statusLabel, BorderLayout.SOUTH);

        // Listener registrieren
        ratenButton.addActionListener(this);
        resetButton.addActionListener(this);
        eingabeFeld.addActionListener(this); // Enter-Taste im Eingabefeld

        // Fenster schliessen
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        neuesSpiel();

        setSize(420, 350);
        zentriereFenster();
        setVisible(true);
    }

    private void zentriereFenster() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    private void neuesSpiel() {
        gesuchteZahl = MIN + (int) (Math.random() * (MAX - MIN + 1));
        versuche = 0;
        spielBeendet = false;
        eingabeFeld.setText("");
        eingabeFeld.setEditable(true);
        eingabeFeld.requestFocus();
        ausgabeKonsole.setText("");
        ausgabeKonsole.append("Neues Spiel gestartet!\n");
        ausgabeKonsole.append("Errate eine Zahl zwischen " + MIN + " und " + MAX + ".\n");
        ausgabeKonsole.append("Du hast " + MAX_VERSUCHE + " Versuche.\n");
        aktualisiereStatus();
    }

    private void aktualisiereStatus() {
        statusLabel.setText("Versuch " + versuche + " von " + MAX_VERSUCHE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object quelle = e.getSource();

        if (quelle == resetButton) {
            neuesSpiel();
            return;
        }

        if (quelle == ratenButton || quelle == eingabeFeld) {
            verarbeiteRateversuch();
        }
    }

    private void verarbeiteRateversuch() {
        if (spielBeendet) {
            ausgabeKonsole.append("Das Spiel ist beendet. Bitte klicke auf 'Reset', um neu zu starten.\n");
            return;
        }

        String eingabe = eingabeFeld.getText().trim();
        int zahl;
        try {
            zahl = Integer.parseInt(eingabe);
        } catch (NumberFormatException ex) {
            ausgabeKonsole.append("Bitte gib eine gueltige ganze Zahl ein.\n");
            eingabeFeld.setText("");
            return;
        }

        if (zahl < MIN || zahl > MAX) {
            ausgabeKonsole.append("Bitte gib eine Zahl zwischen " + MIN + " und " + MAX + " ein.\n");
            eingabeFeld.setText("");
            return;
        }

        versuche++;
        eingabeFeld.setText("");

        if (zahl == gesuchteZahl) {
            ausgabeKonsole.append("Versuch " + versuche + ": " + zahl + " -> Richtig! Du hast die Zahl erraten.\n");
            beendeSpiel(true);
        } else if (zahl < gesuchteZahl) {
            ausgabeKonsole.append("Versuch " + versuche + ": " + zahl + " -> Die Zahl ist groesser\n");
            pruefeVersucheUebrig();
        } else {
            ausgabeKonsole.append("Versuch " + versuche + ": " + zahl + " -> Die Zahl ist kleiner\n");
            pruefeVersucheUebrig();
        }

        aktualisiereStatus();
    }

    private void pruefeVersucheUebrig() {
        if (versuche >= MAX_VERSUCHE) {
            ausgabeKonsole.append("Keine Versuche mehr uebrig! Die gesuchte Zahl war " + gesuchteZahl + ".\n");
            beendeSpiel(false);
        }
    }

    private void beendeSpiel(boolean gewonnen) {
        spielBeendet = true;
        eingabeFeld.setEditable(false);
        ausgabeKonsole.append(gewonnen
                ? "Herzlichen Glueckwunsch! Klicke auf 'Reset', um erneut zu spielen.\n"
                : "Leider verloren. Klicke auf 'Reset', um erneut zu spielen.\n");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Zahlenratespiel::new);
    }
}
