package PranavCode.PranavCode.HWandreferences.Homework;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "PRANPRANTASK2")

public class task2 extends OpMode {

    DcMotor motor_name;

    @Override
    public void init() {
        motor_name = hardwareMap.get(DcMotor.class, "motor1");
    }

    @Override
    public void loop() {
        long st1 = System.currentTimeMillis();
        long ti1 = 1000;
        long et1 = st1 + ti1;
        while (System.currentTimeMillis() < et1) {
            motor_name.setPower(0.25);
        }
        long st2 = System.currentTimeMillis();
        long ti2 = 2000;
        long et2 = st2 + ti2;
        while (System.currentTimeMillis() < et2) {
            motor_name.setPower(0);
        }
    }
}