package view;

import javax.swing.*;
import javax.swing.table.*;

import model.DataModel;
import javax.swing.JFrame;
import java.awt.*;

public class MainFrame extends JFrame {

    
    
    private Container contentPane;
    private JTable table = new JTable();
    private JPanel titlePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private JLabel lblTitle = new JLabel("COMP 305");
    private JButton btnUpdate = new JButton ("Update");
    private JButton btnESP = new JButton ("Spain");
    private JButton btnITA = new JButton ("Italy");
    private JButton btnJPN = new JButton ("Japan");
    private JButton btnOver1M = new JButton ("1M+");
    private JScrollPane scrollPane; 


    public MainFrame(String title) {
        super(title);
        setSize(800, 600);
        initLayout();
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initLayout() {

        contentPane = this.getContentPane();

        // ----------  Title Bar --------------------- //
        contentPane.add(titlePanel, "North");
        titlePanel.add(lblTitle);
        String fontName = lblTitle.getFont().getFontName();
        lblTitle.setFont(new Font(fontName,0,24 ));

        // ---------- Main Panel ---------------------// 
        mainPanel.setBackground(new Color (0xAAFFFF));
        contentPane.add(mainPanel,"Center");

        String[] headers = DataModel.getHeaders();

        // table = new JTable(new DefaultTableModel(DataModel.getCityData(), headers)  );
        // scrollPane = new JScrollPane(table);
        // mainPanel.add(scrollPane);

        btnUpdate.addActionListener(
                event -> {
                    setSize(799, 599);
                    if(scrollPane!=null)
                        mainPanel.remove(scrollPane);
                    table = new JTable(new DefaultTableModel(DataModel.getCityData(), headers)  );
                    scrollPane = new JScrollPane(table);
                    mainPanel.add(scrollPane);
                    setSize(800, 600);
                }
        );
        btnESP.addActionListener(
                event -> {                    
                    setSize(799, 599);
                    if(scrollPane!=null)
                        mainPanel.remove(scrollPane);
                    table = new JTable(new DefaultTableModel(DataModel.getCitiesByCountry("ESP"), headers)  );
                    scrollPane = new JScrollPane(table);
                    mainPanel.add(scrollPane);
                    setSize(800, 600);
                }
        );
        btnITA.addActionListener(
                event -> {                    
                    setSize(799, 599);
                    if(scrollPane!=null)
                        mainPanel.remove(scrollPane);
                    table = new JTable(new DefaultTableModel(DataModel.getCitiesByCountry("ITA"), headers)  );
                    scrollPane = new JScrollPane(table);
                    mainPanel.add(scrollPane);
                    setSize(800, 600);
                }
        );
        btnJPN.addActionListener(
                event -> {                    
                    setSize(799, 599);
                    if(scrollPane!=null)
                        mainPanel.remove(scrollPane);
                    table = new JTable(new DefaultTableModel(DataModel.getCitiesByCountry("JPN"), headers)  );
                    scrollPane = new JScrollPane(table);
                    mainPanel.add(scrollPane);
                    setSize(800, 600);
                }
        );

        btnOver1M.addActionListener(
                event -> {                    
                    setSize(799, 599);
                    if(scrollPane!=null)
                        mainPanel.remove(scrollPane);
                    table = new JTable(new DefaultTableModel(DataModel.getOver1M(), headers)  );
                    scrollPane = new JScrollPane(table);
                    mainPanel.add(scrollPane);
                    setSize(800, 600);
                }
        );
        // ----------- Control Panel ----------------// 

            controlPanel.add(btnUpdate);
            controlPanel.add(btnESP);
            controlPanel.add(btnITA);
            controlPanel.add(btnJPN);
            controlPanel.add(btnOver1M);
            contentPane.add(controlPanel,"South");

            pack();
    }

    

}

