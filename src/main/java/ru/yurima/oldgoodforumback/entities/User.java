package ru.yurima.oldgoodforumback.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="USER_ID")
    private long id;

    @Column(name="USER_NAME")
    private String name;

    @Column(name="USER_LOGIN")
    private String login;

    @Column(name="USER_PASSWORD")
    private String password;

    @Column(name="USER_REGISTERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @JsonBackReference
    private List<Topic> topics = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();

    public User(){}

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.registered = new Date();
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
                ", name='" + name + '\'' +
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
        return  name.equals(user.name) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                registered.equals(user.registered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password, registered);
    }
}
