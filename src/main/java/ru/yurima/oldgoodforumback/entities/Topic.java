package ru.yurima.oldgoodforumback.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "Topics")
public class Topic {

    /**
     * Identifier
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Hibernate service field
     */
    @Version
    private Integer version;

    /**
     * Topic title
     */
    @Column(name="title", nullable = false, length = 64)
    private String title;

    /**
     * Timestamp
     */
    @Column(name="created", nullable = false)
    private LocalDateTime created;

    /**
     * Author
      */
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User author;

    /**
     * Posts
     */
    @OneToMany(mappedBy="topic", cascade = CascadeType.ALL)
    List<Post> posts = new ArrayList<>();

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

    public void setAuthor(User author) {
        if (author != null) {
            author.getTopics().remove(this);
        }
        this.author = author;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setTopic(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setTopic(null);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateTime=" + created +
                ", author=" + author.getLogin() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return  Objects.equals(title, topic.title) &&
                Objects.equals(created, topic.created) &&
                Objects.equals(author, topic.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, created, author);
    }
}
