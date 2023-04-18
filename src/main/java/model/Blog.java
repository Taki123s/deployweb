package model;

import java.time.LocalDateTime;

import java.util.List;



public class Blog {
	private int id;
	private String title;
	private String folder;
	private String avt;
	private LocalDateTime createAt;
	private LocalDateTime releaseAt;
	private List<CommentBlog> listComment;
	private int status;
	public Blog() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getAvt() {
		return avt;
	}

	public void setAvt(String avt) {
		this.avt = avt;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getReleaseAt() {
		return releaseAt;
	}

	public void setReleaseAt(LocalDateTime releaseAt) {
		this.releaseAt = releaseAt;
	}

	public List<CommentBlog> getListComment() {
		return listComment;
	}

	public void setListComment(List<CommentBlog> listComment) {
		this.listComment = listComment;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Blog{" +
				"id=" + id +
				", title='" + title + '\'' +
				", folder='" + folder + '\'' +
				", avt='" + avt + '\'' +
				", createAt=" + createAt +
				", releaseAt=" + releaseAt +
				", listComment=" + listComment +
				'}';
	}
	//	public int getTotalComment() {
//		int rs=listCmt.size();
//		for(CommentBlog bc : listCmt) {
//			rs+=bc.getList().size();
//		}
//		
//		return rs;
//	}
	
	

	

}
