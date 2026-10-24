package edu.umg.biblioteca;
import edu.umg.biblioteca.vista.BibliotecaVista;
import javax.swing.SwingUtilities;
public class Main {public static void main(String[] args){SwingUtilities.invokeLater(()->new BibliotecaVista().setVisible(true));}}
