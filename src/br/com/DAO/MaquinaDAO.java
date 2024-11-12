package br.com.DAO;

import br.com.DTO.MaquinaDTO;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class MaquinaDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

  


    
    
    public void editarMaquina(MaquinaDTO objDTO) {
        String sql = "UPDATE maquinas SET nome, processador, memoria_RAM, armazenamento, numero_serie, data_aquisicao, status WHERE id_maquina = ?";

        try {
            conexao = ConexaoDAO.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objDTO.getNome());
            pst.setString(2, objDTO.getProcessador());
            pst.setString(3, objDTO.getMemoria_RAM());
            pst.setString(4, objDTO.getArmazenamento());
            pst.setString(5, objDTO.getNumero_serie());
            pst.setString(6, objDTO.getData_aquisicao());
            pst.setString(7, objDTO.getStatus());
            pst.setInt(8, objDTO.getId_maquina());
            

            int add = pst.executeUpdate();

            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Máquina alterada com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método Editar: " + e);
            e.printStackTrace(); // Para depuração
        } finally {
            try {
                if (pst != null) pst.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e);
            }
        }
    }

    public void deletarEquipamento(MaquinaDTO objDTO) {
        String sql = "DELETE FROM maquinas WHERE id_maquina = ?";

        try {
            conexao = ConexaoDAO.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objDTO.getId_maquina());

            int add = pst.executeUpdate();
            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Máquina deletada com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método apagar: " + e);
            e.printStackTrace(); // Para depuração
        } finally {
            try {
                if (pst != null) pst.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e);
            }
        }
    }

    public boolean inserirMaquina(MaquinaDTO objDTO) {
        String sql = "INSERT INTO maquinas (id_maquina ,id_laboratorio, nome, processador, memoria_RAM, armazenamento, numero_serie, data_aquisicao, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexao = new ConexaoDAO().conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objDTO.id_maquina);
            pst.setInt(2, objDTO.id_laboratorio);
            pst.setString(3, objDTO.getNome());
            pst.setString(4, objDTO.getProcessador());
            pst.setString(5, objDTO.getMemoria_RAM());
            pst.setString(6, objDTO.getArmazenamento());
            pst.setString(7, objDTO.getNumero_serie());
            pst.setString(8, objDTO.getData_aquisicao());
            pst.setString(9, objDTO.getStatus());
            pst.execute();
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Inserir máquina: " + e);
            e.printStackTrace(); // Para depuração
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e);
            }
        }
    }
}
