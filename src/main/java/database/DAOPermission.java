package database;

import model.Permission;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class DAOPermission {
    public static List<Permission> getEnablePermission(int idUser,int idRole){
    String query="select id,description from permissions where idRole=:idRole and id not in(select idPermission from account_roles " +
            "where idAccount=:idUser and idRole=:idRole and idPermission is not null) and status=1;";
    Jdbi me = JDBiConnector.me();
    return me.withHandle(handle->{
        return handle.createQuery(query).bind("idUser",idUser).bind("idRole",idRole).mapToBean(Permission.class).list();
    })  ;
    }
    public static boolean removePermissionUser(int idUser,int idPermission,int idRole){
        String query="delete from account_roles where idAccount=:idUser and idRole=:idRole and idPermission=:idPermission;";
        Jdbi me = JDBiConnector.me();
        return  me.withHandle(handle->{
           return handle.createUpdate(query).bind("idUser",idUser).bind("idPermission",idPermission).bind("idRole",idRole).execute();
        })==1;
    }
    public static void main(String[] args) {
        System.out.println(DAOPermission.getEnablePermission(2,4));
//        removePermissionUser(2,1,4);
    }

    public static boolean addPermissionUser(int idUser, int idPermission, int idRole) {
        String query="insert into account_roles(idAccount,idRole,idPermission) values(:idUser,:idRole,:idPermission);";
        Jdbi me = JDBiConnector.me();
        return  me.withHandle(handle->{
            return handle.createUpdate(query).bind("idUser",idUser).bind("idPermission",idPermission).bind("idRole",idRole).execute();
        })==1;
    }
}
