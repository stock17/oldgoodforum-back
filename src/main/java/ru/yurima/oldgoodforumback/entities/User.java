package ru.yurima.oldgoodforumback.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="AUTHOR_ID")
    private long id;

    @Column(name="AUTHOR_NAME")
    private String name;

    @Column(name="AUTHOR_LOGIN")
    private String login;

    @Column(name="AUTHOR_PASSWORD")
    private String password;

    @Column(name="AUTHOR_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @OneToMany(mappedBy = "author")
    private List<Topic> topics;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    public User(){}

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.dateTime = new Date();
        this.topics = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public Date getDateTime() {
        return new Date(dateTime.getTime());
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = new Date(dateTime.getTime());
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
        return String.format("User id: %d, name: %s, login: %s, password: %s, created: %s", id, name, login, password, dateTime);
    }
}
