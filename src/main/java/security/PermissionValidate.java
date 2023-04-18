package security;

import database.DAORoleAccount;
import model.Account;
import model.Permission;
import model.Role;


public class PermissionValidate {


    public static boolean userHasPermission(Account account, String source, String action, String externalResource, boolean isUpdateMovie, int idSupplier, int idGenre) {


        if (account.isAdmin()) return true;

        if (!isUpdateMovie) {
            return checkPermissionAction(account, source, action, externalResource);
        } else {
            return checkPermissionUpdateMovie(account, source, action, externalResource, idSupplier, idGenre);
        }

    }

    public static boolean checkPermissionAction(Account account, String source, String action, String externalResource) {
        if (account.isAdmin()) return true;
        for (Role role : account.getRoles().values()) {
            for (Permission pms : role.getPermissions()) {
                if (pms.getResource().equalsIgnoreCase(source) && pms.getAction().equalsIgnoreCase(action) && pms.getExternalResource().equalsIgnoreCase(externalResource)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkPermissionUpdateMovie(Account account, String source, String action, String externalResource, int idSupplier, int idGenre) {
        if (account.isAdmin()) return true;
        for (Role role : account.getRoles().values()) {
            for (Permission pms : role.getPermissions()) {
                if (pms.getResource().equalsIgnoreCase(source) && pms.getAction().equalsIgnoreCase(action) && pms.getExternalResource().equalsIgnoreCase(externalResource) && pms.getIdSupplier() == idSupplier && pms.getIdGenre() == idGenre) {
                    return true;
                }
            }
        }
        return false;

    }
}


