package database;

import java.io.FileNotFoundException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.*;
import org.jdbi.v3.core.Jdbi;

public class DAOAccounts {


	public static void updateAvatarAccount(int idUser,String avatar){
		String query="UPDATE `accounts` SET `avatar` =:avatar WHERE (`id` = :idUser);";
		Jdbi me = JDBiConnector.me();
		me.withHandle(handle->{
			return handle.createUpdate(query).bind("idUser",idUser).bind("avatar",avatar).execute();
		});
	}

	public static Account getUserComment(int idUser){
		String query="select id,avatar,fullName from accounts where id=:idUser and status=1 and isActive=1";
		Jdbi me = JDBiConnector.me();
		return me.withHandle(handle -> {
			return handle.createQuery(query).bind("idUser",idUser).mapToBean(Account.class).one();
		});
	}
	public static Account Login(String userName, String passWord){
		Encode encode = new Encode();
		String pass = encode.toSHA1(passWord);
		Account account = null;
		String query = "SELECT id,userName,password,email,avatar,isActive,fullName,phone,balance,status,externalId FROM animeweb.accounts where userName= :UserName and password = :Password  and status=1 and typeId=1";
		Jdbi me = JDBiConnector.me();
		account = me.withHandle(handle -> {
			return handle.createQuery(query).bind("UserName", userName).bind("Password", pass)
					.mapToBean(Account.class).findFirst().orElse(null);

		});
		if (account != null) {
			account.setRoles(getRoleUser(account.getId()));


		}
		return account;
	}

	public static Map<String,Role> getRoleUser(int idUser) {
		String query = "select distinct a.idRole,description from account_roles a join roles r on a.idRole = r.id where a.idAccount=:idAccount";
		Jdbi me = JDBiConnector.me();
		Map<String,Role> result;
		result = me.withHandle(handle -> {
			return handle.createQuery(query)
					.bind("idAccount", idUser)
					.map((rs, ctx) -> {
						int roleId = rs.getInt("idRole");
						String description = rs.getString("description");
						Role role = new Role(roleId, description);
						role.setPermissions(getRolePermission(idUser, roleId));
						return role;
					})
					.collect(Collectors.toMap(Role::getDescription, Function.identity()));
		});
		result.forEach((key,value)->{
			value.setPermissions(getRolePermission(idUser,value.getId()));
		});
		return result;
	}
	public static List<Permission> getRolePermission(int idUser,int idRole){
		String query ="select p.id,p.description,p.resource,p.action,p.externalResource,p.idSupplier,p.idGenre from account_roles ar join permissions p on ar.idPermission = p.id where ar.idAccount =:idAccount and ar.idRole =:idRole";
		Jdbi me = JDBiConnector.me();
		return me.withHandle(handle -> {
			return handle.createQuery(query).bind("idAccount",idUser).bind("idRole",idRole).mapToBean(Permission.class).list();
		});
	}


	public static boolean editPassword(int idUser, String encryptPassword) {
		Jdbi me = JDBiConnector.me();
		String query="UPDATE `accounts` SET `Password` =:Password WHERE (`id` =:idUser);";
		return me.withHandle(handle->{
			return handle.createUpdate(query).bind("Password", encryptPassword).bind("idUser", idUser).execute();
		})==1;
		
	}
	public static boolean unlockUser(int idUser) {
		Jdbi me = JDBiConnector.me();
		String query="UPDATE `accounts` SET `isActive` = '1' WHERE (`id` =:idUser);";
		return me.withHandle(handle->{
			return handle.createUpdate(query).bind("idUser", idUser).execute();
		})==1;
		
	}
	public static boolean blockAccount(int idUser) {
		Jdbi me = JDBiConnector.me();
		String query="UPDATE `accounts` SET `isActive` = '0' WHERE (`id` =:idUser);";
		return me.withHandle(handle->{
			return handle.createUpdate(query).bind("idUser", idUser).execute();
		})==1;
	}

	public static boolean checkExistEmail(String email,int typeID) {
		Jdbi me = JDBiConnector.me();
		String query="select exists (select 1 from animeweb.accounts where typeid=:typeid and Email=:Email and status=1)";
		
		return me.withHandle(handle->{
			return handle.createQuery(query).bind("typeid", typeID).bind("Email", email).mapTo(Integer.class).one();
		})==1;
	}
	public static boolean editUser(String fullName, String email, String phoneNumber,String avatar,int idUser) {
		Jdbi me = JDBiConnector.me();
		String query = "UPDATE `animeweb`.`accounts` SET `Email` = :Email, `avatar` = :avatar, `fullName` = :FullName, `phone` = :PhoneNumber WHERE (`id` = :idUser);";
		
		return me.withHandle(handle->{
			return handle.createUpdate(query).bind("Email", email).bind("avatar", avatar).bind("FullName",fullName).bind("PhoneNumber", phoneNumber).bind("idUser", idUser).execute();
		})==1;
	}


	public static List<AccountType> getlistType() {
		Jdbi me = JDBiConnector.me();
		String query = "SELECT id,description FROM animeweb.account_types";
		return me.withHandle(handle -> {
			return handle.createQuery(query).mapToBean(AccountType.class).list();
		});
	}
	public static AccountType gettypeAccount(int id) {
		Jdbi me = JDBiConnector.me();
		String query = "SELECT act.id,description FROM accounts ac join account_types act on ac.typeId=act.id where ac.id=:id";
		return me.withHandle(handle -> {
			return handle.createQuery(query).bind("id",id).mapToBean(AccountType.class).one();
		});
	}
	public static boolean removeUserbyId(int idUser) {
		Jdbi me = JDBiConnector.me();
		String query = "UPDATE `accounts` SET `status` = '0' WHERE (id=:idUser);";
		return me.withHandle(handle -> {
			return handle.createUpdate(query).bind("idUser", idUser).execute();
		})==1;
	}

	public static int getSizeListAccountNormal() {
		Jdbi me = JDBiConnector.me();
		String query = "select distinct ac.id from animeweb.accounts ac join animeweb.account_roles acr on ac.id = acr.idAccount where 6 not in"
				+ " (select idRole from animeweb.account_roles where idAccount = ac.id)and ac.status=1";
		List<Account> result = me.withHandle(handle -> {
			return handle.createQuery(query).mapToBean(Account.class).list();
		});
		return result.size();
	}

	public static List<Account> getListAccount(int offset, int limit) {

		Jdbi me = JDBiConnector.me();
		String query = "select distinct ac.id,userName,password,email,avatar,typeId,isActive,joinDate,fullName,phone from animeweb.accounts ac join animeweb.account_roles acr on ac.id = acr.idAccount where 6 not in"
				+ " (select idRole from animeweb.account_roles where idAccount = ac.id) and ac.status=1 LIMIT :limit OFFSET :offset";
		List<Account> result = me.withHandle(handle -> {
			return handle.createQuery(query).bind("limit", limit).bind("offset", offset).mapToBean(Account.class).list();
		});
		for (Account ac : result) {
			ac.setRoles(getRoleUser(ac.getId()));
			ac.setType(gettypeAccount(ac.getId()));
		}

		return result;
	}

	
	public static int findIdByUserName(String userName) throws SQLException {
		int idUser = -1;
		Connection conn = DataSource.getConnection();
		PreparedStatement prepare = conn
				.prepareStatement("SELECT id FROM animeweb.accounts where UserName=? and typeId=1 and status=1");
		prepare.setString(1, userName);
		ResultSet rs = prepare.executeQuery();
		if (rs.next()) {
			idUser = rs.getInt("id");
			return idUser;
		}
		return idUser;

	}

	public static Account findAccountById(int idUser) {

		Jdbi me = JDBiConnector.me();
		String query = "SELECT id,UserName,Password,Email,avatar,typeId,isActive,fullName,phone FROM animeweb.accounts where id=:idUser and status=1";

		Account account;
		account = me.withHandle(handle -> {
			return handle.createQuery(query).bind("idUser", idUser).mapToBean(Account.class).findFirst().orElse(null);

		});
		if (account != null) {
			account.setRoles(getRoleUser(account.getId()));
			account.setType(gettypeAccount(account.getId()));
		}
		return account;

	}

	public static Account findUserByUserNameandEmail(String userName, String Email) {
		Jdbi me = JDBiConnector.me();
		String query = "SELECT id,UserName,Password,Email,avatar,typeId,isActive FROM animeweb.accounts where UserName= :UserName or Email= :Email and typeId=1 and status=1";

		Account account;
		account = me.withHandle(handle -> {
			return handle.createQuery(query).bind("UserName", userName).bind("Email", Email).mapToBean(Account.class)
					.findFirst().orElse(null);

		});
		return account;
	}



	public static int findIdSocialUser(String externalId, String email) throws SQLException {
		int id;
		Jdbi me = JDBiConnector.me();

		String query = "SELECT id from animeweb.accounts where externalId =:externalId and EMAIL=:Email";
		id = me.withHandle(handle -> {
			return handle.createQuery(query).bind("externalId", externalId).bind("Email", email).mapToBean(Account.class)
					.findFirst().orElse(new Account()).getId();
		});
		return id;
	}

	public static Account loginAccountSocialId(int idUser, int type) throws SQLException, FileNotFoundException {
		Account account = null;

		Jdbi me = JDBiConnector.me();
		String query = "SELECT id,UserName,Password,fullName,balance,Email,avatar,typeId,isActive FROM animeweb.accounts where id=:idUser and typeId=:typeId and status=1";
		account = me.withHandle(handle -> {
			return handle.createQuery(query).bind("idUser", idUser).bind("typeId", type).mapToBean(Account.class)
					.findFirst().orElse(null);
		});
		if (account != null) {
			account.setRoles(getRoleUser(idUser));
		}
		System.out.println(account);
		return account;
	}

	public static int findIdUserAccount(String externalId, int type) throws SQLException {
		int id;

		Jdbi me = JDBiConnector.me();
		String query = "SELECT id from animeweb.accounts where  externalId=:externalId and typeId =:typeId and status=1";
		id = me.withHandle(handle -> {
			return handle.createQuery(query).bind("externalId", externalId).bind("typeId", type).mapToBean(Account.class)
					.findFirst().orElse(new Account()).getId();
		});
		return id;
	}

	public static int addBaseUser(String userName, String password, String email, String fullName, String phoneNumber) {
		Encode encrypt = new Encode();
		String encryptPassword = encrypt.toSHA1(password);
		Jdbi me = JDBiConnector.me();
		String avatarPath ="/anime-main/storage/avatarUser/";

		final int[] idUserFinal = {-1};
		me.useHandle(handle -> {
			handle.begin();
			try {
				String query = "INSERT INTO accounts (userName, password,email,avatar,typeId,isActive,fullName,phone) VALUES (:Username,:Password,:Email,:avatar,1,1,:FullName,:PhoneNumber) ";
				int idUser = handle.createUpdate(query).bind("Username", userName).bind("Password", encryptPassword)
						.bind("Email", email).bind("avatar", avatarPath + "defaultavatar.jpg")
						.bind("FullName", fullName).bind("PhoneNumber", phoneNumber).executeAndReturnGeneratedKeys()
						.mapTo(Integer.class).findFirst().orElse(-1);

				String query1 = "INSERT INTO  account_roles (idAccount, idRole) VALUES (:idUser,:idrole) ";
				handle.createUpdate(query1).bind("idUser", idUser).bind("idrole", Role.Base_User).execute();
				handle.commit();
				idUserFinal[0] = idUser;
			} catch (Exception e) {
				e.printStackTrace();
				handle.rollback();

			}
		});
		return idUserFinal[0];

	}

	public static void addGoogle(String externalId, String email, String userName, String avatar, String fullName, String phone)
			throws SQLException {

		Encode encrypt = new Encode();
		String pass = encrypt.toSHA1(externalId);

		Jdbi me = JDBiConnector.me();
		me.useHandle(handle -> {
			handle.begin();
			try {
				String query = "INSERT INTO accounts (Username, Password,Email,avatar,typeId,isActive,FullName,phone,externalId) VALUES (:Username,:Password,:Email,:avatar,2,1,:FullName,:PhoneNumber,:externalId) ";
				int idUser = handle.createUpdate(query).bind("Username", userName).bind("Password", pass)
						.bind("Email", email).bind("avatar", avatar).bind("FullName", fullName)
						.bind("PhoneNumber", phone).bind("externalId",externalId).executeAndReturnGeneratedKeys().mapTo(Integer.class).findFirst()
						.orElse(-1);
				String query3 = "INSERT INTO  account_roles (idAccount, idrole) VALUES (:idUser,:idrole) ";
				handle.createUpdate(query3).bind("idUser", idUser).bind("idrole", Role.Base_User).execute();
				handle.commit();
			} catch (Exception e) {
				e.printStackTrace();
				handle.rollback();

			}
		});

	}

	public static boolean settingUser(String userName, String passWord, String email)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = DataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("update account set PassW=?,Email=? where UserName=?");
		ps.setString(1, passWord);
		ps.setString(2, email);
		ps.setString(3, userName);
		int check = ps.executeUpdate();

		boolean result = check == 1 ? true : false;
		return result;
	}

	// log in withfb
	public static Account checkAcountFacebook(String email, String idfb) throws SQLException, FileNotFoundException {
		Account account = null;
		String query = "SELECT a.idUser,a.UserName,a.Password,a.Email,a.avatar,a.typeId,a.isActive,fb.idFacebook FROM animeweb.accounts a join animeweb.accounts_facebook fb on a.idUser=fb.idUser where a.Email= ? and fb.idFacebook=? and a.typeId=3 and status=1;";

		Jdbi me = JDBiConnector.me();
		account = me.withHandle(handle -> {
			return handle.createQuery(query).bind(0, email).bind(1, idfb).mapToBean(Account.class).findFirst()
					.orElse(null);

		});
		if (account != null) {
			account.setRoles(getRoleUser(account.getId()));
		}
		return account;

	}

	// add account fb if not exist on database
	public static void createAccountByFB(String externalId, String email, String userName, String avatar, String fullName) throws SQLException {


		Encode encrypt = new Encode();
		String pass = encrypt.toSHA1(externalId);

		Jdbi me = JDBiConnector.me();
		me.useHandle(handle -> {
			handle.begin();
			try {
				String query = "INSERT INTO accounts (Username, Password,Email,avatar,typeId,isActive,FullName,phone,externalId) VALUES (:Username,:Password,:Email,:avatar,3,1,:FullName,null,:externalId) ";
				int idUser = handle.createUpdate(query).bind("Username", userName).bind("Password", pass)
						.bind("Email", email).bind("avatar", avatar).bind("FullName", fullName)
						.bind("externalId", externalId).executeAndReturnGeneratedKeys().mapTo(Integer.class).findFirst()
						.orElse(-1);
				String query3 = "INSERT INTO  account_roles (idAccount, idrole) VALUES (:idUser,:idrole) ";
				handle.createUpdate(query3).bind("idUser", idUser).bind("idrole", Role.Base_User).execute();
				handle.commit();
			} catch (Exception e) {
				e.printStackTrace();
				handle.rollback();

			}
		});

	}


	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
//		System.out.println(new DAOAccounts().checkAcountFacebook("20130115@gmail.com", "12345678"));
//		new DAOAccounts().insertAcountFB("ádsada", "1231111", "he123he@gmail.com");
		// System.out.println(getListAccountNormal(0, 100).size());
		// System.out.println(getSizeListAccountNormal());
		//System.out.println(new DAOAccounts().getlistType());
//		System.out.println(new DAOAccounts().Login("admin","ZvniDNSIriVuH6j9pEQZHQey9e4="));
	//System.out.println(DAOAccounts.getPurchasedMovie(1));
//		System.out.println(new DAOAccounts().getListAccount(0,3));
//		System.out.println(new DAOAccounts().getSizeListAccountNormal());
//		System.out.println(new DAOAccounts().gettypeAccount(1));
//		System.out.println(new DAOAccounts().getlistType());
//		System.out.println(new DAOAccounts().getRoleUser(2));
//		System.out.println(new DAOAccounts().getRolePermission(2,3));
		//System.out.println(new DAOAccounts().loginAccountSocialId(2,2).isManagement());
//		System.out.println(new DAOAccounts().getSizeListAccountNormal());
	}


	

	
}
