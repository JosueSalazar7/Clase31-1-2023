import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.PreparedStatement;
public class Datos {
    private JButton guardarButton;
    private JTextField correo;
    private JTextField celular;
    private JTextField nombre;
    private JTextField id;
    private JPanel panel1;
    PreparedStatement ps;

public Datos() {
    guardarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection con;
            try {
                con = getConection();
                ps = con.prepareStatement("INSERT INTO INFO (ID, NOMBRE, CELULAR, CORREO)  VALUES(?,?,?,?) ");
                ps.setString(1, id.getText());
                ps.setString(2, nombre.getText());
                ps.setString(3, celular.getText());
                ps.setString(4, correo.getText());
                System.out.println(ps);//imprimo en consola para verificación

                int res = ps.executeUpdate();

                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Persona Guardada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar persona");
                }
                //limpiartxt();
                //txtId.setText("");
                //txtNombre.setText("");
                //txtCelular.setText("");
                //txtCorreo.setText("");
                con.close();//importante!!!!

            } catch (HeadlessException | SQLException f) {
                System.err.println(f);
            }


        }
    });
}
public static Connection getConection(){
    Connection con = null;
    String base= "DATOS";
    String url = "jdbc:mysql://localhost:3306/" + base;
    String user = "root";
    String password = "Pelota2002";
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,password);
    }catch (ClassNotFoundException | SQLException e){
        System.err.println(e);
    }
    return con;
}
    public static void main(String[] args) {
        JFrame frame = new JFrame("insertar");
        frame.setContentPane(new Datos().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
