import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    // Método para cadastrar um produto.
    public void cadastrarProduto(ProdutosDTO produto) throws ClassNotFoundException {
        conn = new conectaDAO().getConnection();
        try {
            String sql = "INSERT INTO Produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar o produto: " + ex.getMessage());
        } finally {
          
            
        }
    }

    // Método para listar todos os produtos.
    public ArrayList<ProdutosDTO> listarProdutos() {
        listagem.clear();
        conn = new conectaDAO().getConnection();
        try {
            String sql = "SELECT * FROM Produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            System.out.println("Produtos listados com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os produtos: " + ex.getMessage());
        }
        return listagem;
    }

    public void venderProduto(int id) {

        try {

            conectaDAO conectaDAO = new conectaDAO();
            Connection conn = conectaDAO.getConnection();

            String sql = "UPDATE produtos SET status = ? WHERE id = ?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            prep.executeUpdate();
            System.out.println("Produto vendido com sucesso!");

        } catch (SQLException ex) {
            System.err.println("Erro ao vender 1 produto: " + ex.getMessage());
        } 
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        listagem.clear();
        conn = new conectaDAO().getConnection();
        try {
            String sql = "SELECT * FROM Produtos WHERE status = 'Vendido'";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            System.out.println("Produtos vendidos listados com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os produtos vendidos: " + ex.getMessage());
        } 
        return listagem;
    }

    public static ProdutosDTO buscarPorId(int id) {
        ProdutosDTO p = new ProdutosDTO();

        try {
            conectaDAO conectaDAO = new conectaDAO();
            Connection conn = conectaDAO.getConnection();

            String sql = " SELECT * FROM   WHERE id = ?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            ResultSet resposta = prep.executeQuery();

            if (resposta.next()) {

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setStatus(resposta.getString("status"));
                p.setValor(resposta.getInt("valor"));

            }

            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o registro " + id + "do banco de dados");
        }
        return p;
    }

}
