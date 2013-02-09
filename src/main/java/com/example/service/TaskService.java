package com.example.service;

import com.generated.onewebsql.UserTaskLikesCnt;
import com.generated.tasks.Task;

import java.util.List;

/**
 * Service for managing user tasks.
 */
public interface TaskService {

    /**
     * Returns task by id
     *
     * @param taskId task id
     * @return task
     */
    Task getTasksById(Long taskId);

    /**
     * Returns task list of given user with task name containing string
     *
     * @param what   task name
     * @param userId user id
     * @return list of tasks
     */
    List<Task> getTasksLikeWhatAndUserId(String what, Long userId);

    /**
     * Returns a list of tasks by owner id and task name together with likes count per each task
     *
     * @param what   task name
     * @param userId task's owner it
     * @return List of tasks with likes count
     */
    List<UserTaskLikesCnt> getTaskLikesCntLikeWhatAndUserId(String what, Long userId);

    /* Saves task data if it exists or creates new task.
    */
    Task save(Task task);

    /**
     * Delete task given by id
     *
     * @param taskId task id
     */
    void delete(Long taskId);

    /**
     * Like a task of id taskId by user with id of userId
     *
     * @param taskId task id
     * @param userId user id
     */
    void like(Long taskId, Long userId);
}
