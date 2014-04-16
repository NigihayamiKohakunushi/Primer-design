import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;


public class MainFrame extends JFrame {
		
		//components
		private JPanel contentPane;
		private JButton btnPrimers;
		private JButton btnRestrictaza;
		private JButton btnCertificate;
		private JTextArea txtMain;
		private JScrollPane scrlMain;
		
		//objects
		private SimpleExtract passed;
		
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						MainFrame frame = new MainFrame();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		private void initComponents(){
			
			contentPane = new JPanel();
			setContentPane(contentPane);
			btnPrimers = new JButton();
			btnRestrictaza = new JButton();
			btnCertificate = new JButton();
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Master of primers");
			
			btnPrimers.setText("Подобрать праймеры");
			btnPrimers.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					//btnPrimersActionPerformed(evt);
				}
			});
			
			btnRestrictaza.setText("Подобрать рестриктазу");
			btnRestrictaza.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					//btnRestrictazaActionPerformed(evt);
				}
			});
			btnCertificate.setText("Открыть справку");
			btnCertificate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
			//btnCertificateActionPerformed(evt);
			}
			});
			
		}
		public MainFrame() {
			
			initComponents();
			
			setBounds(100, 100, 450, 300);
			
			String zy="";
			try{
				FileInputStream fileStream = new FileInputStream("input.raw");
				Scanner scanner = new Scanner(fileStream);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					zy = zy + line;
					System.out.println(line);
					System.out.println(zy);
					System.out.println();
					}
				scanner.close();
			}
			catch (FileNotFoundException e){
				e.printStackTrace();
			}
			
			
			txtMain=new JTextArea(zy);
			txtMain.setLineWrap(true);
		
			scrlMain = new JScrollPane(txtMain);
			scrlMain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrlMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
			
			/////////////////////////
			//creating the menu
			JMenuBar menuBar;
			JMenu menu, submenu;
			JMenuItem menuItem;
			JRadioButtonMenuItem rbMenuItem;
			JCheckBoxMenuItem cbMenuItem;
			
			//Create the menu bar.
			menuBar = new JMenuBar();

			//Build the first menu.
			menu = new JMenu("Меню");
			menu.setMnemonic(KeyEvent.VK_A);
			menuBar.add(menu);

			//a group of JMenuItems
			menuItem = new JMenuItem("Открыть файл",
			                         KeyEvent.VK_T);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(
			        KeyEvent.VK_1, ActionEvent.ALT_MASK));
			menu.add(menuItem);

			menuItem = new JMenuItem("Создать новый");
			menuItem.setMnemonic(KeyEvent.VK_B);
			menu.add(menuItem);

			cbMenuItem = new JCheckBoxMenuItem("Сохранить изменения");
			cbMenuItem.setMnemonic(KeyEvent.VK_H);
			menu.add(cbMenuItem);

			//Build second menu "Edition"
			menu = new JMenu("Редактировать");
			menu.setMnemonic(KeyEvent.VK_N);
			menuBar.add(menu);
			this.setJMenuBar(menuBar);
			/////////////////////////////////////
		
			contentPane.add(btnPrimers);
			contentPane.add(scrlMain);
		
			TheMainDNAMolecule zyyy = new TheMainDNAMolecule(txtMain.getText());
				
		
			//обработка нажатия на кнопку. Вообще хорошо бы этот класс сделать нормальным, а не анонимным
			btnPrimers.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(ActionEvent evt) {

					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							int i,j;
							i=txtMain.getSelectionStart();
							j=txtMain.getSelectionEnd();
							passed=new SimpleExtract(i,j);
						
							RenzymeMass restrictionEnzymesNearGene = new RenzymeMass();
						
							FRMSimplePrimers x = new FRMSimplePrimers(passed, restrictionEnzymesNearGene);
						}
					});
				
				}
			});
		
			//DESIGN
			GroupLayout layout = new GroupLayout(contentPane);
			contentPane.setLayout(layout);
		
			//auto gaps
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
		
			GroupLayout.ParallelGroup HORGroup = layout.createParallelGroup();
			HORGroup.addComponent(btnPrimers);
			HORGroup.addComponent(btnRestrictaza);
			HORGroup.addComponent(btnCertificate);
			HORGroup.addComponent(scrlMain);
			layout.setHorizontalGroup(HORGroup);
		
			GroupLayout.SequentialGroup VertGroup = layout.createSequentialGroup();
			VertGroup.addComponent(btnPrimers);
			VertGroup.addComponent(btnRestrictaza);
			VertGroup.addComponent(btnCertificate);
			VertGroup.addComponent(scrlMain);
			layout.setVerticalGroup(VertGroup);
		}

}
