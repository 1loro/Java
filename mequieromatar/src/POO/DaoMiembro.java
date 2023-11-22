/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lorem
 */
public class DaoMiembro {
    
    conexion conectar= new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public List listar(){
        List<miembros> lista = new ArrayList<>();
        String sql = "select * from miembros";
        try{
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                miembros m = new miembros();
                m.setId(rs.getInt(1)); 
                m.setRut(rs.getString(2));
                m.setNombre(rs.getString(3));
                m.setPaterno(rs.getString(4));
                m.setMaterno(rs.getString(5));
                m.setTaller(rs.getString(6));
                m.setDias(rs.getInt(7));
                m.setCuota(rs.getInt(8));
                lista.add(m);
            }
            }catch (Exception e){
            }
            return lista;
        }
        public int Agregar(miembros m){
            String sql = "insert into miembros (rut,nombre,paterno,materno,taller,dias,cuota)values (?,?,?,?,?,?,?)";
            try{
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.setString(1,m.getRut());
                ps.setString(2,m.getNombre());
                ps.setString(3,m.getPaterno());
                ps.setString(4,m.getMaterno());
                ps.setString(5,m.getTaller());
                ps.setInt(6, m.getDias());
                ps.setInt(7,m.getCuota());
                ps.executeUpdate();
            }catch (Exception e){
                
            }
            return 1;
        }

        public void Delete(int id) {
            String sql = "DELETE FROM miembros WHERE id = "+id;
            try{
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.executeUpdate();
                }catch (Exception e){
            }
        }
        
        public boolean actualizar (miembros m){
            String sql = "update miembros set rut=?, nombre=?, paterno=?, materno=?, taller=?, dias=?, cuota=? where id=?";
            
            try(
                    Connection c = conectar.getConnection();
                    PreparedStatement statement = c.prepareStatement(sql)) {
                statement.setString(1,m.getRut());
                statement.setString(2,m.getNombre());
                statement.setString(3,m.getPaterno());
                statement.setString(4,m.getMaterno());
                statement.setString(5,m.getTaller());
                statement.setInt(6, m.getDias());
                statement.setInt(7,m.getCuota());
                statement.setInt(8,m.getId());
                statement.executeUpdate();
                
                return true;
            } catch(SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        
        public List filtroDias(int dias){
            List<miembros> lista = new ArrayList<>();
            String sql = "SELECT * FROM miembros WHERE dias = ?";
            try{
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.setInt(1, dias);
                rs=ps.executeQuery();
                while(rs.next()){
                    miembros m = new miembros();
                    m.setId(rs.getInt(1)); 
                    m.setRut(rs.getString(2));
                    m.setNombre(rs.getString(3));
                    m.setPaterno(rs.getString(4));
                    m.setMaterno(rs.getString(5));
                    m.setTaller(rs.getString(6));
                    m.setDias(rs.getInt(7));
                    m.setCuota(rs.getInt(8));
                    lista.add(m);
                }
                }catch (Exception e){
                }
                return lista;
        }            
         public List filtroTaller(String taller){
             
             
            List<miembros> lista = new ArrayList<>();
            String sql = "SELECT * FROM miembros WHERE taller = ?";
            try{
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.setString(1, taller);
                rs=ps.executeQuery();
                
                while(rs.next()){
                    miembros m = new miembros();
                    m.setId(rs.getInt(1)); 
                    m.setRut(rs.getString(2));
                    m.setNombre(rs.getString(3));
                    m.setPaterno(rs.getString(4));
                    m.setMaterno(rs.getString(5));
                    m.setTaller(rs.getString(6));
                    m.setDias(rs.getInt(7));
                    m.setCuota(rs.getInt(8));
                    lista.add(m);
                }
            }catch (Exception e){}
            
            return lista;
        } 
        
        public List filtroAmbos(int dias, String taller){
            List<miembros> lista = new ArrayList<>();
            String sql = "SELECT * FROM miembros WHERE dias = ? AND taller = ?";
            try{
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.setInt(1, dias);
                ps.setString(2, taller);
                rs=ps.executeQuery();
                while(rs.next()){
                    miembros m = new miembros();
                    m.setId(rs.getInt(1)); 
                    m.setRut(rs.getString(2));
                    m.setNombre(rs.getString(3));
                    m.setPaterno(rs.getString(4));
                    m.setMaterno(rs.getString(5));
                    m.setTaller(rs.getString(6));
                    m.setDias(rs.getInt(7));
                    m.setCuota(rs.getInt(8));
                    lista.add(m);
                }
                }catch (Exception e){
                }
                return lista;
        }     
}