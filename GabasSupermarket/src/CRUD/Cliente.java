/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CRUD;

import CONEXAO_BANCO.Banco_dados;
import java.awt.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author reg3jvl
 */
public class Cliente extends javax.swing.JDialog {

    

    
    
    /**
     * Creates new form Cliente
     */
    public Cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        pesquisaCliente();
        initTableListener();        
    }
    Banco_dados bd = new Banco_dados();
    
    private void cadastroCliente(){
        if(bd.getConnection()){
            try{
                String query = "INSERT cliente(nome_cli,cpf_cli,datanasci_cli,"
                        +"endereco_cli,cidade_cli,sexo_cli,casa_cli,defic_cli) VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1, jTNome.getText());
                smtp.setString(2, jTCpf.getText());
                smtp.setString(3, jTDataNascimento.getText());
                smtp.setString(4, jTEndereco.getText());
                smtp.setString(5, jTCidade.getText());
                
                String valorSelecionado = jCSexo.getSelectedItem().toString();
                smtp.setString(6,valorSelecionado);
                
                if (jCCasaPropria.isSelected()){
                    smtp.setString(7, "Yes");
                }else{
                    smtp.setString(7, "No");
                }
                
                if (jRDeficiencia.isSelected()){
                    smtp.setString(8, "Yes");
                }else{
                    smtp.setString(8, "No");
                }
                    
               smtp.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registered!");
                smtp.close();
                bd.conexao.close();
                
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null,"Error! Try again."
                        +erro.toString());
            }
        }
    }
    
    private void pesquisaCliente(){
        if(bd.getConnection()){
            try{
                String query = "SELECT * FROM cliente WHERE nome_cli LIKE ?";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1, "%"+jTPesquisar.getText()+"%");
                ResultSet rs = smtp.executeQuery();
                DefaultTableModel tabela = (DefaultTableModel) jTabelaPesquisa.getModel();
                tabela.setNumRows(0);
                while(rs.next()){
                    tabela.addRow(new Object[]{
                    rs.getString("idCliente"),
                    rs.getString("nome_cli"),
                    rs.getString("cpf_cli"),
                    rs.getString("endereco_cli"),
                    rs.getString("cidade_cli"),
                    rs.getString("datanasci_cli"),
                    rs.getString("sexo_cli")

                });
                }
                smtp.close();
                bd.conexao.close();
            }catch(SQLException e){
                System.out.println("Error when searching... "+e.toString());
            }
        }
    }
    
    private void initTableListener(){
        jTabelaPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int selectedRow = jTabelaPesquisa.getSelectedRow();
                if(selectedRow != -1){
                    jLId.setText(jTabelaPesquisa.getValueAt(selectedRow, 0).toString());
                    jTNome.setText(jTabelaPesquisa.getValueAt(selectedRow, 1).toString());
                    jTCpf.setText(jTabelaPesquisa.getValueAt(selectedRow, 2).toString());
                    jTEndereco.setText(jTabelaPesquisa.getValueAt(selectedRow, 3).toString());
                    jTCidade.setText(jTabelaPesquisa.getValueAt(selectedRow, 4).toString());
                    jTDataNascimento.setText(jTabelaPesquisa.getValueAt(selectedRow, 5).toString());
                    jCSexo.setSelectedItem(jTabelaPesquisa.getValueAt(selectedRow, 6).toString());
                }
            }
        }); 
    }   
    
    private void alterarCliente(){
        if (bd.getConnection()){
            try{
                String query = "UPDATE cliente SET nome_cli = ?, cpf_cli = ?,endereco_cli = ?,"
                        +"cidade_cli = ?,datanasci_cli = ?,sexo_cli = ? WHERE idCliente = ?";
                PreparedStatement alterar= bd.conexao.prepareStatement(query);
                
                alterar.setString(1,jTNome.getText());
                alterar.setString(2,jTCpf.getText());
                alterar.setString(3,jTEndereco.getText());
                alterar.setString(4,jTCidade.getText());
                alterar.setString(5,jTDataNascimento.getText());
                alterar.setString(6,jCSexo.getSelectedItem().toString());
                alterar.setString(7,jLId.getText());

                 
                alterar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Altered!");
                alterar.close();
                bd.conexao.close();
                
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null,"Error! Try again."
                        +erro.toString());
                 
            }
        }
    }
    
    private void excluirCliente(){
        if(bd.getConnection()){
            try{
                String query = "DELETE FROM cliente WHERE idCliente = ?";
                PreparedStatement excluir = bd.conexao.prepareStatement(query);
                String index = (String)jTabelaPesquisa.getModel().getValueAt(jTabelaPesquisa.getSelectedRow(), 0);
                excluir.setString(1, index);
                
                int escolha = JOptionPane.showConfirmDialog(null,"Deseja excluir o cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if(escolha==JOptionPane.YES_OPTION){
                    int resultado = excluir.executeUpdate();
                    if(resultado>0){
                        JOptionPane.showMessageDialog(null, "Deleted!"); 
                    }else{
                        JOptionPane.showMessageDialog(null, "Error in deletion!");
                    }
                    excluir.close();
                    bd.conexao.close();
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Error! Try again."
                        +e.toString());
            }
        }
    }
    
    private void limparCampos(JPanel jPanel){
        Component[] componentes = jPanel3.getComponents();
        for(Component componente : componentes){
            if(componente instanceof JTextField){
                JTextField camposTF = (JTextField)componente;
                camposTF.setText("");
            }
        }
    }
    
    private void limparTabela(){
        DefaultTableModel table = (DefaultTableModel)jTabelaPesquisa.getModel();
        table.setNumRows(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jPanelCliente = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jBAlterar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jCCasaPropria = new javax.swing.JCheckBox();
        jBExcluir = new javax.swing.JButton();
        jTCpf = new javax.swing.JTextField();
        jTEndereco = new javax.swing.JTextField();
        jBLimparCadastro = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCSexo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jRDeficiencia = new javax.swing.JRadioButton();
        jBCadastrar = new javax.swing.JButton();
        jTDataNascimento = new javax.swing.JTextField();
        jTCidade = new javax.swing.JTextField();
        jLId = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaPesquisa = new javax.swing.JTable();
        jBLimparTabela = new javax.swing.JButton();
        jTPesquisar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jTextField6.setText("jTextField6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelCliente.setBackground(new java.awt.Color(153, 204, 255));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Registration"));

        jBAlterar.setBackground(new java.awt.Color(0, 204, 0));
        jBAlterar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBAlterar.setForeground(new java.awt.Color(255, 255, 255));
        jBAlterar.setText("Alter");
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
            }
        });

        jBCancelar.setBackground(new java.awt.Color(51, 51, 51));
        jBCancelar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jBCancelar.setText("Cancel");

        jLabel5.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jLabel5.setText("Birth date:");

        jCCasaPropria.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jCCasaPropria.setText("Do you have your own home?");

        jBExcluir.setBackground(new java.awt.Color(255, 0, 0));
        jBExcluir.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBExcluir.setForeground(new java.awt.Color(255, 255, 255));
        jBExcluir.setText("Delete");
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        jBLimparCadastro.setBackground(new java.awt.Color(255, 0, 153));
        jBLimparCadastro.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBLimparCadastro.setForeground(new java.awt.Color(255, 255, 255));
        jBLimparCadastro.setText("Clean");
        jBLimparCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparCadastroActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jLabel3.setText("Address");

        jLabel6.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jLabel6.setText("CPF:");

        jCSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gender:", "Male", "Fem", "Other" }));

        jLabel4.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jLabel4.setText("City:");

        jRDeficiencia.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jRDeficiencia.setText("Do I have any disabilities?");

        jBCadastrar.setBackground(new java.awt.Color(0, 0, 255));
        jBCadastrar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        jBCadastrar.setText("Register");
        jBCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTDataNascimento))
                            .addComponent(jCSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTCpf))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNome, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTCidade))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jRDeficiencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCCasaPropria, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLId, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jBCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBLimparCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jCSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCCasaPropria)
                        .addGap(32, 32, 32)
                        .addComponent(jRDeficiencia)))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAlterar)
                    .addComponent(jBLimparCadastro)
                    .addComponent(jBCancelar)
                    .addComponent(jBCadastrar)
                    .addComponent(jBExcluir))
                .addGap(20, 20, 20))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
        jPanel4.setToolTipText("");
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel8.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jLabel8.setText("Name:");

        jTabelaPesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Cliente", "CPF", "Endereço", "Cidade", "Data Nasci.", "Sexo"
            }
        ));
        jScrollPane1.setViewportView(jTabelaPesquisa);

        jBLimparTabela.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBLimparTabela.setText("Clean");
        jBLimparTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparTabelaActionPerformed(evt);
            }
        });

        jTPesquisar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBLimparTabela)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jBLimparTabela))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelClienteLayout = new javax.swing.GroupLayout(jPanelCliente);
        jPanelCliente.setLayout(jPanelClienteLayout);
        jPanelClienteLayout.setHorizontalGroup(
            jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanelClienteLayout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelClienteLayout.setVerticalGroup(
            jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteLayout.createSequentialGroup()
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClienteLayout.createSequentialGroup()
                        .addGap(662, 662, 662)
                        .addComponent(jLabel7))
                    .addGroup(jPanelClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastrarActionPerformed
        cadastroCliente();
        limparCampos(jPanelCliente);
       // limparDados();
    }//GEN-LAST:event_jBCadastrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       pesquisaCliente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
        alterarCliente();
        limparCampos(jPanelCliente);
        limparTabela();
        pesquisaCliente();
    }//GEN-LAST:event_jBAlterarActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        excluirCliente();
        limparCampos(jPanelCliente);
        limparTabela();
        pesquisaCliente();
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jBLimparTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparTabelaActionPerformed
        limparTabela();
        
    }//GEN-LAST:event_jBLimparTabelaActionPerformed

    private void jBLimparCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparCadastroActionPerformed
        limparCampos(jPanelCliente);
    }//GEN-LAST:event_jBLimparCadastroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cliente dialog = new Cliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAlterar;
    private javax.swing.JButton jBCadastrar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBLimparCadastro;
    private javax.swing.JButton jBLimparTabela;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCCasaPropria;
    private javax.swing.JComboBox<String> jCSexo;
    private javax.swing.JLabel jLId;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelCliente;
    private javax.swing.JRadioButton jRDeficiencia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTCidade;
    private javax.swing.JTextField jTCpf;
    private javax.swing.JTextField jTDataNascimento;
    private javax.swing.JTextField jTEndereco;
    private javax.swing.JTextField jTNome;
    private javax.swing.JTextField jTPesquisar;
    private javax.swing.JTable jTabelaPesquisa;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
