package ru.yurima.oldgoodforumback.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Topics")
public class Topic {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name = "TOPIC_ID")
    private long id;

    @Column(name="TOPIC_TITLE")
    private String title;

    @Column(name="TOPIC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name="TOPIC_AUTHOR")
    private User author;

    @OneToMany(mappedBy="topic")
    List<Post> posts;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
