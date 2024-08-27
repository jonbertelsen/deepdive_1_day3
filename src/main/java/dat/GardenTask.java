package dat;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
public class GardenTask extends Task
{
    private String gardenLocation;

    public GardenTask(String title, String description, LocalDate dueDate, String gardenLocation)
    {
        super(title, description, dueDate);
        this.gardenLocation = gardenLocation;
    }
}
