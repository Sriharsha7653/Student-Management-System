import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class App extends Frame implements ActionListener {
    // BUTTON INITIALIZATION
    Label title;
    Button addbt, removebt, viewbt, viewst, dropd;
    FirstWindow w1 = null;
    SecondWindow w2 = null;
    ThirdWindow w3 = null;
    FourthWindow w4 = null;
    truncatetable w5 = null;
    Connection con;

    // APP CONSTRUCTOR
    public App() {
        con = connectoserver();
        setLayout(null);
        title = new Label("Student DataBase Management");
        title.setBounds(10, 30, 400, 30);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.white);
        title.setAlignment(Label.CENTER);
        title.setFont(new Font("Algerian", Font.BOLD, 20));
        add(title);
        addbt = new Button("ADD STUDENT");
        addbt.setBounds(140, 80, 100, 30);
        addbt.setFont(new Font("Algerian", Font.BOLD, 10));
        addbt.addActionListener(this);
        add(addbt);
        removebt = new Button("REMOVE STUDENT");
        removebt.setBounds(140, 120, 100, 30);
        removebt.setFont(new Font("Algerian", Font.BOLD, 10));
        removebt.addActionListener(this);
        add(removebt);
        viewbt = new Button("VIEW TABLE");
        viewbt.setBounds(140, 160, 100, 30);
        viewbt.setFont(new Font("Algerian", Font.BOLD, 10));
        viewbt.addActionListener(this);
        add(viewbt);

        viewst = new Button("VIEW STUDENT");
        viewst.setBounds(140, 200, 100, 30);
        viewst.setFont(new Font("Algerian", Font.BOLD, 10));
        viewst.addActionListener(this);
        add(viewst);

        dropd = new Button("DELETE ALL");
        dropd.setBounds(140, 240, 100, 30);
        dropd.setFont(new Font("Algerian", Font.BOLD, 10));
        dropd.addActionListener(this);
        add(dropd);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setTitle("Student DataBase Management System");
        setSize(400, 300);
        setVisible(true);

    }

    public Connection connectoserver() {
        Connection con = null;

        try {
            String url = "jdbc:mysql://localhost:3306/studentdb";
            String user = "root";
            String pass = "Sri@7681";

            con = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    // ACTION LISTNER
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addbt) {
            if (w1 == null) {
                w1 = new FirstWindow();
                addbt.setEnabled(false);

                w1.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        w1 = null;
                        addbt.setEnabled(true);

                    }
                });
            }
        }
        if (e.getSource() == removebt) {
            if (w2 == null) {
                w2 = new SecondWindow();
                removebt.setEnabled(false);

                w2.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        w2 = null;
                        removebt.setEnabled(true);

                    }
                });
            }
        }
        if (e.getSource() == viewbt) {
            if (w3 == null) {
                w3 = new ThirdWindow(con);
                viewbt.setEnabled(false);

                w3.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        w3 = null;
                        viewbt.setEnabled(true);

                    }
                });
            }
        }
        if (e.getSource() == viewst) {
            if (w4 == null) {
                w4 = new FourthWindow(con);
                viewst.setEnabled(false);

                w4.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        w4 = null;
                        viewst.setEnabled(true);

                    }
                });
            }
        }
        if (e.getSource() == dropd) {
            w5 = new truncatetable(con);
        }
    }

    // MAIN CLASS
    public static void main(String[] args) throws Exception {
        new App();
    }
}

// ADD USER CLASS
class FirstWindow extends Frame implements ActionListener {
    // PREPARED STATEMENT

    Label sid, sname, sclass, gender, pname, address, phno;
    TextField t1, t2, t3, t4, t5, t6, t7;
    Checkbox male, female, selected;
    CheckboxGroup genderGroup;
    Button b1;
    Font boldFont = new Font("Arial", Font.BOLD, 16);
    int id;
    String studentname, studentclass, sgender, parname, addre, ph_no;

    FirstWindow() {
        setLayout(null);
        setTitle("ADD USER");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        sid = new Label("Student-ID");
        sid.setBounds(30, 50, 100, 20);
        sid.setFont(boldFont);
        add(sid);

        sname = new Label("Student-Name");
        sname.setBounds(30, 90, 110, 20);
        sname.setFont(boldFont);
        add(sname);

        sclass = new Label("Class");
        sclass.setBounds(30, 130, 100, 20);
        sclass.setFont(boldFont);
        add(sclass);

        gender = new Label("Gender");
        gender.setBounds(30, 170, 100, 20);
        gender.setFont(boldFont);
        add(gender);

        pname = new Label("Parent Name");
        pname.setBounds(30, 210, 100, 20);
        pname.setFont(boldFont);
        add(pname);

        address = new Label("Address");
        address.setBounds(30, 250, 100, 20);
        address.setFont(boldFont);
        add(address);

        phno = new Label("Phone No");
        phno.setBounds(30, 290, 100, 20);
        phno.setFont(boldFont);
        add(phno);

        // ---------- TEXTFIELDS ----------
        t1 = new TextField();
        t1.setBounds(140, 50, 130, 22);
        add(t1);

        t2 = new TextField();
        t2.setBounds(140, 90, 130, 22);
        add(t2);

        t3 = new TextField();
        t3.setBounds(140, 130, 130, 22);
        add(t3);

        genderGroup = new CheckboxGroup();

        male = new Checkbox("Male", genderGroup, true);
        male.setBounds(140, 170, 60, 20);
        add(male);

        female = new Checkbox("Female", genderGroup, false);
        female.setBounds(210, 170, 70, 20);
        add(female);

        t5 = new TextField();
        t5.setBounds(140, 210, 130, 22);
        add(t5);

        t6 = new TextField();
        t6.setBounds(140, 250, 130, 22);
        add(t6);

        t7 = new TextField();
        t7.setBounds(140, 290, 130, 22);
        add(t7);
        b1 = new Button("ADD");
        b1.setBounds(130, 310, 30, 30);
        b1.addActionListener(this);
        add(b1);
        setSize(300, 350);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // String studentname,studentclass,sgender,parname,addre,ph_no;
        if (e.getSource() == b1) {
            try {
                id = Integer.parseInt(t1.getText());
                studentname = t2.getText();
                studentclass = t3.getText();
                selected = genderGroup.getSelectedCheckbox();
                String sgender = selected.getLabel();
                parname = t5.getText();
                addre = t6.getText();
                ph_no = t7.getText();
                String query = "INSERT INTO student (s_id, s_name, class, Gender, par_name, address, phone_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
                App obj1 = new App();
                Connection con = obj1.connectoserver();
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, studentname);
                ps.setString(3, studentclass);
                ps.setString(4, sgender);
                ps.setString(5, parname);
                ps.setString(6, addre);
                ps.setString(7, ph_no);
                ps.executeUpdate();

            } catch (Exception E) {
                E.printStackTrace();
            }
        }
    }
}

// REMOVE USER CLASS
class SecondWindow extends Frame implements ActionListener {
    Label l1;
    TextField t1;
    Button b1;
    Font boldFont = new Font("Arial", Font.BOLD, 16);

    SecondWindow() {
        setLayout(null);
        setTitle("REMOVE USERS");
        setSize(300, 200);

        l1 = new Label("Enter the  Student Roll no to remove");
        l1.setBounds(10, 30, 300, 30);
        l1.setFont(boldFont);
        add(l1);

        t1 = new TextField();
        t1.setBounds(65, 90, 150, 30);
        t1.setFont(boldFont);
        add(t1);

        b1 = new Button("REMOVE");
        b1.setFont(boldFont);
        b1.setBounds(100, 130, 80, 40);
        b1.addActionListener(this);
        add(b1);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                int id = Integer.parseInt(t1.getText());
                App obj2 = new App();
                Connection con = obj2.connectoserver();
                String query = "delete from student where s_id=" + id;
                System.out.println(query);
                PreparedStatement st = con.prepareStatement(query);
                st.executeUpdate();
            } catch (Exception v) {
                v.getStackTrace();
            }
        }
    }
}

// TABLE CHECKER CLASS
class ThirdWindow extends Frame {

    ThirdWindow(Connection con) {

        setLayout(null);
        setTitle("TABLE CHECKER");
        setSize(800, 400);

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 7));
        panel.setBounds(50, 50, 700, 300);

        add(panel);

        loadData(con, panel);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

    }

    void loadData(Connection con, Panel panel) {
        try {
            String query = "SELECT * FROM student";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            int col = meta.getColumnCount();

            for (int i = 1; i <= col; i++) {
                panel.add(new Label(meta.getColumnLabel(i)));
            }

            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    panel.add(new Label(rs.getString(i)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// VIEW Student class
class FourthWindow extends Frame implements ActionListener {

    Label l1;
    TextField t1;
    Button b1, b2;;
    Panel panel;
    Connection con;

    FourthWindow(Connection con) {
        this.con = con;
        setLayout(null);
        setTitle("TABLE CHECKER");
        setSize(800, 400);

        l1 = new Label("ENTER THE STUDENT ID");
        l1.setBounds(10, 50, 300, 30);
        add(l1);

        t1 = new TextField();
        t1.setBounds(10, 90, 120, 30);
        add(t1);

        b1 = new Button("GET");
        b1.setBounds(150, 90, 60, 30);
        b1.addActionListener(this);
        b1.setFont(new Font("Algerian", Font.BOLD, 10));
        add(b1);

        b2 = new Button("CLEAR");
        b2.setBounds(230, 90, 60, 30);
        b2.setFont(new Font("Algerian", Font.BOLD, 10));
        b2.addActionListener(this);
        add(b2);

        panel = new Panel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBounds(10, 150, 760, 200);
        add(panel);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            int id = Integer.parseInt(t1.getText());
            loadData(id);
        }
        if (e.getSource() == b2) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            t1.setText("");
        }
    }

    void loadData(int id) {
        panel.removeAll();

        try {
            String query = "SELECT * FROM student WHERE s_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            ArrayList<String[]> rows = new ArrayList<>();
            while (rs.next()) {
                String[] row = new String[cols];
                for (int c = 1; c <= cols; c++) {
                    row[c - 1] = rs.getString(c);
                }
                rows.add(row);
            }

            if (rows.size() == 0) {

                panel.setLayout(new GridLayout(2, 1, 10, 10));

                StringBuilder hdr = new StringBuilder();
                for (int i = 1; i <= cols; i++) {
                    hdr.append(meta.getColumnLabel(i));
                    if (i != cols)
                        hdr.append("   ");
                }
                panel.add(new Label(hdr.toString()));
                panel.add(new Label("No Data Found for ID: " + id));
            } else {

                int totalRows = 1 + rows.size();
                panel.setLayout(new GridLayout(totalRows, cols, 10, 10));

                for (int i = 1; i <= cols; i++) {
                    Label headerLabel = new Label(meta.getColumnLabel(i));

                    headerLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    panel.add(headerLabel);
                }

                for (String[] r : rows) {
                    for (int c = 0; c < cols; c++) {
                        panel.add(new Label(r[c] == null ? "" : r[c]));
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            panel.setLayout(new GridLayout(1, 1));
            panel.add(new Label("Error: " + ex.getMessage()));
        }
        panel.revalidate();
        panel.repaint();
    }

}

class truncatetable {
    truncatetable(Connection con) {
        try {
            String query = "truncate table student";
            PreparedStatement st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}