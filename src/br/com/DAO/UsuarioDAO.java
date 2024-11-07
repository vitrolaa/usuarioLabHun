package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    
 
   
    
    public void editarUsuario(UsuarioDTO objDTO) {
        String sql = "UPDATE usuario u "
                + "JOIN equipamento e ON u.id_equipamento = e.id_equipamento "
                + "SET u.nomeUsuario = ?,  u.emailUsuario = ?, u.cargo = ?, e.modelo = ?"
                + "WHERE u.id_usuario";

        try {
            conexao = ConexaoDAO.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objDTO.getNomeUsuario());
            pst.setString(2, objDTO.getEmailUsuario());
            pst.setString(3, objDTO.getCargoUsuario());
            pst.setString(4, objDTO.getModelo());

            int add = pst.executeUpdate();

            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Nenhum dado foi atualizado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
