package com.kuliza.imdb;

public class ImdbMovie {
		
	private String id; //primary key
	private String rank;
	private String name;
	private String link;
	private String imageLink;
	private String releasedOn;
	private String category;
	private String rating;
	private String usersRated;
	private String reviewsCount;
	private String metascore;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getReleasedOn() {
		return releasedOn;
	}
	public void setReleasedOn(String releasedOn) {
		this.releasedOn = releasedOn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getUsersRated() {
		return usersRated;
	}
	public void setUsersRated(String usersRated) {
		this.usersRated = usersRated;
	}
	public String getReviewsCount() {
		return reviewsCount;
	}
	public void setReviewsCount(String reviewsCount) {
		this.reviewsCount = reviewsCount;
	}
	public String getMetascore() {
		return metascore;
	}
	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}
	@Override
	public String toString() {
		StringBuilder printStr = new StringBuilder();
		printStr.append("ID: " + this.getId() + "\n");
		printStr.append("Rank: " + this.getRank() + "\n");
		printStr.append("Name: " + this.getName() + "\n");
		printStr.append("Link: " + this.getLink() + "\n");
		printStr.append("Image Link: " + this.getImageLink() + "\n");
		printStr.append("Released On: " + this.getReleasedOn() + "\n");
		printStr.append("Category: " + this.getCategory() + "\n");
		printStr.append("Rating: " + this.getRating() + "\n");
		printStr.append("Users Rated: " + this.getUsersRated() + "\n");
		printStr.append("Reviews Count: " + this.getReviewsCount() + "\n");
		printStr.append("Metascore: " + this.getMetascore() + "\n");
		return printStr.toString();
	}	
		
}
