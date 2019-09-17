import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.Scanner;

public class Notepad extends JFrame {

    Notepad()throws Exception{
        initialize();
    }

    // Initialization
    JPanel panel;
    JTextArea text;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu file,edit,view,help;
    JMenuItem nw,save,open,exit,cut,copy,paste,print,font;
    JFileChooser jfileChooser;
    Scanner scanner;
    String name=null;
    FileNameExtensionFilter textFilter,javaFilter,htmlFilter;
    String button[]={"Save","Continue Without Saving","Cancel"};






    // Initialization End




    private void initialize() throws Exception{
        getTitlee();
        panel=new JPanel();
        setBounds(150,150,900,720);
        panel.setLayout(new BorderLayout());


        text= new JTextArea();
        scrollPane=new JScrollPane(text);
        panel.add(scrollPane);

        menuBar =new JMenuBar();
        menuBar.add(file=new JMenu("File"));menuBar.add(edit=new JMenu("Edit"));//menuBar.add(view=new JMenu("View"));
        menuBar.add(help=new JMenu("Help"));
        file.add(nw=new JMenuItem("New"));file.add(save=new JMenuItem("Save"));file.add(open=new JMenuItem("Open"));file.add(exit=new JMenuItem("Exit"));
        edit.add(cut=new JMenuItem("Cut"));edit.add(copy=new JMenuItem("Copy"));edit.add(paste=new JMenuItem("Past"));edit.add(print=new JMenuItem("Print"));
       // view.add(font=new JMenuItem("Font"));
        panel.add(menuBar,BorderLayout.NORTH);

        //Now Calling the menuItem by method

        //File
        newLable();saveLable();openLable();exitLable();

        //Edit
        cutEdit();copyEdit();paseEdit();printEdit();
        //view
       // fontView();

        //help
        gethelp();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                int option=JOptionPane.showConfirmDialog(panel," Do you want to Exit ","Alert",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
                if(option==JOptionPane.YES_OPTION) {
                    try {
                        saveLable();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else
                {
                    System.exit(0);
                }

            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}

        });



        add(panel);

    }

    private void gethelp() {
        help.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                JOptionPane.showMessageDialog(panel,"Copyright- Dipta Biswas \nVersion : 1.01","Alert",JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
    }

//    private void fontView() {
//        font.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    new Ffont().setVisible(true);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//    }

    private void printEdit() throws PrinterException {
       print.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   text.print();
               } catch (PrinterException ex) {
                   ex.printStackTrace();
               }
           }
       });
    }

    private void paseEdit() {
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.paste();
            }
        });
    }

    private void copyEdit() {
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.copy();
            }
        });
    }

    private void cutEdit() {
    cut.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            text.cut();

        }
    });
    }

    private void getTitlee() {
        if(name!=null)
            setTitle(name);
        else
        {
            setTitle("untitled");
        }
    }


    // Menu Items by Order
    private void newLable() {
        nw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");
                name=null;
                getTitlee();
            }
        });
    }

    private void saveLable() throws IOException {


        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                jfileChooser=new JFileChooser();
                int dialog =jfileChooser.showSaveDialog(panel);


                if(dialog==JFileChooser.APPROVE_OPTION)
                {
                    FileWriter fileWriter= null;
                    try {
                        fileWriter = new FileWriter(new File(jfileChooser.getSelectedFile().getPath()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        fileWriter.write(text.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        fileWriter.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void openLable() {
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");

                jfileChooser=new JFileChooser();
                FileNameExtensionFilter filter=new FileNameExtensionFilter("All Text","txt");
                jfileChooser.addChoosableFileFilter(filter);
                int dialog=jfileChooser.showOpenDialog(panel);

                if(dialog==JFileChooser.APPROVE_OPTION){
                    name=jfileChooser.getSelectedFile().getName();
                    getTitlee();
                    Scanner scanner= null;
                    try {
                        scanner = new Scanner(new FileReader(jfileChooser.getSelectedFile().getPath()));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    while (scanner.hasNext()){
                        text.append(scanner.nextLine());
                    }
                }
            }
        });
    }


    private void exitLable() {
    exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option=JOptionPane.showOptionDialog(panel,"Do You want to Save ","Alert",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,button,button[0]);
            if(option==JOptionPane.YES_OPTION) {
                try {
                    saveLable();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(option==JOptionPane.NO_OPTION)
            {
                System.exit(0);
            }
        }
    });
    }
}
