package com.wxxiaomi.ebs.bean;

/**
 * 评论实体
 * @author 12262
 *
 */
public class Comment {

	private int id;
	private int topic_id;
	private String content;
	private int from_uid;
	private String from_nick;
	private String from_head;
	private int to_uid;
	private String to_unick;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFrom_uid() {
		return from_uid;
	}
	public void setFrom_uid(int from_uid) {
		this.from_uid = from_uid;
	}
	public String getFrom_nick() {
		return from_nick;
	}
	public void setFrom_nick(String from_nick) {
		this.from_nick = from_nick;
	}
	public String getFrom_head() {
		return from_head;
	}
	public void setFrom_head(String from_head) {
		this.from_head = from_head;
	}
	public int getTo_uid() {
		return to_uid;
	}
	public void setTo_uid(int to_uid) {
		this.to_uid = to_uid;
	}
	public String getTo_unick() {
		return to_unick;
	}
	public void setTo_unick(String to_unick) {
		this.to_unick = to_unick;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", topic_id=" + topic_id + ", content="
				+ content + ", from_uid=" + from_uid + ", from_nick="
				+ from_nick + ", from_head=" + from_head + ", to_uid=" + to_uid
				+ ", to_unick=" + to_unick + "]";
	}
	public Comment(int id, int topic_id, String content, int from_uid,
			String from_nick, String from_head, int to_uid, String to_unick) {
		super();
		this.id = id;
		this.topic_id = topic_id;
		this.content = content;
		this.from_uid = from_uid;
		this.from_nick = from_nick;
		this.from_head = from_head;
		this.to_uid = to_uid;
		this.to_unick = to_unick;
	}
	public Comment() {
		super();
	}
	
	
	
	
}
