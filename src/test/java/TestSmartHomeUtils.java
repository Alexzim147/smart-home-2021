import ru.sbt.mipt.oop.event.Action;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.concurrent.atomic.AtomicReference;

public class TestSmartHomeUtils {
    public static Light getLightById(SmartHome smartHome, String lightId) {
        AtomicReference<Light> light = new AtomicReference<>();

        Action action = object -> {
            if (!(object instanceof Light)) {
                return;
            }

            Light lightObject = (Light) object;

            if (lightObject.getId().equals(lightId)) {
                light.set(lightObject);
            }
        };

        smartHome.execute(action);
        return light.get();
    }

    public static Door getDoorById(SmartHome smartHome, String doorId) {
        AtomicReference<Door> door = new AtomicReference<>();

        Action action = object -> {
            if (!(object instanceof Door)) {
                return;
            }

            Door doorObject = (Door) object;

            if (doorObject.getId().equals(doorId)) {
                door.set(doorObject);
            }
        };

        smartHome.execute(action);
        return door.get();
    }
}
