package dat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class Task
{
    private String title;
    private String description;
    private LocalDate dueDate;
}
