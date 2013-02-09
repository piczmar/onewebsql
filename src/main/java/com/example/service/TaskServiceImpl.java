package com.example.service;

import com.generated.onewebsql.Likes;
import com.generated.onewebsql.LikesDAO;
import com.generated.onewebsql.UserTaskLikesCnt;
import com.generated.onewebsql.UserTaskLikesCntDAO;
import com.generated.tasks.Task;
import com.generated.tasks.TaskDAO;
import com.onewebsql.query.LExp;
import com.onewebsql.query.SelectQuery;
import com.onewebsql.util.transaction.TransactionOp;
import com.onewebsql.util.transaction.TransactionUtil;
import com.onewebsql.util.transaction.TransactionalDataSource;
import domain.Difficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TaskService implementation using OneWebSQL
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    LikesDAO likesDAO;
    @Autowired
    UserTaskLikesCntDAO userTaskLikesCntDAO;

    @Autowired
    TransactionalDataSource transactionalDataSource;

    @Override
    public Task getTasksById(Long taskId) {
        return taskDAO.getByPK(taskId);
    }

    /*
   Returns task list given task name and user Id. Parameters are optional, if null then all tasks are returned
    */
    @Override
    public List<Task> getTasksLikeWhatAndUserId(String what, Long userId) {
        SelectQuery selectQuery = new SelectQuery(TaskDAO.TABLE_EXPRESSION);
        LExp whereClause = null;

        if (what != null && !"".equals(what)) {
            whereClause = TaskDAO.WHAT.like("%" + what.trim() + "%");
        }
        if (userId != null) {
            if (whereClause == null) {
                whereClause = TaskDAO.OWNED_BY.eq(userId);
            } else {
                whereClause = whereClause.and(TaskDAO.OWNED_BY.eq(userId));
            }
        }
        selectQuery.orderBy(TaskDAO.WHEN_DATE, false);
        if (whereClause != null) {
            selectQuery.where(whereClause);
        }

        return taskDAO.getTaskList(selectQuery);
    }

    @Override
    public List<UserTaskLikesCnt> getTaskLikesCntLikeWhatAndUserId(String what, Long userId) {
        SelectQuery selectQuery = new SelectQuery(UserTaskLikesCntDAO.TABLE_EXPRESSION);
        LExp whereClause = null;

        if (what != null && !"".equals(what)) {
            whereClause = UserTaskLikesCntDAO.WHAT.like("%" + what.trim() + "%");
        }
        if (userId != null) {
            if (whereClause == null) {
                whereClause = UserTaskLikesCntDAO.OWNED_BY.eq(userId);
            } else {
                whereClause = whereClause.and(UserTaskLikesCntDAO.OWNED_BY.eq(userId));
            }
        }
        selectQuery.orderBy(UserTaskLikesCntDAO.WHEN_DATE, false);
        if (whereClause != null) {
            selectQuery.where(whereClause);
        }

        return userTaskLikesCntDAO.getUserTaskLikesCntList(selectQuery);
    }

    @Override
    public Task save(Task task) {
        calculatePoints(task);
        if (task.getId() != null) {
            taskDAO.update(task);
        } else {
            taskDAO.insert(task);

        }
        return taskDAO.getByPK(task.getId());
    }


    @Override
    public void delete(final Long taskId) {
        SelectQuery selectQuery = new SelectQuery(LikesDAO.TABLE_EXPRESSION);
        LExp whereClause = LikesDAO.TASK_ID.eq(taskId);
        final List<Likes> likes = likesDAO.getLikesList(whereClause);

        TransactionUtil transactionUtil = new TransactionUtil(transactionalDataSource);
        // run your operations within transaction
        transactionUtil.withTransaction(new TransactionOp<Object>() {
            public Object invoke(TransactionalDataSource transactionalDataSource) {
                likesDAO.delete(likes);
                taskDAO.delete(taskId);

                return null;
            }
        });

    }

    @Override
    public void like(Long taskId, Long userId) {
        Likes like = likesDAO.getByPK(userId, taskId);
        if (like == null) {
            // no Like yet so create new but check if task belongs to other user
            Task task = taskDAO.getByPK(taskId);
            if (!task.getOwnedBy().equals(userId)){
                like = new Likes();
                like.setLikedById(userId);
                like.setTaskId(taskId);
                likesDAO.insert(like);
            }
        }
    }

    private void calculatePoints(Task task) {
        Difficulty difficulty = Difficulty.valueOf(task.getDifficulty());
        task.setPoints((difficulty.ordinal() + 1) * 10);
    }
}
