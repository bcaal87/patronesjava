package edu.umg.biblioteca.vista;
import edu.umg.biblioteca.controlador.LibroControlador;
import edu.umg.biblioteca.modelo.Libro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class BibliotecaVista extends JFrame {
 private final LibroControlador controlador=new LibroControlador();
 private final JTextField titulo=new JTextField(20),autor=new JTextField(20);
 private final DefaultTableModel modelo=new DefaultTableModel(new Object[]{"ID","Título","Autor"},0){public boolean isCellEditable(int r,int c){return false;}};
 private final JTable tabla=new JTable(modelo);
 public BibliotecaVista(){
  super("Biblioteca MVC · UMG");setDefaultCloseOperation(EXIT_ON_CLOSE);setSize(800,500);setLocationRelativeTo(null);
  JPanel form=new JPanel(new GridLayout(2,2,8,8));form.add(new JLabel("Título"));form.add(titulo);form.add(new JLabel("Autor"));form.add(autor);
  JPanel botones=new JPanel(); JButton agregar=new JButton("Registrar"),actualizar=new JButton("Actualizar"),borrar=new JButton("Eliminar"),refrescar=new JButton("Listar");
  for(JButton b:new JButton[]{agregar,actualizar,borrar,refrescar}) botones.add(b);
  JPanel arriba=new JPanel(new BorderLayout());arriba.add(form,BorderLayout.CENTER);arriba.add(botones,BorderLayout.SOUTH);
  add(arriba,BorderLayout.NORTH);add(new JScrollPane(tabla),BorderLayout.CENTER);
  agregar.addActionListener(e->accion(()->{controlador.crear(titulo.getText(),autor.getText());cargar();limpiar();}));
  actualizar.addActionListener(e->accion(()->{controlador.editar(idSeleccionado(),titulo.getText(),autor.getText());cargar();}));
  borrar.addActionListener(e->accion(()->{controlador.borrar(idSeleccionado());cargar();limpiar();}));
  refrescar.addActionListener(e->accion(this::cargar));
  tabla.getSelectionModel().addListSelectionListener(e->{if(!e.getValueIsAdjusting()&&tabla.getSelectedRow()>=0){int row=tabla.convertRowIndexToModel(tabla.getSelectedRow());titulo.setText(modelo.getValueAt(row,1).toString());autor.setText(modelo.getValueAt(row,2).toString());}});
  accion(this::cargar);
 }
 private int idSeleccionado(){int row=tabla.getSelectedRow();if(row<0)throw new IllegalArgumentException("Seleccione un libro");return (int)modelo.getValueAt(tabla.convertRowIndexToModel(row),0);}
 private void limpiar(){titulo.setText("");autor.setText("");tabla.clearSelection();}
 private void cargar() throws Exception {modelo.setRowCount(0);for(Libro l:controlador.listar())modelo.addRow(new Object[]{l.id(),l.titulo(),l.autor()});}
 private void accion(Operacion o){try{o.ejecutar();}catch(Exception ex){JOptionPane.showMessageDialog(this,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}}
 @FunctionalInterface private interface Operacion {void ejecutar() throws Exception;}
}
