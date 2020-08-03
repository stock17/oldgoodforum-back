package ru.yurima.oldgoodforumback.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    @Column(name="POST_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne
    @JoinColumn(name="POST_AUTHOR")
    private User author;

    @ManyToOne
    @JoinColumn(name="POST_TOPIC")
    private Topic topic;

    public Post(){}

    public Post(User author, Topic topic, String content) {
        this.content = content;
        this.created = new Date();
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

    public Date getCreated() {
        return created;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        if (author != null) author.removePost(this);
        this.author = author;
    }

    public void unSetAuthor() {
        if (author != null) author.removePost(this);
        author = null;
    }

    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        if (topic != null) topic.removePost(this);
        this.topic = topic;
    }

    public void unSetTopic() {
        if (topic != null) topic.removePost(this);
        topic = null;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dateTime=" + created +
                ", author=" + author.getLogin() +
                ", topic=" + topic.getTitle() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return  Objects.equals(content, post.content) &&
                Objects.equals(created, post.created) &&
                Objects.equals(author, post.author) &&
                Objects.equals(topic, post.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, created, author, topic);
    }
}
