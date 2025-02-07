package IntoTheDeep.PranavCode.HWandreferences.Homework;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "PranavT1")

public class T1 extends OpMode {

    DcMotor motor_name;

    @Override
    public void init() {
        motor_name = hardwareMap.get(DcMotor.class, "motor1");
    }

    @Override
    public void loop() {
        float rt = gamepad1.right_trigger;
        if (rt > 0.5){
            long st = System.currentTimeMillis();
            long ti = 10000;
            long et = st + ti;
            while (System.currentTimeMillis() < et) {
                motor_name.setPower(-0.25);
            }
        } else {
            motor_name.setPower(0);
        }
    }
}