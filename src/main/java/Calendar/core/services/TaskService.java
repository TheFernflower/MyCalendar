package Calendar.core.services;


import Calendar.core.models.Task;
import Calendar.core.repositories.TaskRepository;
import Calendar.exceptions.DataMismatchException;
import Calendar.exceptions.EntityNotFoundException;
import Calendar.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Task findById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id = " + id + " not found"));
    }

    @Transactional
    public void deleteById(long id){
        if(taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
        }
        else throw new DataMismatchException("Task with id = " + id + " does not exist");
    }

}
