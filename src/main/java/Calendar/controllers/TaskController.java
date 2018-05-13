package Calendar.controllers;

import Calendar.models.Task;
import Calendar.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ferney on 23.04.2018.
 */

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }


    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public void createTask(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") long id)
    {
        taskRepository.deleteById(id);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    public void saveTask(@RequestBody Task task){
        taskRepository.save(task);
    }
}