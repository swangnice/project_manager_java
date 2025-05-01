
# Elements in Database

## Members

| Name     | Type    | Description    | Argument |
| -------- | ------- | -------------- | ------- |
| id  | INT    |  automatically creating an index for faster lookups  ｜AUTO_INCREMENT PRIMARY KEY｜
| first_name | VARCHAR(63)  | first name ｜          ｜
| last_name    | VARCHAR(63)| last name  |           |
| username  | VARCHAR(100) |           |           |
| pwd_hash  | VARCHAR(64) |  Hash of "salt+pwd"|           |
| role   | VARCHAR(100) | The role or position of a member |           |



## Projects
| Name     | Type    | Description    | Argument |
| -------- | ------- | -------------- | ------- |
| id  | INT    |  automatically creating an index for faster lookups  ｜AUTO_INCREMENT PRIMARY KEY｜
|  name  |    VARCHAR(100) |      Project Name     |           |
|           |           |           |           |



## Tasks
| Name     | Type    | Description    | Argument |
| -------- | ------- | -------------- | ------- |
|           |           |           |           |
|           |           |           |           |




``` java
CREATE TABLE member_project (
  member_id INT,
  project_id INT,
  PRIMARY KEY (member_id, project_id),
  FOREIGN KEY (member_id)  REFERENCES members(id),
  FOREIGN KEY (project_id) REFERENCES projects(id)
);
```