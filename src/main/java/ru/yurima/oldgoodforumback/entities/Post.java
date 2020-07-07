package ru.yurima.oldgoodforumback.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Posts")
public class Post {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="POST_ID")
    private long id;

    @Column(name="POST_CONTENT", length=999)
    private String content;

    @Column(name="POST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name="POST_AUTHOR")
    private User author;

    @ManyToOne
    @JoinColumn(name="POST_TOPIC")
    private Topic topic;

    public Post(){}

    public Post(String content, User author, Topic topic) {
        this.content = content;
        this.dateTime = new Date();
        this.author = author;
        author.addPost(this);
        this.topic = topic;
        topic.addPost(this);
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", author=" + author +
                ", topic=" + topic +
                '}';
    }
}
