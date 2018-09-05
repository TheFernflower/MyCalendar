package Calendar.core.services;


import Calendar.core.models.Task;
import Calendar.core.repositories.TaskRepository;
import Calendar.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Autowired
    CustomUserDetailsService userService;


    @Transactional(readOnly = true)
    public Iterable<Task> findAll() {
        Iterable<Task> result = taskRepository.findAll();
        return result;
    }

    @Transactional(readOnly = true)
    public Iterable<Task> findAllByUserId() {
        long userId = userService.getCurrentUserId();
        Iterable<Task> result = taskRepository.findAllByUserId(userId);
        return result;
    }

    @Transactional
    public void save(Task task){
        task.setUserId(userService.getCurrentUserId());
        taskRepository.save(task);
    }

    @Transactional
    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public void deleteById(long id){
        taskRepository.deleteById(id);
    }



}
