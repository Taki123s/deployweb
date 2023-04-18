package model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;


public class CommentBlog {
	private int idBlog;
	private int id;
	private int parentId;
	private String content;
	private int idAccount;
	private String replyName;
	private LocalDateTime commentAt;
	private int status;
	private int isActive;
	private List<CommentBlog> replyComment;

	public CommentBlog() {
	}

	public int getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public LocalDateTime getCommentAt() {
		return commentAt;
	}

	public void setCommentAt(LocalDateTime commentAt) {
		this.commentAt = commentAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public List<CommentBlog> getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(List<CommentBlog> replyComment) {
		this.replyComment = replyComment;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//		String fm = format.format(date);
//		System.out.println(fm);
	}

}
