package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "WEWOO")
public class wewoo extends OpMode {
    DcMotor motor;
    @Override
    public void init() {
        motor = hardwareMap.get
                (DcMotor.class, "WEWOO");
    }

    @Override
    public void loop() {

        motor.setPower(1);
    }
}
