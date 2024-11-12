package br.com.DAO;

import br.com.DTO.TecnicoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TecnicoDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void deletarTec(TecnicoDTO objDTO) {
        String sql = "DELETE FROM tecnicos WHERE id_tecnico = ?";

        try {
            conexao = ConexaoDAO.conector();
            pst = conexao.prepareStatement(sql);

            pst.setInt(1, objDTO.getIdTecnico());

            int add = pst.executeUpdate();

            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Técnico excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum técnico encontrado com o ID especificado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método Deletar: " + e);
        }
    }

    public boolean inserirTec(TecnicoDTO objDTO) {
    String sql = "INSERT INTO tecnicos(nome, id_laboratorio, telefone, data_admissao) VALUES(?, ?, ?, ?)";

    try {
        conexao = new ConexaoDAO().conector();
        pst = conexao.prepareStatement(sql);
        pst.setString(1, objDTO.getNomeTecnico());
        pst.setInt(2, objDTO.getIdLaboratorio());
        pst.setString(3, objDTO.getTelefoneTecnico());
        pst.setString(4, objDTO.getDataAdmissao());

        // Executando a inserção no banco
        int add = pst.executeUpdate();

        // Se a inserção foi bem-sucedida (add > 0)
        return add > 0;  // Retorna true se pelo menos uma linha foi inserida

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao inserir técnico: " + e);
        return false;
    } finally {
        try {
            if (pst != null) {
                pst.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e);
        }
    }
}

    

    public void editarTec(TecnicoDTO objDTO) {
        String sql = "UPDATE tecnicos SET nome = ?, id_laboratorio = ?, telefone = ?, data_admissao = ? WHERE id_tecnico = ?";

        try {
            conexao = ConexaoDAO.conector();
            pst = conexao.prepareStatement(sql);

            pst.setString(1, objDTO.getNomeTecnico());

            pst.setInt(2, objDTO.getIdLaboratorio());
            pst.setString(3, objDTO.getTelefoneTecnico());
            pst.setString(4, objDTO.getDataAdmissao());
            pst.setInt(5, objDTO.getIdTecnico());

            int add = pst.executeUpdate();

            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Tecnico editado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método Editar: " + e);
        }
    }
}
