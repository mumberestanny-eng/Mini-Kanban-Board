package mainboard.boardengine;

import java.util.*;



public class TaskEngine {

    private final List<Task> completeTask = new ArrayList<>();
    private final List<Task> incompleteTask = new ArrayList<>();

    public void createTask(String taskName) {
        incompleteTask.clear();

        if (!taskName.isBlank()) {
            completeTask.add(new Task(taskName));
        }
    }

    public List<Task> getCompleteTask() { return completeTask; }

    public List<Task> getIncompleteTask() { return incompleteTask; }

    public void complete() {
        if (!completeTask.isEmpty()) {
            Task finishedTask = completeTask.removeFirst();
            incompleteTask.add(finishedTask);
        }
    }

    public void incomplete() {
        if (!incompleteTask.isEmpty()) {
            Task unfinishedTask = incompleteTask.removeFirst();
            completeTask.add(unfinishedTask);
        }
    }


}
