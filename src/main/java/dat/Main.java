package dat;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello tasks");

        // Initialize a TaskList and add all tasks to it
        TaskList<Task> taskList = new TaskList();
        List<Task> tasks = taskList.populateListOfTasks();
        tasks.forEach(task -> taskList.add(task));
        taskList.print();

        System.out.println("Filtering tasks by keyword 'yard' :::::::");
        taskList.filter("yard").forEach(System.out::println);

        System.out.println("Sorting tasks by due date :::::::");
        taskList.sortByDueDate().forEach(System.out::println);

        System.out.println("Tasks due today :::::::");
        taskList.dueToday(LocalDate.of(2024, 9, 6)).forEach(System.out::println);

        System.out.println("Overdue tasks :::::::");
        taskList.overDue(LocalDate.of(2024, 9, 6)).forEach(System.out::println);

        System.out.println("Show how to use the iterator method :::::::");
        taskList.forEach(System.out::println);

        Iterator<Task> taskListIterator = taskList.iterator();

         while(taskListIterator.hasNext())
         {
              Task task = taskListIterator.next();
              System.out.println(task);
              if (task.getTitle().contains("Trim"))
              {
                  System.out.println("*********** Remoooooooove this: " + task);
                  taskListIterator.remove();
              }
         }
        System.out.println(">>>>>>>> Trim now removed");
        taskList.forEach(System.out::println);


    }
}