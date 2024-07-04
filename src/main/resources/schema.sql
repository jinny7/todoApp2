-- Schema creation script
CREATE TABLE todo (
                      todo_id BIGINT NOT NULL AUTO_INCREMENT,
                      title VARCHAR(255) NOT NULL,
                      content VARCHAR(255) NOT NULL,
                      user_name VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      PRIMARY KEY (todo_id)
);
