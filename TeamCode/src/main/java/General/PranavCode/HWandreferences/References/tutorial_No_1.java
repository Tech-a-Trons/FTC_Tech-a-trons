package General.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "ROOOOOOOOOOOOOOOOOOONALDOSUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII")

public class tutorial_No_1 extends OpMode  {
    DcMotor motor_name;

    @Override
    public void init() {
        motor_name = hardwareMap.get(DcMotor.class, "motor1");
    }

    @Override
    public void loop() {
        motor_name.setPower(gamepad1.right_trigger);
    }

}

