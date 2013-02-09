CREATE SEQUENCE users_id_seq;
CREATE TABLE "user"(
  id    bigint DEFAULT nextval('users_id_seq') PRIMARY KEY,
  login text not null,
  name text,
  surname text,
  CONSTRAINT ak_login UNIQUE (login )
);

CREATE SEQUENCE tasks_id_seq;
CREATE TABLE task (
    id    bigint DEFAULT nextval('tasks_id_seq') PRIMARY KEY,
    what   text,
    when_date date,
    difficulty text,
    points int,
    owned_by     bigint,
    CONSTRAINT FK_task_owned_by FOREIGN KEY(owned_by) REFERENCES "user"(ID)
    );

CREATE TABLE likes (
	LIKED_BY_ID bigint NOT NULL,
	TASK_ID bigint NOT NULL,
	CONSTRAINT pk_like PRIMARY KEY(LIKED_BY_ID,TASK_ID),
	CONSTRAINT FK_like_liked_by FOREIGN KEY(LIKED_BY_ID) REFERENCES "user"(ID),
	CONSTRAINT FK_like_task FOREIGN KEY(TASK_ID) REFERENCES TASK(ID)
	);
CREATE INDEX IDX_likes  ON likes(LIKED_BY_ID,TASK_ID);

CREATE view user_task_likes_cnt
as	  SELECT task.id, task.what, task.when_date, task.difficulty, task.points, task.owned_by,"user".login, count(*) AS count
           FROM task
      JOIN likes ON task.id = likes.task_id
      JOIN "user" ON "user".id = task.owned_by
     GROUP BY likes.task_id, task.id, task.what, task.when_date, task.difficulty, task.points, task.owned_by,"user".login
UNION ALL
         SELECT task.id, task.what, task.when_date, task.difficulty, task.points, task.owned_by, "user".login, 0 AS count
           FROM task
      LEFT JOIN likes ON task.id = likes.task_id
      JOIN "user" ON "user".id = task.owned_by
     WHERE likes.task_id IS NULL;



	-- POPULATE WITH INITIAL DATA

INSERT INTO "user" (login, name, surname) VALUES ('admin', 'jan', 'kowalski');
INSERT INTO "user" (login, name, surname) VALUES ('adamw', 'adam', 'wochniak');
INSERT INTO "user" (login, name, surname) VALUES ('cezary', 'cezary', 'malinowski');


INSERT INTO task (what, when_date, difficulty, points, owned_by) VALUES ('Running 30 min / 5km distance', '2013-02-02', 'EASY', 10, 3);
INSERT INTO task (what, when_date, difficulty, points, owned_by) VALUES ('read ''Crime and punishment'' by Dostoyevsky', '2012-02-01', 'EASY', 10, 1);
INSERT INTO task (what, when_date, difficulty, points, owned_by) VALUES ('Baked a cake', '2013-02-02', 'EASY', 10, 2);
INSERT INTO task (what, when_date, difficulty, points, owned_by) VALUES ('Done SCJP exam', '2012-01-11', 'EASY', 10, 2);
INSERT INTO task (what, when_date, difficulty, points, owned_by) VALUES ('Finished reading ''The Art of Unit Testing: With Examples in .Net''', '2012-01-11', 'EASY', 10, 2);
INSERT INTO task (what, when_date, difficulty, points, owned_by) VALUES ('Finished reading ''The Art of Unit Testing: With Examples in .Net''', '2013-02-11', 'EASY', 10, 1);
INSERT INTO task (what, when_date, difficulty, points, owned_by) VALUES ('Completed Warsaw Marathon at 3h35"', '2012-09-09', 'MEDIUM', 20, 3);
INSERT INTO task (what, when_date, difficulty, points, owned_by) VALUES ('Got 5 at Math', '2013-02-02', 'EASY', 10, 1);


INSERT INTO likes (liked_by_id, task_id) VALUES (1, 1);
INSERT INTO likes (liked_by_id, task_id) VALUES (2, 1);
INSERT INTO likes (liked_by_id, task_id) VALUES (2, 2);
INSERT INTO likes (liked_by_id, task_id) VALUES (3, 1);
INSERT INTO likes (liked_by_id, task_id) VALUES (3, 2);
INSERT INTO likes (liked_by_id, task_id) VALUES (3, 3);

