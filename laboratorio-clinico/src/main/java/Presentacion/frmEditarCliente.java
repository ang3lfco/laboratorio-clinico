/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Dtos.EditarClienteDTO;
import Dtos.GuardarClienteDTO;
import Negocio.IClienteNegocio;
import Negocio.NegocioException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ang3lfco
 */
public class frmEditarCliente extends javax.swing.JFrame {
    private IClienteNegocio clienteNegocio;
    private EditarClienteDTO cliente;
    /**
     * Creates new form frmRegistrarCliente
     */
    public frmEditarCliente(IClienteNegocio clienteNegocio, EditarClienteDTO cliente) {
        
        this.cliente = cliente;
        this.clienteNegocio = clienteNegocio;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cargarDatosTabla();
    }
    
    private void cargarDatosTabla(){
        String nombre = cliente.getNombres();
        String apellidoPaterno = cliente.getApellidoPaterno();
        String apellidoMaterno = cliente.getApellidoMaterno();
        LocalDateTime fecha = cliente.getFechaNacimiento();
        LocalDate fechaNacimiento = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth());
        
        txfNombre.setText(nombre);
        txfApellidoPaterno.setText(apellidoPaterno);
        txfApellidoMaterno.setText(apellidoMaterno);
        dtpFechaNacimiento.setDate(fechaNacimiento);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txfNombre = new javax.swing.JTextField();
        txfApellidoPaterno = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        txfApellidoMaterno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        dtpFechaNacimiento = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txfNombre.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txfNombre.setText("Nombre");

        txfApellidoPaterno.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txfApellidoPaterno.setText("Apellido Paterno");

        btnRegistrar.setText("Registrar");
        btnRegistrar.setBackground(new java.awt.Color(52, 71, 169));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseClicked(evt);
            }
        });

        txfApellidoMaterno.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txfApellidoMaterno.setText("Apellido Materno");

        jLabel1.setText("Editar cliente.");
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 19, 71));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txfNombre)
                            .addComponent(txfApellidoPaterno, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(txfApellidoMaterno)
                            .addComponent(dtpFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtpFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnRegistrar)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseClicked
        // TODO add your handling code here:
        String nombre = txfNombre.getText();
        String apellidoPaterno = txfApellidoPaterno.getText();
        String apellidoMaterno = txfApellidoMaterno.getText();
        
        LocalDate fechaNacimiento = dtpFechaNacimiento.getDate();
        LocalDateTime fecha = LocalDateTime.of(fechaNacimiento, LocalTime.MIN);
        EditarClienteDTO cliente = new EditarClienteDTO(this.cliente.getId(),nombre, apellidoPaterno, apellidoMaterno, fecha);
        
        try{
            clienteNegocio.editar(cliente);
            JOptionPane.showMessageDialog(null, "Client: "+ nombre + "editado.");
            this.dispose();
        }
        catch(NegocioException e){
            JOptionPane.showMessageDialog(null, "Error al editar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frmRegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmRegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmRegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmRegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmRegistrarCliente().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private com.github.lgooddatepicker.components.DatePicker dtpFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txfApellidoMaterno;
    private javax.swing.JTextField txfApellidoPaterno;
    private javax.swing.JTextField txfNombre;
    // End of variables declaration//GEN-END:variables
}
