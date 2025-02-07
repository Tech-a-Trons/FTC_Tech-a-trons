package IntoTheDeep.PranavCode.HWandreferences.Homework;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "PRANPRANTASK1")

public class task1 extends OpMode {

    DcMotor motor_name;

    @Override
    public void init() {
        motor_name = hardwareMap.get(DcMotor.class, "motor1");
    }

    @Override
    public void loop() {
        long st = System.currentTimeMillis();
        long ti = 1000;
        long et = st + ti;
        while (System.currentTimeMillis() < et) {
            motor_name.setPower(0.25);
        }
    }
}
