package dat;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TaskList<T extends Task> implements Iterable<T>
{
    private List<T> taskList = new ArrayList<>();

    public void add(T task)
    {
        taskList.add(task);
    }

    public List<T> filter(String keyword)
    {
        return taskList.stream()
                .filter(task -> task.getTitle().contains(keyword) || task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<T> sortByDueDate()
    {
        return taskList.stream()
                .sorted((t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate()))
                .collect(Collectors.toList());
    }

    public List<T> dueToday(LocalDate today)
    {
        return taskList.stream()
                .filter(task -> task.getDueDate().equals(today))
                .collect(Collectors.toList());
    }

    public List<T> overDue(LocalDate today)
    {
        return taskList.stream()
                .filter(task -> task.getDueDate().isBefore(today))
                .collect(Collectors.toList());
    }

    public void print()
    {
        System.out.println("All tasks :::::::");
        taskList.stream().forEach(System.out::println);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new TaskListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action)
    {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator()
    {
        return Iterable.super.spliterator();
    }

    private class TaskListIterator implements Iterator<T>
    {
        private int index = 0;
        private int lastReturnedIndex = -1; // Track the last returned index by next()

        @Override
        public boolean hasNext()
        {
            return index < taskList.size();
        }

        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = index;
            return taskList.get(index++);
        }

        @Override
        public void remove()
        {
            if (lastReturnedIndex < 0)
            {
                throw new IllegalStateException("remove() can only be called once after next()");
            }
            taskList.remove(lastReturnedIndex);
            index = lastReturnedIndex; // Reset index to last returned after removal
            lastReturnedIndex = -1; // Reset lastReturnedIndex to prevent multiple removes
        }
    }

    public List<Task> populateListOfTasks()
    {
        // Creating 5 instances of Task with fake data
        Task task1 = new Task("Complete report", "Finish the quarterly financial report.", LocalDate.of(2024, 9, 5));
        Task task2 = new Task("Team meeting", "Discuss the new project plan with the team.", LocalDate.of(2024, 9, 7));
        Task task3 = new Task("Code review", "Review the pull requests for the new feature.", LocalDate.of(2024, 9, 3));
        Task task4 = new Task("Client presentation", "Prepare and deliver the presentation to the client.", LocalDate.of(2024, 9, 8));
        Task task5 = new Task("Update documentation", "Revise the API documentation based on recent changes.", LocalDate.of(2024, 9, 10));

        // Creating 5 instances of GardenTask with fake data
        GardenTask gardenTask1 = new GardenTask("Water plants", "Water the plants in the front yard.", LocalDate.of(2024, 9, 2), "Front Yard");
        GardenTask gardenTask2 = new GardenTask("Trim hedges", "Trim the hedges around the backyard.", LocalDate.of(2024, 9, 4), "Backyard");
        GardenTask gardenTask3 = new GardenTask("Plant flowers", "Plant new flowers in the garden bed.", LocalDate.of(2024, 9, 6), "Garden Bed");
        GardenTask gardenTask4 = new GardenTask("Fertilize lawn", "Apply fertilizer to the lawn area.", LocalDate.of(2024, 9, 9), "Lawn");
        GardenTask gardenTask5 = new GardenTask("Harvest vegetables", "Harvest the vegetables in the greenhouse.", LocalDate.of(2024, 9, 11), "Greenhouse");

        return List.of(task1, task2, task3, task4, task5, gardenTask1, gardenTask2, gardenTask3, gardenTask4, gardenTask5);
    }
}
