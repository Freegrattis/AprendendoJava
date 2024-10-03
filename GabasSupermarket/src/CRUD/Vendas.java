/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CRUD;

import CONEXAO_BANCO.Banco_dados;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author reg3jvl
 */
public class Vendas extends javax.swing.JDialog {

    /**
     * Creates new form Vendas
     */
    public Vendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    Banco_dados bd = new Banco_dados();

   
    private void pesquisaCliente(){
        if(bd.getConnection()){
            try{
                String cliente="";
                String query = "SELECT * FROM cliente WHERE nome_cli LIKE ?";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1, "%"+jTNomeClienteV.getText()+"%");
                ResultSet rs = smtp.executeQuery();
                
                while(rs.next()){
                    String add1 = rs.getString("idCliente");
                    jTCodigoClienteV.setText(add1);
                    
                    
                    String add2 = rs.getString("cpf_cli");
                    jTCpfClienteV.setText(add2);
                    
                    String add3 = rs.getString("nome_cli");
                    jTNomeClienteV.setText(add3);
                };
                smtp.close();
                bd.conexao.close();
            }catch(SQLException e){
                System.out.println("Error when searching... "+e.toString());
            }
        }
    }
    
    private void venda(){
        if(bd.getConnection()){
            try{
                String query = "INSERT vendas(cliente_idCliente) VALUES(?)";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1, jTCodigoClienteV.getText());
                smtp.executeUpdate();
                smtp.close();
                bd.conexao.close();
            }catch(SQLException e){
                System.out.println("Error when searching... "+e.toString());
            }
        }
    }

    private void pesquisaProduto(){
        if(bd.getConnection()){
               try{
                String produto="";
                String query = "SELECT * FROM produto WHERE id_produto LIKE ?";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1, "%"+jTCodigoProdutoV.getText()+"%");
                ResultSet rs = smtp.executeQuery();
                
                while(rs.next()){
                    String add1 = rs.getString("id_produto");
                    jTCodigoProdutoV.setText(add1);
                    
                    String add2 = rs.getString("descricao_produto");
                    jTProdutoV.setText(add2);
                    
                    String add3 = rs.getString("valor_unitario_produto");
                    jTPrecoUnitarioV.setText(add3);
                };
                smtp.close();
                bd.conexao.close();
            }catch(SQLException e){
                System.out.println("Error when searching... "+e.toString());
            }
        }
    }
    
    private void idVenda(){
        if(bd.getConnection()){
            try{
                String query = "SELECT MAX(id_vendas) AS id_vendas FROM vendas";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                ResultSet rs = smtp.executeQuery();
                while (rs.next()){
                    String add1 = rs.getString("id_vendas");
                    jTNotaFiscalV.setText(add1);
                }
                smtp.close();
                bd.conexao.close();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null,"Error! Try again."
                        +erro.toString());
            }
        }
    }
    
    private void calcularPrecoProduto(){
        float quantidade = Float.parseFloat(jTQuantidadeV.getText());
        float valor = Float.parseFloat(jTPrecoUnitarioV.getText());
        float total = quantidade * valor;
        jTValorTotalV.setText(String.valueOf(total));
    }
    
    private void adicionarItensVendas(){
        if(bd.getConnection()){
            try{
                String query = "INSERT item (fk_id_vendas,fk_id_produto,item_valor,item_total,"
                        + "item_quantidade) VALUES (?,?,?,?,?)";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1,jTNotaFiscalV.getText());
                smtp.setString(2, jTCodigoProdutoV.getText());
                smtp.setString(3, jTPrecoUnitarioV.getText());
                smtp.setString(4, jTValorTotalV.getText());
                smtp.setString(5, jTQuantidadeV.getText());
                smtp.executeUpdate();
                smtp.close();
                bd.conexao.close();

            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null,"Error! Try again."
                        +erro.toString());
            }
        }
    }
    
    private void limparCampos(JPanel jPanel){
        Component[] componentes = jPanel.getComponents();
        for(Component componente : componentes){
            if(componente instanceof JTextField){
                JTextField camposTF = (JTextField)componente;
                camposTF.setText("");
            }
        }
    }
 
    private void limparTabela(){
        DefaultTableModel table = (DefaultTableModel)jTabelaNotaFiscalV.getModel();
        table.setNumRows(0);
    }
    
    private void somarNotaFiscal(){
        if(bd.getConnection()){
            try{
                String query = "SELECT SUM(item_total) AS total FROM item "
                        + "WHERE fk_id_vendas = ?";
                
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1,jTNotaFiscalV.getText());
                ResultSet rs;
                rs = smtp.executeQuery();
                if(rs.next()){
                    String add1 = rs.getString("total");
                    jTValorTotalVenda.setText(add1);
                }
                rs.close();
                smtp.close();
                bd.conexao.close();
                
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null,"Error! Try again."
                        +erro.toString());
            }
        }
    }
    
    private void consultarNotaFiscal(){
        if(bd.getConnection()){
            try{
                String query = "SELECT i.fk_id_produto, "
                        + "produto.descricao_produto,"
                        + "i.item_valor,"
                        + "i.item_quantidade,"
                        + "i.item_total "
                        + "FROM item AS i "
                        + "INNER JOIN produto ON i.fk_id_produto = produto.id_produto "
                        + "WHERE i.fk_id_vendas = ?";
                
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1,jTNotaFiscalV.getText());
                ResultSet rs;
                rs = smtp.executeQuery();
                DefaultTableModel model = (DefaultTableModel)jTabelaNotaFiscalV.getModel();
                model.setNumRows(0);
                while(rs.next()){
                    model.addRow(new Object[]{
                        rs.getString("fk_id_produto"),
                        rs.getString("descricao_produto"),
                        rs.getString("item_valor"),
                        rs.getString("item_quantidade"),
                        rs.getString("item_total")});
                }
                rs.close();
                smtp.close();
                bd.conexao.close();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null,"Error! Try again."
                        +erro.toString());
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelClienteV = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTCpfClienteV = new javax.swing.JTextField();
        jTCodigoClienteV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTNomeClienteV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanelProdutoV = new javax.swing.JPanel();
        jTProdutoV = new javax.swing.JTextField();
        jBIniciarVenda = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTQuantidadeV = new javax.swing.JTextField();
        jTPrecoUnitarioV = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTValorTotalV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jBAdicionar = new javax.swing.JButton();
        jTCodigoProdutoV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTNotaFiscalV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaNotaFiscalV = new javax.swing.JTable();
        jBNovaVenda = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTValorTotalVenda = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("VENDA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        jPanelClienteV.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Data"));

        jLabel2.setText("Code:");

        jLabel4.setText("CPF:");

        jTNomeClienteV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNomeClienteVActionPerformed(evt);
            }
        });

        jLabel3.setText("Name:");

        javax.swing.GroupLayout jPanelClienteVLayout = new javax.swing.GroupLayout(jPanelClienteV);
        jPanelClienteV.setLayout(jPanelClienteVLayout);
        jPanelClienteVLayout.setHorizontalGroup(
            jPanelClienteVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteVLayout.createSequentialGroup()
                .addGroup(jPanelClienteVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClienteVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTNomeClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelClienteVLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanelClienteVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelClienteVLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4))
                    .addComponent(jTCpfClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelClienteVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTCodigoClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelClienteVLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelClienteVLayout.setVerticalGroup(
            jPanelClienteVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClienteVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelClienteVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTCodigoClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTCpfClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTNomeClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanelProdutoV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Product Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jBIniciarVenda.setBackground(new java.awt.Color(0, 204, 0));
        jBIniciarVenda.setText("Start Selling");
        jBIniciarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIniciarVendaActionPerformed(evt);
            }
        });

        jLabel7.setText("Product:");

        jLabel8.setText("Unitary value:");

        jTQuantidadeV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTQuantidadeVActionPerformed(evt);
            }
        });

        jLabel9.setText("Amount:");

        jTValorTotalV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTValorTotalVActionPerformed(evt);
            }
        });

        jLabel6.setText("Code:");

        jBAdicionar.setText("ADD");
        jBAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAdicionarActionPerformed(evt);
            }
        });

        jTCodigoProdutoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTCodigoProdutoVActionPerformed(evt);
            }
        });

        jLabel5.setText("Invoice:");

        jLabel12.setText("Value:");

        javax.swing.GroupLayout jPanelProdutoVLayout = new javax.swing.GroupLayout(jPanelProdutoV);
        jPanelProdutoV.setLayout(jPanelProdutoVLayout);
        jPanelProdutoVLayout.setHorizontalGroup(
            jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoVLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoVLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(106, 106, 106))
                    .addGroup(jPanelProdutoVLayout.createSequentialGroup()
                        .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelProdutoVLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNotaFiscalV, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBIniciarVenda))
                            .addGroup(jPanelProdutoVLayout.createSequentialGroup()
                                .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTCodigoProdutoV)
                                        .addComponent(jTPrecoUnitarioV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelProdutoVLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel8)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoVLayout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(80, 80, 80)
                                            .addComponent(jLabel12)
                                            .addGap(28, 28, 28))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoVLayout.createSequentialGroup()
                                            .addComponent(jTQuantidadeV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTValorTotalV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jTProdutoV, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanelProdutoVLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jBAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelProdutoVLayout.setVerticalGroup(
            jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTNotaFiscalV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBIniciarVenda))
                .addGap(26, 26, 26)
                .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTCodigoProdutoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTProdutoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanelProdutoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTPrecoUnitarioV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTQuantidadeV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTValorTotalV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jBAdicionar)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setText(" Value:  R$");

        jTabelaNotaFiscalV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Description", "Unitary Value", "Amount", "Value"
            }
        ));
        jScrollPane1.setViewportView(jTabelaNotaFiscalV);

        jBNovaVenda.setBackground(new java.awt.Color(0, 204, 51));
        jBNovaVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBNovaVenda.setForeground(new java.awt.Color(255, 255, 255));
        jBNovaVenda.setText("New Selling");
        jBNovaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNovaVendaActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancel");

        jButton2.setBackground(new java.awt.Color(255, 0, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Finalize Sale");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelClienteV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelProdutoV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTValorTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelClienteV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelProdutoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTValorTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        setJMenuBar(jMenuBar1);

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

    private void jTCodigoProdutoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTCodigoProdutoVActionPerformed
        pesquisaProduto();
    }//GEN-LAST:event_jTCodigoProdutoVActionPerformed

    private void jTNomeClienteVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNomeClienteVActionPerformed
        pesquisaCliente();
    }//GEN-LAST:event_jTNomeClienteVActionPerformed

    private void jBIniciarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIniciarVendaActionPerformed
        venda();
        idVenda();
    }//GEN-LAST:event_jBIniciarVendaActionPerformed

    private void jBAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAdicionarActionPerformed
        adicionarItensVendas();
        consultarNotaFiscal();
        somarNotaFiscal();
    }//GEN-LAST:event_jBAdicionarActionPerformed

    private void jTQuantidadeVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTQuantidadeVActionPerformed
        calcularPrecoProduto();// TODO add your handling code here:
    }//GEN-LAST:event_jTQuantidadeVActionPerformed

    private void jBNovaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNovaVendaActionPerformed
        limparTabela();
        limparCampos(jPanelClienteV);
        limparCampos(jPanelProdutoV);
    }//GEN-LAST:event_jBNovaVendaActionPerformed

    private void jTValorTotalVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTValorTotalVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTValorTotalVActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TelaPagamento pgto = new TelaPagamento(null, true);
        pgto.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Vendas dialog = new Vendas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBAdicionar;
    private javax.swing.JButton jBIniciarVenda;
    private javax.swing.JButton jBNovaVenda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelClienteV;
    private javax.swing.JPanel jPanelProdutoV;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTCodigoClienteV;
    private javax.swing.JTextField jTCodigoProdutoV;
    private javax.swing.JTextField jTCpfClienteV;
    private javax.swing.JTextField jTNomeClienteV;
    private javax.swing.JTextField jTNotaFiscalV;
    private javax.swing.JTextField jTPrecoUnitarioV;
    private javax.swing.JTextField jTProdutoV;
    private javax.swing.JTextField jTQuantidadeV;
    private javax.swing.JTextField jTValorTotalV;
    private javax.swing.JTextField jTValorTotalVenda;
    private javax.swing.JTable jTabelaNotaFiscalV;
    // End of variables declaration//GEN-END:variables
}
