package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.DaoAluno;
import modelo.Aluno;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

import java.text.SimpleDateFormat;

public class Aplicacao extends JFrame {
	
	private Aluno aluno;
	private DaoAluno dao;
	private List<Aluno> listaAlunos;
	private DefaultTableModel tableModel;
	private int idRef;
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputIdade;
	private JTable table;
	private JLabel lblHora;
	private JLabel lblData;
	private Timer timer;
	
    public Aplicacao() {
    	getContentPane().setForeground(SystemColor.textHighlight);
    	getContentPane().setLayout(null);
    	
    	JPanel panel = new JPanel();
    	panel.setBackground(new Color(224, 255, 255));
    	panel.setBounds(10, 11, 614, 439);
    	getContentPane().add(panel);
    	panel.setLayout(null);
    	
    	JLabel lblTitulo = new JLabel("CRUD - Alunos");
    	lblTitulo.setBounds(203, 5, 207, 36);
    	lblTitulo.setForeground(new Color(0, 0, 255));
    	lblTitulo.setFont(new Font("Arial", Font.ITALIC, 30));
    	panel.add(lblTitulo);
    	
    	inputNome = new JTextField();
    	inputNome.setBounds(29, 91, 169, 27);
    	panel.add(inputNome);
    	inputNome.setColumns(10);
    	
    	JLabel lblNome = new JLabel("Nome");
    	lblNome.setLabelFor(inputNome);
    	lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblNome.setBounds(29, 72, 46, 14);
    	panel.add(lblNome);
    	
    	JLabel lblEmail = new JLabel("E-mail");
    	lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblEmail.setBounds(255, 72, 46, 14);
    	panel.add(lblEmail);
    	
    	inputEmail = new JTextField();
    	inputEmail.setBounds(255, 91, 207, 27);
    	panel.add(inputEmail);
    	inputEmail.setColumns(10);
    	
    	JLabel lblIdade = new JLabel("Idade");
    	lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	lblIdade.setBounds(509, 72, 46, 14);
    	panel.add(lblIdade);
    	
    	inputIdade = new JTextField();
    	lblIdade.setLabelFor(inputIdade);
    	inputIdade.setBounds(509, 91, 67, 27);
    	panel.add(inputIdade);
    	inputIdade.setColumns(10);
    	
    	JSeparator separator = new JSeparator();
    	separator.setBounds(21, 129, 568, 2);
    	panel.add(separator);
    	
    	JButton btnGravar = new JButton("Gravar");
    	btnGravar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			gravar();
    			limpaCampos();
    			atualizarTabela();
    		}
    	});
    	btnGravar.setForeground(new Color(255, 255, 255));
    	btnGravar.setBackground(new Color(0, 255, 0));
    	btnGravar.setBounds(390, 147, 89, 27);
    	panel.add(btnGravar);
    	
    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			limpaCampos();
    		}
    	});
    	btnCancelar.setForeground(new Color(255, 255, 255));
    	btnCancelar.setBackground(new Color(0, 0, 128));
    	btnCancelar.setBounds(128, 147, 89, 27);
    	panel.add(btnCancelar);
    	
    	JButton btnExcluir = new JButton("Excluir");
    	btnExcluir.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			excluir();
    			limpaCampos();
    			atualizarTabela();
    		}
    	});
    	btnExcluir.setForeground(new Color(255, 255, 255));
    	btnExcluir.setBackground(new Color(255, 0, 0));
    	btnExcluir.setBounds(487, 147, 89, 27);
    	panel.add(btnExcluir);
    	
    	JPanel panel_1 = new JPanel();
    	panel_1.setBackground(new Color(240, 255, 240));
    	panel_1.setBounds(21, 197, 568, 231);
    	panel.add(panel_1);
    	panel_1.setLayout(null);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(0, 0, 568, 231);
    	panel_1.add(scrollPane);
    	
    	table = new JTable();
    	table.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			carregaInformacoes();
    		}
    	});
    	scrollPane.setViewportView(table);
    	
    	JButton btnPesquisar = new JButton("Buscar");
    	btnPesquisar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			pesquisa(inputNome.getText());
    		}
    	});
    	btnPesquisar.setForeground(Color.WHITE);
    	btnPesquisar.setBackground(new Color(0, 0, 255));
    	btnPesquisar.setBounds(29, 147, 89, 27);
    	panel.add(btnPesquisar);
    	
    	JLabel lblHora = new JLabel("");
    	lblHora.setBounds(433, 22, 74, 27);
    	panel.add(lblHora);
    	
    	JLabel lblData = new JLabel("");
    	lblData.setBounds(517, 22, 74, 27);
    	panel.add(lblData);
        setTitle("Window");
        setSize(650, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        inicializarComponentes();

        setVisible(true);
        
    }
    
    
    private void inicializarComponentes () {
    	aluno		= new Aluno();
    	dao			= new DaoAluno();
    	tableModel	= new DefaultTableModel();
    	
    	idRef = 0;
    	
    	criaModeloTabela();
    	atualizarTabela();
    	
    	table.setModel(tableModel);
    }
    
    private void criaModeloTabela() {
    	tableModel.addColumn("ID");
    	tableModel.addColumn("Nome");
    	tableModel.addColumn("E-mail");
    	tableModel.addColumn("Idade");
    }
    
    private void atualizarTabela() {
    	listaAlunos		= dao.select();
    	montaTabela();
    }
    
    // Limpar os campos apos a operação Adicionar
    private void limpaCampos() {
    	inputNome.setText("");
    	inputEmail.setText("");
    	inputIdade.setText("");
    	idRef = 0;
    	table.clearSelection();
    	
    }
    
    
   /***************************************************************
    *                        Listar                               *
    ***************************************************************/
    
    private void montaTabela() {
    	tableModel.setNumRows(0);
    	
    	Object[] coluna = new Object[4];
    	
    	for (Aluno alunoTMP : listaAlunos) {
    		coluna[0] = alunoTMP.getId();
    		coluna[1] = alunoTMP.getNome();
    		coluna[2] = alunoTMP.getEmail();
    		coluna[3] = alunoTMP.getIdade();
    		
    		tableModel.addRow(coluna);
    	}
    	
    }

    
   /***************************************************************
    *                        Adicionar                            *
    ***************************************************************/

    private void gravar() {
	    aluno.setNome(inputNome.getText());
	    aluno.setEmail(inputEmail.getText());
	    aluno.setIdade(Integer.parseInt(inputIdade.getText()));
	    
	    if(idRef == 0) {
	    	dao.insert(aluno);
    	}else {
	    	aluno.setId(idRef);
	    	dao.update(aluno);
    	}
    }
   
    
   /****************************************************************
    *                        Alterar                               *
    ****************************************************************/ 
    
    private void carregaInformacoes() {
    	String nome;
    	String email;
    	String idade;
    	
    	int linha = table.getSelectedRow();
    	
    	nome	= table.getValueAt(linha, 1).toString();
    	email	= table.getValueAt(linha, 2).toString();
    	idade	= table.getValueAt(linha, 3).toString();
    	
    	idRef 	= (int) table.getValueAt(linha, 0);
    	
    	inputNome.setText(nome);
    	inputEmail.setText(email);
    	inputIdade.setText(idade);
	}
    
    
   /****************************************************************
    *                       Excluir                                *
    * **************************************************************/
    
    private void excluir() {
    	if(idRef == 0) {
	    	System.out.println("Erro: nenhum parametro foi passado para a exclusão");
    	}else {
	    	dao.delete(idRef);
    	}
    }
    
    
   /****************************************************************
    *                      Pesquisar                               *
    ****************************************************************/

    private void pesquisa(String nome) {
    	listaAlunos = dao.selectByNome(nome);
    	montaTabela();
    }
    
    
    public static void main(String[] args) {
        new Aplicacao();
    }
}
