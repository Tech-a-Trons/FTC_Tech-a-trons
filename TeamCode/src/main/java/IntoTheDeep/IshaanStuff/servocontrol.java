package IntoTheDeep.IshaanStuff;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class servocontrol extends OpMode {
    @Override
    public void init() {
        Servo servo1;

        servo1 = hardwareMap.get(Servo.class,"servo1");
    }

    @Override
    public void loop() {

    }
}
