package SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "WEWOO")
public class wewoo extends OpMode {
    DcMotor WEWOO;
    @Override
    public void init() {
    WEWOO = hardwareMap.get(DcMotor.class, "WEWOO");
    }

    @Override
    public void loop() {

        WEWOO.setPower(1);
    }
}
