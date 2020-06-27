package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(null /* null est à remplacer */);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    push();
                } catch (PilePleineException exc){
                    exc.printStackTrace();
                }
            }
        });
        boutons.add(add);   add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    add();
                } catch (PilePleineException exc){
                    exc.printStackTrace();
                } catch (PileVideException exc2){
                    exc2.printStackTrace();
                }
            }
        });
        boutons.add(sub);   sub.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    push();
                } catch (PilePleineException exc){
                    exc.printStackTrace();
                }
            }
        });
        boutons.add(mul);   mul.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    mul();
                } catch (PilePleineException exc){
                    exc.printStackTrace();
                } catch (PileVideException exc2){
                    exc2.printStackTrace();
                }
            }
        });
        boutons.add(div);   div.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    div();
                } catch (PilePleineException exc){
                    exc.printStackTrace();
                } catch (PileVideException exc2){
                    exc2.printStackTrace();
                }
            }
        });
        boutons.add(clear); clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    clear();
                } catch (PileVideException exc){
                    exc.printStackTrace();
                }
            }
        });
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        add.setEnabled(true);
        sub.setEnabled(true);
        mul.setEnabled(true);
        div.setEnabled(true);
        
        if (pile.taille() < 2) {
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
        }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)
    
    public void push() throws PilePleineException {
        pile.empiler(operande());
        actualiserInterface();
    }
    
    public void add() throws PilePleineException, PileVideException {
        pile.empiler(pile.depiler() + pile.depiler());
        donnee.setText(pile.toString());
        actualiserInterface();
    }
    
    public void sub() throws PilePleineException, PileVideException {
        pile.empiler(pile.depiler() - pile.depiler());
        actualiserInterface();
    }
    
    public void mul() throws PilePleineException, PileVideException {
        pile.empiler(pile.depiler() * pile.depiler());
        actualiserInterface();
    }
    
    public void div() throws PilePleineException, PileVideException {
        pile.empiler(pile.depiler() / pile.depiler());
        actualiserInterface();
    }
    
    public void clear() throws PileVideException {
        for(int i=pile.taille();i>0;i--) {
            pile.depiler();
        }
        actualiserInterface();
    }
}
