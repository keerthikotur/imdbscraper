package com.kuliza.imdb;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;


public class GetFieldsHelper {	
	
	public static String queryImageLink(Document doc) {
		try {
			return doc.select(".image").first().childNode(1).childNode(1).attr("src");
		} catch (Exception e1) {
			Logger.getLogger(GetFieldsHelper.class.getName()).log(Level.WARNING, "Image link null", e1);
		}
		return "";
	}
	
	public static String queryReleasedOn(Document doc) {
		try {
			return doc.getElementsByAttributeValue("title", "See all release dates").get(0).text();
		} catch (Exception e1) {
			Logger.getLogger(GetFieldsHelper.class.getName()).log(Level.WARNING, "Relesed On null", e1);
		}
		return "";
	}
	
	public static String queryCategory(Document doc) {
		try {
			return doc.select(".itemprop").select("[itemprop=genre]").text();
		} catch (Exception e1) {
			Logger.getLogger(GetFieldsHelper.class.getName()).log(Level.WARNING, "Category null", e1);
		}
		return "";
	}
	
	public static String queryRating(Document doc) {
		try {
			return doc.select(".star-box-details").get(0).select("span[itemprop=ratingValue]").text();
		} catch (Exception e1) {
			Logger.getLogger(GetFieldsHelper.class.getName()).log(Level.WARNING, "Rating null", e1);
		}
		return "";
	}
	
	public static String queryUsersRated(Document doc) {
		try {
			return doc.select(".star-box-details").get(0).select("span[itemprop=ratingCount]").text();
		} catch (Exception e1) {
			Logger.getLogger(GetFieldsHelper.class.getName()).log(Level.WARNING, "Users rated null", e1);
		}
		return "";
	}
	
	public static String queryReviewsCount(Document doc) {
		try {
			return doc.select(".star-box-details").get(0).select("span[itemprop=reviewCount]").text();
		} catch (Exception e1) {
			Logger.getLogger(GetFieldsHelper.class.getName()).log(Level.WARNING, "Reviews Count null", e1);
		}
		return "";
	}
	
	public static String queryMetascore(Document doc) {
		try {
			return doc.select(".star-box-details").get(0).childNodes().get(6).childNodes().get(0).toString();
		} catch (Exception e1) {
		//	Logger.getLogger(GetFieldsHelper.class.getName()).log(Level.WARNING, "Metascore null", e1);
		}
		return "";
	}
	
}
