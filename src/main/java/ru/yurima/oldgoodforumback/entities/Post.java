package ru.yurima.oldgoodforumback.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="Posts")
public class Post {
    /**
     * Identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    /**
     * Hibernate service field
     */
    @Version
    private Integer version;

    /**
     * Content
     */
    @Column(name="text", length=1024, nullable = false)
    private String text;

    /**
     * Timestamp
     */
    @Column(name="created", nullable = false)
    private LocalDateTime created;

    /**
     * User, who creates post
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    private User author;

    /**
     * Topic which post belongs to
     */
    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topic topic;

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreated() {
        return created;
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
                ", text='" + text + '\'' +
                ", created=" + created +
                ", author=" + author.getLogin() +
                ", topic=" + topic.getTitle() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return  Objects.equals(text, post.text) &&
                Objects.equals(created, post.created) &&
                Objects.equals(author, post.author) &&
                Objects.equals(topic, post.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, created, author, topic);
    }
}
