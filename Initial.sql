CREATE TABLE Sessions
(
    id VARCHAR(16) NOT NULL,
    PRIMARY KEY (id)  
);

CREATE TABLE QuestionAnswers
(
     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
     question VARCHAR(3000) NOT NULL,
     questionType VARCHAR(3000) NOT NULL,
     answer VARCHAR(3000) NOT NULL,
     answerChoices VARCHAR(3000) NOT NULL,
     session_id VARCHAR(16),
     FOREIGN KEY (session_id) REFERENCES Sessions(id),
     PRIMARY KEY (id)
);