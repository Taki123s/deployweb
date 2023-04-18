package model;

public class Permission {
    private int id;
    private String description;
    private String resource;
    private String action;
    private int idRole;
    private String externalResource;
    private int idSupplier;
    private int idGenre;

    public Permission() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getExternalResource() {
        return externalResource;
    }

    public void setExternalResource(String externalResource) {
        this.externalResource = externalResource;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", resource='" + resource + '\'' +
                ", action='" + action + '\'' +
                ", idRole=" + idRole +
                ", externalResource='" + externalResource + '\'' +
                ", idSupplier=" + idSupplier +
                ", idGenre=" + idGenre +
                '}';
    }
}
