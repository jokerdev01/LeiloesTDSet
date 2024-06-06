import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conectaDAO {

    private static final String url = "jdbc:mysql://localhost:3306/Uc11";
    private static final String user = "root";
    private static final String password = "Luc@s2024";

    private static Connection conn;

    public Connection getConnection() {
        try {
            // Carrega o driver atualizado do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Verifica se já existe uma conexão ativa
            if (conn == null) {
                // Cria a conexão com o banco de dados
                conn = DriverManager.getConnection(url, user, password);
            }
            return conn; // Retorna a conexão

        } catch (ClassNotFoundException ex) {
            // Exibe mensagem de erro caso o driver não seja encontrado
            JOptionPane.showMessageDialog(null, "Erro ao carregar o driver do MySQL: " + ex.getMessage());
            Logger.getLogger(conectaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException erro) {
            // Exibe mensagem de erro caso ocorra algum problema com a conexão
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + erro.getMessage());
            Logger.getLogger(conectaDAO.class.getName()).log(Level.SEVERE, null, erro);
        }
        return null; // Retorna null se a conexão falhar
    }
}