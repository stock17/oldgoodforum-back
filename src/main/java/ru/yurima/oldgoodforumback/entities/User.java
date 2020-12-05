package ru.yurima.oldgoodforumback.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Users")
public class User {

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
     * Login
     */
    @Column(name="login", nullable = false, length = 64)
    private String login;

    /**
     * Password
     */
    @Column(name="password", nullable = false, length = 64)
    private String password;

    /**
     * Registration date and time
     */
    @Column(name="registered", nullable = false)
    private Date registered;

    /**
     * Topics
     */
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "author")
    private List<Topic> topics = new ArrayList<>();

    /**
     * Posts
     */
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return new Date(registered.getTime());
    }
    public void setRegistered(Date registered) {
        this.registered = new Date(registered.getTime());
    }

    public List<Topic> getTopics() {
        return topics;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addTopic(Topic topic){
        topics.add(topic);
    }

    public void removeTopic(Topic topic){
        topics.remove(topic);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return  login.equals(user.login) &&
                password.equals(user.password) &&
                registered.equals(user.registered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, registered);
    }
}
