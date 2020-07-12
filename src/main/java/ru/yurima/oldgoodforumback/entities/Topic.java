package ru.yurima.oldgoodforumback.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy="topic", cascade = CascadeType.ALL)
    List<Post> posts = new ArrayList<>();

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
    public void setTitle(String title) {this.title = title;}

    public User getAuthor() {
        return author;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateTime=" + dateTime +
                ", author=" + author.getLogin() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return  Objects.equals(title, topic.title) &&
                Objects.equals(dateTime, topic.dateTime) &&
                Objects.equals(author, topic.author);

    }

    @Override
    public int hashCode() {
        return Objects.hash(title, dateTime, author);
    }
}
