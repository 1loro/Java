/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import POO.DaoMiembro;
import POO.miembro;
import POO.miembros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author lorem
 */

public class Controlador implements ActionListener {

    
    DaoMiembro dao = new DaoMiembro();
    miembros m = new miembros();
    miembro miembro= new miembro();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlador(miembro m){
        listar(m.tabla);
        
        this.miembro=m;
        this.miembro.btnListar.addActionListener(this);
        this.miembro.btnAgregar.addActionListener(this);
        this.miembro.btnModificar.addActionListener(this);
        this.miembro.btnGuardar.addActionListener(this);
        this.miembro.btnEliminar.addActionListener(this);
        this.miembro.btnFiltrar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==miembro.btnListar){
           listar(miembro.tabla);
           limpiarTabla();
           listar(miembro.tabla);
           
        }
        if(ae.getSource()==miembro.btnAgregar){
            agregar();
            limpiarTabla();
            listar(miembro.tabla);
        }
        if(ae.getSource()==miembro.btnModificar){
            int fila=miembro.tabla.getSelectedRow();
            if(fila==-1){
               JOptionPane.showMessageDialog(miembro, "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                int id=Integer.parseInt((String)miembro.tabla.getValueAt(fila,0).toString());
                String rut=(String)miembro.tabla.getValueAt(fila,1);
                String nombre=(String)miembro.tabla.getValueAt(fila,2);
                String paterno=(String)miembro.tabla.getValueAt(fila,3);
                String materno=(String)miembro.tabla.getValueAt(fila,4);
                String taller=(String)miembro.tabla.getValueAt(fila,5);
                int dias=Integer.parseInt((String)miembro.tabla.getValueAt(fila,6).toString());
                int cuota=Integer.parseInt((String)miembro.tabla.getValueAt(fila,7).toString());
                miembro.txtID.setText(""+id);
                miembro.txtRut.setText(rut);
                miembro.txtNombre.setText(nombre);
                miembro.txtPaterno.setText(paterno);
                miembro.txtMaterno.setText(materno);
                miembro.txtTaller.setText(taller);
                miembro.membresiaComboBox.setSelectedItem(dias);
                miembro.txtCuota.setText(""+cuota);
                         
            }
        }
        if (ae.getSource().equals(miembro.btnGuardar)) {
            actualizar();
            limpiarTabla();
            listar(miembro.tabla);
        }
if(ae.getSource() == miembro.btnEliminar) {
    int fila = miembro.tabla.getSelectedRow();

    if(fila == -1) {
        JOptionPane.showMessageDialog(miembro, "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        int id = Integer.parseInt(miembro.tabla.getValueAt(fila, 0).toString());

        // Mostrar el cuadro de diálogo de confirmación
        int respuesta = JOptionPane.showConfirmDialog(miembro, "¿Seguro que desea eliminar el miembro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            // Si el usuario elige "Sí", entonces procede con la eliminación
            dao.Delete(id);
            JOptionPane.showMessageDialog(miembro, "Miembro eliminado con éxito", "Eliminar usuario", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            listar(miembro.tabla);
        }
        // Si el usuario elige "No", no se hace nada
    }
}
        
        if(ae.getSource().equals(miembro.btnFiltrar)) {
            int dias = (int) miembro.filtroDias.getSelectedItem();
            String taller = miembro.filtroTaller.getText();
            
            if(dias == 0 && taller.isEmpty()) {
                  JOptionPane.showMessageDialog(miembro, "No hay filtros validos", "Error", JOptionPane.ERROR_MESSAGE);
            } else if(dias == 0 && !taller.isEmpty()) {
                List<miembros>lista=dao.filtroTaller(taller);
                
                  System.out.println(Arrays.toString(lista.toArray()));
                
                listar(miembro.tabla, lista);
            } else if(taller.isEmpty() && dias > 0) {
                List<miembros>lista=dao.filtroDias(dias);
                listar(miembro.tabla, lista);
            } else {
                List<miembros>lista=dao.filtroAmbos(dias, taller);
                listar(miembro.tabla, lista);
            }
        }
    }
    
    void limpiarTabla(){
        for (int i = 0; i <miembro.tabla.getRowCount(); i++){
            modelo.removeRow(i);
            i=i-1;
        }
    }
    
    
public void actualizar() {
    System.out.println("hola");
    String rut = miembro.txtRut.getText();
    String nombre = miembro.txtNombre.getText();
    String paterno = miembro.txtPaterno.getText();
    String materno = miembro.txtMaterno.getText();
    String taller = miembro.txtTaller.getText();
    String diasText = (String) miembro.membresiaComboBox.getSelectedItem();
    String cuotaText = miembro.txtCuota.getText();

    if (rut.isEmpty() || nombre.isEmpty() || paterno.isEmpty() || materno.isEmpty() || diasText.isEmpty() || cuotaText.isEmpty()) {
        JOptionPane.showMessageDialog(miembro, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        return; 
    }
    if (rut.length() > 10) {
        JOptionPane.showMessageDialog(miembro, "El rut no puede tener más de 10 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int id;
    int dias;
    int cuota;

    try {
        id = Integer.parseInt(miembro.txtID.getText());
        dias = Integer.parseInt(diasText);
        cuota = Integer.parseInt(cuotaText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(miembro, "Ingresa valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    m.setId(id);
    m.setRut(rut);
    m.setNombre(nombre);
    m.setPaterno(paterno);
    m.setMaterno(materno);
    m.setTaller(taller);
    m.setDias(dias);
    m.setCuota(cuota);

    boolean result = dao.actualizar(m);

    if (result) {
        JOptionPane.showMessageDialog(miembro, "Usuario modificado con éxito", "Modificación correcta", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(miembro, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
public void agregar() {
    String rut = miembro.txtRut.getText();
    String nombre = miembro.txtNombre.getText();
    String paterno = miembro.txtPaterno.getText();
    String materno = miembro.txtMaterno.getText();
    String taller = miembro.txtTaller.getText();
    String diasText = (String) miembro.membresiaComboBox.getSelectedItem();
    String cuotaText = miembro.txtCuota.getText();

    if (rut.isEmpty() || nombre.isEmpty() || paterno.isEmpty() || materno.isEmpty() || diasText.isEmpty() || cuotaText.isEmpty()) {
        JOptionPane.showMessageDialog(miembro, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    if (rut.length() > 10) {
        JOptionPane.showMessageDialog(miembro, "El rut no puede tener más de 10 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int dias;
    int cuota;

    try {
        dias = Integer.parseInt(diasText);
        cuota = Integer.parseInt(cuotaText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(miembro, "Tipo de datos invalidos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    m.setRut(rut);
    m.setNombre(nombre);
    m.setPaterno(paterno);
    m.setMaterno(materno);
    m.setTaller(taller);
    m.setDias(dias);
    m.setCuota(cuota);
    
    int r = dao.Agregar(m);

    if (r == 1) {
        JOptionPane.showMessageDialog(miembro, "Usuario agregado con éxito","Nuevo usuario",JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(miembro, "Error al agregar", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    public void listar (JTable tabla){
        List<miembros>lista=dao.listar();
        listar(tabla, lista);
    }
    
    public void listar (JTable tabla, List<miembros> lista){
        modelo=(DefaultTableModel)tabla.getModel();
        
        // Limpia los valores del modelo
        modelo.setRowCount(0);

        Object[]object=new Object[8];
        
        // Añade 1 x 1 los miembros al modelo
        for (int i = 0; i < lista.size() ; i++){
            object[0]=lista.get(i).getId();
            object[1]=lista.get(i).getRut();
            object[2]=lista.get(i).getNombre();
            object[3]=lista.get(i).getPaterno();
            object[4]=lista.get(i).getMaterno();
            object[5]=lista.get(i).getTaller();
            object[6]=lista.get(i).getDias();
            object[7]=lista.get(i).getCuota();
            modelo.addRow(object);
        }
        
        // Actualiza el modelo de la tabla
        miembro.tabla.setModel(modelo);
    }
    
}
