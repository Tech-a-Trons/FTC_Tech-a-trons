package PranavCode.HWandreferences.Homework;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "PRANPRANTASK3")

public class task3 extends OpMode {

    DcMotor motor_name;

    @Override
    public void init() {
        motor_name = hardwareMap.get(DcMotor.class, "motor1");
    }

    @Override
    public void loop() {
        if (gamepad1.left_trigger > 0.05) {
            motor_name.setPower(0.75);
        }else{
            motor_name.setPower(0);
        }
    }
}