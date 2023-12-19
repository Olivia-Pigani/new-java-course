//package org.example.jdbc.ecole.dao;
//
//
//import org.example.jdbc.ecole.entity.Eleve;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//public class EleveDao extends BaseDAO<Eleve> {
//
//
//    protected EleveDao(Connection connection) {
//        super(connection);
//    }
//
//    @Override
//    public boolean save(Eleve element) throws SQLException {
//        request = "INSERT INTO etudiants (nom,prenom,nb_classe,date_diplome) VALUES (?,?,?,?);";
//        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
//        statement.setString(1,element.getFirstName());
//        statement.setString(2,element.getLastName());
//        statement.setInt(3,element.getClassNumber());
//       // statement.setDate(4,new java.sql.Date(getD.getTime()));    }
//
//    @Override
//    public boolean update(Eleve element) throws SQLException {
//        return false;
//    }
//
//    @Override
//    public boolean delete(Eleve element) throws SQLException {
//        return false;
//    }
//
//    @Override
//    public Eleve get(int id) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public List<Eleve> get() throws SQLException {
//        return null;
//    }
//}
