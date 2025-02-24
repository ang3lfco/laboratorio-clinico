/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Dtos.ClienteDTO;
import Dtos.ClienteTablaDTO;
import Dtos.EditarClienteDTO;
import Negocio.IClienteNegocio;
import Negocio.NegocioException;
import Utilidades.ButtonEditor;
import Utilidades.ButtonRenderer;
import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ang3lfco
 */
public class frmAdmClientes extends javax.swing.JFrame {
    private IClienteNegocio clienteNegocio;
    /**
     * Creates new form frmAdmClientes
     */
    public frmAdmClientes(IClienteNegocio clienteNegocio) throws NegocioException {
        initComponents();
        this.clienteNegocio = clienteNegocio;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cargarDatos();
        configurarTabla();
        
    }
    
    private void cargarDatos() throws NegocioException {
        List<ClienteTablaDTO> clientes = clienteNegocio.buscarClientes();
        List<ClienteTablaDTO> clientesDisponibles = new ArrayList<>();
        clientesDisponibles.clear();
        
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setRowCount(0);
        for(ClienteTablaDTO c : clientes){
            Object[] row = new Object[4];
            row[0] = c.getNombres();
            row[1] = c.getApellidoPaterno();
            row[2] = c.getApellidoMaterno();
            row[3] = c.getFechaNacimiento();
            model.addRow(row);
        }
        tblClientes.setRowHeight(40);
        
        tblClientes.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                ClienteTablaDTO cliente = clientes.get(row);
                if (cliente.isEstaBorrado()) {
                    cell.setBackground(new java.awt.Color(255, 200, 200));
                    cell.setForeground(java.awt.Color.GRAY);
                }
                else {
                    cell.setBackground(java.awt.Color.WHITE); 
                    cell.setForeground(java.awt.Color.BLACK);
                }
                return cell;
            }
        });
    }
    
    private void configurarTabla() {
        tblClientes.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());

        ButtonEditor editor = new ButtonEditor(
                e -> {
            try {
                EditarClienteDTO clienteEditado;
                
                ClienteTablaDTO clienteAEditar = clienteNegocio.buscarClientes().get(tblClientes.getSelectedRow());
                
                clienteEditado = new EditarClienteDTO(
                        clienteAEditar.getId(),
                        clienteAEditar.getNombres(),
                        clienteAEditar.getApellidoPaterno(),
                        clienteAEditar.getApellidoMaterno(),
                        (LocalDateTime.of(clienteAEditar.getFechaNacimiento().getYear(),
                                clienteAEditar.getFechaNacimiento().getMonthValue(),
                                clienteAEditar.getFechaNacimiento().getDayOfMonth(),
                                0, 0) ));
                
                
                frmEditarCliente frmEditar = new frmEditarCliente(clienteNegocio,clienteEditado);
                frmEditar.setVisible(true);
                
                
            } catch (NegocioException ex) {
                Logger.getLogger(frmAdmClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
                },
                e -> {
            try {
                ClienteDTO cliente;
                
                ClienteTablaDTO clienteAEditar = clienteNegocio.buscarClientes().get(tblClientes.getSelectedRow());
                
                cliente = new ClienteDTO(
                        clienteAEditar.getId(),
                        clienteAEditar.getNombres(),
                        clienteAEditar.getApellidoPaterno(),
                        clienteAEditar.getApellidoMaterno(),
                        clienteAEditar.getFechaNacimiento(),
                        false);
                
                
                frmEliminarCliente frmEliminar = new frmEliminarCliente(clienteNegocio,cliente);
                frmEliminar.setVisible(true);
            } catch (NegocioException ex) {
                Logger.getLogger(frmAdmClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        );

        tblClientes.getColumnModel().getColumn(4).setCellEditor(editor);
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 19, 71));
        jLabel1.setText("Administracion de Clientes.");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha de Nacimiento", ""
            }
        ));
        tblClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
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

//    /**
//     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(frmAdmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmAdmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmAdmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmAdmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmAdmClientes().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    // End of variables declaration//GEN-END:variables
}
