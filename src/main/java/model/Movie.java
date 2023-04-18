package model;


import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;



public class Movie {
	private int id;
	private String name;
	private List<Genre> genres;
	private int currentEpisode;
	private int totalEpisode;
	private int views;
	private List<AvartarMovie> avatars;
	private List<CommentMovie> listComment;
	private List<ChapterMovie> listChapter;
	private String descriptionVN;
	private String descriptionEN;
	private int status;
	private List<RateMovie> listRate;
	private LocalDateTime createAt;
	private List<Producer> listProducer;
	private Supplier supplier;
	private TypeMovie type;
	private double price;
	private double renderPrice;
	private Bonus maxPercent;
	private Bonus minSamePrice;
	private Bonus maxUnitPrice;
	private LocalDateTime purchaseAt;
	private Double purchasePrice;


	public Movie() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public int getCurrentEpisode() {
		return currentEpisode;
	}

	public void setCurrentEpisode(int currentEpisode) {
		this.currentEpisode = currentEpisode;
	}

	public int getTotalEpisode() {
		return totalEpisode;
	}

	public void setTotalEpisode(int totalEpisode) {
		this.totalEpisode = totalEpisode;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<AvartarMovie> getAvatars() {
		return avatars;
	}

	public void setAvatars(List<AvartarMovie> avatars) {
		this.avatars = avatars;
	}

	public List<CommentMovie> getListComment() {
		return listComment;
	}

	public void setListComment(List<CommentMovie> listComment) {
		this.listComment = listComment;
	}

	public List<ChapterMovie> getListChapter() {
		return listChapter;
	}

	public void setListChapter(List<ChapterMovie> listChapter) {
		this.listChapter = listChapter;
	}

	public String getDescriptionVN() {
		return descriptionVN;
	}

	public void setDescriptionVN(String descriptionVN) {
		this.descriptionVN = descriptionVN;
	}

	public String getDescriptionEN() {
		return descriptionEN;
	}

	public void setDescriptionEN(String descriptionEN) {
		this.descriptionEN = descriptionEN;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<RateMovie> getListRate() {
		return listRate;
	}

	public void setListRate(List<RateMovie> listRate) {
		this.listRate = listRate;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public List<Producer> getListProducer() {
		return listProducer;
	}

	public void setListProducer(List<Producer> listProducer) {
		this.listProducer = listProducer;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public TypeMovie getType() {
		return type;
	}

	public void setType(TypeMovie type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public String getFirstAvatar(){
		try{
			return avatars.get(0).getName();
		}catch (Exception e){
			return "null";
		}
	}
	public double getRenderPrice() {
		double[] listPrice = {price- maxUnitPrice.getUnitPrice(), minSamePrice.getSamePrice(),price-((price/100)* maxPercent.getPercent())};

		return Arrays.stream(listPrice).min().getAsDouble();

	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public void setRenderPrice(double renderPrice) {
		this.renderPrice = renderPrice;
	}
	public boolean isFree(){
		return type.getId()==1;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"id=" + id +
				", name='" + name + '\'' +
				", genres=" + genres +
				", currentEpisode=" + currentEpisode +
				", totalEpisode=" + totalEpisode +
				", views=" + views +
				", avatars=" + avatars +
				", listComment=" + listComment +
				", listChapter=" + listChapter +
				", descriptionVN='" + descriptionVN + '\'' +
				", descriptionEN='" + descriptionEN + '\'' +
				", status=" + status +
				", listRate=" + listRate +
				", createAt=" + createAt +
				", listProducer=" + listProducer +
				", supplier=" + supplier +
				", type=" + type +
				", price=" + price +
				", renderPrice=" + renderPrice +
				", maxPercent=" + maxPercent +
				", minSamePrice=" + minSamePrice +
				", maxUnitPrice=" + maxUnitPrice +
				", purchaseAt=" + purchaseAt +
				", purchasePrice=" + purchasePrice +
				'}';
	}

	public String getFirstChapter(){
		try{
			return String.valueOf(listChapter.get(0).getIndex());
		}catch (Exception e){
			return null;
		}
	}
	public Bonus getMaxPercent() {
		return maxPercent;
	}

	public void setMaxPercent(Bonus maxPercent) {
		this.maxPercent = maxPercent;
	}

	public Bonus getMinSamePrice() {
		return minSamePrice;
	}

	public void setMinSamePrice(Bonus minSamePrice) {
		this.minSamePrice = minSamePrice;
	}

	public Bonus getMaxUnitPrice() {
		return maxUnitPrice;
	}

	public void setMaxUnitPrice(Bonus maxUnitPrice) {
		this.maxUnitPrice = maxUnitPrice;
	}

	public LocalDateTime getPurchaseAt() {
		return purchaseAt;
	}

	public String changeFormat(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");
		String formattedDateTime = purchaseAt.format(formatter);
		return  formattedDateTime;
	}
	public void setPurchaseAt(LocalDateTime purchaseAt) {
		this.purchaseAt = purchaseAt;
	}
	//	public double getAvgScore() throws ClassNotFoundException, SQLException {
//		Rate DBAO = new Rate();
//		ArrayList<Integer> listRate = DBAO.getListRate(idMovie);
//		model.RateMovie rate = new model.RateMovie(listRate);
//		double avgScore = rate.averageScore();
//
//		return (double) Math.ceil(avgScore * 10) / 10;
//	}
//
//	public int voteTotal() throws ClassNotFoundException, SQLException {
//		Rate DBAO = new Rate();
//		ArrayList<Integer> listRate = DBAO.getListRate(idMovie);
//		return listRate.size();
//	}

	public int getPerCent(double total) throws ClassNotFoundException, SQLException {

		return (int) ((views / total) * 100);

	}




}
