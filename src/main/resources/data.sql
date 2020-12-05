INSERT INTO Users (id, version, login, password, registered) VALUES
   (1, 0, 'user1', 'password1', '2020-12-12'),
   (2, 0, 'user2', 'password2', '2020-12-12')
;

INSERT INTO Topics (id, version, title, created, user_id) VALUES
    (1, 0, 'topic1', '2020-12-12', 1),
	(2, 0, 'topic2', '2020-12-12', 2)
;

INSERT INTO Posts (id, version, text, created, user_id, topic_id) VALUES
    (1, 0, 'post text 1', '2020-12-12', 1, 1),
	(2, 0, 'post text 2', '2020-12-12', 2, 2)
;