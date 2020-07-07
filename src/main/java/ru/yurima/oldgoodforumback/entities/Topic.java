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

    public Topic () {}

    public Topic(String title, User author) {
        this.title = title;
        this.dateTime = new Date();
        this.author = author;
        author.addTopic(this);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return String.format("Topic title: %s, author: %s, date: %s", title, author.getName(), dateTime);
    }
}
