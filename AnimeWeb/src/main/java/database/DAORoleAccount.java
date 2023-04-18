package database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.Jdbi;

import model.Account;
import model.Role;

public class DAORoleAccount {
	public DAORoleAccount() {

	}

//	public static boolean checkPermissionUpdateMovie(int idUser,String source,String action,String externalResource,int idSupplier,int idGenre){
//		Jdbi me = JDBiConnector.me();
//		String query=" SELECT EXISTS (SELECT 1 FROM account_roles ar " +
//				"JOIN permissions p ON ar.idPermission = p.id " +
//				"WHERE ar.idAccount = :idUser AND p.resource = :source " +
//				"AND p.externalResource = :externalResource AND p.idSupplier=:idSupplier AND p.idGenre=:idGenre and p.action=:action)";
//		return me.withHandle(handle -> {
//			return handle.createQuery(query).bind("idUser", idUser).bind("source", source).bind("externalResource", externalResource).bind("action",action).mapTo(Integer.class).one();
//		})==1;
//	}
//	public static boolean checkPermissionAction(int idUser,String source,String action,String externalResource){
//		Jdbi me = JDBiConnector.me();
//		String query=" SELECT EXISTS (SELECT 1 FROM account_roles ar " +
//				"JOIN permissions p ON ar.idPermission = p.id " +
//				"WHERE ar.idAccount = :idUser AND p.resource = :source " +
//				"AND p.externalResource = :externalResource and p.action=:action)";
//		return me.withHandle(handle -> {
//			return handle.createQuery(query).bind("idUser", idUser).bind("source", source).bind("externalResource", externalResource).bind("action",action).mapTo(Integer.class).one();
//		})==1;
//	}
	public static boolean isAdmin(int idUser){
		String query="SELECT EXISTS(SELECT 1 FROM account_roles where idAccount=:idUser and idRole=:admin);";
		Jdbi me = JDBiConnector.me();
		return me.withHandle(handle->{
			return handle.createQuery(query).bind("idUser",idUser).bind("admin",Role.Administrator).mapTo(Integer.class).one();
		})==1;
	}
	public static List<Role> getUnableAddRoles(Account account,Account userAdd) {
		List<Role> listRole = new ArrayList<>();
		Jdbi me;
		boolean checkRole = isAdmin(userAdd.getId());
		me = JDBiConnector.me();
		String query;

			query= "select distinct id,description from animeweb.roles where id not in(select idRole from animeweb.account_roles where idAccount=:idUser) and id <> 6  and status=1";

		listRole = me.withHandle(handle -> {
			return handle.createQuery(query).bind("idUser", account.getId()).mapToBean(Role.class).stream()
					.collect(Collectors.toList());
		});

		return listRole;
	}

	public static boolean addRoleUser(int idUser, int idRole) {
		Jdbi me = JDBiConnector.me();
		String query = "INSERT INTO animeweb.account_roles (idAccount, idrole) VALUES (:idUser, :idrole);";
		return me.withHandle(handle -> {
			return handle.createUpdate(query).bind("idUser", idUser).bind("idrole", idRole).execute();
		}) == 1;
	}

	public static void removeAllManagerRole(int idUser) {
		Jdbi me = JDBiConnector.me();
		String query = "delete account_roles from account_roles where idAccount=:idUser and idrole<>1 ;";
		me.withHandle(handle -> {
			return handle.createUpdate(query).bind("idUser", idUser).execute();
		});
	}

	public static boolean removeRoleUser(int idUser, int idRole) {
		Jdbi me = JDBiConnector.me();

		String query = "delete account_roles from account_roles where idAccount=:idUser and idrole=:idrole";
		return me.withHandle(handle -> {
			return handle.createUpdate(query).bind("idUser", idUser).bind("idrole", idRole).execute();
		}) == 1;
	}

	public static void main(String[] args) {
//		List<Role> updateRoleUser = new DAOAccounts().getRoleUser(14);
//		for (Role r : updateRoleUser) {
//			System.out.println(r);
//		}
		System.out.println(new DAORoleAccount().isAdmin(2));
	}
}
